import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node_A[] city = new node_A[n];
        for (int i = 0; i < n; i++) {
            city[i] = new node_A();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            city[a].child.add(city[b]);
            city[a].len.add(in.nextInt());
        }
        heap h = new heap(n);
        city[0].dist = 0;
        h.insert(city[0]);
        while(h.size!=0){
            node_A delete = h.delete();
            delete.isVisited = true;
            for (int i = 0; i < delete.child.size(); i++) {
                if (!delete.child.get(i).isVisited){
                    long newDist = delete.dist+delete.len.get(i);
                    if (newDist < delete.child.get(i).dist){
                        delete.child.get(i).dist = newDist;
                        if (delete.child.get(i).index==0){
                            h.insert(delete.child.get(i));
                        }else {
                            h.liftUp(delete.child.get(i).index);
                        }
                    }
                }
            }
        }
        if (city[n-1].dist!=Long.MAX_VALUE){
            out.println(city[n-1].dist);
        }else out.println(-1);
        out.close();
    }
}
class node_A {
    long dist = Long.MAX_VALUE;
    int index;
    boolean isVisited = false;
    ArrayList<node_A> child = new ArrayList<>();
    ArrayList<Integer> len = new ArrayList<>();
}
class heap  {
    node_A[] heap;
    int size = 0;
    public heap(int n){
        heap = new node_A[n+1];
    }

    public void insert(node_A n){
        size++;
        heap[size] = n;
        n.index = size;
        liftUp(size);
    }

    public void liftUp(int n){
        int temp = n;
        while (temp>1 && heap[temp/2].dist >heap[temp].dist){
            swap(temp/2,temp);
            temp = temp/2;
        }
    }

    public void swap(int a,int b){
        heap[a].index = b;
        heap[b].index = a;
        node_A temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    public node_A delete(){
        swap(1,size);
        node_A r = heap[size--];
        int temp = 1;
        while(temp*2<=size){
            if(temp*2+1<=size){
                if (heap[temp].dist<heap[temp*2].dist && heap[temp].dist<heap[temp*2+1].dist)break;
                if(heap[temp*2].dist>heap[temp*2+1].dist){
                    swap(temp*2+1,temp);
                    temp = temp*2+1;
                }else {
                    swap(temp*2,temp);
                    temp = temp*2;
                }
            }else{
                if(heap[temp*2].dist>heap[temp].dist)break;
                swap(temp*2,temp);
                temp = temp*2;
            }
        }
        return r;
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