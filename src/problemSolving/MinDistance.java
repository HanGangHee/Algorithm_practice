package problemSolving;
/*
김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아가려 한다.
회사와 집의 위치, 그리고 각 고객의 위치는 (x, y) 좌표로 주어진다.
2명에서 10명 사이의 고객 좌표가 주어질 때 이들을 모두 방문하고 집에 돌아가는
경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라


입력
10
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
7
22 47 72 42 61 93 8 31 72 54 0 64 26 71 93 87 84 83
8
30 20 43 14 58 5 91 51 55 87 40 91 14 55 28 80 75 24 74 63
9
3 9 100 100 16 52 18 19 35 67 42 29 47 68 59 38 68 81 80 37 94 92
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
10
26 100 72 2 71 100 29 48 74 51 27 0 58 0 35 2 43 47 50 49 44 100 66 96
10
46 25 16 6 48 82 80 21 49 34 60 25 93 90 26 96 12 100 44 69 28 15 57 63
10
94 83 72 42 43 36 59 44 52 57 34 49 65 79 14 20 41 9 0 39 100 94 53 3
10
32 79 0 0 69 58 100 31 67 67 58 66 83 22 44 24 68 3 76 85 63 87 7 86

출력
#1 200
#2 304
#3 265
#4 307
#5 306
#6 366
#7 256
#8 399
#9 343
#10 391

 */

import java.util.Arrays;
import java.util.Scanner;
public class MinDistance {
    static int N;
    static int[][] points;
    static int INF = 987654321;
    static int cache[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            N = sc.nextInt();
            points = new int[N + 2][2];
            cache = new int[N + 2][1 << N + 2];
            for(int i = 0; i < N + 2; i ++){
                Arrays.fill(cache[i], -1);
            }
            for(int i = 0; i < N + 2; i ++){
                points[i][0] = sc.nextInt();
                points[i][1] = sc.nextInt();
            }
            int answer = INF;
            for(int i = 2; i < N + 2; i ++){
                answer = Math.min(answer, Math.abs(points[i][0] - points[0][0]) + Math.abs(points[i][1] - points[0][1])
                        + findMinDistance(i, 1 << i));
            }
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
    public static int findMinDistance(int node, int visited){
        if(visited == (1 << N + 2) - 1 - 3){
            return Math.abs(points[1][0] - points[node][0]) + Math.abs(points[1][1] - points[node][1]);
        }
        if(cache[node][visited] != -1) return cache[node][visited];
        int ret = INF;
        for(int next = 2; next < N + 2; next ++){
            if((visited & 1 << next) == 0){
                ret = Math.min(ret, Math.abs(points[next][0] - points[node][0]) + Math.abs(points[next][1] - points[node][1])
                        + findMinDistance(next, visited | 1 << next));
            }
        }
        return cache[node][visited] = ret;
    }
}
