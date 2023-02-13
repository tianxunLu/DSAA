import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            System.out.print(random.nextInt(1000)+" ");
        }
        System.out.println(" ");
        int n = random.nextInt(500);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            int k = random.nextInt(4);
            switch (k) {
                case 0 -> System.out.print("U");
                case 1 -> System.out.print("D");
                case 2 -> System.out.print("R");
                case 3 -> System.out.print("L");
            }
        }

        //生成测试代码

    }
}
