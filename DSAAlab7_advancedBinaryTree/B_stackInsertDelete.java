import java.util.Scanner;

public class B_stackInsertDelete {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            heap h = new heap(n);
            for (int j = 0; j < n; j++) {
                h.insert(in.nextInt());
            }
            int result = 0;
            while(h.size!=1){
                int a = h.heap[1];
                h.delete();
                int b = h.heap[1];
                h.delete();
                result+=a+b;
                h.insert(a+b);
            }
            System.out.println(result);
        }
    }
}

class heap{
    int[] heap;
    int size = 0;
    public heap(int n){
        heap = new int[n+1];
    }

    public void insert(int x){
        size++;
        heap[size] = x;
        int temp = size;
        while (temp>1 && heap[temp/2]>heap[temp]){
            int t = heap[temp/2];
            heap[temp/2] = heap[temp];
            heap[temp] = t;
            temp = temp/2;
        }
    }

    public void delete(){
        heap[1] = heap[size];
        heap[size] = 0;
        size--;
        int temp = 1;
        while(temp*2<=size){
            if(temp*2+1<=size){
                if (heap[temp]<heap[temp*2] && heap[temp]<heap[temp*2+1])break;
                if(heap[temp*2]>heap[temp*2+1]){
                    int a = heap[temp*2+1];
                    heap[temp*2+1] = heap[temp];
                    heap[temp] = a;
                    temp = temp*2+1;
                }else {
                    int b = heap[temp*2];
                    heap[temp*2] = heap[temp];
                    heap[temp] = b;
                    temp = temp*2;
                }
            }else{
                if(heap[temp*2]>heap[temp])break;
                int c = heap[temp*2];
                heap[temp*2] = heap[temp];
                heap[temp] = c;
                temp = temp*2;
            }
        }
    }
}
