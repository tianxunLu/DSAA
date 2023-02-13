import java.util.Arrays;
import java.util.Scanner;

public class B {
    static long count = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            //initialize
            int length = input.nextInt();
            int[] sortArray = new int[length];
            for (int j = 0; j < length; j++) {
                sortArray[j] = input.nextInt();
            }
            sortArray = merge_sort(sortArray,length);
            System.out.println(count);
            count = 0;
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
                count += nl-i;
            }
        }
        return A;
    }
}
