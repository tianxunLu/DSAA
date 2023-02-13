import java.util.Arrays;
import java.util.Scanner;

public class A_haffuman {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        int[] A = new int[N];
        int[] B = new int[M];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
        }
        for (int i = 0; i < M; i++) {
            B[i] = in.nextInt();
        }
        A = merge_sort(A,N);

        heap h = new heap(M);
        for (int j = 0; j < M; j++) {
            node temp = new node((long) A[0]*B[j],0,j);
            h.insert(temp);
        }
        for (int i = 0; i < K; i++) {
            node cur = h.heap[1];
            System.out.print(cur.val+" ");
            h.delete();
            if (cur.Ai<N-1){
                cur.Ai++;
                node temp = new node((long)A[cur.Ai]*B[cur.Bi],cur.Ai,cur.Bi);
                h.insert(temp);
            }
        }
    }

    public static int[] merge_sort(int[] A,int n){
        if (n>1) {
            int p = n / 2;
            int[] B = Arrays.copyOfRange(A, 0, p);//bqd
            int[] C = Arrays.copyOfRange(A, p, A.length);
            B = merge_sort(B, p);
            C = merge_sort(C, n - p);
            A = merge(B, p, C, n - p);
        }
        return A;
    }
    public static int[] merge(int[] L, int nl, int[] R, int nr){
        int n = nl+nr;
        int i = 0,j = 0;
        int[] A = new int[n];
        for (int k = 0; k < n; k++) {
            if (i<nl && (j>=nr || L[i]<=R[j])){
                A[k] = L[i];i++;
            }else {
                A[k] = R[j];j++;
            }
        }
        return A;
    }
}

class node{
    int Ai,Bi;
    long val;
    public node(long val,int Ai,int Bi){
        this.val = val;
        this.Ai = Ai;
        this.Bi = Bi;
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
    public void delete(){
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
    }
}