import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node[] arr_A = new node[n];
        //5   5 4 3 2 1
        for (int i = 0; i < n; i++) {
            node temp = new node(in.nextInt());
            arr_A[i] = temp;
        }
        node[] arr = Arrays.copyOfRange(merge_sort(arr_A,n),0,n);
        node cur = arr[0];
        for (int i = 1; i < n; i++) {
            cur.next = arr[i];
            cur.next.prev = cur;
            cur = cur.next;
        }
        for (int i = 0; i < n-1; i++) {
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (arr_A[i].prev!=null){
                left=Math.abs(arr_A[i].coe-arr_A[i].prev.coe);
                if (arr_A[i].next!=null){
                    arr_A[i].prev.next = arr_A[i].next;
                    arr_A[i].next.prev = arr_A[i].prev;
                }else {
                    arr_A[i].prev.next = null;
                }
            }
            if (arr_A[i].next!=null){
                right=Math.abs(arr_A[i].coe-arr_A[i].next.coe);
                if (arr_A[i].prev!=null){
                    arr_A[i].prev.next = arr_A[i].next;
                    arr_A[i].next.prev = arr_A[i].prev;
                }else {
                    arr_A[i].next.prev = null;
                }
            }
            out.print(Math.min(left,right)+" ");
        }
        out.close();
    }
    public static node[] merge_sort(node[] A,int n){
        if (n>1) {
            int p = n / 2;
            node[] B = Arrays.copyOfRange(A, 0, p);
            node[] C = Arrays.copyOfRange(A, p, A.length);
            B = merge_sort(B, p);
            C = merge_sort(C, n - p);
            A = merge(B, p, C, n - p);
        }
        return A;
    }

    public static node[] merge(node[] L, int nl, node[] R, int nr){
        int n = nl+nr;
        int i = 0,j = 0;
        node[] A = new node[n];
        for (int k = 0; k < n; k++) {
            node temp = new node(0);
            A[k] = temp;
        }
        for (int k = 0; k < n; k++) {
            if (i<nl && (j>=nr || L[i].coe<=R[j].coe)){
                A[k] = L[i];i++;
            }else {
                A[k] = R[j];j++;
            }
        }
        return A;
    }
}
class node{
    int coe;
    node next;
    node prev;
    public node(int coe) {
        this.coe = coe;
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}