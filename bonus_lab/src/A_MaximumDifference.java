import java.util.Scanner;
public class A_MaximumDifference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int max = 0,cha = -1000000000;
            for (int j = 0; j < n; j++) {
                int cur = in.nextInt();
                if (cha<max-cur)cha = max-cur;
                if (max<cur)max = cur;
            }
            System.out.println(cha);
        }
    }
}
