import java.util.Random;

public class Main {
    static long mod = 20220000;
    public static void main(String[] args) {
        char[] initial = {'a','a', 'b', 'd', 'd', 'a', 'm'};
        char[][] str = {{'a','a','b'},{'a','a','b','d'}};
        char[] t = {'a','b','a'};

        System.out.println(longestCommonPrefix(t));
        System.out.println(Prefix(t));
    }

    public static boolean longestCommonPrefix(char[] CB) {
        int length=CB.length;
        int[] next = new int[length+1];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j < length){
            if(k==-1 || CB[k]==CB[j]){
                k++;
                j++;
                next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next[length]!=0;
    }

    public static boolean Prefix(char[] CB) {
        int length = CB.length;
        int[] next = new int[length + 1];
        next[0] = 0;
        int i = 0;
        int j = 0;
        for (; j < length; j++) {
            while (CB[i] != CB[j] && i > 0) {
                i = next[i - 1];
            }
            if (CB[i] == CB[j]) {
                i++;
                next[j] = i;
            } else {
                next[j] = 0;
            }
        }
        return next[length]!=0;
    }
}
