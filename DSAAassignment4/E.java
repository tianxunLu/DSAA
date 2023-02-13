import java.io.*;
import java.util.StringTokenizer;

//此题n^2 tle了
public class E {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        ListNode head = new ListNode(0,-1);
        ListNode cur = head;
        int pos = 1;
        for (int i = 0; i < n; i++) {
            ListNode temp = new ListNode(pos,in.nextInt());
            cur.next = temp;
            cur = cur.next;
            pos++;
        }
        //merge_sort
        mergeSort(head);
        int length = n;
        //operate
        for (int i = 0; i < m; i++) {
            cur = head;
            char type = in.nextChar();
            switch (type){
                case 'I':
                    int p = in.nextInt();
                    int v = in.nextInt();
                    ListNode insert = new ListNode(p,v);
                    while(cur.next.coe < v){
                        cur = cur.next;
                    }
                    insert.next = cur.next;
                    cur.next = insert;
                    length++;
                    cur = head;
                    while(cur.next != null){
                        if (cur.next.pos>=p && cur.next!=insert){
                            cur.next.pos++;
                        }
                        cur = cur.next;
                    }
                    break;
                case 'M':
                    int pp = in.nextInt();
                    int vv = in.nextInt();
                    length++;
                    for (int j = 0; j < length; j++) {
                        if(cur.next.pos == pp){
                            break;
                        }
                        cur = cur.next;
                    }
                    cur.next = cur.next.next;
                    cur = head;
                    ListNode change = new ListNode(pp,vv);
                    while(cur.next.coe < vv){
                        cur = cur.next;
                    }
                    change.next = cur.next;
                    cur.next = change;
                    break;
                case 'Q':
                    int l = in.nextInt();
                    int r = in.nextInt();
                    int k = in.nextInt();
                    while (k>0){
                        cur = cur.next;
                        if (cur.pos<=r && cur.pos>=l){
                            k--;
                        }
                    }
                    out.println(cur.coe);
                    break;
            }
        }
        out.close();
    }

    public static ListNode mergeSort(ListNode head){
        if(head==null || head.next==null)    return head;

        ListNode mid = getMid(head);//获取链表中间节点

        //把链表从之间拆分为两个链表：head和second两个子链表
        ListNode second = mid.next;
        mid.next = null;

        //对两个子链表排序
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(second);

        return merge(right,left);
    }

    private static ListNode merge(ListNode l1, ListNode l2){
        //辅助节点，排好序的节点将会链接到dummy后面
        ListNode dummy = new ListNode(0,-1);

        ListNode tail = dummy;//tail指向最后一个排好序的节点
        while(l1!=null&&l2!=null){
            if(l1.coe<=l2.coe){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next; //移动tail指针
        }

        if(l1!=null)
            tail.next = l1;
        else
            tail.next = l2;

        return dummy.next;

    }
    //return the node
    private static ListNode getMid(ListNode head){
        if(head==null ||head.next==null)    return head;

        ListNode slow = head;
        ListNode faster = head.next;
        while(faster!=null&&faster.next!=null){
            slow = slow.next;
            faster = faster.next.next;
        }
        return slow;
    }
}
class ListNode{
    int coe;
    int pos;
    ListNode next;
    public ListNode(int pos,int coe) {
        this.coe = coe;
        this.pos = pos;
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