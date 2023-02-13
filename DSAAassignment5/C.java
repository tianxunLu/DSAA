import java.io.*;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] Q = new int[n];
        for (int i = 0; i < n; i++) {
            Q[i] = in.nextInt();
        }
        //operate
        int[] result = new int[n-k+1];
        int front = 0;
        int rear = -1;
        int[] temp = new int[n];
        // 6 4 2   1 5 2 3 2 1   1 3
        for (int i = 0; i < n; i++) {
            while (rear >= front && Q[i] > Q[temp[rear]]){
                rear--;
            }
            temp[++rear] = i;
            if (temp[rear]-temp[front]==k){
                front++;
            }
            if (i>=k-1){
                result[i-k+1] = Q[temp[front]];
            }
        }
        //output
        for (int i = 0; i < q; i++) {
            out.println(result[in.nextInt()-1]);
        }
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