package kakao;



import java.util.Arrays;
import java.util.Scanner;
/*
3 6
0 2 0 0 0 2
0 0 2 0 1 0
1 0 0 2 2 0
 */
public class Walker {
    static int m;
    static int n;
    static int city_map[][];
    static int cache[][];
    static boolean visited[][];
    static int MOD = 20170805;
    static int[] dx = { 1, 0 , -1, 1 };
    static int[] dy = { 0 ,1 , 0, -1 };
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        city_map = new int[m][n];
        cache = new int[m][n];
        visited = new boolean[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                city_map[i][j] = sc.nextInt();
            }
        }
        int answer = numberOfCases(0, 0, 0 , 0);
        System.out.println(answer);
    }
    public static int numberOfCases(int prevA, int prevB, int a, int b){
        System.out.println(a + " " + b);
        for(int i = 0; i < m; i ++){
            System.out.println(Arrays.toString(visited[i]));
        }
        System.out.println();
        if(a < 0 || a >= m || b < 0 || b >= n || city_map[a][b] == 1) return 0;
        if(a == m - 1 && b == n - 1) return 1;
        if(cache[a][b] != 0) return cache[a][b];
        visited[a][b] = true;
        int ret = 0;
        if(city_map[a][b] == 0){
            for(int i = 0; i < 4; i ++){
                ret += numberOfCases(a, b, a + dx[i], b + dy[i]);
            }
        }
        if(city_map[a][b] == 2){
            int tempX = a - prevA;
            int tempY = b - prevB;
            ret += numberOfCases(a, b, a + tempX, b + tempY);
        }

        return cache[a][b] = ret % MOD;
    }
}
