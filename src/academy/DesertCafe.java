package academy;

import java.util.*;

public class DesertCafe {
    static int N;
    static int dx[] = {1, 1, -1, -1};
    static int dy[] = {1, -1, -1, 1};
    static int[][] cafes;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            cafes = new int[N][N];
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    cafes[i][j] = sc.nextInt();
                }
            }
            int answer = -1;
            for(int i = 0; i < N - 2; i ++){
                for(int j = 0; j < N - 1; j ++){
                    answer = Math.max(checkPath(i, j), answer);
                }
            }

            System.out.println("#" + t + " " + answer);

        }

    }
    public static int checkPath(int x, int y){
        int ret = -1;
//        boolean[][] visited2 = new boolean[N][N];
//
//        boolean[][] visited = new boolean[N][N];
        for(int i = 1; i <= N - 1; i ++){
            for(int j = 1; j <= N - 1; j ++){


//                for(int a = 0; a < N; a ++){
//                    visited[a] = visited2[a].clone();
//                }

                set.clear();
                boolean is_promise = true;
                int curX = x;
                int curY = y;

//                visited[curX][curY] = true;

                for(int a = 1; a <= i; a ++){
                    curX = curX + dx[0];
                    curY = curY + dy[0];
                    if(outOfRange(curX, curY) || set.contains(cafes[curX][curY])){
                        is_promise = false;
                        break;
                    }
                    set.add(cafes[curX][curY]);

//                    visited[curX][curY] = true;

                }
                if(is_promise){
                    for(int a = 1; a <= j; a ++){
                        curX = curX + dx[1];
                        curY = curY + dy[1];
                        if(outOfRange(curX, curY) || set.contains(cafes[curX][curY])){
                            is_promise = false;
                            break;
                        }
                        set.add(cafes[curX][curY]);

//                        visited[curX][curY] = true;

                    }
                }
                if(is_promise){
                    for(int a = 1; a <= i; a ++){
                        curX = curX + dx[2];
                        curY = curY + dy[2];
                        if(outOfRange(curX, curY) || set.contains(cafes[curX][curY])){
                            is_promise = false;
                            break;
                        }
                        set.add(cafes[curX][curY]);

//                        visited[curX][curY] = true;

                    }
                }
                if(is_promise){
                    for(int a = 1; a <= j; a ++){
                        curX = curX + dx[3];
                        curY = curY + dy[3];
                        if(outOfRange(curX, curY) || set.contains(cafes[curX][curY])){
                            is_promise = false;
                            break;
                        }
                        set.add(cafes[curX][curY]);

//                        visited[curX][curY] = true;

                    }
                }
//                System.out.println(set + " " + is_promise);
//                for(int a = 0; a < N; a ++){
//                    System.out.println(Arrays.toString(visited[a]));
//                }
//                System.out.println();

                if(is_promise){

                    ret = Math.max(ret, set.size());
                }
            }
        }
        return ret;
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
