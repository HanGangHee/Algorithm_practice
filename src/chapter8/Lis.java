package chapter8;
/*
수열의 최장길이 증가 부분 수열 찾기

입력
1. test_case
2. N   : 배열의 길이
3. 배열의 각 요소



예제
3
5
1 2 4 7 3
5
10 20 30 5 6
9
9 0 2 4 1 3 9 8 10

출력
#1 4
#2 4
#3 5

 */

import java.util.Arrays;
import java.util.Scanner;
public class Lis {
    static int N;
    static int arrN[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            arrN = new int[N];
            for(int i = 0; i < N; i ++){
                arrN[i] = sc.nextInt();
            }
            int[] dp = new int[N];
            Arrays.fill(dp, 1);
            for(int i = 1; i < N; i ++){   //O(n^2)
                for(int j = 0; j < N; j ++){
                    if(arrN[i] > arrN[j] && dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            System.out.println("#" + t + " " + Arrays.toString(dp));

            int[] dp2 = new int[N + 1];   // 지금까지 만든 부분 배열이 갖는 길이 i인 증가 부분 수열 중 최소의 마지막 값
            dp2[1] = arrN[0];
            int lastIdx = 1;
            for(int i = 1; i < N; i ++){
               int p = 1;
               int r = lastIdx;
               while(p < r){
                   int q = (p + r) / 2;
                   if(dp2[q] < arrN[i]){
                       p = q;
                   } else if(dp2[q] > arrN[i]){
                       r = q;
                   } else {
                       p = q;
                       break;
                   }
                   if(r - p == 1){
                       if(arrN[i] > dp2[r] || arrN[i] > dp2[p]){
                           p = r;
                       }
                       break;
                   }
               }
               if(p == lastIdx && dp2[lastIdx] < arrN[i]){
                   ++lastIdx;
                   dp2[lastIdx] = arrN[i];
                   continue;
               }
               if(dp2[p] > arrN[i]){
                   dp2[p] = arrN[i];
               }
            }
            System.out.println("#" + t + " " + Arrays.toString(dp2));
        }
    }

}
