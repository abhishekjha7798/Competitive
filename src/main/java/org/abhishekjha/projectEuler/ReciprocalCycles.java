package org.abhishekjha.projectEuler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find the value of d < 1000 for which 1 / d contains the longest recurring cycle in its decimal fraction part.
 */

public class ReciprocalCycles {
    static int getCycleLength(int d) {
        int len = 0;
        int dividend = 10;
        int r = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        while (r != 0) {
            r = dividend % d;
            len++;
            if (map.containsKey(r)) {
                return len - map.get(r);
            }
            map.put(r, len);
            dividend = r * 10;
        }
        return 0;
    }

    static int solve(int limit) {
        int maxLength = 0;
        int resultD = 0;
        for (int d = 2; d < limit; d++) {
            int cycleLength = getCycleLength(d);
//            System.out.println(cycleLength);
            if (cycleLength > maxLength) {
                maxLength = cycleLength;
                resultD = d;
            }
        }
        return resultD;
    }

    public static void main(String[] args) {
        int limit = 1000;
        int result = solve(limit);
        System.out.println("The value of d < " + limit + " for which 1/d contains the longest recurring cycle is: " + result);
    }
}
