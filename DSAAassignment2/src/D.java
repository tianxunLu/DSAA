import java.util.Arrays;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long x_r = input.nextLong();
        long y_r = input.nextLong();
        long x_c = input.nextLong();
        long y_c = input.nextLong();
        int period = input.nextInt();
        String seq =  input.nextLine();
        seq =  input.nextLine();
        char[] sequence = new char[period];
        for (int i = 0; i < period; i++) {
            sequence[i] = seq.charAt(i);
        }

        long dist;
        long distant = Math.abs(x_c - x_r)+Math.abs(y_c - y_r);
        long initial = 0;
        long max = distant*period;
        dist = newDist(sequence,period,x_r,y_r,x_c,y_c)[0] - distant;

        if (dist==period){
            System.out.println(-1);
        }else {
            while (initial<=max){
                long mid = (initial+max)/2;
                if (ok(mid,sequence,distant,dist,x_r,y_r,x_c,y_c)){
                    max = mid-1;
                }else {
                    initial = mid+1;
                }
            }
            System.out.println(initial);
        }
    }

    public static long[] newDist(char[] sequence,long x,long x_r,long y_r,long x_c,long y_c){
        long[] list = new long[5];
        long x_r0 = x_r, y_r0 = y_r;
        for (int i = 0; i < x; i++) {
            switch (sequence[i]) {
                case 'U':
                    y_r = y_r + 1;
                    break;
                case 'D':
                    y_r = y_r - 1;
                    break;
                case 'L':
                    x_r = x_r - 1;
                    break;
                case 'R':
                    x_r = x_r + 1;
                    break;
            }
        }
        list[0] = Math.abs(x_c - x_r)+Math.abs(y_c - y_r);
        list[1] = x_r - x_r0;
        list[2] = y_r - y_r0;
        return list;
    }

    public static boolean ok(long mid,char[] sequence,long distant,long dist,long x_r,long y_r,long x_c,long y_c) {
        long[] list = newDist(sequence,sequence.length,x_r,y_r,x_c,y_c);
        long l = mid / sequence.length, n = mid % sequence.length;
        x_r = l *list[1] + x_r;
        y_r = l *list[2] + y_r;
        list = newDist(sequence, n,x_r,y_r,x_c,y_c);
        long result = mid - list[0];
        return result >= 0;
    }
}