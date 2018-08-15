package chapter8;

/*

와일드카드
*  몇 글자인지 상관없음
?  한 글자 대응

이 문제를 어렵게 만드는 것은 *가 몇 글자에 대응되어야 하는지를 미리 알 수 없다는 점이다.

주어진 패턴이 m개의 *를 포함한다고 하면  m + 1조각으로 나누어 생각한다.

3
he?p
3
help
heap
helpp
*p*
3
help
papa
hello
*bb*
1
babbbc

t*l?*o*r?ng*s
theoordoftherings
 */

import java.util.Scanner;
public class WildCard {
    static char[][] chached;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        for(int t = 1; t <= test_case; t ++){
            String pattern = sc.next();
            int N = sc.nextInt();
            String[] s = new String[N];
            for(int i = 0; i < N; i ++){
                s[i] = sc.next();
            }
            for(int i = 0; i < N; i ++){
                if(match(pattern, s[i])) System.out.println("#" + t + " " + s[i] + " " +true);
                else System.out.println("#" + t + " " + s[i] + " " +false);
            }
        }

    }
    public static boolean match(String w, String s){
        //w[pos] 와 s[pos]를 맞춰나간다.
        int w_idx = 0;
        int s_idx = 0;
        while(w_idx < w.length() && s_idx < s.length() &&
                (w.charAt(w_idx) == '?' || w.charAt(w_idx) == s.charAt(s_idx))){
            ++s_idx;
            ++w_idx;
        }
        //종료
        /*
        1. s.charAt(pos) != w.charAt(pos)   대응 실패
        2. w 끝에 도달: 패턴에 * 이 하나도 없는 경우, 이 겨우에 패턴과 문자열의 길이가 정확히 같아야만 한다.
        3. s 끝에 도달했다 :  패턴은 남았지만 문자열이 이미 끝난 경우  , 당연희 대응 실패라고 생각할 수 있지만
                                남은 패턴이 전부 * 이라면 가능
        4. w.charAt(pos) 가 * 인 경우 : w가 몇 글자에 대응될지 모르기 때문에 , 0 글자부터 남은 문자열의 길이까지를 순회하며
                                모든 가능성을 검사
         */
        if(w_idx == w.length()) return w_idx == s.length();
        if(w.charAt(w_idx) == '*'){
            while(w_idx + 1 < w.length() && w.charAt(w_idx + 1) == '*'){
                ++w_idx;
            }// 연속적인 *을 지운다.
            if(w_idx == w.length() - 1) return true;
            for(int skip = s_idx; skip < s.length(); skip ++){
                if(w_idx + 1 < w.length() && w.charAt(w_idx + 1) == s.charAt(skip) && match(w.substring(w_idx + 1), s.substring(skip))) return true;
            }
        }
        return false;
    }



}
