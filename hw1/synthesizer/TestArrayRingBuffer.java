package synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);

        Assert.assertEquals(arb.fillCount, 3);
        Assert.assertEquals(arb.isEmpty(),false);
        Assert.assertEquals(arb.capacity,10);
        Assert.assertEquals(arb.peek(),3);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
