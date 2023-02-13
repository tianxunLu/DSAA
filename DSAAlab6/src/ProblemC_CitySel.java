import java.util.ArrayList;
import java.util.Scanner;

public class ProblemC_CitySel {
    static nodeC[] tree;
    static int[] arrayA, arrayB, arrayNA;
    static nodeC Root;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        tree = new nodeC[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new nodeC();
        }
        for (int i = 0; i < n-1; i++) {
            int u = input.nextInt()-1;
            int v = input.nextInt()-1;
            tree[u].children.add(tree[v]);
            tree[v].children.add(tree[u]);
        }
        int m = input.nextInt();
        arrayA = new int[m];
        arrayB = new int[m];
        arrayNA = new int[m];
        for (int i = 0; i < m; i++) {
            arrayA[i] = input.nextInt()-1;
            arrayB[i] = input.nextInt()-1;
            arrayNA[i] = input.nextInt();
        }
        Root = tree[arrayA[0]];
        Root.visit = true;
        preprocess(Root);

        int l = 0, r = n;
        while (l <= r){
            int mid = (l + r)/2;
            if (check(mid)) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        if (l > n) System.out.println(-1);
        else System.out.println(l);
    }
    public static void preprocess(nodeC root){
        if (root == null) return;
        for (int i = 0; i < root.children.size(); i++) {
            nodeC temp = root.children.get(i);
            if (!temp.visit){
                temp.visit = true;
                preprocess(temp);
                root.val += temp.val;
            }
            else root.children.remove(i--);
        }
    }

    public static boolean check(int k){
        for (int i = 0; i < tree.length; i++) {
            tree[i].min = 0;
            tree[i].max = tree[i].val;
        }
        for (int i = 0; i < arrayA.length; i++) {
            nodeC A = tree[arrayA[i]];
            nodeC B = tree[arrayB[i]];
            int nA = arrayNA[i];

            if (B.children.contains(A)) A.min = nA;
            else if (A.children.contains(B)) B.max = k-nA;
            else System.out.println("Error!");
        }

        if (merge(Root)) {
            return k >= Root.min && k <= Root.max;
        }
        return false;
    }

    public static boolean merge(nodeC root){
        long sumMin = 0, sumMax = 0;
        for (int i = 0; i < root.children.size(); i++) {
            nodeC temp = root.children.get(i);
            if (!merge(temp)) return false;
            sumMin += temp.min;
            sumMax += temp.max;
        }
        sumMax++;
        root.min = Math.max(sumMin, root.min);
        root.max = Math.min(sumMax, root.max);
        return root.min <= root.max;
    }
}

class nodeC{
    int val = 1;
    long min, max;
    boolean visit = false;
    ArrayList<nodeC> children = new ArrayList<>();
}
