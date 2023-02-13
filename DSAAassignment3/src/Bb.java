import java.util.Random;

public class Bb {

    public static void main(String[] args) {
        Random r=new Random();
        int t= r.nextInt(10)+1;
        long[] times=new long[t];
        System.out.println(t);
        for (int i = 0; i < t; i++) {
            int n= r.nextInt(10)+1;
            int[] array=new int[n];
            for (int j = 0; j < n; j++) {
                array[j]= r.nextInt(10)+1;
            }
            System.out.println(n);
            for (int j = 0; j < n; j++) {
                System.out.print(array[j]+" ");
            }
            times[i]=sort(array);
            System.out.println();
        }
        System.out.println("--------result---------");
        for (int i = 0; i < t; i++) {
            System.out.println(times[i]);
        }
    }
    static  long sort(int[] ints){
        long times=0;
        for (int i=ints.length-1;i>=0;i--){
            for (int j=0;j<i;j++){
                if (ints[j]>ints[j+1]){
                    int temp=ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1]=temp;
                    times++;
                }
            }
        }
        return times;

    }
}