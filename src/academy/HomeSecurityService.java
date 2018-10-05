package academy;

        import java.util.*;

//운영 비용  =  K * K + (K - 1) * (K - 1)
public class HomeSecurityService {
    static int N;
    static int M;
    static int cities[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            M = sc.nextInt();
            cities = new int[N][N];
            int houseCnt = 0;
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    cities[i][j] = sc.nextInt();
                    if(cities[i][j] == 1) houseCnt ++;
                }
            }
            int sumM = houseCnt * M;
            int K = 1;
            while(sumM - (K * K + (K - 1) * (K - 1)) >= 0){
                K += 1;
            }
            K -= 1;
            int answer = Integer.MIN_VALUE;
            for(int k = 1; k <= K; k ++){
                for(int i = 0; i < N; i ++){
                    for(int j = 0; j < N; j ++){
                        answer = Math.max(answer, findMaxHouses(i, j, k));
                    }
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }
    public static int findMaxHouses(int x, int y, int k){
        int ret = 0;
        int idx = 0;
        k -= 1;
        for(int a = x - k; a <= x + k; a ++){
            for(int b = y - idx; b <= y + idx; b ++){
                if(!outOfRange(a, b) && cities[a][b] == 1){
                    ++ret;
                }
            }
            if(a >= x) idx -=1;
            else idx += 1;
        }
        if(ret * M >= (k + 1)*(k + 1) + k * k) return ret;
        else return Integer.MIN_VALUE;
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
