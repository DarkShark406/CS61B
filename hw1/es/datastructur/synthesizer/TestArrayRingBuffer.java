package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void testCapacity() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(9);
        assertEquals(9, arb.capacity());
        assertEquals(0, arb.fillCount());
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(3);
        assertEquals(1, arb.fillCount());
        arb.enqueue(2);
        arb.enqueue(1);
        assertEquals(3, arb.fillCount());
        assertTrue(arb.isFull());
        assertFalse(arb.isEmpty());
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2, arb.fillCount());

        assertEquals(2, (int) arb.dequeue());
        assertEquals(1, arb.fillCount());

        assertNotEquals(2, (int) arb.dequeue());
        assertEquals(0, arb.fillCount());
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(5);
        arb1.enqueue(1);
        arb1.enqueue(2);
        arb1.enqueue(3);

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(5);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);

        assertEquals(arb1, arb2);

        ArrayRingBuffer<Integer> arb3 = new ArrayRingBuffer<>(9);
        arb3.enqueue(1);
        arb3.enqueue(2);
        arb3.enqueue(3);

        assertEquals(arb1, arb3);
        assertEquals(arb2, arb3);
    }
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }
}
