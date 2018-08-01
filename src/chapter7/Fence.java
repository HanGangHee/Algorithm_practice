package chapter7;


/*
너비가 같은 N개의 나무 판자를 붙여 세운 울타리가 있다.
시간이 지남에 따라 판자들이 부러지거나 망가져 높이가 다 달라진 관계로 울타리를 통째로 교체하기로 했다.
이때 버리는 울타리의 일부를 직사각형으로 잘라내 활용하고 싶다.
울타리를 구성하는 각 판자의 높이가 주어질 때 잘라낼 수 있는 직사각형의 최대 크기를 계산하는 프로그램을 작성하세요
판자의 너비는 모두 1이라고 가정한다.

입력
1. TestCase : C
2. 판자의 수 N (1 <= N <= 20000)
3. 판자의 높이 배열


3
7
7 1 5 9 6 7 3
7
1 4 4 4 4 1 1
4
1 8 2 2

20
16
8
 */

import java.util.*;
public class Fence {
    static int[] fences;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int C = sc.nextInt();
        for(int t = 1; t <= C; t ++ ){
            int N = sc.nextInt();
            fences = new int[N];
            for(int i = 0; i < N; i ++){
                fences[i] = sc.nextInt();
            }
            int answer = solve(0, N - 1);
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
    public static int bruteForce(int[] fences){
        int ret = 0;
        int N = fences.length;
        for(int left = 0; left < N; left ++){
            int minHeight = fences[left];
            for(int right = left; right < N; right ++){
                minHeight = Math.min(minHeight, fences[right]);
                ret = Math.max(ret, (right - left + 1) * minHeight);
            }
        }
        return ret;
    }
    public static int solve(int left, int right){
        System.out.println(left + " " + right);
        if(left == right) return fences[left];
        int mid = (left + right) / 2;
        int ret = Math.max(solve(left, mid), solve(mid + 1, right));

        int lo = mid;
        int hi = mid + 1;
        int height = Math.min(fences[lo], fences[hi]);
        ret = Math.max(ret, height * 2);
        while(left < lo || hi < right){
            if(hi < right && (lo == left || fences[lo - 1] < fences[hi + 1])){
                hi++;
                height = Math.min(height, fences[hi]);
            }
            else {
                lo--;
                height = Math.min(height, fences[lo]);
            }
            ret = Math.max(ret, height * (hi - lo + 1));
        }
        return ret;
    }



}
