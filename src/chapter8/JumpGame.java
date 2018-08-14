package chapter8;

import java.util.Arrays;
import java.util.Scanner;

/*
n * n크기의 격자에 1부터 9까지의 정수를 쓴 게임판이 있다.
이 게임의 목적은 게임판의 왼쪽 위 칸에서 시작해서 게임판의 맨 오른쪽 아래 칸에 도착하는 것이다.
중간에 게임판 밖으로 벗어나면 안되며, 게임판이 주어질 때 시작점에서 끝점으로 도달하는 방법이
존재하는지를 확인하는 것이다.

1
7 7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 2
3 3 1 2 3 4 1
1 5 2 9 4 7 0
 */
public class JumpGame {
    static int N;
    static int M;
    static int[][] board;
    static int[][] check;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            M = sc.nextInt();
            board = new int[N][M];
            check = new int[N][M];
            for(int i = 0; i < N; i ++){
                Arrays.fill(check[i], -1);
            }
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < M; j ++){
                    board[i][j] = sc.nextInt();
                }
            }
            boolean answer = checkPosibility(0, 0);
            int answer2 = checkPosibility2(0, 0);
            if(answer) sb.append("#" + t + " 가능합니다.\n");
            else sb.append("#" + t + " 불가능합니다.\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean checkPosibility(int a, int b){ // 중복을 허용한 경우
        if(a >= N || b >= M) return false;
        else if(a == N -1 && b == M - 1) return true;
        return checkPosibility(a + board[a][b], b) || checkPosibility(a, b + board[a][b]);
    }
    public static int checkPosibility2(int a, int b){ //dp를 이용해 중복 제거
        System.out.println(a + " "  +b);
        for(int i = 0; i < N; i ++){
            System.out.println(Arrays.toString(check[i]));
        }
        System.out.println();
        if(a >= N || b >= M) return 0;
        else if(a == N -1 && b == M - 1) return 1;
        int ret = check[a][b];
        if(ret != -1) return ret;
        return check[a][b] = (checkPosibility2(a + board[a][b], b) != 0 || checkPosibility2(a, b + board[a][b]) != 0)? 1 : 0;
    }

}
