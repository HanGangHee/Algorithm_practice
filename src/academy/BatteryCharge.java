package academy;

import java.util.*;

public class BatteryCharge {
    static int M;
    static int A;
    static int dx[] = {0, -1, 0, 1, 0};
    static int dy[] = {0,  0, 1, 0, -1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            M = sc.nextInt();
            A = sc.nextInt();
            int[] aPath = new int[M + 1];
            int[] bPath = new int[M + 1];
            for(int i = 1; i <= M; i ++){
                aPath[i] = sc.nextInt();
            }
            for(int i = 1; i <= M; i ++){
                bPath[i] = sc.nextInt();
            }
            BC[] bcs = new BC[A];

            for(int i = 0; i < A; i ++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int C = sc.nextInt();
                int P = sc.nextInt();
                bcs[i] = new BC(y - 1,  x - 1, C, P);
            }
            int curAX = 0;
            int curAY = 0;
            int curBX = 9;
            int curBY = 9;
            int answer = 0;
            for(int time = 0; time <= M; time ++){
                int nextAX = curAX + dx[aPath[time]];
                int nextAY = curAY + dy[aPath[time]];

                int AmaxIdx = -1;
                int AmaxVal = 0;
                int AprevIdx = -1;
                int AprevVal = 0;
                for(int a = 0; a < bcs.length; a ++){
                    int dis = Math.abs(bcs[a].x - nextAX) + Math.abs(bcs[a].y - nextAY);
//                    System.out.println(nextAX + " " + nextAY);
//                    System.out.println(dis + " " + bcs[a].C  + " " + bcs[a].P);

                    if(dis <= bcs[a].C){
                        if(AmaxVal <= bcs[a].P){
                            AprevVal = AmaxVal;
                            AprevIdx = AmaxIdx;
                            AmaxVal = bcs[a].P;
                            AmaxIdx = a;
                            continue;
                        }
                        else if(AprevVal <= bcs[a].P){
                            AprevIdx = a;
                            AprevVal = bcs[a].P;
                            continue;
                        }
                    }
                }
//                System.out.println("A");
//                System.out.println(AmaxVal + " " + AprevVal);

                int nextBX = curBX + dx[bPath[time]];
                int nextBY = curBY + dy[bPath[time]];
                int BmaxIdx = -1;
                int BmaxVal = 0;
                int BprevIdx = -1;
                int BprevVal = 0;
//                System.out.println(nextBX + " " + nextBY);

                for(int a = 0; a < bcs.length; a ++){
                    int dis = Math.abs(bcs[a].x - nextBX) + Math.abs(bcs[a].y - nextBY);
                    if(dis <= bcs[a].C){
                        if(BmaxVal <= bcs[a].P){
                            BprevVal = BmaxVal;
                            BprevIdx = BmaxIdx;
                            BmaxVal = bcs[a].P;
                            BmaxIdx = a;
                            continue;
                        }
                        else if(BprevVal <= bcs[a].P){
                            BprevIdx = a;
                            BprevVal = bcs[a].P;
                            continue;
                        }
                    }
                }

//                System.out.println("B");
//                System.out.println(BmaxVal + " " + BprevVal);

                if(AmaxIdx != BmaxIdx){
                    answer += (AmaxVal + BmaxVal);
                } else {
                    if(AprevIdx == -1 && BprevIdx == -1){
                        answer += AmaxVal;
                    }
                    if(AprevIdx != -1 && BprevIdx != -1){
                        answer += (Math.max(AprevVal, BprevVal) + AmaxVal);
                    }
                    else if(AprevIdx != -1 && BprevIdx == -1){
                        answer += (BmaxVal + AprevVal);
                    }
                    else if(BprevIdx != -1 && AprevIdx == -1){
                        answer += (AmaxVal + BprevVal);
                    }
                }
                curAX = nextAX;
                curAY = nextAY;
                curBX = nextBX;
                curBY = nextBY;
            }
            System.out.println("#" + t + " " + answer);

        }
    }
}

class BC{
    int x;
    int y;
    int C; // 구역
    int P; // 성능
    BC(int x,int y, int C, int P){
        this.x = x;
        this.y = y;
        this.C = C;
        this.P = P;
    }
}
