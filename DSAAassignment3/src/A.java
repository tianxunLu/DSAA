import java.io.*;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args){
        QReader in = new QReader();
        QWriter out = new QWriter();

        while (in.hasNext()){int num = in.nextInt();
        long[] seq = new long[num];
        for (int i = 0; i < num; i++) {
            seq[i] = in.nextLong();
        }
        seq = mergeSort(seq,num);
        long sum = 0;
        for (int i = 0; i < seq.length/2; i++) {
            sum += seq[i]*seq[seq.length-1-i];
        }
        out.println(sum);
       } out.close();
    }

    public static int[] merge(int[] L,int nl,int[] R,int nr){
        int n = nl + nr;
        int[] A = new int[n];
        int i = 0,j = 0;
        for (int k = 0;k<n;k++){
            if (i<=n && (j>nr || L[i]<=R[j])){
                A[k] = L[i];i++;
            }else {
                A[k] = R[j];j++;
            }
        }
        return A;
    }

    public static long[] mergeSort(long[] seq,int n){
       digui(seq, 0, n-1);
            return seq;
    }

    private static void digui(long[] seq, int p, int r){
        if (p >= r) {
            return;
        }
        int q = p + (r-p)/2;
        digui(seq, p, q);
        digui(seq, q+1, r);
        merge(seq, p, q, r);
    }

    private static void merge(long[] seq, int p, int q, int r) {
        int i = p;
        int j = q+1;
        int k = 0;
        long[] arr = new long[r-p+1];
        while (i <= q && j <= r) {
            if (seq[i] <= seq[j]) {
                arr[k++] = seq[i++];
            } else {
                arr[k++] = seq[j++];
            }
        }

        int initial = i;
        int end = q;
        if (j <= r) {
            initial = j;
            end = r;
        }
        while (initial <= end) {
            arr[k++] = seq[initial++];
        }
        //return
        for (i = 0; i <= r-p; ++i) {
            seq[p+i] = arr[i];
        }
    }

    static class QReader {
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

    static class QWriter implements Closeable {
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

}