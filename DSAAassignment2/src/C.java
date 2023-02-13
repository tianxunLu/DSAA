import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int Rnum = input.nextInt();
        double[] r = new double[Rnum];
        double sum = 0;
        for (int i = 0; i < Rnum; i++) {
            r[i] = Math.pow(input.nextInt(),2)*Math.PI;
            sum = sum+r[i];
        }

        double max = sum/num;
        double initial = 0;
        while(max - initial > 1e-5){
            double mid = (initial+max)/2;
            if (check(num,mid,r)){
                initial = mid;
            }else{
                max = mid;
            }
        }
        System.out.println(formatDouble(max));

    }

    public static boolean check(int num,double x,double[] r) {
        int number = 0;
        for (int i = 0; i < r.length; i++) {
            number += Math.floor(r[i]/x);
        }
        if (number >= num){
            return true;
        }else return false;
    }

    public static double formatDouble(double d) {
        BigDecimal bg = new BigDecimal(d).setScale(7, RoundingMode.DOWN);
        return bg.doubleValue();
    }
}
