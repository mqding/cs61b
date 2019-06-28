/** interface  contains all methods in LinkedListDeque and ArrayDeque*/
public interface Deque<T> {

    /** the following methods are override by LinkedListDeque */
    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        if (size() ==0) {
            return true;
        }
        return false;
    }

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);
}