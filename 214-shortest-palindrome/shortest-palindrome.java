class Solution {
    public String shortestPalindrome(String s) {
        String ss = s + "#" + new StringBuilder(s).reverse().toString();
        int len = ss.length();
        int[] next = new int[len];
        next[0] = -1;
        for(int i = 1, j = -1; i < len; i++){
            while(j > -1 && ss.charAt(i) != ss.charAt(j + 1)){
                j = next[j];
            }
            if(ss.charAt(j + 1) == ss.charAt(i)) j++;
            next[i] = j;
        }
        return new StringBuilder(ss.substring(next[len - 1] + 1, s.length())).reverse().toString() + s;
    }
}