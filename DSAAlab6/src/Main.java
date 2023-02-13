import java.util.Random;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            int n = 100;
            System.out.println(n);
            for (int j = 0; j < n-1; j++) {
                System.out.println(r.nextInt(100));
                System.out.println(r.nextInt(100));
            }

        }
    }
}