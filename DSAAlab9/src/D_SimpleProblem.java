import java.util.ArrayList;
import java.util.Scanner;

public class D_SimpleProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int S = in.nextInt();

        node_D[] vertices = new node_D[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new node_D();
            vertices[i].index = i;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            vertices[a].child.add(vertices[b]);
            //reverse
            vertices[b].reverse.add(vertices[a]);
        }
        //dfs gives sequence
        node_D[] stack = new node_D[n];
        int top = 0;
        node_D[] arr = new node_D[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (!vertices[i].isVisited){
                stack[top++] = vertices[i];
                vertices[i].isVisited = true;
                while (top != 0){
                    node_D temp = stack[top-1];
                    boolean flag = false;
                    for (int j = 0; j < temp.reverse.size(); j++) {
                        node_D child = temp.reverse.get(j);
                        if (!child.isVisited){
                            stack[top++] = child;
                            child.isVisited = true;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag){
                        arr[index++] = temp;
                        top--;
                    }
                }
            }
        }
        //dfs gives scc
        int scc = 0;
        node_D[] STACK = new node_D[n];
        int TOP = 0;
        for (int i = 0; i < n; i++) {
            if (!arr[n-i-1].isRed){
                arr[n-1-i].isRed = true;
                STACK[TOP++] = arr[n-1-i];
                arr[n-1-i].scc = scc++;
                while (TOP != 0){
                    node_D temp = STACK[TOP-1];
                    boolean flag = false;
                    for (int j = 0; j < temp.child.size(); j++) {
                        node_D child = temp.child.get(j);
                        if (!child.isRed){
                            STACK[TOP++] = child;
                            child.isRed = true;
                            child.scc = temp.scc;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag){
                        TOP--;
                    }
                }
            }
        }
        //in degree is 0
        int[] scc_arr = new int[scc];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < vertices[i].child.size(); j++) {
                if (vertices[i].scc!=vertices[i].child.get(j).scc){
                    scc_arr[vertices[i].child.get(j).scc]++;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < scc; i++) {
            if (scc_arr[i]==0) result++;
        }
        if (scc_arr[vertices[S-1].scc]==0){
            System.out.println(result-1);
        }else System.out.println(result);
    }
//    public static void dfs(node_D temp){
//        if (temp == null) return;
//        for (int i = 0; i < temp.reverse.size(); i++) {
//            if (!temp.reverse.get(i).isVisited){
//                temp.reverse.get(i).isVisited = true;
//                arr[index++] = temp.reverse.get(i).index;
//                dfs(temp.reverse.get(i));
//            }
//        }
//
//    }
//    public static void dfs2(node_D root){
//        if (root == null) return;
//        for (int i = 0; i < root.child.size(); i++) {
//            if (!root.child.get(i).isRed){
//                root.child.get(i).isRed = true;
//                root.child.get(i).scc = root.scc;
//                dfs2(root.child.get(i));
//            }
//        }
//    }
}
class node_D{
    int index;
    int scc;
    boolean isRed;
    boolean isVisited;
    ArrayList<node_D> child = new ArrayList<>();
    ArrayList<node_D> reverse = new ArrayList<>();
}