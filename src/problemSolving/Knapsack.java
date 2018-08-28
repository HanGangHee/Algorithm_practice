package problemSolving;

/*
1번부터 N번까지의 번호가 부여된 물건과  최대 부피만큼을 넣을 수 있는 가방이 있다.
물건은 각각  부피 V 와 가치 C 를 가지고 있다.
부피 합이 K 이하이며 가방에 담을 수 있는 K 이하여야 한다.

입력 예제
1
4 5
1 2
3 2
4 4
2 3

출력
6
 */
import java.util.Arrays;
import java.util.Scanner;
public class Knapsack {
    static int N;
    static int K;
    static int[] V;
    static int[] C;
    static int cache[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            K = sc.nextInt();
            V = new int[N];
            C = new int[N];
            for(int i = 0; i < N; i ++){
                V[i] = sc.nextInt();
                C[i] = sc.nextInt();
            }
            cache = new int[N + 1][K + 1];
            for(int i = 1; i <= N; i ++){
                for(int j = 1; j <= K; j++){
                    if(j >= V[i - 1]) cache[i][j] = Math.max(C[i - 1] + cache[i - 1][j - V[i - 1]], cache[i - 1][j]);
                    else cache[i][j] = cache[i - 1][j];
                }
            }
            sb.append("#" + t + " " + cache[N][K] + "\n");
        }
        System.out.println(sb.toString());
    }
}
