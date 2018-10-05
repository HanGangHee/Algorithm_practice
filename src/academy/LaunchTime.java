package academy;


import java.util.*;

/*
계단 입구까지 이동 시간
- 사람이 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간

계단을 내려가는 시간
계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간이다
계단 위에는 동시에 최대 3명까짐나 올라가 있을 수 있다.

 */
public class LaunchTime {
    static int N;
    static int[][] rooms;
    static ArrayList<Person> persons;
    static ArrayList<Stairs> stairs;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            N = sc.nextInt();
            rooms = new int[N][N];
            persons = new ArrayList<>();
            stairs = new ArrayList<>();
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j++){
                    rooms[i][j] = sc.nextInt();
                    if(rooms[i][j] == 1){
                        persons.add(new Person(i, j));
                    } else if(rooms[i][j] > 1){
                        stairs.add(new Stairs(i, j, rooms[i][j]));
                    }
                }
            }
            int answer = Integer.MAX_VALUE;
            for(int i = 0; i < (1 << persons.size()); i ++){
                ArrayList<Integer> stair1 = new ArrayList<>();
                ArrayList<Integer> stair2 = new ArrayList<>();

                for(int j = 0; j < persons.size(); j ++){
                    if((i & (1 << j)) != 0){
                        Stairs s = stairs.get(0);
                        stair1.add(Math.abs(s.x - persons.get(j).x) + Math.abs(s.x - persons.get(j).y) + s.length);
                    } else {
                        Stairs s = stairs.get(1);
                        stair2.add(Math.abs(s.x - persons.get(j).x) + Math.abs(s.x - persons.get(j).y) + s.length);
                    }
                }
                Collections.sort(stair1);
                Collections.sort(stair2);

//                int time = Math.max(stair1Time, stair2Time);
//                answer = Math.min(answer, time);
            }
        }
    }
}

class Person{
    int x;
    int y;
    Person(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Stairs{
    int x;
    int y;
    int length;
    int personCnt = 0;
    Stairs(int x, int y, int length){
        this.x = x;
        this.y = y;
        this.length = length;
    }
}
