package academy;

import java.util.*;

public class ProtectiveFilm {
    static int D;
    static int W;
    static int K;
    static int A[];  //0
    static int B[]; //1
    static int films[][];
    static int INF = 987654321;
    static boolean[] checked;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();
            A = new int[W];
            B = new int[W];
            Arrays.fill(B, 1);
            films = new int[D][W];
            checked = new boolean[D];
            for(int i = 0; i < D; i ++){
                for(int j = 0; j < W; j ++){
                    films[i][j] = sc.nextInt();
                }
            }
            int answer = K;
            for(int cnt = 0; cnt <= K - 1; cnt ++){
                answer = Math.min(answer, findMinCase(0, 0, cnt));
            }
            System.out.println("#" + t + " "  + answer);
        }
    }
    public static int findMinCase(int step, int cnt, int end){
        if(cnt == end){
            boolean chk = true;
            for(int j = 0; j < W; j ++){
                int maxCnt = 0;
                int curCnt = 1;
                int cur = films[0][j];
                for(int i = 1; i < D; i ++){
                    if(cur == films[i][j]){
                        curCnt += 1;
                    } else {
                        maxCnt = Math.max(maxCnt, curCnt);
                        curCnt = 1;
                        cur = films[i][j];
                    }
                    if(i == D - 1){
                        maxCnt = Math.max(maxCnt, curCnt);
                    }
                }
                if(maxCnt < K){
                    chk = false;
                    break;
                }
            }
            if(chk) return end;
            else return INF;
        }
        if(step >= D) return INF;
        int ret = INF;
        checked[step] = true;
        int temp[] = films[step].clone();
        films[step] = A.clone();
        ret = Math.min(ret, findMinCase(step + 1, cnt + 1, end));
        films[step] = B.clone();
        ret = Math.min(ret, findMinCase(step + 1, cnt + 1, end));
        films[step] = temp.clone();
        ret = Math.min(ret, findMinCase(step + 1, cnt, end));

        return ret;
    }
}
