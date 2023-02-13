import java.util.Scanner;

public class G_completeBinaryTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node_G[] tree = new node_G[n];
            for (int j = 0; j < n; j++) {
                tree[j] = new node_G();
            }
            for (int j = 0; j < n; j++) {
                int a = in.nextInt()-1;
                int b = in.nextInt()-1;
                if (a!=-1) {
                    tree[j].left = tree[a];
                    tree[a].parent = tree[j];
                }
                if (b!=-1) {
                    tree[j].right = tree[b];
                    tree[b].parent = tree[j];
                }
            }
            node_G root = tree[0];
            while (root.parent != null){
                root = root.parent;
            }
            if (isCompleteTree(root,n)) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    public static boolean isCompleteTree(node_G root,int n){
        if (root == null) {
            return true;
        }
        node_G[] queue = new node_G[n];
        int front = 0,rear = 0;
        boolean leaf = false;
        queue[rear++] = root;
        while (front!=rear) {
            node_G cur = queue[front++];
            node_G l = cur.left;
            node_G r = cur.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)){
                return false;
            }
            if (l != null) queue[rear++] = l;
            if (r != null) queue[rear++] = r;
            if (l == null || r == null) leaf = true;
        }
        return true;
    }
}
class node_G {
    node_G left;
    node_G right;
    node_G parent;
}
