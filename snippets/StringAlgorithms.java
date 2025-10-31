// String Algorithms - Common string manipulation and pattern matching
public class StringAlgorithms {
    // KMP (Knuth-Morris-Pratt) - Pattern matching
    static int[] buildKMPTable(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    static int kmpSearch(String text, String pattern) {
        int[] lps = buildKMPTable(pattern);
        int i = 0, j = 0;

        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                return i - j; // Found at index
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    // Reverse a string
    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // Check if palindrome
    static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // Count character frequency
    static java.util.Map<Character, Integer> charFrequency(String s) {
        java.util.Map<Character, Integer> freq = new java.util.HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    // Longest substring without repeating characters
    static int longestSubstringWithoutRepeat(String s) {
        java.util.Map<Character, Integer> lastIndex = new java.util.HashMap<>();
        int maxLen = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (lastIndex.containsKey(s.charAt(i))) {
                start = Math.max(start, lastIndex.get(s.charAt(i)) + 1);
            }
            lastIndex.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    // Generate all permutations of a string
    static void permute(String s, int l, int r, java.util.List<String> result) {
        if (l == r) {
            result.add(s);
        } else {
            char[] chars = s.toCharArray();
            for (int i = l; i <= r; i++) {
                char temp = chars[l];
                chars[l] = chars[i];
                chars[i] = temp;

                permute(new String(chars), l + 1, r, result);

                temp = chars[l];
                chars[l] = chars[i];
                chars[i] = temp;
            }
        }
    }
}

