package samsung;


import java.util.*;

public class Easy {
    static int N;
    static int answer = 0;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[][] board = new int[N][N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                board[i][j] = sc.nextInt();
            }
        }
        findMaxValue(1, board);
        System.out.println(answer);
    }
    public static void findMaxValue(int step, int[][] board2){
        System.out.println(step);
        for(int i = 0; i < N; i ++){
            System.out.println(Arrays.toString(board2[i]));
        }
        System.out.println();

        if(step == 6){
            int max = 0;
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    max = Math.max(max, board2[i][j]);
                }
            }
            answer = Math.max(max, answer);
            return;
        }
        int[][] board = new int[N][N];
        for(int a = 0; a < 4; a ++){
            for(int i = 0; i < N; i ++){
                board[i] = board2[i].clone();
            }
            // 아래 위 왼쪽 오른쪽
            switch (a){
                case 0 : down(board);
                    break;
                case 1 : up(board);
                    break;
                case 2 : left(board);
                    break;
                case 3 : right(board);
                    break;
            }
            findMaxValue(step + 1, board);
        }
    }
    public static void down(int[][] board){
        for(int j = 0; j < N; j ++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = N - 1; i >= 0; i --){
                if(board[i][j] != 0) list.add(board[i][j]);
                board[i][j] = 0;
            }
            int idx = N - 1;
            for(int i = 0; i < list.size(); i ++){
                if(i + 1 < list.size() && list.get(i) == list.get(i + 1)){
                    board[idx][j] = 2 * list.get(i);
                    i += 1;
                    idx -= 1;
                    continue;
                }
                board[idx][j] = list.get(i);
                idx -= 1;
            }
        }
    }
    public static void up(int[][] board){
        for(int j = 0; j < N; j ++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < N; i ++){
                if(board[i][j] != 0) list.add(board[i][j]);
                board[i][j] = 0;
            }
            int idx = 0;
            for(int i = 0; i < list.size(); i ++){
                if(i + 1 < list.size() && list.get(i) == list.get(i + 1)){
                    board[idx][j] = 2 * list.get(i);
                    i += 1;
                    idx += 1;
                    continue;
                }
                board[idx][j] = list.get(i);
                idx += 1;
            }
        }
    }
    public static void left(int[][] board){
        for(int i = 0; i < N; i ++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < N; j ++){
                if(board[i][j] != 0) list.add(board[i][j]);
                board[i][j] = 0;
            }
            int idx = 0;
            for(int j = 0; j < list.size(); j ++){
                if(j + 1 < list.size() && list.get(j) == list.get(j + 1)){
                    board[i][idx] = 2 * list.get(j);
                    j += 1;
                    idx += 1;
                    continue;
                }
                board[i][idx] = list.get(j);
                idx += 1;
            }
        }
    }
    public static void right(int[][] board){
        for(int i = 0; i < N; i ++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = N - 1; j >= 0; j --){
                if(board[i][j] != 0) list.add(board[i][j]);
                board[i][j] = 0;
            }
            int idx = N - 1;
            for(int j = 0; j < list.size(); j ++){
                if(j + 1 < list.size() && list.get(j) == list.get(j + 1)){
                    board[i][idx] = 2 * list.get(j);
                    j += 1;
                    idx -= 1;
                    continue;
                }
                board[i][idx] = list.get(j);
                idx -= 1;
            }
        }
    }
}
