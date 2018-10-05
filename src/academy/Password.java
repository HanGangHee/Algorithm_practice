package academy;

import java.util.*;

public class Password {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            int N = sc.nextInt();
            int K = sc.nextInt();
            String str = sc.next();
            StringBuffer sb = new StringBuffer(str);
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i < N / 4; i ++){
                for(int j = 0; j < N; j += N / 4){
                    StringBuffer temp = new StringBuffer();
                    for(int k = j; k < j + N / 4; k ++){
                        temp.append(sb.charAt(k));
                    }
                    set.add(temp.toString());
                }
                char c = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                sb.insert(0, c);
            }
            List sortedSet = new ArrayList(set);
            Collections.sort(sortedSet);
            String string = (String)sortedSet.get(sortedSet.size() - K);
            int answer = 0;
            int pow = 0;

            for(int i = string.length() - 1; i >= 0; i --){
                if('0' <= string.charAt(i) && '9' >= string.charAt(i)){
                    answer += (string.charAt(i) - '0') * Math.pow(16, pow);
                }
                else if('A' <= string.charAt(i) && 'F' >= string.charAt(i)){
                    answer += (string.charAt(i) - 'A' + 10) * Math.pow(16, pow);
                }
                pow += 1;
            }
            System.out.println("#" + t + " " + answer);
        }

    }
}
