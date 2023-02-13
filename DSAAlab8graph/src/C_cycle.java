import java.util.ArrayList;
import java.util.Scanner;

public class C_cycle {
    static int bian;
    static int dian;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        node_D[] arr = new node_D[n];
        boolean result = true;
        for (int i = 0; i < n; i++) {
            node_D temp = new node_D();
            arr[i] = temp;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
                arr[b].child.add(arr[a]);
                arr[a].child.add(arr[b]);
        }
        for (int i = 0; i < n; i++) {
            if (!arr[i].isUsed){
                arr[i].isUsed = true;
                dfs(arr[i]);
                if (!(dian> bian/2))result = false;
                bian = 0;
                dian = 0;
//                if (!bfs(arr[i],arr))result = false;
            }
        }
        if (result) {
            System.out.println("Good");
        }else {
            System.out.println("Bad");
        }
    }

    public static void dfs(node_D temp){
        dian++;
        bian+=temp.child.size();
        for (int i = 0; i < temp.child.size(); i++) {
            if (!temp.child.get(i).isUsed){
                temp.child.get(i).isUsed = true;
                dfs(temp.child.get(i));
            }
        }
    }
}

class node_D{
    boolean isUsed;
    ArrayList<node_D> child = new ArrayList<>();
}
