package kakao;


import java.util.Arrays;
import java.util.Scanner;

public class DartGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] nums = new int[3];
        int numIdx = 0;
        for(int i = 0; i < s.length(); i ++){
            System.out.println(s.charAt(i) + " " + Arrays.toString(nums));
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                if(i + 1 < s.length() && s.charAt(i) == '1' && s.charAt(i + 1) == '0'){
                    nums[numIdx] = 10;
                    i += 1;
                } else {
                    nums[numIdx] = s.charAt(i) - 48;
                }
                numIdx += 1;
                continue;
            }
            else if(s.charAt(i) == 'D'){
                nums[numIdx - 1] *= nums[numIdx - 1];
            }
            else if(s.charAt(i) == 'T'){
                int temp = nums[numIdx - 1];
                nums[numIdx - 1] *= nums[numIdx - 1];
                nums[numIdx - 1] *= temp;
            }
            else if(s.charAt(i) == '*'){
                if(numIdx == 1){
                    nums[numIdx - 1] *= 2;
                } else {
                    nums[numIdx - 1] *= 2;
                    nums[numIdx - 2] *= 2;
                }
            }
            else if(s.charAt(i) == '#'){
                nums[numIdx - 1] *= -1;
            }
        }
        int answer = 0;
        for(int i = 0; i < 3; i ++){
            answer += nums[i];
        }
        System.out.println(answer);
    }
}
