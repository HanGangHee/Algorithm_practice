package academy;

import java.util.*;

public class BreakBlock {
    static int dx[] = { 1, 0, -1, 0};
    static int dy[] = { 0, -1, 0, 1};
    static int N;
    static int W;
    static int H;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            int[][] blocks = new int[H][W];
            for(int i = 0; i < H; i ++){
                for(int j = 0; j < W; j ++){
                    blocks[i][j] = sc.nextInt();
                }
            }
            int answer = findMinBlocks(blocks, 0);
            System.out.println("#" + t + " " + answer);
        }
    }
    public static int findMinBlocks(int[][] blocks, int step){
        if(step > N) return INF;
        if(step == N){
            int ret = 0;
            for(int i = 0; i < H; i ++){
                for(int j = 0; j < W; j ++){
                    if(blocks[i][j] != 0) ret += 1;
                }
            }
            return ret;
        }
        int ret = INF;
        int temp[][] = new int[H][W];
        int cntCol = 0;
        for(int col = 0; col < W; col ++){
            int rowIdx = -1;
            for(int row = 0; row < H; row ++){
                if(blocks[row][col] != 0){
                    rowIdx = row;
                    break;
                }
            }
            if(rowIdx == -1){
                cntCol += 1;
                if(cntCol == W){
                    return 0;
                }
                continue;
            }
            for(int i = 0; i < H; i ++){
                temp[i] = blocks[i].clone();
            }
            ArrayList<Point> list = new ArrayList<>();
           list.add(new Point(rowIdx, col));
           while(!list.isEmpty()){
               Point p = list.remove(0);
               int val = blocks[p.x][p.y];
               temp[p.x][p.y] = 0;
               for(int d = 0; d < 4; d ++){
                   int x = p.x;
                   int y = p.y;
                   int cnt = 1;
                   while (!outOfRange(x + dx[d], y + dy[d]) && cnt < val) {
                       x += dx[d];
                       y += dy[d];
                       if(blocks[x][y] > 0 && temp[x][y] > 0 ){
                           list.add(new Point(x, y));
                       }
                       cnt += 1;
                   }
               }
           }
           for(int b = 0; b < W; b ++){
               int idx = H - 1;
               for(int a = H - 1; a >= 0; a --){
                    if(temp[a][b] > 0){
                        int tempVal = temp[a][b];
                        temp[a][b] = temp[idx][b];
                        temp[idx][b] = tempVal;
                        idx -= 1;
                    }
               }
           }
           ret = Math.min(ret, findMinBlocks(temp, step + 1));
        }
        return ret;
    }
    public static boolean outOfRange(int x, int y){
        return x < 0 || x >= H || y < 0 || y >= W;
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