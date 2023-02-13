import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sequenceLength = input.nextInt();
        int rangeNum = input.nextInt();
        long[] sequence = new long[sequenceLength];
        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = input.nextLong();
        }

        for (int i = 0; i < rangeNum; i++) {
            long left = input.nextLong();
            long right = input.nextLong();
            int num = 0;
            if (left >= right || left > sequence[sequenceLength - 1] || right < sequence[0]) {
                System.out.println("NO");
            } else {
                num += l(sequence, left) - r(sequence, right) + 1;
                if (num == 0) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES " + num);
                }
            }
        }
    }


    //zuo
    public static int r(long[] a,long x) {
        int zuo = 0;
        int you = a.length - 1;
        while (zuo < you){
            int mid = (zuo + you )/2;
            if (a[mid] >= x){

                you = mid;
            }else zuo = mid+1;
        }
        if (a[you] == x){
            return you;
        }else{
            return zuo;
        }
    }

    public static int binary(long[] a,long x){
        int zuo = 0;
        int you = a.length - 1;
        while (zuo < you){
            int mid = (zuo + you)/2;
            if (a[mid] <= x){
                if (a[mid+1] > x){
                    return mid+1;
                }else{
                    zuo = mid+1;
                }
            }else{
                you = mid-1;
            }
        }
        return you;
    }

    //you
    public static int l(long[] a,long x) {
        int zuo = 0;
        int you = a.length - 1;
        while (zuo < you){
            int mid = (zuo + you)/2 + 1;
            if (a[mid] <= x){
                zuo = mid;
            }else {
                you = mid - 1;
            }
        }
        if (a[you] == x){
            return you;
        }else{
            return zuo;
        }
    }
}
