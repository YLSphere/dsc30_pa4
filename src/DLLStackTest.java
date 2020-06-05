import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLStackTest {
    DLLStack test;

    @Before
    public void setup() {
        test = new DLLStack();
    }

    @Test
    public void size() {
        assertEquals(0, test.size());

        test.push(1);
        test.push(6);
        assertEquals(2, test.size());

        test.pop();
        assertEquals(1, test.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(test.isEmpty());

        test.push(1);
        assertFalse(test.isEmpty());

        test.pop();
        assertTrue(test.isEmpty());

    }

    @Test
    public void push() {
        test.push(1);
        assertEquals(1, test.peek());

        test.push(4);
        assertEquals(4, test.peek());

        test.pop();
        test.push(2);
        assertEquals(2, test.peek());


    }

    @Test
    public void pop() {
        assertEquals(null, test.pop());

        test.push(1);
        test.pop();
        assertTrue(test.isEmpty());

        test.push(1);
        test.push(2);
        test.pop();
        test.push(3);
        assertEquals(3, test.pop());
    }

    @Test
    public void peek() {
        assertEquals(null, test.peek());

        test.push(4);
        assertEquals(4, test.peek());

        test.pop();
        test.push(1);
        test.push(2);
        assertEquals(2, test.peek());
    }
}