class Solution {
    /**
     * Finds the minimum number of people to teach a new language so that
     * all friendships can communicate.
     * 
     * @param n           The total number of languages available (1 to n)
     * @param languages   2D array where languages[i] contains languages known by person i+1
     * @param friendships 2D array where each element [u, v] represents a friendship
     * @return The minimum number of people that need to be taught a new language
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Set to store people who cannot communicate with at least one friend
        Set<Integer> peopleNeedingHelp = new HashSet<>();
      
        // Check each friendship pair to find those who cannot communicate
        for (int[] friendship : friendships) {
            int person1 = friendship[0];
            int person2 = friendship[1];
          
            // If these two friends don't share a common language
            if (!haveCommonLanguage(person1, person2, languages)) {
                peopleNeedingHelp.add(person1);
                peopleNeedingHelp.add(person2);
            }
        }
      
        // If everyone can already communicate, no teaching needed
        if (peopleNeedingHelp.isEmpty()) {
            return 0;
        }
      
        // Count how many people needing help already know each language
        int[] languageCount = new int[n + 1];  // Index 0 unused, languages are 1 to n
      
        for (int person : peopleNeedingHelp) {
            // For each language this person knows
            for (int language : languages[person - 1]) {  // person-1 because array is 0-indexed
                languageCount[language]++;
            }
        }
      
        // Find the language known by the most people who need help
        int maxPeopleKnowingLanguage = 0;
        for (int count : languageCount) {
            maxPeopleKnowingLanguage = Math.max(maxPeopleKnowingLanguage, count);
        }
      
        // The minimum people to teach = total people needing help - those who already know the best language
        return peopleNeedingHelp.size() - maxPeopleKnowingLanguage;
    }
  
    /**
     * Checks if two people share at least one common language.
     * 
     * @param person1   First person (1-indexed)
     * @param person2   Second person (1-indexed)
     * @param languages 2D array of languages known by each person
     * @return true if they share a common language, false otherwise
     */
    private boolean haveCommonLanguage(int person1, int person2, int[][] languages) {
        // Check each language of person1 against each language of person2
        for (int language1 : languages[person1 - 1]) {  // person1-1 for 0-indexed array
            for (int language2 : languages[person2 - 1]) {  // person2-1 for 0-indexed array
                if (language1 == language2) {
                    return true;  // Found a common language
                }
            }
        }
        return false;  // No common language found
    }
}
