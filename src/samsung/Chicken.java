//package samsung;
//
//import java.util.*;
//public class Chicken
//{
//    static ArrayList<Point> houses;
//    static ArrayList<Point> chickens;
//    static boolean choosed[];
//    static int M;
//    static int INF = 987654321;
//    public static void main (String[] args)
//    {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        M = sc.nextInt();
//        houses = new ArrayList<>();
//        chickens = new ArrayList<>();
//        for(int i = 1; i <= N; i ++){
//            for(int j = 1; j <= N; j ++){
//                int temp = sc.nextInt();
//                if(temp == 1){
//                    houses.add(new Point(i, j));
//                } else if(temp == 2){
//                    chickens.add(new Point(i, j));
//                }
//            }
//        }
//        for(int i = 0; i < houses.size(); i ++){
//            for(int j = 0; j < chickens.size(); j ++){
//                int d = Math.abs(chickens.get(j).x - houses.get(i).x) + Math.abs(chickens.get(j).y - houses.get(i).y);
//                houses.get(i).distance = Math.min(houses.get(i).distance, d);
//            }
//        }
//        int answer = 0;
//        if(chickens.size() <= M){
//            for(int i = 0; i < houses.size(); i ++){
//                answer += houses.get(i).distance;
//            }
//        } else {
//            choosed = new boolean[chickens.size()];
//            answer = findMinChikenDistance(0, 0);
//        }
//        System.out.println(answer);
//    }
//    public static int findMinChikenDistance(int step, int cnt){
//        int ret = INF;
//        if(cnt == M){
//            for(int i = 0; i < houses.size(); i ++){
//                houses.get(i).distance = INF;
//                for(int j = 0; j < choosed.length; j ++){
//                    if(choosed[j]){
//                        int d = Math.abs(chickens.get(j).x - houses.get(i).x) + Math.abs(chickens.get(j).y - houses.get(i).y);
//                        houses.get(i).distance = Math.min(houses.get(i).distance, d);
//                    }
//                }
//            }
//            int sum = 0;
//            for(int i = 0; i < houses.size(); i ++){
//                System.out.print(houses.get(i).distance +" ");
//                sum += houses.get(i).distance;
//            }
//            System.out.println(Arrays.toString(choosed));
//            System.out.println();
//            return sum;
//        }
//        if(step >= chickens.size()) return INF;
//        choosed[step] = true;
//        ret = Math.min(ret, findMinChikenDistance(step + 1, cnt + 1));
//        choosed[step] = false;
//        ret = Math.min(ret, findMinChikenDistance(step + 1, cnt));
//        return ret;
//    }
//}
//
//class Point{
//    int x;
//    int y;
//    int distance = 987654321;
//    Point(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}