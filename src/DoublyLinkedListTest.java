import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    DoublyLinkedList test;
    @Before
    public void setup() {
        test = new DoublyLinkedList();

    }

    @Test (expected = NullPointerException.class)
    public void addEx() {
        test.add(null);


    }

    @Test
    public void add() {
        test.add(1);
        assertEquals(1, test.size());
        test.add(3);
        assertEquals("[(head) -> 1 -> 3 -> (tail)]", test.toString());
        test.add("Hello");
        assertEquals("Hello", test.get(2));

    }

    @Test
    public void addIndex() {
        test.add(0,1);

        assertEquals(1, test.size());
        test.add(1,2);
        assertEquals(2, test.get(1));

        test.add(0,0);
        assertEquals(0, test.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void addIndexEx1() {
        test.add(-1,1);
        test.add(9,1);
    }
    @Test (expected = NullPointerException.class)
    public void addIndexEx2() {
        test.add(0,null);
    }

    @Test
    public void clear() {
        test.add(2);
        test.add(5);
        test.clear();
        assertEquals("[(head) -> (tail)]", test.toString());
        test.add(5);
        test.clear();
        assertEquals("[(head) -> (tail)]", test.toString());
        test.add(5);
        test.clear();
        assertEquals(0, test.size());

    }

    @Test
    public void contains() {
        test.add(5);
        test.add("I love ashe");
        assertTrue(test.contains("I love ashe"));

        test.clear();
        assertFalse(test.contains(1));

        test.add("Get me out of bronze pls");
        assertFalse(test.contains(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getEx() {
        test.get(9);
        test.get(-9);


    }
    @Test
    public void get() {
        test.add(1);
        assertEquals(1, test.get(0));

        test.add(2);
        test.add(1, 0.5);
        assertEquals(0.5, test.get(1));

        assertEquals(2, test.get(2));
    }

    @Test
    public void isEmpty() {
        test.add(1);
        assertFalse(test.isEmpty());

        test.clear();
        assertTrue(test.isEmpty());

        test.add(1);
        test.add(1);
        test.add(1);
        test.add(1);
        assertFalse(test.isEmpty());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeEx() {
        test.add(1);
        test.remove(9);
    }

    @Test
    public void remove() {
        test.add(1);
        test.remove(0);
        assertTrue(test.isEmpty());

        test.add(1);
        test.add(1);
        test.add(1);
        test.add(2);
        test.remove(3);
        assertFalse(test.contains(2));

        test.remove(2);
        assertEquals(2, test.size());
    }



    @Test (expected = NullPointerException.class)
    public void setEx1() {
        test.add(1);
        test.set(0, null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setEx2() {
        test.add(1);
        test.set(2, 0);
    }

    @Test
    public void set() {
        test.add(1);
        test.set(0, 0);
        assertEquals(0, test.get(0));

        assertEquals(1, test.size());

        test.add(1);
        test.set(1, "Hello");
        assertEquals("Hello", test.get(1));
    }

    @Test
    public void size() {
        assertEquals(0, test.size());

        test.add(0);
        assertEquals(1, test.size());

        test.remove(0);
        assertEquals(0, test.size());
    }

    @Test
    public void testToString() {
        assertEquals("[(head) -> (tail)]", test.toString());

        test.add(1);
        assertEquals("[(head) -> 1 -> (tail)]", test.toString());

        test.add(2);
        test.add(1, "Middle?");
        assertEquals("[(head) -> 1 -> Middle? -> 2 -> (tail)]", test.toString());
    }
}