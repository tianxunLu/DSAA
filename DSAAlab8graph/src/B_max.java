import java.util.ArrayList;
import java.util.Scanner;

public class B_max {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        node[] matrix = new node[n];
        for (int i = 0; i < n; i++) {
            node temp = new node();
            temp.val = i+1;
            matrix[i] = temp;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            matrix[b].child.add(matrix[a]);
        }
        for (int i = 0; i < n; i++) {
            matrix[n-i-1].isVisited = true;
            dfs(matrix[n-i-1]);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(matrix[i].val+" ");
        }
    }

    public static void dfs(node temp){
        for (int i = 0; i < temp.child.size(); i++) {
            if (!temp.child.get(i).isVisited){
                temp.child.get(i).isVisited = true;
                temp.child.get(i).val = temp.val;
                dfs(temp.child.get(i));
            }
        }
    }
}

class node{
    int val;
    boolean isVisited;
    ArrayList<node> child = new ArrayList<>();
}
