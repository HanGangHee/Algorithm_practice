package samsung;

import java.util.*;
public class CCTV {
    static int N;
    static int M;
    static int dx[] = { 0, -1, 0, 1};
    static int dy[] = { 1, 0, -1, 0};

    static int INF = 987654231;
    static ArrayList<Point> cctv;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[][] office = new int[N][M];
        cctv = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                office[i][j] = sc.nextInt();
                if(office[i][j] != 0 && office[i][j] != 6){
                    switch (office[i][j]){
                        case 1:
                            cctv.add(new Point(i, j, new int[] {1, 0 , 0, 0}));break;
                        case 2:
                            cctv.add(new Point(i, j, new int[] {1, 0 , 1, 0}));break;
                        case 3:
                            cctv.add(new Point(i, j, new int[] {1, 1 , 0, 0}));break;
                        case 4:
                            cctv.add(new Point(i, j, new int[] {1, 1 , 1, 0}));break;
                        case 5:
                            cctv.add(new Point(i, j, new int[] {1, 1, 1, 1}));break;
                    }
                }
            }
        }
        int answer = findMinBlindSpot(0, office);
        System.out.println(answer);
}

    public static int findMinBlindSpot(int step, int[][] office){
//        System.out.println(step);
//        for(int i = 0; i < N; i ++){
//            System.out.println(Arrays.toString(office[i]));
//        }
//        System.out.println();
        if(step == cctv.size()){
            int cntBlindSpot = 0;
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(office[i][j] == 0) cntBlindSpot += 1;
                }
            }
            return cntBlindSpot;
        }
        Point p = cctv.get(step);
        int temp[][];
        int x = p.x;
        int y = p.y;
        int[] d = p.d;
        int ret = INF;
        for(int i = 0; i < 4; i ++){
            temp = new int[N][M];
            for(int a = 0; a < N; a ++){
                temp[a] = office[a].clone();
            }
            for(int j = 0; j < 4; j ++){
                x = p.x;
                y = p.y;
                if(d[j] == 0) continue;
                while(outOfRange(x + dx[j], y + dy[j]) &&
                        temp[x + dx[j]][y + dy[j]] != 6){
                    if(temp[x + dx[j]][y + dy[j]] != 0){
                        x += dx[j];
                        y += dy[j];
                        continue;
                    }
                    temp[x + dx[j]][y + dy[j]] = -1;
                    x += dx[j];
                    y += dy[j];
                }
            }
            ret = Math.min(ret, findMinBlindSpot(step + 1, temp));

            int tempD[] = d.clone();
            for(int j = 0; j < 4; j ++){  // 한 칸씩 뒤로 미루기
                d[j] = tempD[(j + 1) % 4];
            }
            if(office[p.x][p.y] == 5) break;
        }
        return ret;
    }
    public static boolean outOfRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}


class Point{
    int x;
    int y;
    int[] d;
    Point(int x, int y, int[] d){
        this.x = x;
        this.y = y;
        this.d = d.clone();
    }

}
