//import java.io.*;
//import java.util.StringTokenizer;
//
//public class EE {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int n = in.nextInt();
//        int m = in.nextInt();
//        node head = new node(-1);
//        node cur = head;
//        int pos = 1;
//        for (int i = 0; i < n; i++) {
//            node temp = new node(in.nextInt());
//            cur.next = temp;
//            cur = cur.next;
//            pos++;
//        }
//        int length = n;
//        for (int i = 0; i < m; i++) {
//            cur = head;
//            char type = in.nextChar();
//            switch (type) {
//                case 'I':
//                    int p = in.nextInt();
//                    int v = in.nextInt();
//                    node insert = new node(v);
//                    for (int j = 0; j < p - 1; j++) {
//                        cur = cur.next;
//                    }
//                    insert.next = cur.next;
//                    cur.next = insert;
//                    length = length + 1;
//                    break;
//                case 'M':
//                    int pp = in.nextInt();
//                    int vv = in.nextInt();
//                    node change = new node(vv);
//                    for (int j = 0; j < pp - 1; j++) {
//                        cur = cur.next;
//                    }
//                    change.next = cur.next.next;
//                    cur.next = change;
//                    break;
//                case 'Q':
//                    int l = in.nextInt();
//                    int r = in.nextInt();
//                    int k = in.nextInt();
//                    for (int j = 0; j < l; j++) {
//                        cur = cur.next;
//                    }
//                    node pinl = cur;
//                    int right = cur.coe;
//                    int left = 0;
//                    //
//                    for (int j = 0; j < k; j++) {
//                            left = findmin(cur,right,left,r-l+1);
//                            cur = pinl;
//                    }
//                    out.println(left);
//                    break;
//            }
//        }
//        out.close();
//    }
//    public static int findmin(node cur,int right,int left,int length){
//        if (cur.next.next != null){
//            for (int j = 0; j < length; j++) {
//                if (cur.next.coe < right && cur.next.coe >= left) {
//                    right = cur.next.coe;
//                }
//                cur = cur.next;
//            }
//        }return right;
//    }
//}
//class node{
//    int coe;
//    node next;
//    public node(int coe) {
//        this.coe = coe;
//    }
//}
//
//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//
//    public char nextChar() {
//        return next().charAt(0);
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}