package samsung;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PutOperator {
    static int N;
    static int arrN[];
    static int opCnt[] = new int[4];
    static char op[] = { '+', '-', '*', '%' };
    static int max = -987654321;
    static int min = 987654321;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N];
        for(int i = 0; i < N; i ++){
            arrN[i] = sc.nextInt();
        }
        for(int i = 0; i < 4; i ++){
            opCnt[i] = sc.nextInt();   // +  -  *  %
        }
        if(arrN.length == 0){
            System.out.println(0);
            return;
        }
        findMaxAndMin(1, arrN[0] + "");
        System.out.println(max);
        System.out.println(min);
    }
    public static void findMaxAndMin(int step, String s){
        boolean is_end = true;
        for(int i = 0; i < 4; i ++){
            if(opCnt[i] >= 1){
                is_end = false;
                break;
            }
        }
        if(is_end){
            int temp = strToInteger(s);
            max = Math.max(max, temp);
            min = Math.min(min, temp);
            return;
        }
        for(int i = 0; i < 4; i ++){
            if(opCnt[i] >= 1){
                opCnt[i] -= 1;
                findMaxAndMin(step + 1,s + op[i] + "" + arrN[step]);
                opCnt[i] += 1;
            }
        }
        HashMap<String, String> a = new HashMap<>();
        String sa = "";

    }
    public static int strToInteger(String s){
        String sub = s.substring(1);
        String regex = "[-*+%]\\d{1,3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sub);
        int ret = arrN[0];
        while(m.find()){
            String temp = m.group();
            switch (temp.charAt(0)){
                case '+' : ret += Integer.parseInt(temp.substring(1));break;
                case '-' : ret -= Integer.parseInt(temp.substring(1));break;
                case '%' : ret /= Integer.parseInt(temp.substring(1));break;
                case '*' : ret *= Integer.parseInt(temp.substring(1));break;
            }
        }
        return ret;
    }
}
