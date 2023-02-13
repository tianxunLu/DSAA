import java.io.*;
import java.util.StringTokenizer;
public class B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long m = in.nextLong();
        node head = new node(0);
        node tail = new node(0);
        node cur = head;
        for (int i = 0; i < n; i++) {
            node temp = new node(in.nextInt());
            cur.next = temp;
            cur = cur.next;
        }
        cur.next = tail;
        cur = head;
        long num = 0;

        while(m>0){
            int subtract = 0;
            long price =0;
            long cishu = 0;
            for (int i = 0; i < n; i++) {
                if (m >= cur.next.coe){
                    m -= cur.next.coe;
                    price += cur.next.coe;
                    cur = cur.next;
                    cishu ++;
                }else {
                    subtract ++;
                    cur.next = cur.next.next;
                }
            }
            n -= subtract;
            cur = head;
            if (price>0){
                num += (1+m/price)*cishu;
                m = m%price;
            }
            if (cur.next.coe == 0) break;
        }
        out.println(num);
        out.close();
    }
}
class node{
    int coe;
    node next;
    public node(int coe) {
        this.coe = coe;
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