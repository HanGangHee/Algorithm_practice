package academy;

import java.util.*;

/*
등산로를 만들기 위한 부지는 N * N 크기를 가지고 있으며  최대한 긴 드산로를 만들 계획이다.

등산로를 만드는 규칙

1. 등산로는 가장 높은 봉우리에서 시작해야한다.


2. 등산로는 산으로 올라갈 수 있도록 반다시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
즉 높이가 같은 곳 혹은 낮은 치형이나, 대각선 방향은 불간으하다.

3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.

 */
public class HikingTrail {
    static int N;
    static int dx[] = { -1, 0, 1, 0};
    static int dy[] = { 0, -1, 0, 1};
    static int map[][];
    static ArrayList<Point> tops;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            N = sc.nextInt();
            int K = sc.nextInt();
            map = new int[N][N];
            tops = new ArrayList<>();
            int max = -1;
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    map[i][j] = sc.nextInt();
                    max = Math.max(map[i][j], max);
                }
            }
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    if(map[i][j] == max) tops.add(new Point(i, j));
                }
            }

            int answer = 0;
            for(int k = 0; k <= K; k ++){
                for(int i = 0; i < N; i ++){
                    for(int j = 0; j < N; j ++){
                        map[i][j] -= k;
                        for(int z = 0; z < tops.size(); z ++){
                            answer = Math.max(answer, findMaxPathCnt(tops.get(z).x, tops.get(z).y, 1));
                        }
                        map[i][j] += k;
                    }
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }
    public static int findMaxPathCnt(int x, int y, int cnt){
        int ret = cnt;
        for(int d = 0; d < 4; d ++){
            int nextX = x + dx[d];
            int nextY = y + dy[d];
            if(!outOfRange(nextX, nextY) && map[x][y] > map[nextX][nextY]){
                ret = Math.max(ret, findMaxPathCnt(nextX, nextY, cnt + 1));
            }
        }
        return  ret;
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
