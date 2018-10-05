package academy;

import java.util.*;
// N 극 0  S 극 1
public class Magnet {
    static int magnets[][];
    static boolean[] is_diff;
    static boolean check[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            int K = sc.nextInt();
            magnets = new int[5][9];
            for(int i = 1; i < 5; i ++){
                for(int j = 0; j < 8; j ++){
                    magnets[i][j]  = sc.nextInt();
                }
            }
            // 1 은  시계방향   -1  반시계방향
            for(int i = 0; i < K; i ++){
                int magnet = sc.nextInt();
                int dir = sc.nextInt();
                /*
                1 2
                2 3
                3 4  가 서로 다른 지확인해야 한다.
                 */
                is_diff = new boolean[3];
                for(int a = 0; a < 3; a ++){
                    is_diff[a] = magnets[a + 1][(magnets[a + 1][8] + 2) % 8] != magnets[a + 2][(magnets[a + 2][8] - 2 + 8) % 8];
                }
                check = new boolean[5];
                switch (magnet){
                    case 1: lotationMagnetOne(dir); break;
                    case 2: lotationMagnetTwo(dir); break;
                    case 3: lotationMagnetThree(dir); break;
                    case 4: lotationMagnetFour(dir); break;
                }
//                System.out.println("magnet : " + magnet + "  dir : " + dir);
//                for(int a = 1; a < 5; a ++){
//                    System.out.println(magnets[a][8]);
//                }
//                System.out.println();
            }
            int answer = 0;
            for(int i = 1; i < 5; i ++){
                switch (i){
                    case 1 :
                        answer += magnets[i][magnets[i][8]];
                        break;
                    case 2 :
                        answer += magnets[i][magnets[i][8]] * 2;
                        break;
                    case 3 :
                        answer += magnets[i][magnets[i][8]] * 4;
                        break;
                    case 4 :
                        answer += magnets[i][magnets[i][8]] * 8;
                        break;
                }
            }
            System.out.println("#" + t + " " + answer);
        }


    }

    //1
    public static void lotationMagnetOne(int dir){
//        System.out.println(1);
        check[1] = true;
          if(dir == 1){
            magnets[1][8] = (magnets[1][8] - 1 + 8) % 8;
          }
          else if(dir == -1){
              magnets[1][8] = (magnets[1][8]  + 1) % 8;
          }
          if(is_diff[0] && !check[2]) {
              lotationMagnetTwo(dir * -1);
          }
    }
    //2
    public static void lotationMagnetTwo(int dir){
//        System.out.println(2);
        check[2] = true;
        if(dir == 1){
            magnets[2][8] = (magnets[2][8] - 1 + 8) % 8;
        }
        else if(dir == -1){
            magnets[2][8] = (magnets[2][8]  + 1) % 8;
        }
        if(!is_diff[0] && is_diff[1] && !check[3]){
                lotationMagnetThree(dir * -1);
        }
        else if(is_diff[0] && !is_diff[1] && !check[1]){
                lotationMagnetOne(dir * -1);
        }
        else if(is_diff[0] && is_diff[1]){
            if(!check[1]) lotationMagnetOne(dir * -1);
            if(!check[3]) lotationMagnetThree(dir * -1);
        }
    }
    //3
    public static void lotationMagnetThree(int dir){
//        System.out.println(3);
        check[3] = true;
        if(dir == 1){
            magnets[3][8] = (magnets[3][8] - 1 + 8) % 8;
        }
        else if(dir == -1){
            magnets[3][8] = (magnets[3][8]  + 1) % 8;
        }
        if(!is_diff[1] && is_diff[2] && !check[4]){
            lotationMagnetFour(dir * -1);
        }
        else if(is_diff[1] && !is_diff[2] && !check[2]){
            lotationMagnetTwo(dir * -1);
        }
        else if(is_diff[1] && is_diff[2]){
            if(!check[2]) lotationMagnetTwo(dir * -1);
            if(!check[4]) lotationMagnetFour(dir * -1);
        }
    }
    //4
    public static void lotationMagnetFour(int dir){
//        System.out.println(4);
        check[4] = true;
        if(dir == 1){
            magnets[4][8] = (magnets[4][8] - 1 + 8) % 8;
        }
        else if(dir == -1){
            magnets[4][8] = (magnets[4][8]  + 1) % 8;
        }
        if(is_diff[2] && !check[3]) {
            lotationMagnetThree(dir * -1);
        }
    }
}
