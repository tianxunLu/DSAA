import java.util.Arrays;
import java.util.Scanner;

public class D_graph {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        boolean isSimpleGraph = true, isTree = true;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (!(arr[i] > 0)) {
                isTree = false;
            }
        }
        Arrays.sort(arr);
        long[] postfix = new long[n];
        postfix[n - 1] = arr[0];
        for (int i = 1; i < n; i++) {
            postfix[n - i - 1] = arr[i] + postfix[n - i];
        }
        long sum = postfix[0];
        if (sum % 2 == 0) {
            System.out.println("YES");
            int ink = n - 1;
            for (int i = 0; i < n; i++) {
                long supply1 = (long) i * (i + 1);
                while (ink >= 0 && arr[arr.length - 1 - ink] < i) {
                    ink--;
                }
                if (ink < i) ink = i;
                long supply2 = (long) (ink - i) * (i + 1);
                long supply3 = (ink + 1 < n) ? postfix[ink + 1] : 0;
                if (!(supply1 + supply2 + supply3 >= sum - postfix[i] + arr[n - 1 - i])) {
                    isSimpleGraph = false;
                    break;
                }
            }
            if (isSimpleGraph) {
                if ((n == 1 && sum == 0) || sum / 2 == n - 1 && isTree) {
                    System.out.println("YES");
                    System.out.println("YES");
                } else {
                    System.out.println("YES");
                    System.out.println("NO");
                }
            } else {
                System.out.println("NO");
                System.out.println("NO");
            }
        } else {
            System.out.println("NO");
            System.out.println("NO");
            System.out.println("NO");
        }
    }
}
