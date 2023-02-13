import java.util.ArrayList;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        node_D[] tree = new node_D[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node_D();
            tree[i].maxChild = new node_D();
        }
        for (int i = 0; i < n-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }
        int max_id = 0;
        for (int i = 0; i < n; i++) {
            tree[i].pi = in.nextInt();
            if (tree[i].pi>tree[max_id].pi){
                max_id = i;
            }
        }
        //delete father node
        tree[max_id].isVisited = true;
        dfs(tree[max_id]);
        node_D root = tree[max_id];
        boolean tf = false;
        int set = root.pi;

        if (root.child.size()==1){
            tf = true;
            root.ei = root.pi;
        }
        while (root.child.size()==1){
            root = root.child.get(0);
        }
        //change the ei
        if (tf){
            root.maxChild.pi = set;
        }else {
            root.maxChild.pi = set;
            int max = 0;
            int index = 0;
            for (int i = 0; i < root.child.size(); i++) {
                if (root.child.get(i).pi> max && root.child.get(i)!= root.maxChild){
                    max = root.child.get(i).pi;
                    index = i;
                }
            }
            root.child.get(index).pi = set;
        }
        //go through downward
        for (int i = 0; i < root.child.size(); i++) {
            downdfs(root.child.get(i));
        }
        long result = 0;
        for (int i = 0; i < n; i++) {
            result+=tree[i].ei;
        }
        System.out.println(result);
    }

    static void downdfs(node_D root){
        if (root.child.size()==0) {
            root.ei = root.pi;
            return;
        }
        for (int i = 0; i < root.child.size(); i++) {
            root.maxChild.pi = root.pi;
            downdfs(root.child.get(i));
        }
    }

    static void dfs(node_D root){
        if (root==null)return;
        for (int i = 0; i < root.child.size(); i++) {
            node_D temp = root.child.get(i);
            if (!temp.isVisited){
                temp.isVisited = true;
                dfs(temp);
                if (root.pi<temp.pi) root.pi = temp.pi;
                if (root.maxChild.pi<temp.pi) root.maxChild = temp;
            }
            else root.child.remove(i--);
        }
    }
}
class node_D {
    int pi;
    int ei;
    node_D maxChild;
    ArrayList<node_D> child = new ArrayList<>();
    boolean isVisited;
}