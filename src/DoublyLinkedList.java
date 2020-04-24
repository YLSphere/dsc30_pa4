/*
 * NAME: Yin Lam Lai
 * PID: A15779757
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Yin Lam Lai
 * @since 21/4/2020
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
            nelems++;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            if (nextNode == null) {
                this.prev = prevNode;
                this.next = tail;
                tail.prev = this;
                this.prev.next = this;
                nelems++;
            } else if (prevNode == null) {
                this.next = nextNode;
                this.next = head;
                head.next = this;
                this.next.prev = this;
                nelems++;
            } else {
                this.prev = prevNode;
                prevNode.next = this;
                this.next = nextNode;
                nextNode.prev = this;
                nelems++;
            }
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            Node tempNext = this.getNext();
            Node tempPrev = this.getPrev();
            tempPrev.setNext(tempNext);
            tempNext.setPrev(tempPrev);
            nelems--;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        nelems--;
        nelems--;

        head.setNext(tail);
        tail.setPrev(head);

    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element);
        Node temp = tail.getPrev();

        tail.setPrev(newNode);
        newNode.setNext(tail);
        newNode.setPrev(temp);
        temp.setNext(newNode);
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * TODO: Javadoc comments
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        } else if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (nelems > 0) {
            if (nelems < index) {
                throw new IndexOutOfBoundsException();
            }
        }
        if (nelems == 0) {
            this.add(element);
        } else {
            Node temp = head;
            for (int n = 0; n < index + 1; n++) {
                temp = temp.getNext();
            }
            Node newNode = new Node(element, temp, temp.getPrev());
        }

    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node temp = head;
        for (int n = 0; n < nelems; n++) {
            temp = temp.getNext();
            if (temp.getElement().equals(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (nelems - 1 < index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int n = 0; n < index + 1; n++) {
            temp = temp.getNext();
        }

        return temp.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * TODO: Javadoc comments
     */
    private Node getNth(int index) {
        Node temp = head;
        for (int n = 0; n < index + 1; n++) {
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * Determine if the list empty
     *
     * TODO: javadoc comments
     */
    @Override
    public boolean isEmpty() {
        if (head.getNext() == tail) {
            return true;
        }
        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * TODO: javadoc comments
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (nelems > 0) {
            if (nelems - 1 < index) {
                throw new IndexOutOfBoundsException();
            }
        }
        if (nelems == 1) {
            T temp = this.get(0);
            this.clear();
            return temp;
        } else {
            Node temp = head;
            for (int n = 0; n < index + 1; n++) {
                temp = temp.getNext();
            }
            temp.remove();
            return temp.getElement();
        }

    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        } else if (nelems - 1 < index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        for (int n = 0; n < index + 1; n++) {
            temp = temp.getNext();
        }
        T tempData = temp.getElement();
        temp.setElement(element);
        return tempData;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * TODO: javadoc comments
     */
    @Override
    public String toString() {
        String returned = "(head) -> ";
        Node temp = head;
        for (int n = 0; n < nelems; n++) {
            temp = temp.getNext();
            returned += temp.getElement().toString() + " -> ";
        }
        return "[" + returned + "(tail)]";
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Inserts another linked list of the same type into this one
     *
     * TODO: javadoc comments
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        //TODO: Determine if index is valid

        //TODO: Splicing implementation. HINT: remember DoublyLinkedList's  have dummy nodes
    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * TODO: javadoc comments
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //TODO: Add implementation to find the starting indices

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}