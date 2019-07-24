package synthesizer;

public abstract class AbstructBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }     // return size of the buffer
    public int fillCount() {
        return fillCount;
    }    // return number of items currently in the buffer
    /** should not declare explicitly.
     *
     * public abstract void enqueue(T x);  // add item x to the end
    public abstract T dequeue();  // delete and return item from the front
    public abstract T peek();    // return (but do not delete) item from the front

    public boolean isEmpty() {

        return true;
    }
    public boolean isFull() {

        return false;
    }*/
}
