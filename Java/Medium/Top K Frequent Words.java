//  https://leetcode.com/problems/top-k-frequent-words/

/*
HashMap to store frequencies
Then put them into an array of Word objects
then sort the array of word objects using first the
frequency, and then the strings themselves
*/

// 5 ms, faster than 85.97%
// 39.1 mb, less than 57.99%
// Solved in 20 minutes 25 seconds

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String word : words)
            if(hashMap.containsKey(word))
                hashMap.put(word, hashMap.get(word) + 1);
            else
                hashMap.put(word, 1);
        
        // After we have filled out the hash map, we should transfer
        // the data onto this array, which we will then sort using the
        // logic given in the description
        Count[] counts = new Count[hashMap.size()];
        
        // fill in array
        Set<String> keys = (Set<String>) hashMap.keySet();
        Iterator it = keys.iterator();
        
        int i = 0;
        String curKey;
        while(it.hasNext()) {
            curKey = (String) it.next();
            counts[i] = new Count(hashMap.get(curKey), curKey);
            i++;
        }
        
        Arrays.sort(counts, (e1, e2) -> {
           // custom sorting logic
            // e1 and e1 are Count objects
            if(e1.freq < e2.freq)
                return 1;
            else if(e1.freq > e2.freq)
                return -1;
            else  // if the have equal frequency, sort lexicographically
                return e1.word.compareTo(e2.word);
        });
        
        // now we create the final array
        List<String> ans = new ArrayList<>();
        for(int c = 0; c < k; c++) {
            ans.add(counts[c].word);
        }
        
        return ans;
    }
    
    class Count {
        public int freq;
        public String word;
        
        public Count(int f, String w) {
            freq = f;
            word = w;
        }
        
        public String toString() {
            return "[" + word + ": " + freq + "] ";
        }
    }
}

/*
Sample input:
["i", "love", "leetcode", "i", "love", "coding"]
2

["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"]
4

["a", "aa", "a", "aa", "a", "aa", "A", "AA", "A", "AA", "A", "AA"]
4

*/

/*
A better approach would likely be to use the Quick Sort algorithm.
*/
