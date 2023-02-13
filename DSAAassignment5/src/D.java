import java.io.*;
import java.util.StringTokenizer;
public class D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] arr_prefix = new int[n+1];
        arr_prefix[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr_prefix[i] = arr_prefix[i-1] + in.nextInt();
        }



//5   2 0 -1 -4 2
        //6  6 -5 4 -3 2 -1
        int rear = 0;
        int[] temp = new int[n+1];
        temp[0] = 0;
        int wa = 0;
        for (int i = 1; i < n+1; i++) {
            if (arr_prefix[i]>arr_prefix[temp[rear]]){
                int left = 0;
                int right = rear;
                while(left<=right){
                    int mid = (right+left)/2;
                    if (arr_prefix[temp[mid]]<arr_prefix[i]){
                        right = mid-1;
                    }else {
                        left = mid+1;
                    }
                }
                if (i-temp[left]>wa)wa = i-temp[left];
            }else {
                rear++;
                temp[rear] = i;
            }
        }
        out.println(wa);
        out.close();
    }
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public char nextChar() {
        return next().charAt(0);
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}