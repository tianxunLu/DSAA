import java.util.HashSet;
import java.util.Scanner;

public class B {
    final static long mod = 300000001;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true){
        String AA = in.nextLine();
        String BB = in.nextLine();
        //string A is the short one
        String A;
        String B;
        if (AA.length() > BB.length()) {
            A = BB;
            B = AA;
        } else {
            A = AA;
            B = BB;
        }
        int even = 0;
        int odd = 0;
        if (A.length() % 2 == 0) {
            even = A.length();
            odd = A.length() - 1;
        } else {
            odd = A.length();
            even = A.length() - 1;
        }
        int L = 0;
        int R = even;
        //abababa
        //bababab
        //calculate the max even
        while (L <= R) {
            int mid = (L + R) / 2;
            if (mid % 2 != 0) {
                mid++;
            }
            if (check(mid, A, B)) {
                L = mid + 2;
            } else {
                R = mid - 2;
            }
        }
        long Leven = R;
        //calculate the max odd
        L = 1;
        R = odd;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (mid % 2 == 0) {
                mid++;
            }
            if (check(mid, A, B)) {
                L = mid + 2;
            } else {
                R = mid - 2;
            }
        }
        //output
        long result = Math.max(R, Leven);
        if (result == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
        }
    }

    public static boolean check(int k,String A,String B){
            int length = A.length() - k + 1;
            long[] arr_A = subcheck(A, k);
            long[] arr_B = subcheck(B, k);
            //reverse
            StringBuffer buffer_A = new StringBuffer(A);
            String A_ = buffer_A.reverse().toString();
            long[] arr_i = subcheck(A_, k);
            //compare
            HashSet<Long> sites = new HashSet<>();
            for (int i = 0; i < length; i++) {
                if (arr_A[i] == arr_i[length - i - 1]) {
                    sites.add(arr_A[i]);
                }
            }
            //find in B
            for (long l : arr_B) {
                if (sites.contains(l)) {
                    return true;
                }
            }
            return false;
    }

    public static long[] subcheck(String str,int k){
        int length = str.length()-k+1;
        long[] arr = new long[length];
        long power = pow(k-1);
        char[] arr_str = str.toCharArray();
        for (int i = 0; i < k; i++) {
            long power1 = pow(k-i-1);
            arr[0] = (arr[0]+(arr_str[i]-97)*power1%mod)%mod;
        }
        if (length>1){
            for (int i = k; i < str.length(); i++) {
                arr[i-k+1] = ((arr[i-k]-(arr_str[i-k]-97)*power%mod+mod)%mod*26%mod+(arr_str[i]-97))%mod;
            }
        }
        return arr;

    }

    public static long pow(long power){
        long a = 26;
        long ans = 1;
        while (power > 0) {
            if (power % 2 == 1)
                ans = (ans%mod * a%mod) % mod;
            power /= 2;
            a = (a%mod * a%mod) % mod;
        }
        return ans;
    }
}

