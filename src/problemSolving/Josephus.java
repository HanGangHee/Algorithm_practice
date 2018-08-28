package problemSolving;

/*
Josephus의 병사 N명이 적에게 포위되어 자살을 결심했다.
마지막에 자살하는 위치에 있어 혼자 남았을 때 Josephus를 투항하고자 설득하려고 한다.
Josephus가 제안한 자살 방법을 고려하여 마지막에 죽는 위치를 알아내고 그 위치에 있어야 한다.

N 만큼의 사람들이 원형을 앉아 있을 때, 처음 시작하는 사람부터 1 ~ N 까지 번호를 붙이고
1번부터 K번 만큼 떨어진 사람이 죽어야 한다.
죽고 나면 다음 사람을 시작으로 하여 다시 K번 떨어진 사람이 죽어야 한다.
마지막 사람이 남게 되는 사람의 번호를 구해본다.


입력: 포위된 병사의 수 N  건너 뛸 횟수 K
예제
10 3

출력 : 최종적으로 살아남는 사람의 번호를 출력
2 8
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Josephus {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        List<Integer> people = new ArrayList<Integer>();
        for(int i = 1; i <= N; i ++){
            people.add(i);
        }
        int cur = 0;
        for(int i = 1; i < N; i ++){
            cur += K - 1;
            cur = cur % people.size();
            people.remove(cur);
        }
        System.out.println(people.get(0));
    }
}
