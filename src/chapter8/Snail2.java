package chapter8;

/*
여름 장마가 찾아와 매일 비가 올 확률이 75%로 올라갔다.
N 미터 , M 일이 주어질 때 M 동안 N 미터를 올라갈 확률을 구하시오

1
13 7
 */
import java.util.Arrays;
import java.util.Scanner;
public class Snail2 {
    static int N;
    static int M;
    static double cache[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            N = sc.nextInt();
            M = sc.nextInt();
            cache = new double[M][2*M + 1];
            for(int i = 0; i < M; i ++){
                Arrays.fill(cache[i], -1);
            }
            climb(0, 0);
            for(int i = 0; i < M; i ++){
                System.out.println(Arrays.toString(cache[i]));
            }
            System.out.println(cache[0][0]);
        }
    }
    public static double climb(int days, int climbed){
        if(days == M){
            return climbed >= N ? 1 : 0;
        }
        if(cache[days][climbed] != -1) return cache[days][climbed];
        return cache[days][climbed] = 0.75 * climb(days + 1, climbed + 2) + 0.25 * climb(days + 1, climbed + 1);
    }
}
