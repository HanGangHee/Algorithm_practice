package chapter8;

/*
비대칭타일링
2 * N 크기의 직사각형을 2 * 1크기의 타일로 채우려고한다. 타일들은 서로 겹쳐서는 안 되고
90도로 회전해서 쓸 수 있다. 단 이 타일링 방법은 좌우 대칭이어서는 안 된다.


입력
1. test case
2. 사각형의 너비

예제 입력
4
2
3
4
92

0
2
2
913227494
 */


import java.util.Scanner;

public class AsymTiling {
    static int MOD = 1000000007;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            int N = sc.nextInt();
            int answer = 0;
            int temp = tiling(N);
            if(N % 2 == 0){
                answer = (temp - tiling(N / 2 - 1)+ MOD) % MOD;
                answer = (answer - tiling(N / 2)+ MOD) % MOD;
            } else {
                answer = (temp - tiling(N / 2) + MOD) % MOD;
            }
            System.out.println(answer);
        }
    }
    public static int tiling(int N){
        if(N <= 1) return 1;
        int a = 1;
        int b = 2;
        for(int i = 3; i <= N; i ++){
            int temp = b;
            b = (a + b) % MOD;
            a = temp;
        }
        return b;
    }
}
