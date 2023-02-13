import java.util.Scanner;
public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        char[] A = in.nextLine().toCharArray();
        char[] B = in.nextLine().toCharArray();
        //inverse
        char[] C = new char[n-1];
        for (int i = 0; i < n-1; i++) {
            switch (A[i]){
                case 'N':C[i] = 'S';break;
                case 'S':C[i] = 'N';break;
                case 'E':C[i] = 'W';break;
                case 'W':C[i] = 'E';break;
            }
        }
        //7
        //SSWNEE
        //NENENE
        //get connected
        char[] CB = new char[2*n-2];
        for (int i = 0; i < n-1; i++) {
            CB[n-2-i] = C[i];
        }
        for (int i = 0; i < n-1; i++) {
            CB[i+n-1] = B[i];
        }
        //prefix
        if (next(CB)){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

    public static boolean next(char[] pattern){
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
        return next[next.length-1] == 0;
    }
}
