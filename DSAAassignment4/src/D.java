//import java.io.*;
//import java.util.StringTokenizer;
//
//public class D {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int q = in.nextInt();
//
//        int[][] array = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                array[i][j] = in.nextInt();
//            }
//        }
//        node[] hangHead = new node[n];
//        node[] lieHead = new node[m];
//        for (int i = 0; i < n; i++) {
//            hangHead[i] = new node(0);
//            for (int j = 0; j < m; j++) {
//                node temp = new node(array[i][j]);
//                temp.next = hangHead[i].next;
//                hangHead[i].next = temp;
//            }
//        }
//        for (int i = 0; i < m; i++) {
//            lieHead[i] = new node(0);
//            for (int j = 0; j < n; j++) {
//                node temp = new node(array[j][i]);
//                temp.down = lieHead[i].down;
//                lieHead[i].down = temp;
//            }
//        }
//        node a  = hangHead[0];
//        /*4 2 1
//1 1
//1 1
//2 2
//2 2
//2 1 4 1 1 2
//*/
//        for (int i = 0; i < q; i++) {
//            int x1 = in.nextInt();
//            int y1 = in.nextInt();
//            int x2 = in.nextInt();
//            int y2 = in.nextInt();
//            int I1 = in.nextInt();
//            int I2 = in.nextInt();
//            node left;
//            node right;
//
//        }
//        //final output
//
//        for (int i = 0; i < n; i++) {
//            node cur = hangHead[i].next;
//            for (int j = 0; j < m; j++) {
//                out.print(cur.coe+" ");
//                cur = cur.next;
//            }
//            out.print("\n");
//        }
//        out.close();
//    }
//}
//class node{
//    int coe;
//    node next;
//    node down;
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