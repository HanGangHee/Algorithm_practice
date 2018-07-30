package chapter6;


/*
4 * 4개의 격자 형태로 배치된 열여선 개의 시계가 있다.
이 시계들은 모두 12시 3시 6시 혹은 9시를 가리키고 있다.
이 시계들이 모두 12시를 가리키도록 바꾸고 싶다.

시계의 시간을 조작하는 유일한 방법은 열개의 스위치들을 조작하는 것으로
각 스위치들은 모두 적게는 세 개에서 많게는 다섯 개의 시계에 연결되어 있다.
해당 스위치와 연결된 시계들의 시간은 3시간씩 앞으로 움직인다.

최소한 스위치를 몇번이나 눌러야 할지 계산하는 프로그램을 작성하세요


입력:
1. 테스트 케이스 C
2. 한줄에 16개의 정수로 주어지며 각 정수는 0번부터 15번까지  12, 3, 6, 9 중 하나로 표현한다.


2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6



2
9
 */

import java.util.HashMap;
import java.util.Scanner;
public class ClockSynk {
    static HashMap<Integer, int[]> switches = new HashMap<>();
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        switches.put(0, new int[]{0, 1, 2});
        switches.put(1, new int[]{3, 7, 9, 11});
        switches.put(2, new int[]{4, 10, 14, 15});
        switches.put(3, new int[]{0, 4, 5, 6, 7});
        switches.put(4, new int[]{6, 7, 8, 10, 12});
        switches.put(5, new int[]{0, 2, 14, 15});
        switches.put(6, new int[]{3, 14, 15});
        switches.put(7, new int[]{4, 5, 7, 14, 15});
        switches.put(8, new int[]{1, 2, 3, 4, 5});
        switches.put(9, new int[]{3, 4, 5, 9, 13});
        int C = sc.nextInt();
        for(int t = 1; t <= C; t ++){
            int[] clocks = new int[16];
            for(int i = 0; i < 16; i ++){
                clocks[i] = sc.nextInt();
            }
            int answer = findMinClicks(clocks, 0, 0);
            answer = answer == INF? -1 : answer;
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int findMinClicks(int[] clocks, int sw, int clicks){
        boolean bool = true;
        for(int i = 0; i < 16; i ++){
            if(clocks[i] != 12){
                bool = false;
                break;
            }
        }
        if(sw >= 10) return INF;
        if(bool) return clicks;

        int ret = INF;
        for(int i = 1; i <= 4; i ++){
            int temp[] = switches.get(sw);
            for(int v: temp){
                clocks[v] += 3;
                clocks[v] = clocks[v] == 15 ? 3 : clocks[v];
            }
            ret = Math.min(ret, findMinClicks(clocks, sw + 1, clicks + (i % 4)));
        }
        return ret;
    }
}
