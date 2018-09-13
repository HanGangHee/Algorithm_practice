package samsung;

import java.util.*;

public class tetromino {
    static int N;
    static int M;
    static int arrN[][];
    static boolean[][] visited;
    static int dx[] = { -1, 0, 1, 0};
    static int dy[] = { 0, 1,  0, -1};
    static int INF = -987654321;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arrN = new int[N + 2][M + 2];
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= M; j ++){
                arrN[i][j] = sc.nextInt();
            }
        }
        int answer = 0;
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= M; j ++){
                visited = new boolean[N + 2][M + 2];
                answer = Math.max(findMaxSum(i, j, 0, 0), answer);
                answer = Math.max(findMaxSum2(i, j), answer);
            }
        }
        System.out.println(answer);
    }
    public static boolean outOfRange(int a, int b){
        return a >=1 && a <= N && b >= 1 && b <= M;
    }
    public static int findMaxSum(int a, int b, int cnt, int sum){
        if(cnt == 4){
            return sum;
        }
        int ret = INF;
        visited[a][b] = true;
        for(int i = 0; i < 4; i ++){
            int nextA = a + dx[i];
            int nextB = b + dy[i];
            if(outOfRange(nextA, nextB) && !visited[nextA][nextB]){
                ret = Math.max(ret, findMaxSum(nextA, nextB, cnt + 1, sum + arrN[nextA][nextB]));
            }
        }
        visited[a][b] = false;
        return ret;
    }
    public static int findMaxSum2(int a, int b){
        int answer = 0;
        int min = Integer.MAX_VALUE;
        try{
            for(int i = 0; i < 4; i ++){
                answer += arrN[a + dx[i]][b + dy[i]];
                min = Math.min(min, arrN[a + dx[i]][b + dy[i]]);
            }
        } catch (Exception e){
            System.out.println(a + " " + b);
        }

        return answer - min + arrN[a][b];
    }
}
