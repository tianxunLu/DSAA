import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        r(5);
    }
    public static void r(int n){
        if (n<=0)return;
        if (n%2 == 0){
            System.out.println(n);
            r(n-1);
        }else {
            r(n-1);
            System.out.println(n);
        }
    }
}
