//possible anagrams

//using hashmap to keep track of count of characters we have seen on a given sliding window once it reaches the map size add it to result
// time complexity : O(m+n)
//space complexity: O(1) considerong only small case english letters in hash map
class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> freqMap = new HashMap<>();

        int n = p.length();

        for (int i = 0; i < p.length(); i++) {
            freqMap.put(
                p.charAt(i),
                freqMap.getOrDefault(p.charAt(i), 0) + 1
            );
        }

        int match = 0;

        for (int i = 0; i < s.length(); i++) {

            // Add current character to window
            char c = s.charAt(i);

            if (freqMap.containsKey(c)) {

                int freq = freqMap.get(c);
                freq--;

                freqMap.put(c, freq);

                if (freq == 0) {
                    match++;
                }
            }

            // Remove character leaving window
            if (i >= n) {

                char out = s.charAt(i - n);

                if (freqMap.containsKey(out)) {

                    int newfreq = freqMap.get(out);

                    newfreq++;

                    freqMap.put(out, newfreq);

                    if (newfreq == 1) {
                        match--;
                    }
                }
            }

            if (match == freqMap.size()) {
                result.add(i - n + 1);
            }
        }

        return result;
    }
}
