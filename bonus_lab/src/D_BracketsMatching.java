import java.util.Scanner;

public class D_BracketsMatching {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(in.nextLine());
            String str = in.nextLine();
            char[] str_arr = str.toCharArray();
            int[] stack = new int[n];
            int pin = 0;
            for (int j = 0; j < n; j++) {
                switch (str_arr[j]){
                    case '(':{
                        stack[pin++] = 1;break;
                    }
                    case '[':{
                        stack[pin++] = 2;break;
                    }
                    case '{':{
                        stack[pin++] = 3;break;
                    }
                    case ')':{
                        if (pin-1>=0 && stack[pin-1] == 1)pin--;
                        else stack[pin++] = 4;
                        break;
                    }
                    case ']':{
                        if (pin-1>=0 && stack[pin-1] == 2)pin--;
                        else stack[pin++] = 5;
                        break;
                    }
                    case '}':{
                        if (pin-1>=0 && stack[pin-1] == 3)pin--;
                        else stack[pin++] = 6;
                        break;
                    }
                }
            }
            if (pin==0){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}
