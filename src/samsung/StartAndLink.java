package samsung;

import java.util.*;

public class StartAndLink {
    static int N;
    static int[][] arrN;
    static boolean choosed[];
    static int INF = 987654321;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N][N];
        choosed = new boolean[N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                arrN[i][j] = sc.nextInt();
            }
        }
        int answer = findMinDiff(0, 0);
        System.out.println(answer);
    }
    public static int findMinDiff(int step, int cnt){
        if(cnt == N / 2){
            int start = 0;
            int link = 0;
            for(int i = 0; i < N; i ++){
                if(choosed[i]){
                    for(int j = 0; j < N; j ++){
                        if(choosed[j] && i != j) start += arrN[i][j];
                    }
                } else {
                    for(int j = 0; j < N; j ++){
                        if(!choosed[j] && i != j) link += arrN[i][j];
                    }
                }
            }
            return Math.abs(start - link);
        }
        if(step == N) return INF;
        int ret = INF;
        choosed[step] = true;
        ret = Math.min(ret, findMinDiff(step + 1, cnt + 1));
        choosed[step] = false;
        ret = Math.min(ret, findMinDiff(step + 1, cnt));
        return ret;
    }
}
