package chapter8;

/*
우물을 기어오르는 갈팽이

깊이가 N 미터인 우물의 맨 밑바닥에 달팽이가 있다.
이 달팽이는 물의 맨 위까지 기어올라가고 싶어하는데, 달팽이의 움직임은 그 날의 날씨에
좌우된다.
만약 날이 맑으면 달팽이는 하루에 2미터를 기어올라갈 수 있지만 비가 내리면 1미터밖에 못올라간다.
M일 간의 비가 올 확률이 50% 일 때 M일 안에 달팽이가 우물 끝까지 올라갈 확률은 얼마나 될까



2
10 5
10 7
 */

import java.util.Arrays;
import java.util.Scanner;
public class Snail {
    static int N;
    static int M;
    static int cache[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            M = sc.nextInt();
            cache = new int[M][2 * M + 1];
            for(int i = 0; i < M; i ++){
                Arrays.fill(cache[i], -1);
            }

            int answer = climb(0, 0);
            System.out.println("#" + t + " " + answer + "\n");
        }
    }
    public static int climb(int days, int climbed){
        System.out.println(days + " " + climbed);
        for(int i = 0; i < M; i ++){
            System.out.println(Arrays.toString(cache[i]));
        }
        System.out.println();
        if(days == M){
            return climbed >= N ? 1 : 0;
        }
        if(cache[days][climbed] != -1) return cache[days][climbed];
        return cache[days][climbed] = (climb(days + 1, climbed + 1) + climb(days + 1, climbed + 2));
    }
}
