package academy;

import java.util.*;

public class SimulationOfElementDisappearance {
    static int dy[] = {1, -1, 0, 0};
    static int dx[] = { 0, 0, -1, 1};
    final static int TEN_THOUSAND = 10000;
    static ArrayList<Element> elements;
    static HashMap<Integer, Integer> visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t ++){
            int N = sc.nextInt();
            elements = new ArrayList<>();
           for(int i = 0; i < N; i ++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                x += 1000;
                x *= 2;
                y += 1000;
                y *= 2;
                int dir = sc.nextInt();
                int K = sc.nextInt();
                elements.add(new Element(x, y, dir, K));
            }
            int answer = 0;
            visited = new HashMap<>(); // key = 10000 * y + x   value = count
            for(int time = 1; time <= 4002; time ++){
                visited.clear();
                for(int a = 0; a < elements.size(); a ++){
                        Element e = elements.get(a);
                        int nextX = e.x + dx[e.dir];
                        int nextY = e.y + dy[e.dir];
                        if(nextX < 0 || nextX > 4000 || nextY < 0 || nextY > 4000){
                            elements.remove(a);
                            --a;
                            continue;
                        }
                        elements.get(a).x = nextX;
                        elements.get(a).y = nextY;
                        int key = nextY * TEN_THOUSAND + nextX;
                        if(visited.containsKey(key)){
                            visited.put(key, visited.get(key) + 1);
                        }
                        else {
                            visited.put(nextY * TEN_THOUSAND + nextX, 1);
                        }
                    }
                  if(visited.size() != elements.size()){
                    for(int a = 0; a < elements.size(); a ++){
                        Element e = elements.get(a);
                        int key = e.y * TEN_THOUSAND + e.x;
                        int temp = visited.get(key);
                        if(temp >= 2){
                            answer += e.energy;
                            elements.remove(a);
                            --a;
                        }
                    }
                }
                if(elements.size() <= 1) break;
            }
            System.out.println("#" + t + " " + answer);
        }

    }
}

class Element{
    int x;
    int y;
    int dir;
    int energy;
    Element(int x, int y, int dir, int energy){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.energy = energy;
    }
}