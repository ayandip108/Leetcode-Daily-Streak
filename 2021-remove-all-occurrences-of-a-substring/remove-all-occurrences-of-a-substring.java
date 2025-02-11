class Solution {

    /**
     * Removes all occurrences of the substring 'part' from the string 's'.
     *
     * @param s    The original string from which occurrences of 'part' will be removed.
     * @param part The substring to be removed from 's'.
     * @return The modified string with all occurrences of 'part' removed.
     */
    public String removeOccurrences(String s, String part) {
        // Keep removing 'part' from 's' while 's' contains 'part'
        while (s.contains(part)) {
            // Replace the first occurrence of 'part' in 's' with an empty string
            s = s.replaceFirst(part, "");
        }
        return s;
    }
}