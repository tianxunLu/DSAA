import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random(1);
        for (int i = 1; i < 100; i++) {
            System.out.println(i+" "+(i+1));
            for (int j = 0; j < i; j++) {
                System.out.print((int)r.nextInt(100)+" ");
                System.out.println((int)r.nextInt(100)+" ");
            }
        }
    }
}