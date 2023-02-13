import java.util.Arrays;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        long[] b = new long[num];
        for (int i = 0; i < num; i++) {
            b[i] = input.nextLong();
        }

        long[] result = new long[num];
        b = mergeSort(b,num);
        System.out.println(b[num/3]);
        for (int i = 0; i < num/3; i++) {
            result[i*3] = b[i];
            result[i*3+1] = b[num/3+2*i];
            result[i*3+2] = b[num/3+2*i+1];
        }
        if (num%3 == 1){
            result[num-1] = b[num-1];
        }else if(num%3 == 2){
            result[num-2] = b[num-2];
            result[num-1] = b[num-1];
        }
        for (int i = 0; i < num; i++) {
            System.out.print(result[i]+" ");
        }
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
}
