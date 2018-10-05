package academy;
import java.util.*;

public class DetaineeArrest {
    static int N;
    static int M;
    static int R;
    static int C;
    static int L;
    static int dx[] = { -1, 0, 1, 0};
    static int dy[] = { 0, 1, 0, -1}; // 상 우 하 좌
    static int reverseDir[] = {2, 3, 0, 1};
    static int[][] pipes = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 1, 0, 0},
            {0 ,1, 1, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 1}
    };
    static int[][][] tunnel;
    static int[][] visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();
            tunnel = new int[N][M][4];
            visited = new int[N][M];
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    int temp = sc.nextInt();
                    tunnel[i][j] = pipes[temp].clone();
                }
            }
//            dfs(R, C, 1);
            int answer = bfs(R, C, L);

            System.out.println("#" + t + " " + answer);
        }
    }
    public static int bfs(int x, int y, int time){
        int ret = 0;
        ArrayList<Point> q = new ArrayList<>();
        q.add(new Point(x, y));
        visited[x][y] += 1;
        for(int t = 1; t < time; t ++){
            int qSize = q.size();
            for(int i = 0 ; i < qSize; i ++){
                Point p = q.remove(0);
                for(int d = 0; d < 4; d ++){
                    if(tunnel[p.x][p.y][d] == 1){
                        int nextX = p.x + dx[d];
                        int nextY = p.y + dy[d];
                        if(!outOfRange(nextX, nextY) && visited[nextX][nextY] == 0 && tunnel[nextX][nextY][reverseDir[d]] == 1){
                            visited[nextX][nextY] += 1;
                            q.add(new Point(nextX, nextY));
                        }
                    }
                }
            }
        }
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                if(visited[i][j] != 0) ret += 1;
            }
        }
        return ret;

    }
    public static void dfs(int x, int y, int time){
        visited[x][y] = time;
        if(time == L) return;
        for(int d = 0; d < 4; d ++){
            if(tunnel[x][y][d] == 1){
                int nextX = x + dx[d];
                int nextY = y + dy[d];
                if(!outOfRange(nextX, nextY) && tunnel[nextX][nextY][reverseDir[d]] == 1){
                    if(visited[nextX][nextY] == 0 || visited[nextX][nextY] > time + 1){
                        dfs(nextX, nextY, time + 1);
                    }
                }
            }
        }
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= M;
    }

}
