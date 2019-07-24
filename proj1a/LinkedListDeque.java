import javax.management.NotificationEmitter;

public class LinkedListDeque<T> {
    private class Node {
        /*never use any instance variables or
        * method of outer class*/
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    /* the first item is at sentinel.next*/
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size  = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T x) {
        size += 1;
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T x) {
        size += 1;
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty() {
        /*
        if (sentinel.prev == sentinel.next) {
            return true;
        }
        return false;
         */
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T res =sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next =  sentinel;
        return res;
    }

    public T get(int index) {
        if (index < size && index >= 0) {
            Node p = sentinel.next;
            int i = 0;
            while (i < index) {
                p = p.next;
                i++;
            }
            return p.item;
        }
        return null;
    }


    public T getRecursiveHelper(int index, int count, Node ptr) {
        if (index == count) {
            return ptr.item;
        }
        return getRecursiveHelper(index, count+1, ptr.next);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int count = 0;
        Node ptr = sentinel.next;
        return getRecursiveHelper(index, count, ptr);
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>(0);

        L.addFirst(5);
        L.addFirst(1);
        L.addLast(10);
        L.printDeque();
        System.out.println(L.removeLast());

        System.out.println(L.size());
        L.printDeque();
        System.out.println("---get test---");
        System.out.println(L.get(2));
        System.out.println(L.getRecursive(2));

        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        System.out.println("---null test---");
        L1.printDeque();
        L1.addFirst(1);
        L1.addLast(2);
        L1.printDeque();
        System.out.println(L1.get(0));

        L1.removeFirst();
        L1.printDeque();
        L1.removeLast();
        L1.printDeque();
        L.equals(L);
    }
}



