class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        StringBuilder sb = new StringBuilder();

        HashMap<String, String> map = new HashMap<>();

        // Store every key-value pair for quick lookup.
        for (List<String> l : knowledge) {
            map.put(l.get(0), l.get(1));
        }

        int open = -1;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                // Store the position of the opening bracket.
                open = i;
            }

            else if (s.charAt(i) == ')') {

                // Extract the key between '(' and ')'.
                String key = s.substring(open + 1, i);

                // Replace the key with its value or '?' if unknown.
                if (map.containsKey(key)) {
                    sb.append(map.get(key));
                } else {
                    sb.append("?");
                }

                open = -1;
            }

            else if (open == -1) {
                // Characters outside brackets are added directly.
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
