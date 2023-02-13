public class C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String STR = in.nextLine();
        int[] arr = new int[STR.length()];
        //aabaaabb
        for (int i = 0; i < STR.length(); i++) {
            arr[i] = STR.charAt(i)-97;
        }
        int[][] arr_out = new int[STR.length()][26];
        int count = 1;
        int pattern_index = 0;
        for (int i = 0; i < STR.length(); i++) {
            arr_out[i][arr[i]] = count++;
            if (i>1){
                pattern_index = arr_out[pattern_index][arr[i-1]];
            }
            for (int j = 0; j < 26; j++) {
                if (j!=arr[i]){
                    arr_out[i][j] = arr_out[pattern_index][j];
                }
            }

        }
        for (int i = 0; i < STR.length(); i++) {
            for (int j = 0; j < 26; j++) {
                out.print(arr_out[i][j]+" ");
            }
            out.println("");
        }
        out.close();
    }
}
