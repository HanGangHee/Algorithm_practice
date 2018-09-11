package samsung;

/*
5 5
#####
#..B#
#.#.#
#RO.#
#####
 */
import java.util.Arrays;
import java.util.Scanner;
public class EscapeRed {
    static int N;
    static int M;
    static char[][] board;
    static int INF = 987654321;
    static int dx[] = {-1, 1, 0,  0};
    static int dy[] = {0,  0,  -1, 1};
    static int reverseDirectoin[] = { 1, 0, 3, 2};
    static boolean visited[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new char[N][M];
        visited = new boolean[N][M];
        int RX = 0;
        int RY = 0;
        int BX = 0;
        int BY = 0;
        for(int i = 0; i < N; i ++){
            board[i] = sc.next().toCharArray();
            if(BX != 0 && BY != 0 && RX != 0 && RY != 0) continue;
            for(int j = 0; j < M; j ++){
                if(board[i][j] == 'R'){
                    RX = i;
                    RY = j;
                } else if(board[i][j] == 'B'){
                    BX = i;
                    BY = j;
                }
            }
        }
        int answer = findMinCase(RX, RY, BX, BY, 1, -1);
        if(answer == INF){
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
    public static int findMinCase(int RX, int RY, int BX, int BY, int step, int prevDirection){
        if(step == 11) return INF;
        int ret = INF;
        int tempRX = RX;
        int tempRY = RY;
        int tempBX = BX;
        int tempBY = BY;
        for(int i = 0; i < 4; i ++){
            if(i == prevDirection) continue;
            else if(prevDirection != -1 && i == reverseDirectoin[prevDirection]) continue;
            boolean is_promise = false;
            RX = tempRX;
            RY = tempRY;
            BX = tempBX;
            BY = tempBY;
            System.out.println("RX : " + RX + " RY : " +  RY + " BX : " + BX + " BY : " + BY + " step : " + step + " I : "   + i);
            for(int a = 0; a < N; a ++){
                System.out.println(Arrays.toString(board[a]));
            }
            System.out.println();
            boolean redIsFirst = true; // red가 앞에있다.
            while(outOfRange(RX + dx[i], RY + dy[i]) && board[RX + dx[i]][RY + dy[i]] != '#'){
                if(board[RX + dx[i]][RY + dy[i]] == 'O'){
                    is_promise = true;
                    break;
                } else if(board[RX + dx[i]][RY + dy[i]] == 'B'){
                    redIsFirst = false;
                }
                RX += dx[i];
                RY += dy[i];
            }
            if(is_promise){
                boolean is_blackOut = false;
                while(outOfRange(BX + dx[i], BX + dy[i]) && board[BX + dx[i]][BY + dy[i]] != '#'){
                    if(board[BX + dx[i]][BY + dy[i]] == 'O'){
                        is_blackOut = true;
                        break;
                    }
                    BX += dx[i];
                    BY += dy[i];
                }
                if(!is_blackOut) return step;
                else continue;
            }
            if(RX == tempRX && RY == tempRY) continue;
            if(RX == BX && RY == BY){
                if(redIsFirst){
                    swap(RX, RY, tempRX, tempRY);
                    swap(BX - dx[i], BY - dy[i], tempBX, tempBY);
                    ret = Math.min(ret, findMinCase(RX, RY, BX - dx[i], BY - dy[i], step + 1, i));
                    swap(RX, RY, tempRX, tempRY);
                    swap(BX - dx[i], BY - dy[i], tempBX, tempBY);

                } else {
                    swap(RX - dx[i], RY - dy[i], tempRX, tempRY);
                    swap(BX, BY, tempBX, tempBY);
                    ret = Math.min(ret, findMinCase(RX - dx[i], RY - dy[i], BX, BY, step + 1, i));
                    swap(RX -dx[i], RY - dy[i], tempRX, tempRY);
                    swap(BX, BY, tempBX, tempBY);
                }
            } else {
                swap(RX, RY, tempRX, tempRY);
                swap(BX, BY, tempBX, tempBY);
                ret = Math.min(ret, findMinCase(RX, RY, BX, BY, step + 1, i));
                swap(RX, RY, tempRX, tempRY);
                swap(BX, BY, tempBX, tempBY);
            }

        }
        return ret;
    }
    public static boolean outOfRange(int x, int y){
        return x >= 1 && x < N - 1 && y >= 1 && y < M - 1;
    }
    public static void swap(int x, int y, int changeX, int changeY){
        char temp = board[x][y];
        board[x][y] = board[changeX][changeY];
        board[changeX][changeY] = temp;
    }
}
