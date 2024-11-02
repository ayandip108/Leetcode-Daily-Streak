class Solution {
    // Method to truncate the sentence to the first k words
    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ' && (--k) == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    // Method to check if a sentence is "circular"
    public boolean isCircularSentence(String s) {
        // Split the sentence into words
        String[] words = s.split(" ");
        
        // Check if the last character of each word matches the first character of the next word
        for (int i = 0; i < words.length; i++) {
            char lastChar = words[i].charAt(words[i].length() - 1);
            char firstChar = words[(i + 1) % words.length].charAt(0);
            if (lastChar != firstChar) {
                return false;
            }
        }
        return true;
    }
}
