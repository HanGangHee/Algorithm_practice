package samsung;

import java.util.*;

public class Leave {
    static int N;
    static int arrN[][];
    static boolean chose[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N + 1][2];
        for(int i = 1; i <= N; i ++){
            arrN[i][0] = sc.nextInt();
            arrN[i][1] = sc.nextInt();
        }
        chose = new boolean[N + 1];
        int answer = findMaxProfit(1);
        System.out.println(answer);
    }
    public static int findMaxProfit(int step){
        System.out.println(step);
        System.out.println(Arrays.toString(chose));
        System.out.println();

        if(step >= N + 1){
            int ret = 0;
            for(int i = 1; i <= N; i ++){
                if(chose[i]){
                    ret += arrN[i][1];
                }
            }
            return ret;
        }
        int ret = 0;
        if(step + arrN[step][0] > N + 1){
            ret = Math.max(findMaxProfit(step + arrN[step][0]), ret);
            ret = Math.max(findMaxProfit(step + 1), ret);
        } else if(step + arrN[step][0] == N + 1){
            chose[step] = true;
            ret = Math.max(findMaxProfit(step + arrN[step][0]), ret);
            chose[step] = false;
        }else {
            chose[step] = true;
            ret = Math.max(findMaxProfit(step + arrN[step][0]), ret);
            chose[step] = false;
            ret = Math.max(findMaxProfit(step + 1), ret);
        }
        return ret;
    }
}
