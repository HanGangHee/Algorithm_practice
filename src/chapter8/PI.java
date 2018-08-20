package chapter8;


/*
원주율 외우기

숫자를 세 자리에서 다섯 자리까지로 끊어서 외운다.

1. 모든 숫자가 같은 때 333, 44444                    1
2. 숫자가 1씩 단조 증가 또는 감소할 때 1234          2
3. 두 개의 숫자가 번갈아가며 나타날 때               4
4. 숫자가 등차 수열을 이룰 때                        5
이 외의 모든 경우                                    10

입력
1. testCase
2. 숫자 입력(String 으로 받아서 처리)



5
12341234
11112222
12122222
22222222
12673939

 4
 2
 5
 2
 14
 */




import java.util.Arrays;
import java.util.Scanner;

public class PI {
    static int dp[];
    static String s;
    static int INF = 987654321;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            s = sc.next();
            dp = new int[s.length()];
            Arrays.fill(dp, -1);
            memorize(0);
            System.out.println(dp[0]);
        }
    }
    public static int memorize(int begin){
        if(begin == dp.length) return 0;
        if(dp[begin] != -1) return dp[begin];
        dp[begin] = INF;
        for(int L = 3; L <= 5; L ++){
            if(begin + L <= dp.length){
                dp[begin] = Math.min(dp[begin], memorize(begin + L) + classify(begin, begin + L));
            }
        }
        return dp[begin];
    }
    public static int classify(int begin, int end){
        char temp[] = new char[end - begin];
        Arrays.fill(temp, s.charAt(begin));
        String sub_s = s.substring(begin, end);
        if(sub_s.equals(String.valueOf(temp))){
            return 1;
        }
        boolean alternating = true;
        for(int i = 0; i < sub_s.length(); i ++){
            if(sub_s.charAt(i) != sub_s.charAt(i % 2)){
                alternating = false;
                break;
            }
        }
        if(alternating) return 4;
        boolean progressive = true;
        for(int i = 1; i < sub_s.length(); i++){
            if(sub_s.charAt(i) - sub_s.charAt(i - 1) != sub_s.charAt(1) - sub_s.charAt(0)){
                progressive = false;
                break;
            }
        }
        if(progressive && Math.abs(sub_s.charAt(1) - sub_s.charAt(0)) == 1){
            return 2;
        }
        if(progressive) return 5;
        return 10;
    }
}
