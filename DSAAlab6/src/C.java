import java.util.ArrayList;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        node_C[] tree = new node_C[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node_C();
        }
        for (int i = 0; i < n-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            tree[a].child.add(tree[b]);
            tree[b].child.add(tree[a]);
        }
        tree[0].isVisited = true;
        dfs(tree[0]);
        int m = in.nextInt();
        int[][] constrain = new int[m][3];
        for (int i = 0; i < m; i++) {
            constrain[i][0] = in.nextInt()-1;
            constrain[i][1] = in.nextInt()-1;
            constrain[i][2] = in.nextInt();
        }
        
        int left = 0,right = n;
        while (left<=right){
            int mid = (left+right)/2;
            if (check(mid,constrain,tree)){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        if (left>n) {
            System.out.println(-1);
        }
        else {
            System.out.println(left);
        }
    }
    static boolean check(int k,int[][] constrain,node_C[] tree){
        for (int i = 0; i < tree.length; i++) {
            tree[i].left = 0;
            tree[i].right = tree[i].val;
        }
        for (int i = 0; i < constrain.length; i++) {
            if (tree[constrain[i][0]].father != tree[constrain[i][1]]){
                tree[constrain[i][1]].right = Math.min(k-constrain[i][2],tree[constrain[i][1]].right);
                if(tree[constrain[i][1]].right<tree[constrain[i][1]].left)return false;
            }else {
                tree[constrain[i][0]].left = Math.max(constrain[i][2],tree[constrain[i][0]].left);
                if(tree[constrain[i][0]].right<tree[constrain[i][0]].left)return false;
            }
        }
        if (add(tree[0])) return tree[0].left<=k && tree[0].right>=k;
        return false;
    }

    static boolean add(node_C root){
        for (int i = 0; i < root.child.size(); i++) {
            if (!add(root.child.get(i))) return false;
            if (!merge(root)) return false;
        }
        return true;
    }
    
    static boolean merge(node_C leaf){
        int max=0,min=0;
        for (int i = 0; i < leaf.child.size(); i++) {
            max+=leaf.child.get(i).right;
            min+=leaf.child.get(i).left;
        }
        leaf.left = Math.max(leaf.left,min);
        leaf.right = Math.min(leaf.right,max+1);

        return leaf.left <= leaf.right;
    }
    static void dfs(node_C temp){
        for (int i = 0; i < temp.child.size(); i++) {
            if (!temp.child.get(i).isVisited){
                temp.child.get(i).isVisited = true;
                temp.child.get(i).father = temp;
                dfs(temp.child.get(i));
                temp.val+=temp.child.get(i).val;
            }
            else temp.child.remove(i--);
        }
    }
}
class node_C {
    int val=1;
    int left;
    int right;
    node_C father;
    ArrayList<node_C> child = new ArrayList<>();
    boolean isVisited;
}
