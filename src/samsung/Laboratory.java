package samsung;

import java.util.*;

public class Laboratory {
    static int N;
    static int M;
    static int[][] map;
    static boolean chose[];
    static ArrayList<Loc> empties = new ArrayList<>();
    static ArrayList<Loc> viruses = new ArrayList<>();
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0) empties.add(new Loc(i, j));
                if(map[i][j] == 2) viruses.add(new Loc(i, j));
            }
        }
        chose = new boolean[empties.size()];
        int answer = findMaxArea(0, 0);
        System.out.println(answer);
    }

    public static int findMaxArea(int step, int cnt){
        if(cnt == 3){
            int[][] tempMap = new int[N][M];
            for(int i = 0; i < N; i ++){
                tempMap[i] = map[i].clone();
            }
            for(int i = 0; i < chose.length; i ++){
                if(chose[i]){
                    tempMap[empties.get(i).x][empties.get(i).y] = 1;
                }
            }
            ArrayList<Loc> q = new ArrayList<>();
            for(int i = 0; i < viruses.size(); i ++){
                q.clear();
                q.add(viruses.get(i));
                while(!q.isEmpty()){
                    Loc l = q.remove(0);
                    for(int d = 0; d < 4; d ++){
                        if(outOfRange(l.x + dx[d], l.y + dy[d]) &&
                                tempMap[l.x + dx[d]][l.y + dy[d]] == 0){
                            q.add(new Loc(l.x + dx[d], l.y + dy[d]));
                            tempMap[l.x + dx[d]][l.y + dy[d]] = 2;
                        }
                    }
                }
            }
            int ret = 0;
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    if(tempMap[i][j] == 0) ret += 1;
                }
            }
            return ret;
        }
        int ret = 0;
        if(step >= empties.size()) return 0;
        chose[step] = true;
        ret = Math.max(ret, findMaxArea(step + 1, cnt + 1));
        chose[step] = false;
        ret = Math.max(ret, findMaxArea(step + 1, cnt));
        return ret;
    }
    public static boolean outOfRange(int x, int y){
        return x >= 0 && x < N && y >= 0 & y < M;
    }
}

class Loc{
    int x;
    int y;
    Loc(int x, int y){
        this.x = x;
        this.y = y;
    }
}
