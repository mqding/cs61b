public class SLList {
    private static class IntNode {
        /*never use any instance variables or
        * method of outer class*/
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    /* the first item is at sentinel.next*/
    private IntNode sentinel;
    private int size;

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;

        //if (sentinel == null) {
        //    sentinel = new IntNode(x, null);
        //    return;
        //}

        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }

        return 1 + size(p.next);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList(10);

        L.addFirst(5);
        //L.addFirst(15);
        //L.addFirst(16);
        //L.addLast(5);
        //L.addLast(10);
        L.addLast(15);
        //L.first.next.next=L.first.next;
        int x = L.getFirst();
        System.out.println(x);
        System.out.println(L.size());

        SLList s1 = new SLList();
        s1.addLast(5);
        s1.addFirst(2);
        System.out.println(s1.getFirst());
    }
}



