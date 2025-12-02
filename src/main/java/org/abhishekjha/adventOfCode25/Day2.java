package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

/**
 * You get inside and take the elevator to its only other stop: the gift shop. "Thank you for visiting the North Pole!" gleefully exclaims a nearby sign. You aren't sure who is even allowed to visit the North Pole, but you know you can access the lobby through here, and from there you can access the rest of the North Pole base.
 *
 * As you make your way through the surprisingly extensive selection, one of the clerks recognizes you and asks for your help.
 *
 * As it turns out, one of the younger Elves was playing on a gift shop computer and managed to add a whole bunch of invalid product IDs to their gift shop database! Surely, it would be no trouble for you to identify the invalid product IDs for them, right?
 *
 * They've even checked most of the product ID ranges already; they only have a few product ID ranges (your puzzle input) that you'll need to check. For example:
 *
 * 11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
 * 1698522-1698528,446443-446449,38593856-38593862,565653-565659,
 * 824824821-824824827,2121212118-2121212124
 * (The ID ranges are wrapped here for legibility; in your input, they appear on a single long line.)
 *
 * The ranges are separated by commas (,); each range gives its first ID and last ID separated by a dash (-).
 *
 * Since the young Elf was just doing silly patterns, you can find the invalid IDs by looking for any ID which is made only of some sequence of digits repeated twice. So, 55 (5 twice), 6464 (64 twice), and 123123 (123 twice) would all be invalid IDs.
 *
 * None of the numbers have leading zeroes; 0101 isn't an ID at all. (101 is a valid ID that you would ignore.)
 *
 * Your job is to find all of the invalid IDs that appear in the given ranges. In the above example:
 *
 * 11-22 has two invalid IDs, 11 and 22.
 * 95-115 has one invalid ID, 99.
 * 998-1012 has one invalid ID, 1010.
 * 1188511880-1188511890 has one invalid ID, 1188511885.
 * 222220-222224 has one invalid ID, 222222.
 * 1698522-1698528 contains no invalid IDs.
 * 446443-446449 has one invalid ID, 446446.
 * 38593856-38593862 has one invalid ID, 38593859.
 * The rest of the ranges contain no invalid IDs.
 * Adding up all the invalid IDs in this example produces 1227775554.
 *
 * What do you get if you add up all of the invalid IDs?
 */

/**
 * --- Part Two ---
 * The clerk quickly discovers that there are still invalid IDs in the ranges in your list. Maybe the young Elf was doing other silly patterns as well?
 *
 * Now, an ID is invalid if it is made only of some sequence of digits repeated at least twice. So, 12341234 (1234 two times), 123123123 (123 three times), 1212121212 (12 five times), and 1111111 (1 seven times) are all invalid IDs.
 *
 * From the same example as before:
 *
 * 11-22 still has two invalid IDs, 11 and 22.
 * 95-115 now has two invalid IDs, 99 and 111.
 * 998-1012 now has two invalid IDs, 999 and 1010.
 * 1188511880-1188511890 still has one invalid ID, 1188511885.
 * 222220-222224 still has one invalid ID, 222222.
 * 1698522-1698528 still contains no invalid IDs.
 * 446443-446449 still has one invalid ID, 446446.
 * 38593856-38593862 still has one invalid ID, 38593859.
 * 565653-565659 now has one invalid ID, 565656.
 * 824824821-824824827 now has one invalid ID, 824824824.
 * 2121212118-2121212124 now has one invalid ID, 2121212121.
 * Adding up all the invalid IDs in this example produces 4174379265.
 *
 * What do you get if you add up all of the invalid IDs using these new rules?
 */
public class Day2 {
    static boolean isInvalidID(long id) {
        String s = Long.toString(id);
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }
        String firstHalf = s.substring(0, len / 2);
        String secondHalf = s.substring(len / 2);
        return firstHalf.equals(secondHalf);
    }

    static long solvePart1(String input) {
        String[] ranges = input.split(",");
        long totalInvalidSum = 0;

        for (String range : ranges) {
            String[] bounds = range.split("-");
            long start = Long.parseLong(bounds[0]);
            long end = Long.parseLong(bounds[1]);

            for (long id = start; id <= end; id++) {
                if (isInvalidID(id)) {
                    totalInvalidSum += id;
                }
            }
        }

        return totalInvalidSum;
    }

    static long solvePart2(String input) {
        String[] ranges = input.split(",");
        long totalInvalidSum = 0;

        for (String range : ranges) {
            String[] bounds = range.split("-");
            long start = Long.parseLong(bounds[0]);
            long end = Long.parseLong(bounds[1]);

            for (long id = start; id <= end; id++) {
                String s = Long.toString(id);
                int len = s.length();
                boolean isInvalid = false;

                for (int subLen = 1; subLen <= len / 2; subLen++) {
                    if (len % subLen == 0) {
                        String subStr = s.substring(0, subLen);
                        StringBuilder repeated = new StringBuilder();
                        int repeatCount = len / subLen;
                        repeated.append(subStr.repeat(repeatCount));
                        if (repeated.toString().equals(s)) {
                            isInvalid = true;
                            break;
                        }
                    }
                }

                if (isInvalid) {
                    totalInvalidSum += id;
                }
            }
        }

        return totalInvalidSum;
    }

    public static void main(String[] args) {
        String input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

        String content = FileUtil.readFileAsString("src/main/java/org/abhishekjha/adventOfCode25/input/Day2.txt");
        input = content.trim();

        long resultPart1 = solvePart1(input);
        long resultPart2 = solvePart2(input);
        System.out.println("Part 1: The sum of all invalid IDs is: " + resultPart1);
        System.out.println("Part 2: The sum of all invalid IDs with new rules is: " + resultPart2);
    }
}
