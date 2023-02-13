import java.io.*;
import java.util.StringTokenizer;
public class A {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        int m = input.nextInt();
        node head = new node(-1000000001, -1000000001);//fake head
        node cur = head;
        for (int i = 0; i < n; i++) {
            node temp = new node(input.nextInt(), input.nextInt());
            cur.next = temp;
            cur = cur.next;//not the same operation for each i
        }
        node tail = new node(-1000000001, -1000000001);//fake tail
        cur.next = tail;
        cur = head;//return to the fake head
        for (int i = 0; i < m; i++) { //keep on reading
            node temp = new node(input.nextInt(), input.nextInt());
            while (cur.next != tail){
                if (temp.exp < cur.next.exp){
                    break; //we have fake tail
                }
                cur = cur.next; //we use this head as a movable pointer
            }
            if (cur.exp != temp.exp){
                //insert
                temp.next = cur.next;
                cur.next = temp;
            }else {
                //add value
                cur.coe += temp.coe;
            }
        }

        int num = 0;
        cur = head.next;
        while (cur != tail){
            if (cur.coe!= 0)num++;
            cur = cur.next;
        }
        out.println(num);
        cur = head.next;
        while(cur.next != null){
            if (cur.coe != 0)out.println(cur.coe +" "+ cur.exp);
            cur = cur.next;
        }
        out.close();
    }
}

class node{
    int coe;
    int exp;
    node next;
    public node(int coe,int exp) {
        this.coe = coe;
        this.exp = exp;
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