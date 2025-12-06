package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * --- Day 6: Trash Compactor ---
 * After helping the Elves in the kitchen, you were taking a break and helping them re-enact a movie scene when you over-enthusiastically jumped into the garbage chute!
 *
 * A brief fall later, you find yourself in a garbage smasher. Unfortunately, the door's been magnetically sealed.
 *
 * As you try to find a way out, you are approached by a family of cephalopods! They're pretty sure they can get the door open, but it will take some time. While you wait, they're curious if you can help the youngest cephalopod with her math homework.
 *
 * Cephalopod math doesn't look that different from normal math. The math worksheet (your puzzle input) consists of a list of problems; each problem has a group of numbers that need to be either added (+) or multiplied (*) together.
 *
 * However, the problems are arranged a little strangely; they seem to be presented next to each other in a very long horizontal list. For example:
 *
 * 123 328  51 64
 *  45 64  387 23
 *   6 98  215 314
 * *   +   *   +
 * Each problem's numbers are arranged vertically; at the bottom of the problem is the symbol for the operation that needs to be performed. Problems are separated by a full column of only spaces. The left/right alignment of numbers within each problem can be ignored.
 *
 * So, this worksheet contains four problems:
 *
 * 123 * 45 * 6 = 33210
 * 328 + 64 + 98 = 490
 * 51 * 387 * 215 = 4243455
 * 64 + 23 + 314 = 401
 * To check their work, cephalopod students are given the grand total of adding together all of the answers to the individual problems. In this worksheet, the grand total is 33210 + 490 + 4243455 + 401 = 4277556.
 *
 * Of course, the actual worksheet is much wider. You'll need to make sure to unroll it completely so that you can read the problems clearly.
 *
 * Solve the problems on the math worksheet. What is the grand total found by adding together all of the answers to the individual problems?
 *
 * Your puzzle answer was 6295830249262.
 *
 * --- Part Two ---
 * The big cephalopods come back to check on how things are going. When they see that your grand total doesn't match the one expected by the worksheet, they realize they forgot to explain how to read cephalopod math.
 *
 * Cephalopod math is written right-to-left in columns. Each number is given in its own column, with the most significant digit at the top and the least significant digit at the bottom. (Problems are still separated with a column consisting only of spaces, and the symbol at the bottom of the problem is still the operator to use.)
 *
 * Here's the example worksheet again:
 *
 * 123 328  51 64
 *  45 64  387 23
 *   6 98  215 314
 * *   +   *   +
 * Reading the problems right-to-left one column at a time, the problems are now quite different:
 *
 * The rightmost problem is 4 + 431 + 623 = 1058
 * The second problem from the right is 175 * 581 * 32 = 3253600
 * The third problem from the right is 8 + 248 + 369 = 625
 * Finally, the leftmost problem is 356 * 24 * 1 = 8544
 * Now, the grand total is 1058 + 3253600 + 625 + 8544 = 3263827.
 *
 * Solve the problems on the math worksheet again. What is the grand total found by adding together all of the answers to the individual problems?
 *
 * Your puzzle answer was 9194682052782.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 */
public class Day6 {
    public static long solvePart1Helper(List<List<Long>> numbers, char[] operations) {
        int rows = numbers.size();
        int cols = numbers.getFirst().size();
        long result = 0;

        for (int j = 0; j < cols; j++) {
            char op = operations[j];
            long colResult = numbers.getFirst().get(j);
            for (int i = 1; i < rows; i++) {
                long num = numbers.get(i).get(j);
                if (op == '+') {
                    colResult += num;
                } else if (op == '*') {
                    colResult *= num;
                }
            }
            result += colResult;
        }
        return result;
    }

    public static long solvePart2Helper(List<List<Long>> numbers, char[] operations) {
        int rows = numbers.size();
        long result = 0;

        for (int i = 0; i < rows; i++) {
            char op = operations[i];
            long colResult = numbers.get(i).getFirst();
            for (int j = 1; j < numbers.get(i).size(); j++) {
                long num = numbers.get(i).get(j);
                if (op == '+') {
                    colResult += num;
                } else if (op == '*') {
                    colResult *= num;
                }
            }
            result += colResult;
        }
        return result;
    }

    public static long solvePart1(String input) {
        String[] lines = input.split("\n");
        List<List<Long>> numbers = new ArrayList<>();
        for (int i = 0; i < lines.length - 1; i++) {
            String[] numStrs = lines[i].trim().split("\\s+");
            List<Long> row = new ArrayList<>();
            for (String numStr : numStrs) {
                row.add(Long.parseLong(numStr));
            }
            numbers.add(row);
        }
        String operationsLine = lines[lines.length - 1].trim();
        char[] operations = operationsLine.replaceAll("\\s+", "").toCharArray();
        return solvePart1Helper(numbers, operations);
    }

    public static long solvePart2(String input) {
        String[] lines = input.split("\n");
        List<List<Long>> numbers = new ArrayList<>();
        int rows = lines.length - 1;
        int cols = lines[0].length();
        List<Long> rowN = new ArrayList<>();
        for (int j = cols-1; j >= -1; j--) {
            boolean flag = false;
            long num = 0;
            if (j >= 0) {
                for (int i = 0; i < rows; i++) {
                    char c = lines[i].charAt(j);
                    if (c != ' ') {
                        int digit = Integer.parseInt(String.valueOf(c));
                        num = num*10L + digit;
                        flag = true;
                    }
                }
            }
            if (flag) {
                rowN.add(num);
            } else {
                if (!rowN.isEmpty()) {
                    List<Long> row = new ArrayList<>(rowN);
                    numbers.add(row);
                    rowN.clear();
                }
            }
        }
        String operationsLine = new StringBuilder(lines[lines.length - 1].trim()).reverse().toString();
        char[] operations = operationsLine.replaceAll("\\s+", "").toCharArray();
        return solvePart2Helper(numbers, operations);
    }

    public static void main(String[] args) {
        String input = "123 328  51 64 \n" +
                " 45 64  387 23 \n" +
                "  6 98  215 314\n" +
                "*   +   *   +  ";
        input = FileUtil.readFileAsString("adventOfCode25/input/Day6.txt");


        long resultPart1 = solvePart1(input);
        System.out.println("Part 1 Result: " + resultPart1);
        long resultPart2 = solvePart2(input);
        System.out.println("Part 2 Result: " + resultPart2);
    }
}
