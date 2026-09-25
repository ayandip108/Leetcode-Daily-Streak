class Solution {

    private String s;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++; // skip comma
            result.addAll(parseTerm());
        }

        return result;
    }

    // term = factor factor ...
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != '}'
                && s.charAt(idx) != ',') {

            Set<String> factor = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : factor) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // factor = letter OR '{' expression '}'
    private Set<String> parseFactor() {

        if (s.charAt(idx) == '{') {
            idx++; // skip '{'

            Set<String> result = parseExpression();

            idx++; // skip '}'

            return result;
        }

        Set<String> result = new HashSet<>();
        result.add(String.valueOf(s.charAt(idx)));

        idx++;

        return result;
    }
}