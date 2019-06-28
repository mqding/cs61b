public class Palindrome {

    /** convert the word to Deque using str.charAt(int index). */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ans = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }

    /** recursive way to check whether the word is a palindrome. */
    public boolean isPalindrome(String word) {

        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        }else {

            if (wordDeque.removeFirst() == wordDeque.removeLast()) {
                return isPalindrome(dequeToWord(wordDeque));
            } else {
                return false;
            }
        }
    }

    /** helper method to convert deque to word. */
    private String dequeToWord(Deque d) {
        String word = "";
        while (d.size() > 0) {
            word += d.removeFirst();
        }
        return word;
    }

    /** overload Palindrome.isPalindrome method. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        }else {

            if (cc.equalChars(wordDeque.removeFirst(),wordDeque.removeLast())) {
                return isPalindrome(dequeToWord(wordDeque),cc);
            } else {
                return false;
            }
        }
    }

}
