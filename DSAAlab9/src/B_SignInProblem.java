import java.util.ArrayList;
import java.util.Scanner;

public class B_SignInProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        node_B[] arr = new node_B[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new node_B();
        }
        node_B u = new node_B();
        int minWeight = 1000000001;
        long sumWeight = 0;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            arr[a].linked.add(arr[b]);
            arr[b].linked.add(arr[a]);
            int w = in.nextInt();
            if (w<minWeight){
                minWeight = w;
                u = arr[a];
            }
            if (w>0){
                sumWeight += w;
            }
            arr[a].weight.add(w);
            arr[b].weight.add(w);
        }
        u.dist = minWeight;
        heap_B h = new heap_B(n);
        h.insert(u);
        long sumMin = 0;
        while (h.size!=0){
            node_B temp = h.delete();
            temp.isVisited = true;
            for (int i = 0; i < temp.linked.size(); i++) {
                node_B child = temp.linked.get(i);
                if (!child.isVisited && temp.weight.get(i) < child.dist) {
                    if (child.index>0) {
                        child.dist = temp.weight.get(i);
                        h.liftUp(child.index);
                    } else {
                        child.dist = temp.weight.get(i);
                        h.insert(child);
                    }
                }
            }
            if (temp.dist>0){
                sumMin+=temp.dist;
            }
        }
        if (minWeight>0){
            sumMin = sumMin-minWeight;
        }
        System.out.println(sumWeight-sumMin);
    }
}
class node_B{
    boolean isVisited;
    int dist = 1000000001;
    int index;
    ArrayList<node_B> linked = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();
}
class heap_B  {
    node_B[] heap;
    int size = 0;
    public heap_B(int n){
        heap = new node_B[n+1];
    }

    public void insert(node_B n){
        heap[++size] = n;
        n.index = size;
        liftUp(size);
    }

    public void liftUp(int n){
        int temp = n;
        while (temp>1 && heap[temp/2].dist >heap[temp].dist){
            swap(temp/2,temp);
            temp = temp/2;
        }
    }

    public void swap(int a,int b){
        heap[a].index = b;
        heap[b].index = a;
        node_B temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    public node_B delete(){
        swap(1,size);
        node_B r = heap[size--];
        int temp = 1;
        while(temp*2<=size){
            if(temp*2+1<=size){
                if (heap[temp].dist<heap[temp*2].dist && heap[temp].dist<heap[temp*2+1].dist)break;
                if(heap[temp*2].dist>heap[temp*2+1].dist){
                    swap(temp*2+1,temp);
                    temp = temp*2+1;
                }else {
                    swap(temp*2,temp);
                    temp = temp*2;
                }
            }else{
                if(heap[temp*2].dist>heap[temp].dist)break;
                swap(temp*2,temp);
                temp = temp*2;
            }
        }
        return r;
    }
}
