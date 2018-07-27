package chapter6;

/*
소풍 때 학생들을 두 명씩 짝을 지어 행동하게 하려고 한다.
근데 서로 친구가 아닌 학생들끼리 짝을 지어 주면 서로 싸우거나 같이 돌아다니지
않기 때문에 항상 서로 친구인 학생들끼리만 짝을 지어야 한다.

각 학생들의 쌍에 대해 이들이 서로 친구인지 주어질 때 , 학생들을 짝지을 수 있는 방법의 수를 계산하시오


 시간 및 메모리 제한
 시간 : 1 초     64MB 이하의 메모리 사용


 입력
 1. TEST CASE : C
 2. STUDENTS : N  (2 <= N <= 10)
 3. 친구 쌍의 수 : M (0 <= M <= n(n - 1) / 2 )
 학생들의 수는 짝수이다.

예제 입력
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 */


import java.util.Scanner;
public class Picnic {
    static int N;
    static int M;
    static boolean [][] areFriends;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int C = sc.nextInt();
        for(int t = 1; t <= C; t ++){
            N = sc.nextInt();
            M = sc.nextInt();
            areFriends = new boolean[N][N];
            for(int i = 0; i < M; i ++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                areFriends[a][b] = true;
                areFriends[b][a] = true;
            }
            int answer = countParings(new boolean[N]);
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
    public static int countParings(boolean[] taken){   //순서를 강제해서 중복을 피하는 법
       int firstFree = -1;
       for(int i = 0; i < N; i ++){
           if(!taken[i]){
               firstFree = i;
               break;
           }
       }
       if(firstFree == -1) return 1;  // 모두 짝을 지음
       int ret = 0;
       for(int pairWith = firstFree + 1; pairWith < N; pairWith ++){
           if(!taken[pairWith] && areFriends[firstFree][pairWith]){
               taken[firstFree] = taken[pairWith] = true;
               ret += countParings(taken);
               taken[firstFree] = taken[pairWith] = false; //새로운 new boolean을 만들 필요 없다.
           }
       }
       return ret;
    }
}
