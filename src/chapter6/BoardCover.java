package chapter6;

import java.util.Scanner;


/*
6.5 게임판 덮기

H * W 크기의 게임판이 있다.
게임판은 검은 칸과 흰 칸으로 구성된 격자 모양을 하고 있다.
이 중 모든 흰 캉르 세 칸짜리 L 자 모양의 블록으로 덮고 싶다.
이때 블록들은 자유록게 회전해서 놓을 수 있지만
서로 겹치거나, 검은 칸을 덮거나, 게임판 밖으로 나가서는 안된다.
게임판이 주어질 때 이를 덮는 방법의 수를 계산하는 프로그램을 작성하세요



입력
1. 테스트 케이스 C
2. H , W   (1 <= H, W <= 20)
3. H줄에 W   글자로 게임판의 모양이 주어진다.
H 은 검은 칸 ,  .는 흰 칸을 나타낸다.

흰 칸의 수는 50을 넘지 않는다.

ex)
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########

출력
0
2
1514
 */
public class BoardCover {

    static int[][][] coverType = {{{1, 0}, {0, 1}}, {{0, 1}, {1, 1}}, {{1, 0}, {1, 1}}, {{1, 0}, {1, -1}}};  //  ㄱ ㄴ  and 옆으로 뒤집은 모양
    static char[][] board;
    static int H;
    static int W;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= C; t ++){
            H = sc.nextInt();
            W = sc.nextInt();
            board = new char[H][W];
            boolean is_covered[][] = new boolean[H][W];
            int white = 0;
            for(int i = 0; i < H; i ++){
                board[i] = sc.next().toCharArray();
                for(int j = 0; j < W; j ++){
                    if(board[i][j] == '.') white += 1;
                    else is_covered[i][j] = true;
                }
            }
            if(white % 3 != 0){
                sb.append("#" + t + " " + 0 + "\n");
            } else {
                int answer = countCover(is_covered);
                sb.append("#" + t + " " + answer + "\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static int countCover(boolean[][] is_covered){
        int ret = 0;
        int uncovered_x = -1;
        int uncovered_y = -1;
        for(int i = 0; i < H; i ++){
            for(int j = 0; j < W; j ++){
                if(!is_covered[i][j]){
                    uncovered_x = i;
                    uncovered_y = j;
                    break;
                }
            }
            if(uncovered_x != -1 && uncovered_y != -1) break;
        }
        if(uncovered_x == -1 && uncovered_y == -1) return 1;
        for(int a = 0; a < 4; a ++){
            int f_x = uncovered_x + coverType[a][0][0];
            int f_y = uncovered_y + coverType[a][0][1];
            int s_x = uncovered_x + coverType[a][1][0];
            int s_y = uncovered_y + coverType[a][1][1];
            if(f_x >= 0 && f_x < H && f_y >= 0 && f_y < W && s_x >= 0 && s_x < H && s_y >= 0 && s_y < W &&
                !is_covered[f_x][f_y] && !is_covered[s_x][s_y]){
                 is_covered[uncovered_x][uncovered_y] = is_covered[f_x][f_y] = is_covered[s_x][s_y] = true;
                 ret += countCover(is_covered);
                 is_covered[uncovered_x][uncovered_y] = is_covered[f_x][f_y] = is_covered[s_x][s_y] = false;
            }
        }
        return ret;
    }
}
