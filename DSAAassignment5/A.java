import java.io.*;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String str = in.nextLine();
        String[] brackets = str.split("");
        int[] stack = new int[brackets.length];
        stack[0] = -1;
        //((()())())
        int pin = 0;
        for (int i = 0; i < brackets.length; i++) {
            switch (brackets[i]){
                case "(":
                    pin++;
                    stack[pin] = -1;
                    break;
                case ")":
                    if (stack[pin] == -1){
                        stack[pin] = 1;
                    }else {
                        while (stack[pin-1]!=-1){
                            pin--;
                            stack[pin] = (stack[pin+1]+stack[pin])%514329;
                        }
                        pin--;
                        stack[pin] = (stack[pin+1]*2)%514329;
                    }
                    break;
            }
        }
        if (pin == 1){
            out.println(stack[1]);
        }else {
            while (stack[pin-1]!=-1){
                pin--;
                stack[pin] = (stack[pin+1]+stack[pin])%514329;
            }
            out.println(stack[1]);
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