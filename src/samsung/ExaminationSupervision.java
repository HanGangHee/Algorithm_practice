package samsung;

import java.util.*;

public class ExaminationSupervision {
    static int N;
    static int arrN[];
    static int B;
    static int C;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arrN = new int[N];
        for(int i = 0; i < N; i ++){
            arrN[i] = sc.nextInt();
        }
        B = sc.nextInt(); //총 감독관
        C = sc.nextInt(); //부 감독관
        int answer = 0;
        for(int i = 0; i < N; i ++){
            arrN[i] -= B;
            answer += 1;
            if(arrN[i] <= 0) continue;
            if(arrN[i] % C == 0){
                answer += arrN[i] / C;
            } else {
                answer += arrN[i] / C + 1;
            }
        }
        System.out.println(answer);
    }
}
