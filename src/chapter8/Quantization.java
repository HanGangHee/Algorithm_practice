package chapter8;

/*
Quantization 양자화

과정은 더 넓은 범위를 갖는 값들을 작은 범위를 갖는 값들로 근사해 표현함으로써
자료를 손실 압축하는 과정을 말한다.
161 164 178 184 인 학생 넷을 160 대 둘 170 대 하나 180대 하나라고 츅약해 표현하는 것 또한
양자화라고 한다.


1000 이하의 자연수들로 구성된 수열을 s가지의 자연수만을 사용하도록 양자화하려고 한다.
오차 제곱의 합을 최소화하는 양자화 결과를 알고 싶다.


입력
1.testCase
2.n 수열의 길이
3.사용할 숫자의 수 s
4.n 개의 정수



2
10 3
3 3 3 1 2 3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777

 */

import java.util.Arrays;
import java.util.Scanner;
public class Quantization {
    static final int INF = 987654321;
    static int N;
    static int S;
    static int arrN[];
    static int pSum[];
    static int pSqSum[];
    static int cache[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            S = sc.nextInt();
            arrN = new int[N];
            pSum = new int[N];
            pSqSum = new int[N];
            cache = new int[N][S + 1];
            for(int i = 0; i < N; i ++){
                arrN[i] = sc.nextInt();
            }
            precalc();
            quantize(0, S);
        }
    }
    static void precalc(){
        Arrays.sort(arrN);
        pSum[0] = arrN[0];
        pSqSum[0] = arrN[0] * arrN[0];
        for(int i = 1; i < N; i ++){
            pSum[i] = pSum[i - 1] + arrN[i];
            pSqSum[i] = pSqSum[i - 1] + arrN[i] * arrN[i];
        }
        for(int i = 0; i < N; i ++){
            Arrays.fill(cache[i], -1 );
        }
    }
    static int minError(int lo, int hi){
        int sum = pSum[hi] - (lo == 0? 0 : pSum[lo - 1]);
        int sqSum = pSqSum[hi] - (lo == 0 ? 0 : pSqSum[lo - 1]);
        int m = (int)(0.5 + (double)sum / (hi - lo + 1));

        int ret = sqSum - 2 * m * sum + m * m * (hi - lo + 1);
        return ret;
    }
    static int quantize(int from, int parts){
        System.out.println();
        System.out.println(from + " " + parts);

        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(cache[i]));
        }
        System.out.println();
        if(from == N) return 0;
        if(parts == 0) return INF;
        if(cache[from][parts] != -1) return cache[from][parts];
        cache[from][parts] = INF;
        for(int i = from; i < N; i ++){
            cache[from][parts] = Math.min(cache[from][parts], minError(from, i) + quantize(i + 1, parts - 1));
        }
        return cache[from][parts];
    }
}
