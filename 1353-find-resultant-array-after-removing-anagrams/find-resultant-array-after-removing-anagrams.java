class Solution {
    /**
     * Removes consecutive anagrams from the array, keeping only the first occurrence
     * @param words Array of strings to process
     * @return List of strings with consecutive anagrams removed
     */
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
      
        // Always add the first word
        result.add(words[0]);
      
        // Check each word against its predecessor
        for (int i = 1; i < words.length; i++) {
            // If current word is NOT an anagram of the previous word, add it
            if (isNotAnagram(words[i - 1], words[i])) {
                result.add(words[i]);
            }
        }
      
        return result;
    }
  
    /**
     * Checks if two strings are NOT anagrams of each other
     * @param firstWord First string to compare
     * @param secondWord Second string to compare
     * @return true if strings are NOT anagrams, false if they are anagrams
     */
    private boolean isNotAnagram(String firstWord, String secondWord) {
        // Different lengths mean they cannot be anagrams
        if (firstWord.length() != secondWord.length()) {
            return true;
        }
      
        // Count frequency of each character in the first word
        int[] characterCount = new int[26];
        for (int i = 0; i < firstWord.length(); i++) {
            characterCount[firstWord.charAt(i) - 'a']++;
        }
      
        // Decrement count for each character in the second word
        for (int i = 0; i < secondWord.length(); i++) {
            // If count goes negative, words have different character frequencies
            if (--characterCount[secondWord.charAt(i) - 'a'] < 0) {
                return true;
            }
        }
      
        // All character counts matched - words are anagrams
        return false;
    }
}