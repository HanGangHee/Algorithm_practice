import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
[img12.png, img10.png, img02.png, img1.png, IMG01.GIF, img2.JPG]
HEAD NUMBERr TAIL
 */
public class Solution {
    public static void main(String[] args){
        String[] s = { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.10JPG" };
        String head = "^[a-zA-Z]+";
        String number = "[0-9]{1,5}";
        Pattern pattern1 = Pattern.compile(head);
        Pattern pattern2 = Pattern.compile(number);

        for(int i = 0; i < s.length; i ++){
            Matcher m1 = pattern1.matcher(s[i]);
            Matcher m2 = pattern2.matcher(s[i]);
            System.out.println(m1.find());
            System.out.println(m2.find());

            System.out.println(m1.group());
            System.out.println(m2.group());
        }
    }
}
