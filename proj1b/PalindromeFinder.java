/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        String longestPalindromeOffByN_string;
        int maxPaliromeOffByN = 0;
        for (int N=0;N<26;N++){
            In in = new In("../library-sp19/data/words.txt");
            Palindrome palindrome = new Palindrome();
            CharacterComparator cc = new OffByN(N);
            int longestPalindromeOffByN = 0;
            int countPalindromeOffByN = 0;
            System.out.println("N = " + N);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word,cc)) {
                    System.out.println(word);
                    countPalindromeOffByN++;
                    if (word.length() > longestPalindromeOffByN){
                        longestPalindromeOffByN = word.length();
                        longestPalindromeOffByN_string = word;
                    }
                }
            }
            if (maxPaliromeOffByN < countPalindromeOffByN)
                maxPaliromeOffByN = countPalindromeOffByN;
        }
        System.out.println("The most palindromes in English ");
    }
} //Uncomment this class once you've written isPalindrome.