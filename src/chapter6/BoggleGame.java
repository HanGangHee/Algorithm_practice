package chapter6;

/*
보글은  5 * 5 크기의 알파벳 격자를 가지고 하는 게임이다.
이때 게임의 목적은 상하좌우/ 대각선으로 인접한 칸들의 글자들을 이어서
찾아내는 것이다.


ex)
URLPM
XPRET
GIAET
XTNZY
XOQRS
3
PRETTY
GIRL
YES


출력
TRUE
TRUE
FALSE
 */


import java.util.Scanner;

public class BoggleGame {

    static char[][] boggle;
    static int dx[] = {-1, -1 ,-1 , 1 , 1 ,1, 0 ,0};
    static int dy[] = {-1, 0, 1, -1 , 0, 1, -1 , 1};

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        boggle = new char[5][5];
        for(int i = 0; i < 5; i++){
            boggle[i] = sc.next().toCharArray();
        }
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            String word = sc.next();
            boolean answer = false;
            for(int i = 0; i < 5; i ++){
                for(int j = 0; j < 5; j ++){
                    if(boggle[i][j] == word.charAt(0)){
                        if(hasWord(i, j, word)){
                            answer = true;
                            break;
                        }
                    }
                }
                if(answer) break;
            }
            if(answer){
                sb.append("#" + t + " " + true + "\n");
            } else {
                sb.append("#" + t + " " + false + "\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static boolean hasWord(int a, int b, String word){
        if(a < 0 || a >= 5 || b < 0 || b >= 5 || boggle[a][b] != word.charAt(0)) return false;
        if(word.length() == 1) return true;
        for(int d = 0; d < 8; d ++){
            int nextA = a + dx[d];
            int nextB = b + dy[d];
            if(hasWord(nextA, nextB, word.substring(1))){
                return true;
            }
        }
        return false;
    }
}
