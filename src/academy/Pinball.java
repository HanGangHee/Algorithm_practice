package academy;

import java.lang.reflect.Array;
import java.util.*;
public class Pinball {
    static int dx[] = { 1, 0 , -1, 0}; // 0 1 2 3
    static int dy[] = { 0 , -1, 0, 1};  //아래 왼 위 오
    static int reverseDir[] = {2, 3, 0, 1};
    static int arrN[][];
    static int N;
    static int[][] blocks = {
            {-1, 3, 2, 2, 1, 2},
            {-1, 2, 0, 3, 3, 3},
            {-1, 0, 3, 1, 0, 0},
            {-1, 1, 1, 0, 2, 1}
    };
    static int[][] wormhole = new int[11][4];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N][N];
        for(int i = 0; i < 11; i ++){
            Arrays.fill(wormhole[i], -1);
        }
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                arrN[i][j] = sc.nextInt();
                if(arrN[i][j] >= 6 && arrN[i][j] <= 10){
                    if(wormhole[arrN[i][j]][0] >= 0 && wormhole[arrN[i][j]][1] >= 0){
                        wormhole[arrN[i][j]][2] = i;
                        wormhole[arrN[i][j]][3] = j;
                    } else {
                        wormhole[arrN[i][j]][0] = i;
                        wormhole[arrN[i][j]][1] = j;
                    }
                }
            }
        }
        int answer = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(arrN[i][j] != 0) continue;
                int temp = 0;
                for(int d = 0; d < 4; d ++){
                    temp = Math.max(findPathCnt(i, j, d), temp);
                }
                answer = Math.max(temp, answer);
            }
        }
        System.out.println(answer);
    }
    public static int findPathCnt(int x, int y, int dir){
        int pathCnt = 0;
        int curX = x;
        int curY = y;
        int curDir = dir;
        boolean visited[][] = new boolean[N][N];
        visited[curX][curY] = true;
        while(arrN[curX][curY] != -1){
            curX += dx[curDir];
            curY += dy[curDir];
            if(curX == x && curY == y){
                System.out.println(x + " " + y);
                break;
            }
            if(outOfRange(curX, curY)){
                pathCnt += 1;
                curX -= dx[curDir];
                curY -= dy[curDir];
                curDir = reverseDir[curDir];
                if(arrN[curX][curY] >= 1 && arrN[curX][curY] <= 5){
                    pathCnt += 1;
                    curDir = blocks[curDir][arrN[curX][curY]];
                } else if(arrN[curX][curY] >= 6 && arrN[curX][curY] <= 10){
                    int val = arrN[curX][curY];
                    if(curX == wormhole[val][0] && curY == wormhole[val][1]){
                        curX = wormhole[val][2];
                        curY = wormhole[val][3];
                    }
                    else if(curX == wormhole[val][2] && curY == wormhole[val][3]){
                        curX = wormhole[val][0];
                        curY = wormhole[val][1];
                    }
                }
            } else {
                visited[curX][curY] = true;

                if(arrN[curX][curY] >= 1 && arrN[curX][curY] <= 5){
                   pathCnt += 1;
                   curDir = blocks[curDir][arrN[curX][curY]];
                }
                else if(arrN[curX][curY] >= 6 && arrN[curX][curY] <= 10){
                    int val = arrN[curX][curY];
                    if(curX == wormhole[val][0] && curY == wormhole[val][1]){
                        curX = wormhole[val][2];
                        curY = wormhole[val][3];
                    }
                    else if(curX == wormhole[val][2] && curY == wormhole[val][3]){
                        curX = wormhole[val][0];
                        curY = wormhole[val][1];
                    }
                }
            }
        }

        return pathCnt;
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
