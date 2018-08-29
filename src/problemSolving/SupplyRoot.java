package problemSolving;

import java.util.ArrayList;
import java.util.Scanner;
public class SupplyRoot {
    static int INF = 987654321;
    static int[][] map;
    static int cache[][];
    static int N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            N = sc.nextInt();
            map = new int[N][N];
            char[][] s = new char[N][N];
            for(int i = 0; i < N; i ++){
                s[i] = sc.next().toCharArray();
            }
            cache = new int[N][N];
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    map[i][j] = s[i][j] - 48;
                    cache[i][j] = INF;
                }
            }
            cache[0][0] = 0;
            int answer = restore();
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
    public static int restore(){
        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};
        ArrayList<Point> q = new ArrayList<>();
        q.add(new Point(0, 0));
        while(!q.isEmpty()){
            Point p = q.remove(0);
            for(int i = 0; i < 4; i ++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                    if(cache[nx][ny] > cache[p.x][p.y] + map[nx][ny]){
                        cache[nx][ny] = cache[p.x][p.y] + map[nx][ny];
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
        return cache[N - 1][N - 1];
    }
}

class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}