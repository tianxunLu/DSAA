import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        heap h = new heap(N);
        node[] arr = new node[N];
        for (int i = 0; i < N; i++) {
            node temp = new node(in.nextLong(),i);
            arr[i] = temp;
        }
        node cur = arr[0];
        for (int i = 1; i < N; i++) {
            cur.next = arr[i];
            cur.next.prev = cur;
            cur = cur.next;
        }
        for (int i = 0; i < N; i++) {
            h.insert(arr[i]);
        }
        while(true) {
            long left  = 0;
            long right = 0;
            node temp;
            temp = h.delete();
            while (temp.isMerged)temp = h.delete();
            node qian = temp.prev;
            node hou  = temp.next;
            while (qian!=null && qian.isMerged)qian = qian.prev;
            while (hou !=null && hou.isMerged )hou  = hou.next;
            if (qian!=null) left = (temp.val^qian.val)+1;
            if (hou!=null) right = (temp.val^hou.val)+1;
            if (left == right && left == 0) {
                System.out.println(temp.val);
                break;
            }
            if (hou!=null && left<right){
                temp.val = right;
                hou.isMerged = true;
                //connect
                if (hou.next!=null){
                    hou.next.prev = temp;
                    temp.next = hou.next;
                }
            }
            if (qian!=null && left>=right){
                temp.val = left;
                qian.isMerged = true;
                //connect
                if (qian.prev!=null){
                    qian.prev.next = temp;
                    temp.prev = qian.prev;
                }
            }
            h.insert(temp);
        }
    }
}
class node{
    long val;
    int pos;
    node next;
    node prev;
    boolean isMerged = false;
    public node(long val,int pos){
        this.val = val;
        this.pos = pos;
    }
}
class heap{
    node[] heap;
    int size = 0;
    public heap(int n){
        heap = new node[n+1];
    }

    public void insert(node n){
        size++;
        heap[size] = n;
        int temp = size;
        while (temp>1 && heap[temp/2].val >heap[temp].val){
            node a = heap[temp/2];
            heap[temp/2] = heap[temp];
            heap[temp] = a;
            temp = temp/2;
        }
    }

    public void swap(int a,int b){
        node temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    public node delete(){
        node a = heap[1];
        swap(1,size);
        size--;
        int temp = 1;
        while(temp*2<=size){
            if(temp*2+1<=size){
                if (heap[temp].val<heap[temp*2].val && heap[temp].val<heap[temp*2+1].val)break;
                if(heap[temp*2].val>heap[temp*2+1].val){
                    swap(temp*2+1,temp);
                    temp = temp*2+1;
                }else {
                    swap(temp*2,temp);
                    temp = temp*2;
                }
            }else{
                if(heap[temp*2].val>heap[temp].val)break;
                swap(temp*2,temp);
                temp = temp*2;
            }
        }
        return a;
    }
}