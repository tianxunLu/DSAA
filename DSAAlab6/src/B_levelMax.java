import java.util.ArrayList;
import java.util.Scanner;

public class B_levelMax {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        node[] tree = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
        }
        for (int i = 0; i < n-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            tree[in.nextInt()-1].hasGiant = true;
        }
        //
        int max = 0;
        tree[0].isVisited = true;
        tree[0].level = 0;
        for (int i = 0; i < tree[0].child.size(); i++) {
            node[] q = new node[n];
            int front = 0,rear = 0;
            q[rear++] = tree[0].child.get(i);//put the root in
            tree[0].child.get(i).isVisited = true;
            tree[0].child.get(i).level = 0;
            int[] level = new int[m];
            int levelID = 0;
            int checkMax = 0;
            //
            while (rear!=front){
                node temp = q[front++];
                for (int j = 0; j < temp.child.size(); j++) {
                    if (!temp.child.get(j).isVisited){
                        temp.child.get(j).isVisited = true;
                        q[rear++] = temp.child.get(j);
                        temp.child.get(j).level = temp.level+1;
                        if (temp.child.get(j).hasGiant){
                            level[levelID++] = temp.child.get(j).level;
                        }
                    }

                }
            }
            //
            for (int j = 0; j < m; j++) {
                if (level[j]>checkMax){
                    checkMax = level[j];
                }else if(level[j]!=0) {
                    checkMax++;
                }
            }
            if (checkMax>max){
                max = checkMax;
            }
        }
        System.out.println(max+1);
    }
}

class node {
    int level;
    boolean isVisited;
    boolean hasGiant;
    ArrayList<node> child = new ArrayList<>();
}