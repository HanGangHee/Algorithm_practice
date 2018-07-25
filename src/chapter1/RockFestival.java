package chapter1;

// 1.2 록 페스티벌

/*
시간 및 메모리 제한
프로그램은 2초 안에 실행되어야 하며 64MB 이하의 메모리를 사용한다.

입력
1. 테스트케이스 C
2. 공연장을 대여할 수 있는 날들의 수 N (1 <= N <= 1000)
3. 이미 섭외한 동연 팀의 수 L (1 <= L <= 1000, L <= N)
4. N 개의 숫자로 공연장 대여 비용이 날짜별로 주어진다.  100 이하의 자연수
*/


//풀이
// 최소 L 일은 연속적으로 해야하므로 L 일부터 N 일까지 평균을 구한다. O(N * (N - L))



/*
2
6 3
1 2 3 1 2 3
6 2
1 2 3 1 2 3

#1 1.75
#2 1.5
 */
import java.util.*;


public class RockFestival {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int c = 1; c <= C; c ++){
            int N = sc.nextInt();
            int L = sc.nextInt();
            int arrN[] = new int[N];
            for(int i = 0; i < N; i ++){
                arrN[i] = sc.nextInt();
            }
            double answer = Double.MAX_VALUE;
            for(int i = L; i <= N; i ++){
                for(int j = 0; j <= N - i; j ++){
                    double sum = 0;
                    for(int k = j; k < j + i; k ++){
                        sum += arrN[k];
                    }
                    answer = answer > (sum / i)? (sum / i) : answer;
                }
            }
            sb.append("#" + c + " " + answer + '\n');
        }
        System.out.println(sb.toString());
    }
}
