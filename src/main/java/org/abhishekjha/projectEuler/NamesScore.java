package org.abhishekjha.projectEuler;

import org.abhishekjha.utils.FileUtil;

import java.util.Arrays;

/**
 * Using names.txt (saved in ./resources), a 46K text file containing over five-thousand first names,
 * begin by sorting it into alphabetical order.
 * Then working out the alphabetical value for each name,
 * multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order, COLIN,
 * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 * So, COLIN would obtain a score of 938 * 53 = 49714.
 * What is the total of all the name scores in the file?
 */
public class NamesScore {
    static long solve(String[] names) {
        // sort names
        Arrays.sort(names);
        long totalScore = 0;
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int nameValue = 0;
            for (char c : name.toCharArray()) {
                nameValue += (c - 'A' + 1);
            }
            int position = i + 1;
            totalScore += nameValue * position;
        }
        return totalScore;
    }

    public static void main(String[] args) {
        // Load names from the file (assuming names are in a single line, separated by commas and enclosed in quotes)
        String filePath = "src/main/java/org/abhishekjha/projectEuler/resources/names.txt";
        String fileContent = FileUtil.readFileAsString(filePath);
        String[] names = fileContent.replace("\"", "").split(",");

        long result = solve(names);
        System.out.println("The total of all the name scores in the file is: " + result);
    }
}
