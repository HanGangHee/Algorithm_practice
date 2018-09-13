package samsung;

import java.util.*;

public class StartAndLink {
    static int N;
    static int[][] arrN;
    static boolean chose[];
    static int INF = 987654321;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N][N];
        chose = new boolean[N];
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
                if(chose[i]){
                    for(int j = 0; j < N; j ++){
                        if(chose[j] && i != j) start += arrN[i][j];
                    }
                } else {
                    for(int j = 0; j < N; j ++){
                        if(!chose[j] && i != j) link += arrN[i][j];
                    }
                }
            }
            return Math.abs(start - link);
        }
        if(step == N) return INF;
        int ret = INF;
        chose[step] = true;
        ret = Math.min(ret, findMinDiff(step + 1, cnt + 1));
        chose[step] = false;
        ret = Math.min(ret, findMinDiff(step + 1, cnt));
        return ret;
    }
}
