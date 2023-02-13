import java.util.Scanner;

public class E_Gift {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            int[] arr = next(str.toCharArray());
            if (arr[arr.length-1]!=0){
                int diff = arr.length-arr[arr.length-1];
                int len = 0;
                if (arr.length%diff==0){
                    len = arr.length/diff;
                }else len = arr.length/diff+1;
                System.out.println(len*diff-arr.length);
            }else System.out.println(arr.length);
        }
    }

    public static int[] next(char[] pattern){
        int[] next = new int[pattern.length];
        int prefix_len = 0;
        next[0] = 0;
        int i = 1;
        while(i < pattern.length){
            if (pattern[prefix_len] == pattern[i]){
                prefix_len++;
                next[i++] = prefix_len;
            }
            else {
                if (prefix_len == 0){
                    next[i++] = 0;
                }
                else {
                    prefix_len = next[prefix_len - 1];
                }
            }
        }
        return next;
    }
}
