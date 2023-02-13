import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class C_NaiveProblem {
    public static Integer k;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//nodes
        int m = in.nextInt();//edges
        k = in.nextInt();//color
        int c = in.nextInt();//differences
        node_C[] arr = new node_C[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new node_C();
            arr[i].color = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            arr[a].linked.add(arr[b]);
            arr[b].linked.add(arr[a]);
        }
        node_C[] q = new node_C[n];
        for (int i = 0; i < k; i++) {
            int front = 0,rear = 0;
            for (int j = 0; j < n; j++) {
                arr[j].isVisited = false;
                if (arr[j].color-1==i){
                    q[rear++] = arr[j];//put the root in
                    arr[j].isVisited = true;
                }
            }
            //when the queue is empty the traverse ends
            while (rear!=front){
                node_C temp = q[front++];
                for (int j = 0; j < temp.linked.size(); j++) {
                    if (!temp.linked.get(j).isVisited){
                        temp.linked.get(j).color_arr[i] = temp.color_arr[i]+1;
                        q[rear++] = temp.linked.get(j);
                        temp.linked.get(j).isVisited = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.sort(arr[i].color_arr);
            int result = 0;
            for (int j = 0; j < c; j++) {
                result+=arr[i].color_arr[j];
            }
            System.out.print(result+" ");
        }
    }
    static class node_C{
        int color;
        boolean isVisited;
        ArrayList<node_C> linked = new ArrayList<>();
        int[] color_arr = new int[k];
    }
}

