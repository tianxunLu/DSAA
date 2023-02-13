import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        long S = input.nextLong();
        long[] arr = new long[num];
        for (int i = 0; i < num; i++) {
            arr[i] = input.nextLong();
        }

        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i+1;
            int right = arr.length-1;
            while(left < right){
                long sum = arr[i]+arr[left]+arr[right];
                if (sum < S){
                    left++;
                }else if (sum > S){
                    right--;
                }else {
                    int j = 1;
                    while(arr[left] == arr[left+j] && left+j<right){
                        j++;
                    }
                    if (arr[left] == arr[right]) {
                        count += ((long) (j + 1) *(j))/2;
                        break;
                    }
                    left = left + j;
                    int k = 1;
                    while(arr[right] == arr[right-k] && right-k >= left){
                        k++;
                    }
                    count = count + (long) j*k;
                    right = right - k;
                }
            }
        }
        System.out.println(count);
    }
}
