class Solution {
    /**
     * Compares two version strings.
     * @param version1 The first version string (e.g., "1.2.3")
     * @param version2 The second version string (e.g., "1.2.4")
     * @return -1 if version1 < version2, 1 if version1 > version2, 0 if equal
     */
    public int compareVersion(String version1, String version2) {
        int version1Length = version1.length();
        int version2Length = version2.length();
      
        int index1 = 0;
        int index2 = 0;
      
        // Process both version strings simultaneously
        while (index1 < version1Length || index2 < version2Length) {
            // Parse the current revision number from version1
            int revision1 = 0;
            while (index1 < version1Length && version1.charAt(index1) != '.') {
                revision1 = revision1 * 10 + (version1.charAt(index1) - '0');
                index1++;
            }
          
            // Parse the current revision number from version2
            int revision2 = 0;
            while (index2 < version2Length && version2.charAt(index2) != '.') {
                revision2 = revision2 * 10 + (version2.charAt(index2) - '0');
                index2++;
            }
          
            // Compare the current revision numbers
            if (revision1 != revision2) {
                return revision1 < revision2 ? -1 : 1;
            }
          
            // Skip the dot separator for next iteration
            index1++;
            index2++;
        }
      
        // All revisions are equal
        return 0;
    }
}
