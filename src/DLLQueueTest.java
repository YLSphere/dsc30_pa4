import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DLLQueueTest {

    DLLQueue test;

    @Before
    public void setup() {
        test = new DLLQueue();
    }

    @Test
    public void size() {
        assertEquals(0, test.size());

        test.enqueue(1);
        test.enqueue(6);
        assertEquals(2, test.size());

        test.dequeue();
        assertEquals(1, test.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(test.isEmpty());

        test.enqueue(1);
        assertFalse(test.isEmpty());

        test.dequeue();
        assertTrue(test.isEmpty());
    }

    @Test
    public void enqueue() {
        test.enqueue(1);
        assertEquals(1, test.peek());

        test.enqueue(4);
        assertEquals(4, test.peek());

        test.dequeue();
        test.enqueue(2);
        assertEquals(2, test.peek());
    }

    @Test
    public void dequeue() {
        assertEquals(null, test.dequeue());

        test.enqueue(1);
        test.dequeue();
        assertTrue(test.isEmpty());

        test.enqueue(1);
        test.enqueue(2);
        test.dequeue();
        test.enqueue(3);
        assertEquals(2, test.dequeue());
    }

    @Test
    public void peek() {
        assertEquals(null, test.peek());

        test.enqueue(4);
        assertEquals(4, test.peek());

        test.dequeue();
        test.enqueue(1);
        test.enqueue(2);
        assertEquals(1, test.peek());
    }
}