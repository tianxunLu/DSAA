import java.util.HashSet;
import java.util.Scanner;

public class ProblemB_Palindrome {
    public static final long MOD = 300000002;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
        String A = input.nextLine();
        String B = input.nextLine();
        if (B.length() < A.length()){
            String temp = A;
            A = B;
            B = temp;
        }
        int even,odd,l,r,mid,even_max,odd_max;
        if (A.length()%2 == 0){
            even = A.length();
            odd =  A.length()-1;
        }
        else {
            even = A.length()-1;
            odd =  A.length();
        }

        l = 1;
        r = odd;
        while (l <= r){
            mid = (l+r)/2;
            if (mid%2 == 0) mid++;
            if (check(A,B,mid)) l = mid + 2;
            else r = mid - 2;
        }
        odd_max = r;

        l = 2;
        r = even;
        while (l <= r){
            mid = (l+r)/2;
            if (mid%2 == 1) mid++;
            if (check(A,B,mid)) l = mid + 2;
            else r = mid - 2;
        }
        if (r == 0) even_max = -1;
        else even_max = r;

        System.out.println(Math.max(odd_max,even_max));
    }}

    public static boolean check(String A,String B,int k){
        char[] arrayA = A.toCharArray(), arrayB = B.toCharArray();
        char[] arrayAre = new char[arrayA.length], arrayBre = new char[arrayB.length];
        for (int i = 0; i < arrayA.length; i++) {
            arrayAre[arrayA.length-1-i] = arrayA[i];
            arrayBre[arrayB.length-1-i] = arrayB[i];
        }
        long[] hashArrayA = new long[A.length()-k+1], hashArrayB = new long[A.length()-k+1];
        long[] hashArrayC = new long[B.length()-k+1];
        for (int i = 0; i < A.length()-k+1; i++) {
            hashArrayA[i] = Hash(arrayA,i,i+k,hashArrayA);
            hashArrayB[i] = Hash(arrayAre,i,i+k,hashArrayB);
        }
        HashSet<Long> seen = new HashSet<>();
        for (int i = 0; i < A.length()-k+1; i++) {
            if (hashArrayA[i] == hashArrayB[A.length()-k-i]){
                seen.add(hashArrayA[i]);
            }
        }
        for (int i = 0; i < B.length()-k+1; i++) {
            hashArrayC[i] = Hash(arrayB,i,i+k,hashArrayC);
            if (seen.contains(hashArrayC[i])){
                return true;
            }
        }
        return false;
    }

    public static long Hash(char[] array, int begin, int end, long[] hashArray) {
        long sum = 0;
        if (begin < end) {
            if (begin == 0){
                for (int i = begin; i < end; i++) {
                    sum = sum*26%MOD + (array[i]-'a');
                }
            }
            else {
                sum = (hashArray[begin-1]-((array[begin-1]-'a')*power(26,end-begin-1))% MOD + MOD)%MOD*26%MOD
                        + (array[end-1]-'a');
            }
        } else System.out.println("error!");
        return sum % MOD;
    }

    public static long power(long a, long b){
        long ans = 1;
        while (b > 0) {
            if (b % 2 == 1)
                ans = (ans%MOD * a%MOD) % MOD;
            b /= 2;
            a = (a%MOD * a%MOD) % MOD;
        }
        return ans;
    }
}
