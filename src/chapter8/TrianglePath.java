package chapter8;


/*
삼각형 위의 최대 경로 구하기

맨 위의 숫자에서 시작해서 , 한 번에 한 칸씩 아래로 내려가
맨 아래 줄까지 닿는 경로를 만들려고 한다.
경로는 아래줄에 내려갈 때마다 바로 아래 숫자 혹은 오른쪽 아래 숫자로 내려갈 수 있다.
이때 모든 경로 중 숫자의 합을 최대화하는 경로는 무엇일까

5
6
1 2
3 7 4
9 4 1 7
2 7 5 9 4


5
1
2 4
8 16 8
32 64 32 64
128 256 128 256 128
 */

import java.util.Scanner;
public class TrianglePath {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int triangle[][] = new int[N][N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j <= i; j ++){
                triangle[i][j] = sc.nextInt();
            }
        }

        for(int i = 1; i < N; i ++){
            for(int j = 0; j <= i; j ++){
                if(j - 1 >= 0){
                    triangle[i][j] = Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]) + triangle[i][j];
                }
                else {
                    triangle[i][j] = triangle[i - 1][j] + triangle[i][j];
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for(int j = 0; j < N; j ++){
            answer = answer > triangle[N - 1][j] ? answer : triangle[N - 1][j];
        }
        System.out.println("# " + answer);
    }
}
