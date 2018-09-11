package kakao;

/*
 카카오프렌즈 컬러링북

출판사의 편집자인 어피치는 네오에게 컬러링북에 들어갈 원하를 그려달라고 부탁했다.

어피치는 영역이 많으면 색칠 하기가 까다로워 어려워진다는 사실을 발견했다.
그림의 난이도를 영역의 수로 정희하였다.

그림에 몇 개의 영역이 있는지와 가장 큰 영역의 넓이는 얼마인지 계산하라.


예제 입력
6 4
1 1 1 0
1 2 2 0
1 0 0 1
0 0 0 1
0 0 0 3
0 0 0 3

출력
[4, 5]
 */
import java.util.Scanner;
import java.util.ArrayList;
public class ColoringBook {
    static int N;
    static int M;
    static int[][] picture;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        picture = new int[M][N];
        for(int i = 0; i < M; i ++){
            for(int j = 0; j < N; j ++){
                picture[i][j] = sc.nextInt();
            }
        }

        boolean[][] counted = new boolean[M][N];
        int cntArea = 0;
        int maxArea = 0;
        for(int i = 0; i < M; i ++){
            for(int j = 0; j < N; j ++){
                if(counted[i][j] || picture[i][j] == 0) continue;
                cntArea += 1;
                ArrayList<Point> q = new ArrayList<Point>();
                q.add(new Point(i, j));
                int tempArea = 1;
                counted[i][j] = true;
                while(!q.isEmpty()){
                    Point p = q.remove(0);
                    for(int d = 0; d < 4; d ++){
                        if(p.x + dx[d] >= 0 && p.x + dx[d] < M &&
                                p.y + dy[d] >= 0 && p.y + dy[d] < N &&
                                !counted[p.x + dx[d]][p.y + dy[d]] &&
                                picture[i][j] == picture[p.x + dx[d]][p.y + dy[d]]){
                            tempArea += 1;
                            q.add(new Point(p.x + dx[d], p.y + dy[d]));
                            counted[p.x + dx[d]][p.y + dy[d]] = true;
                        }
                    }
                }
                maxArea = maxArea > tempArea ? maxArea : tempArea;
            }
        }
        System.out.println(cntArea + " " + maxArea);
    }
}
class Point {
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}