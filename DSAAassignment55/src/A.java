import java.util.Scanner;
public class A {
    public static void main(String[] args) {// output 3   1 3   2 1   2 5   -1
        Scanner in = new Scanner(System.in);
        //input
        int T = Integer.parseInt(in.nextLine());
        for (int i = 0; i < T; i++) {
            String str = in.nextLine();
            char[] initial = str.toCharArray();
            int a_i = Integer.parseInt(in.nextLine());
            char[][] str_arr = new char[a_i][];
            for (int j = 0; j < a_i; j++) {
                str_arr[j] = in.nextLine().toCharArray();
            }
            //operation , farthest is arr[1]
            int[][] result = new int[initial.length][2];
            int L = 0;
            int R = 0;
            int count = 0;
            int newMax = 0;
            while (R<initial.length){
                boolean ttff = true;
                //find the max in[L,R]
                for (int j = L; j <= R; j++) {
                    int cur_len = max_substring(j,str_arr,initial)[1];
                    int cur_id = max_substring(j,str_arr,initial)[0];
                    if (cur_len>newMax){
                        newMax = cur_len;
                        result[count][0] = cur_id+1;
                        result[count][1] = cur_len-str_arr[cur_id].length+1;
                        ttff = false;
                    }
                }
                L = result[count][1];
                if (ttff){
                    System.out.println(-1);
                    break;
                }else {
                    count++;
                    R = newMax;
                }
            }
            count--;
            if (R == initial.length){
                System.out.println(++count);
                for (int j = 0; j < count; j++) {
                    System.out.println(result[j][0]+" "+result[j][1]);
                }
            }
        }
    }

    //start from L go through all substrings, find the furthest, return the number behind
    public static int[] max_substring(int L,char[][] str_arr,char[] initial){
        int id = 0; //the first id is 1
        int[] arr = new int[2];
        while (id<str_arr.length){
            if (L+str_arr[id].length<=initial.length){
                //<=is not sure
                boolean tf = true;
                for (int j = L; j < L+str_arr[id].length; j++) {
                    if (initial[j]!=str_arr[id][j-L]){
                        tf = false;
                    }
                }
                if (tf){
                    if (L+str_arr[id].length>arr[1]){
                        arr[1] = L+str_arr[id].length;//farthest
                        arr[0] = id;//the id of the string
                    }
                }
            }
            id++;
        }
        return arr;
    }
    //2
    //ababab
    //2
    //aba
    //ab
    //abcdef
    //3
    //abcd
    //bc
    //cdefg
}