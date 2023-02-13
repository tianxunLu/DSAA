import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class A_SumTree {
    static int num = 0;
    static int cnt = 0;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        num = in.nextInt();
        node[] tree  = new node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new node();
        }
        for (int i = 0; i < n-1; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            int d = in.nextInt();
            tree[a].child.add(tree[b]);
            tree[a].len.add(d);
            tree[b].child.add(tree[a]);
            tree[b].len.add(d);
        }
        tree[0].isVisited = true;
        dfs(tree[0]);
        out.println(cnt);
        out.close();
    }
    public static void dfs(node temp){
        for (int i = 0; i < temp.child.size(); i++) {
            if (!temp.child.get(i).isVisited){
                temp.child.get(i).isVisited = true;
                temp.child.get(i).path = temp.path + temp.len.get(i);
                if (temp.child.get(i).path == num && temp.child.get(i).child.size()==1){
                    cnt++;
                }
                dfs(temp.child.get(i));
            }
        }
    }
}
class node{
    int path;
    boolean isVisited;
    ArrayList<node> child = new ArrayList<>();
    ArrayList<Integer> len = new ArrayList<>();
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