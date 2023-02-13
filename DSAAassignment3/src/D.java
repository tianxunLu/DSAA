import java.util.Arrays;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int mostTime = input.nextInt();
        int Kcost = input.nextInt();
        long[] arr = new long[num];
        long zuihou = 0;
        for (int i = 0; i < num; i++) {
            arr[i] = input.nextLong();
            zuihou += arr[i];
        }
        //no same arr[i]


        int[] addValue = new int[num*9];//need to consider it longer
        int count = 0;
        for (int i = 0; i < num; i++) { //judge every number

            int n = 0;//positive or negative
            if (arr[i]>=0){
                n = 1;
            }else n = -1;
            arr[i] = Math.abs(arr[i]);

            String number = String.valueOf(arr[i]);
            int length = number.length();
            int[] countA = new int[length];
            for (int j = 0; j < length; j++) {
                countA[j] = Integer.parseInt(number.substring(j, j + 1));
            }
            int[] countB = Arrays.copyOfRange(countA,0,countA.length); //would countA chang?
            countB = merge_sort(countB,length);

            //judge every weishu
            for (int j = 0; j < number.length(); j++) {
                int element = 0;

                if (n == 1){
                    int shu = countB[length-j-1];
                    int index = findIndex(shu,countA);
                    element = (int) ((countB[length-j-1]-countA[j])*(Math.pow(10,length-j-1)-Math.pow(10,length-index-1)) - Kcost);
                    int trans = countA[j];
                    countA[j] = countA[index];
                    countA[index] = trans;
                }else {
                    int fushu = countB[j];
                    int index = findIndex(fushu,countA);
                    element = (int) ((countB[j]-countA[j])*(Math.pow(10,length-j-1)-Math.pow(10,length-index-1)) - Kcost);
                    int trans = countA[j];
                    countA[j] = countA[index];
                    countA[index] = trans;
                }
                if (element > 0){
                    addValue[count] = element;
                    count++;
                }
            }
        }
        merge_sort(addValue,addValue.length);
        if (addValue.length < mostTime){
            mostTime = addValue.length;
        }
        long sum = 0;
        for (int i = addValue.length-1; i >= 0; i--) {
            sum += addValue[i];
        }
        System.out.println(sum+zuihou);
    }

    public static int findIndex(int shu,int[] countA){
        //current max index
        int index = 0;
        for (; index < countA.length; index++) {
            if (countA[index] == shu){
                break;
            }
        }
        return index;
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

    public static int[] merge(int[] L,int nl,int[] R,int nr){
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
