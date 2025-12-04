package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

/**
 * --- Day 3: Lobby ---
 * You descend a short staircase, enter the surprisingly vast lobby, and are quickly cleared by the security checkpoint. When you get to the main elevators, however, you discover that each one has a red light above it: they're all offline.
 *
 * "Sorry about that," an Elf apologizes as she tinkers with a nearby control panel. "Some kind of electrical surge seems to have fried them. I'll try to get them online soon."
 *
 * You explain your need to get further underground. "Well, you could at least take the escalator down to the printing department, not that you'd get much further than that without the elevators working. That is, you could if the escalator weren't also offline."
 *
 * "But, don't worry! It's not fried; it just needs power. Maybe you can get it running while I keep working on the elevators."
 *
 * There are batteries nearby that can supply emergency power to the escalator for just such an occasion. The batteries are each labeled with their joltage rating, a value from 1 to 9. You make a note of their joltage ratings (your puzzle input). For example:
 *
 * 987654321111111
 * 811111111111119
 * 234234234234278
 * 818181911112111
 * The batteries are arranged into banks; each line of digits in your input corresponds to a single bank of batteries. Within each bank, you need to turn on exactly two batteries; the joltage that the bank produces is equal to the number formed by the digits on the batteries you've turned on. For example, if you have a bank like 12345 and you turn on batteries 2 and 4, the bank would produce 24 jolts. (You cannot rearrange batteries.)
 *
 * You'll need to find the largest possible joltage each bank can produce. In the above example:
 *
 * In 987654321111111, you can make the largest joltage possible, 98, by turning on the first two batteries.
 * In 811111111111119, you can make the largest joltage possible by turning on the batteries labeled 8 and 9, producing 89 jolts.
 * In 234234234234278, you can make 78 by turning on the last two batteries (marked 7 and 8).
 * In 818181911112111, the largest joltage you can produce is 92.
 * The total output joltage is the sum of the maximum joltage from each bank, so in this example, the total output joltage is 98 + 89 + 78 + 92 = 357.
 *
 * There are many batteries in front of you. Find the maximum joltage possible from each bank; what is the total output joltage?
 *
 * --- Part Two ---
 * The escalator doesn't move. The Elf explains that it probably needs more joltage to overcome the static friction of the system and hits the big red "joltage limit safety override" button. You lose count of the number of times she needs to confirm "yes, I'm sure" and decorate the lobby a bit while you wait.
 *
 * Now, you need to make the largest joltage by turning on exactly twelve batteries within each bank.
 *
 * The joltage output for the bank is still the number formed by the digits of the batteries you've turned on; the only difference is that now there will be 12 digits in each bank's joltage output instead of two.
 *
 * Consider again the example from before:
 *
 * 987654321111111
 * 811111111111119
 * 234234234234278
 * 818181911112111
 * Now, the joltages are much larger:
 *
 * In 987654321111111, the largest joltage can be found by turning on everything except some 1s at the end to produce 987654321111.
 * In the digit sequence 811111111111119, the largest joltage can be found by turning on everything except some 1s, producing 811111111119.
 * In 234234234234278, the largest joltage can be found by turning on everything except a 2 battery, a 3 battery, and another 2 battery near the start to produce 434234234278.
 * In 818181911112111, the joltage 888911112111 is produced by turning on everything except some 1s near the front.
 * The total output joltage is now much larger: 987654321111 + 811111111119 + 434234234278 + 888911112111 = 3121910778619.
 *
 * What is the new total output joltage?
 */
public class Day3 {
    static int solvePart1(String[] lines) {
        int sum = 0;
        for (String line : lines) {
            int maxTillNow = Integer.parseInt(String.valueOf(line.charAt(line.length() - 1)));
            int ans = maxTillNow;
            for (int j = line.length() - 2; j >= 0; j--) {
                int currentDigit = Integer.parseInt(String.valueOf(line.charAt(j)));
                int currAns = currentDigit * 10 + maxTillNow;
                if (currAns > ans) {
                    ans = currAns;
                }
                if (currentDigit > maxTillNow) {
                    maxTillNow = currentDigit;
                }
            }
            sum += ans;
        }

        return sum;
    }
    static long solvePart2(String[] lines, int totalDigits) {
        long sum = 0;
        for (String line : lines) {
            int startIndex = 0;
            int endIndex = line.length() - totalDigits;
            long num = 0;
            for (int k = 0; k < totalDigits; k++) {
                long maxDigit = -1;
                int maxIndex = -1;
                // find largest digit bw startIndex and endIndex
                for (int i = startIndex; i <= endIndex; i++) {
                    int currentDigit = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (currentDigit > maxDigit) {
                        maxDigit = currentDigit;
                        maxIndex = i;
                    }
                }
                num = num * 10L + maxDigit;
                startIndex = maxIndex + 1;
                endIndex++;
            }
            sum += num;
        }
        return sum;
    }
    public static void main(String[] args) {
        String input = "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111";
        input = FileUtil.readFileAsString("adventOfCode25/input/Day3.txt");
        String[] lines = input.split("\n");
        int resultPart1 = solvePart1(lines);
        System.out.println("Part 1 Result: " + resultPart1);
        long resultPart2 = solvePart2(lines, 12);
        System.out.println("Part 2 Result: " + resultPart2);
    }
}
