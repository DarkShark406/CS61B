import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('c', 'd'));
        assertFalse(offByOne.equalChars('z', 'z'));
        assertTrue(offByOne.equalChars('m', 'n'));
        assertTrue(offByOne.equalChars('M', 'N'));
    }
}