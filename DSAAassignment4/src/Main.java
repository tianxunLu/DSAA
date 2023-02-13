public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 单链表
        SlistedChange(2, 6);
    }

    private static void SlistedChange(int pos1, int pos2) {
        // TODO Auto-generated method stub
        Snode<Integer> Send = new Snode<>(null, null);
        Snode<Integer> S7 = new Snode<>(7, Send);
        Snode<Integer> S6 = new Snode<>(6, S7);
        Snode<Integer> S5 = new Snode<>(5, S6);
        Snode<Integer> S4 = new Snode<>(4, S5);
        Snode<Integer> S3 = new Snode<>(3, S4);
        Snode<Integer> S2 = new Snode<>(2, S3);
        Snode<Integer> S1 = new Snode<>(1, S2);
        Snode<Integer> Shead = new Snode<>(null, S1);

            // 交换前
        System.out.println("单链表交换前：");
        Snode tmp = Shead.next;
        while (tmp.next != null) {
            System.out.print(tmp.getValue());
            tmp = tmp.next;
        }
        System.out.println();
        // 交换
        Snode tmp1 = Shead;
        Snode p1_pre = null, p1 = null, p2 = null; // 交换位置的节点
        int index = -1; // 索引
            while (tmp1.next != null) {
                if (index == pos1 - 1) {
				//System.out.printf("pos1-1:%d\n", pos1-1);
                    p1_pre = tmp1;
                }
                if (index == pos1) {
				//System.out.printf("pos1:%d\n", pos1);
                    p1 = tmp1;
                }
                if (index == pos2) {
				//System.out.printf("pos2:%d\n", pos2);
                    p2 = tmp1;
                    // 交换
                    p1.next = p2.next;
                    p2.next = p1;
                    p1_pre.next = p2;
                    break;
                }
                index++;
                tmp1 = tmp1.next;
            }
            // 交换后
            System.out.println("单链表交换后：");
            Snode tmp2 = Shead.next;
            while (tmp2.next != null) {
                System.out.print(tmp2.getValue());
                tmp2 = tmp2.next;
            }
            System.out.println();
    }
}
class Snode<AnyType> {
    private AnyType value;
    public Snode next;

    public Snode(AnyType v, Snode n) {
            value = v;
            next = n;
    }

    public AnyType getValue() {
        return this.value;
    }
}
