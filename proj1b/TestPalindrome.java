import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome("txt"));
        assertFalse(palindrome.isPalindrome("dog"));
        assertTrue(palindrome.isPalindrome("T"));
        assertFalse(palindrome.isPalindrome("Carac"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindrome_CharacterComparatorOffByOne(){
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("cat",cc));
        assertTrue(palindrome.isPalindrome("aBeAb",cc));
        assertTrue(palindrome.isPalindrome("T",cc));
        assertFalse(palindrome.isPalindrome("Carac",cc));
        assertTrue(palindrome.isPalindrome("eExFd",cc));
    }

    @Test
    public void testIsPalindrome_CharacterComparatorOffByN(){
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("afwaf",cc));
        assertFalse(palindrome.isPalindrome("aBeAb",cc));
        assertTrue(palindrome.isPalindrome("T",cc));
        assertTrue(palindrome.isPalindrome("",cc));
        assertFalse(palindrome.isPalindrome("Carac",cc));
        assertTrue(palindrome.isPalindrome("bG1Bg",cc));
    }
}
//Uncomment this class once you've created your Palindrome class.