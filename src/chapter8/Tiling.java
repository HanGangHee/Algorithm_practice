package chapter8;


/*
TILING2
2 * N 크기의 사각형을  2 * 1 크기의 타일로 채우는 방법의 수를 계산하는 문제


1 2 3 5 8. . . 피보나치 수열을 이룸
 */
import java.util.Scanner;

public class Tiling {
    static final int MOD = 1000000007;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            int N = sc.nextInt();
            if(N <= 3){
                System.out.println("#" + t + " " + N);
            }
            else {
                int a = 1;
                int b = 2;
                for(int i = 3; i <= N; i ++){
                    int temp = b;
                    b = (b + a) % MOD;
                    a = temp;
                }
                System.out.println("#" + t + " " + b);
            }
        }
    }

}
