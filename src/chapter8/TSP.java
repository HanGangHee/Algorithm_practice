package chapter8;


import java.util.Arrays;
import java.util.Scanner;

/*
2
4
0 1 2 3
1 0 4 5
2 4 0 6
3 5 6 0
6
0 10 11 13 24 12
10 0 16 11 8 19
11 16 0 12 12 14
13 11 12 0 10 18
24 8 12 10 0 13
12 19 14 18 13 0
 */
public class TSP {
    static int INF = 987654321;
    static int N;
    static int[][] graph;
    static int[][] cache;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            N = sc.nextInt();
            graph = new int[N][N];
            cache = new int[N][1 << N];
            for(int i = 0; i < N; i ++){
                Arrays.fill(cache[i], -1);
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j ++){
                    graph[i][j] = sc.nextInt();
                }
            }
            int answer= INF;
            for(int i = 0; i < N; i ++){
                answer = Math.min(answer, tsp(i, 1 << i));
            }
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int tsp(int pos, int visited){
        System.out.println(pos + " " + visited);
        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(cache[i]));
        }
        System.out.println();

        if(visited == (1 << N) - 1){
            return 0;
        }
        if(cache[pos][visited] != -1) return cache[pos][visited];
        int ret = INF;
        for(int next = 0; next < N; next ++){
            if((1 << next & visited ) == 0 && graph[pos][next] != 0){
                ret = Math.min(ret, graph[pos][next] + tsp(next, visited | 1 << next));
            }
        }
        return cache[pos][visited] = ret;
    }
}
