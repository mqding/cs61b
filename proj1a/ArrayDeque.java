public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst = 4;
    private int nextLast = 5;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        items = (T []) new Object[8];
        size = 0;

        for (int i = 0; i < items.length; i++) {
            addLast((T) other.get(i));
        }
    }

    private int RFACTOR = 2;

    /**resize */
    public void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items,0,a,0,nextLast);
        System.arraycopy(items,nextFirst + 1,a,size + nextLast,size - nextLast);
        nextFirst = size + nextLast - 1;
        items = a;
    }

    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = nextFirst -1;
        size = size + 1;
        if (nextLast == nextFirst) {
            resize(size * RFACTOR);
        }
    }

    public void addLast(T x) {
        //if (size == items.length) {
          //  resize(size * RFACTOR);
        //}
        items[nextLast] = x;
        if (nextLast < items.length-1) {
            nextLast = nextLast + 1;
        }else{nextLast = 0;}
        size = size + 1;

        if (nextLast == nextFirst) {
            resize(size * RFACTOR);
        }
    }

    public T removeFirst() {
        if (nextFirst < items.length) {
            nextFirst = nextFirst + 1;
        }else { nextFirst = 0;}

        T x = items[nextFirst];
        size = size - 1;
        return x;
    }

    public T removeLast() {
        //T x = items[size - 1];//getLast();
        //items[size - 1] = 0;
        if (nextLast > 0) {
            nextLast = nextLast - 1;
        }else{nextLast = items.length - 1;}

        T x = items[nextLast];
        size = size - 1;
        return x;
    }

    public T get(int index) {
        T x = null;
        if (index < items.length) {
            x = items[index];
        }
        return x;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ArrayDeque<String> L = new ArrayDeque();

        L.addLast("a");
        L.addLast("b");
        L.addFirst("c");
        L.addLast("d");
        L.addLast("e");
        L.addFirst("f");
        L.addLast("g");
        //L.addLast("h");
        //L.addFirst("i");
        //L.addLast("h");

        L.removeLast();
        L.removeLast();
        System.out.println(L.getSize());
        for (int i = 0; i < 14; i++) {
            System.out.println(L.get(i));
        }

    }

}
