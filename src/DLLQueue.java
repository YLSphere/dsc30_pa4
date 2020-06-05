/**
 * Queue implementation using Doubly-linked list.
 * @param <T> generic container
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    public DLLQueue() {
        queue = new DoublyLinkedList<>();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(T data) {
        queue.add(data);
    }

    public T dequeue() {
        return queue.remove(0);
    }

    public T peek() {
        return queue.get(0);
    }

}
