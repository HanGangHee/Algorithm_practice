package academy;

import java.lang.reflect.Array;
import java.util.*;

/*

 */
public class MicrobialIsolation {
    static int N;
    static int K;
    static int dx[] = {0 , -1, 1, 0 , 0};
    static int dy[] = {0 ,  0, 0, -1, 1};
    static int reverseDir[] = {0, 2, 1, 4, 3};
    static ArrayList<Microbial> microbials;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t  = 1; t <= T; t ++){
            N = sc.nextInt();
            int M = sc.nextInt();
            K = sc.nextInt();
            microbials = new ArrayList<>();
            for(int i = 0; i < K; i ++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int num = sc.nextInt();
                int dir = sc.nextInt();
                microbials.add(new Microbial(x, y, num, dir));
            }
            int answer = findMicrobialsNum(M);
            System.out.println("#" + t + " " + answer);
        }
    }
    public static int findMicrobialsNum(int times){
        int ret = 0;
        for(int time = 1; time <= times; time ++){
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for(int i = 0; i < K; i ++){
                Microbial m = microbials.get(i);
                if(m.num == 0) continue;
                int nextX = m.x + dx[m.dir];
                int nextY = m.y + dy[m.dir];

                if(nextX == 0 || nextX == N - 1 || nextY == 0 || nextY == N - 1){
                    microbials.get(i).num = m.num / 2;
                    microbials.get(i).dir = reverseDir[m.dir];
                }
                microbials.get(i).x = nextX;
                microbials.get(i).y = nextY;
                int key = nextX * 100 + nextY;
                if(key < 0){
                    System.out.println(nextX + " " + nextY);
                }
                if(!map.containsKey(key)){
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    map.put(key, temp);
                } else {
                    ArrayList<Integer> temp = map.get(key);
                    temp.add(i);
                    map.put(key, temp);
                }
            }
            for(int v : map.keySet()){
                ArrayList<Integer> temp = map.get(v);
                int maxIdx = 0;
                int maxNum = 0;
                for(int i = 0; i < temp.size(); i ++){
                    if(maxNum < microbials.get(temp.get(i)).num){
                        maxNum = microbials.get(temp.get(i)).num;
                        maxIdx = i;
                    }
                }
                int sum = 0;
                for(int i = 0; i < temp.size(); i ++){
                    sum += microbials.get(temp.get(i)).num;
                    if(i != maxIdx){
                        microbials.get(temp.get(i)).num = 0;
                    }
                }
                microbials.get(temp.get(maxIdx)).num = sum;
            }
        }
        for(int i = 0; i < K; i ++){
            ret += microbials.get(i).num;
        }
        return ret;
    }
}

class Microbial{
    int x;
    int y;
    int num;
    int dir;
    Microbial(int x, int y, int num, int dir){
        this.x = x;
        this.y = y;
        this.num = num;
        this.dir = dir;
    }
}