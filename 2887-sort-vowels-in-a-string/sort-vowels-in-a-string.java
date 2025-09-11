class Solution {
    /**
     * Sorts the vowels in a string while keeping consonants in their original positions.
     * Vowels are sorted in ascending order based on ASCII values.
     * 
     * @param s the input string to process
     * @return a new string with vowels sorted and consonants unchanged
     */
    public String sortVowels(String s) {
        // List to store all vowels found in the string
        List<Character> vowelsList = new ArrayList<>();
      
        // Convert string to character array for easier manipulation
        char[] charArray = s.toCharArray();
      
        // First pass: Extract all vowels from the string
        for (char currentChar : charArray) {
            // Convert to lowercase for vowel checking
            char lowerCaseChar = Character.toLowerCase(currentChar);
          
            // Check if the character is a vowel
            if (lowerCaseChar == 'a' || lowerCaseChar == 'e' || 
                lowerCaseChar == 'i' || lowerCaseChar == 'o' || 
                lowerCaseChar == 'u') {
                // Add the original character (preserving case) to vowels list
                vowelsList.add(currentChar);
            }
        }
      
        // Sort the vowels in ascending order (based on ASCII values)
        Collections.sort(vowelsList);
      
        // Second pass: Replace vowels in original positions with sorted vowels
        int vowelIndex = 0; // Index to track position in sorted vowels list
      
        for (int i = 0; i < charArray.length; i++) {
            // Convert to lowercase for vowel checking
            char lowerCaseChar = Character.toLowerCase(charArray[i]);
          
            // If current position contains a vowel, replace with sorted vowel
            if (lowerCaseChar == 'a' || lowerCaseChar == 'e' || 
                lowerCaseChar == 'i' || lowerCaseChar == 'o' || 
                lowerCaseChar == 'u') {
                // Replace with the next sorted vowel
                charArray[i] = vowelsList.get(vowelIndex);
                vowelIndex++;
            }
        }
      
        // Convert character array back to string and return
        return String.valueOf(charArray);
    }
}