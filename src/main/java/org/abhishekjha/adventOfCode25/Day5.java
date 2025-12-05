package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 5: Cafeteria ---
 * As the forklifts break through the wall, the Elves are delighted to discover that there was a cafeteria on the other side after all.
 *
 * You can hear a commotion coming from the kitchen. "At this rate, we won't have any time left to put the wreaths up in the dining hall!" Resolute in your quest, you investigate.
 *
 * "If only we hadn't switched to the new inventory management system right before Christmas!" another Elf exclaims. You ask what's going on.
 *
 * The Elves in the kitchen explain the situation: because of their complicated new inventory management system, they can't figure out which of their ingredients are fresh and which are spoiled. When you ask how it works, they give you a copy of their database (your puzzle input).
 *
 * The database operates on ingredient IDs. It consists of a list of fresh ingredient ID ranges, a blank line, and a list of available ingredient IDs. For example:
 *
 * 3-5
 * 10-14
 * 16-20
 * 12-18
 *
 * 1
 * 5
 * 8
 * 11
 * 17
 * 32
 * The fresh ID ranges are inclusive: the range 3-5 means that ingredient IDs 3, 4, and 5 are all fresh. The ranges can also overlap; an ingredient ID is fresh if it is in any range.
 *
 * The Elves are trying to determine which of the available ingredient IDs are fresh. In this example, this is done as follows:
 *
 * Ingredient ID 1 is spoiled because it does not fall into any range.
 * Ingredient ID 5 is fresh because it falls into range 3-5.
 * Ingredient ID 8 is spoiled.
 * Ingredient ID 11 is fresh because it falls into range 10-14.
 * Ingredient ID 17 is fresh because it falls into range 16-20 as well as range 12-18.
 * Ingredient ID 32 is spoiled.
 * So, in this example, 3 of the available ingredient IDs are fresh.
 *
 * Process the database file from the new inventory management system. How many of the available ingredient IDs are fresh?
 *
 * Your puzzle answer was 617.
 *
 * --- Part Two ---
 * The Elves start bringing their spoiled inventory to the trash chute at the back of the kitchen.
 *
 * So that they can stop bugging you when they get new inventory, the Elves would like to know all of the IDs that the fresh ingredient ID ranges consider to be fresh. An ingredient ID is still considered fresh if it is in any range.
 *
 * Now, the second section of the database (the available ingredient IDs) is irrelevant. Here are the fresh ingredient ID ranges from the above example:
 *
 * 3-5
 * 10-14
 * 16-20
 * 12-18
 * The ingredient IDs that these ranges consider to be fresh are 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, and 20. So, in this example, the fresh ingredient ID ranges consider a total of 14 ingredient IDs to be fresh.
 *
 * Process the database file again. How many ingredient IDs are considered to be fresh according to the fresh ingredient ID ranges?
 *
 * Your puzzle answer was 338258295736104.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 */
public class Day5 {
    static int solvePart1(String[] inv, String[] ins) {
        int freshCount = 0;
        for (String s : ins) {
            long sLong = Long.parseLong(s.trim());
            for (String string : inv) {
                long start = Long.parseLong(string.split("-")[0].trim());
                long end = Long.parseLong(string.split("-")[1].trim());
                if (sLong >= start && sLong <= end) {
                    freshCount++;
                    break;
                }
            }
        }
        return freshCount;
    }
    static long solvePart2(String[] inv) {
        List<long[]> ranges = new ArrayList<>();
        for (String s : inv) {
            long start = Long.parseLong(s.split("-")[0].trim());
            long end = Long.parseLong(s.split("-")[1].trim());
            ranges.add(new long[]{start, end});
        }
        Collections.sort(ranges, (a, b) -> Long.compare(a[0], b[0]));

        List<long[]> merged = new ArrayList<>();
        for (long[] range : ranges) {
            if (merged.isEmpty() || merged.getLast()[1] < range[0] - 1) {
                merged.add(range);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], range[1]);
            }
        }
        long totalCount = 0;
        for (long[] range : merged) {
            totalCount += (range[1] - range[0] + 1);
        }
        return totalCount;
    }
    public static void main(String[] args) {
        String input = "3-5\n" +
                "10-14\n" +
                "16-20\n" +
                "12-18\n" +
                "\n" +
                "1\n" +
                "5\n" +
                "8\n" +
                "11\n" +
                "17\n" +
                "32";

        input = FileUtil.readFileAsString("adventOfCode25/input/Day5.txt");

        String[] data = input.split("\n\n");

        String[] inventoryLines = data[0].split("\n");
        String[] instructionLines = data[1].split("\n");

        int resultPart1 = solvePart1(inventoryLines, instructionLines);
        System.out.println("Part 1 Result: " + resultPart1);
        long resultPart2 = solvePart2(inventoryLines);
        System.out.println("Part 2 Result: " + resultPart2);
    }
}
