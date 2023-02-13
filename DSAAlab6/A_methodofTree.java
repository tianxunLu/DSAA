import java.util.ArrayList;
import java.util.Scanner;
public class A_methodofTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num = in.nextInt();
        int cnt = 0;
        node[] tree = new node[n];
        //initialization
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
        }
        //create a tree
        for (int i = 0; i < n-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            //if we know a is b's father
            //tree[a].child.add(tree[b]);
            //but if we don't know
            int w = in.nextInt();
            //there is an edge between a,b
            /*
            dfs depth first
            bfs broad first queue
            no matter bfs or dfs, the father node will be visited earlier than child*/
            //a two-way link list
            tree[a].child.add(tree[b]);
            tree[a].len.add(w);
            tree[b].child.add(tree[a]);
            tree[b].len.add(w);
        }
        //operate
        //traverse
        node[] q = new node[n];
        int front = 0,rear = 0;
        q[rear++] = tree[0];//put the root in
        tree[0].isVisited = true;
        tree[0].path = 0;
        //when the queue is empty the traverse ends
        while (rear!=front){
            node temp = q[front++];
            for (int i = 0; i < temp.child.size(); i++) {
                if (temp.child.get(i).isVisited = false){
                    temp.child.get(i).path = temp.path + temp.len.get(i);
                    if (temp.child.get(i).path == num && temp.child.get(i).child.size()==1){
                        cnt++;
                    }
                    q[rear++] = temp.child.get(i);
                    temp.child.get(i).isVisited = true;
                }
            }
        }
        dfs(tree[0]);
        System.out.println(cnt);
    }

    static void dfs(node temp){
        //binary tree dfs(zuo),
        for (int i = 0; i < temp.child.size(); i++) {
            if (temp.child.get(i).isVisited == false){
                temp.child.get(i).isVisited = true;
                temp.child.get(i).path = temp.path + temp.len.get(i);
//                if (temp.child.get(i).path == num && temp.child.get(i).child.size()==1){
//                    cnt++;
//                }
                dfs(temp.child.get(i));
            }
        }
    }
}
//class Node {
//    int path;
//    boolean isvisited;
//    ArrayList<Node> child = new ArrayList<>();
//    ArrayList<Integer> length = new ArrayList<>();
//}
