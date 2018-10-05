package academy;

import java.util.*;

public class Airstrip {
    static int N;
    static int X;
    static int map[][];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            X = sc.nextInt();
            map = new int[N][N];
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j ++){
                    map[i][j] = sc.nextInt();
                }
            }
            int answer = solve();
            System.out.println("#" + t + " " + answer);
        }
    }
    public static int solve(){
        int ret = 0;
        // 가로 기준
        for(int i = 0; i < N; i ++){
            ArrayList<Integer> counter = new ArrayList<>();
            int cur = map[i][0];
            int cnt = 1;
            boolean is_promise = true;
            for(int j = 1; j < N; j ++){
                if(cur == map[i][j]){
                    cnt ++;
                } else if(Math.abs(cur - map[i][j]) > 1){
                    is_promise = false;
                    break;
                } else {
                    counter.add(cnt);
                    cur = map[i][j];
                    cnt = 1;
                }
                if(j == N - 1){
                    counter.add(cnt);
                }
            }
            if(!is_promise) continue;
            int idx = 0;
            for(int j = 1; j < N; j ++){
                if(map[i][j] != map[i][j - 1] && map[i][j] - map[i][j - 1] == 1){
                    int temp = counter.get(idx);
                    if(temp < X){
                        is_promise = false;
                        break;
                    } else {
                        counter.set(idx, temp - X);
                    }
                    idx += 1;
                }
                else if(map[i][j] != map[i][j - 1] && map[i][j] - map[i][j - 1] == -1){
                    int temp = counter.get(idx + 1);
                    if(temp < X){
                        is_promise = false;
                        break;
                    } else {
                        counter.set(idx + 1, temp - X);
                    }
                    idx += 1;
                }
            }
            if(is_promise) ret += 1;
        }

        // 세로 기준
        for(int j = 0; j < N; j ++){

            ArrayList<Integer> counter = new ArrayList<>();
            int cur = map[0][j];
            int cnt = 1;
            boolean is_promise = true;
            for(int i = 1; i < N; i ++){
                if(cur == map[i][j]){
                    cnt ++;
                } else if(Math.abs(cur - map[i][j]) > 1){
                    is_promise = false;
                    break;
                } else {
                    counter.add(cnt);
                    cur = map[i][j];
                    cnt = 1;
                }
                if(i == N - 1){
                    counter.add(cnt);
                }
            }
            if(!is_promise) continue;
            int idx = 0;
            for(int i = 1; i < N; i ++){
                if(map[i][j] != map[i - 1][j] && map[i][j] - map[i - 1][j] == 1){
                    int temp = counter.get(idx);
                    if(temp < X){
                        is_promise = false;
                        break;
                    } else {
                        counter.set(idx, temp - X);
                    }
                    idx += 1;
                }
                else if(map[i][j] != map[i - 1][j] && map[i][j] - map[i - 1][j] == -1){
                    int temp = counter.get(idx + 1);
                    if(temp < X){
                        is_promise = false;
                        break;
                    } else {
                        counter.set(idx + 1, temp - X);
                    }
                    idx += 1;
                }
            }
            if(is_promise) ret += 1;
        }
        return ret;
    }
}
