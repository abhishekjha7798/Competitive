package org.abhishekjha.projectEuler;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class CountingSundays {
    static boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        return year % 100 != 0 || year % 400 == 0;
    }
    static long solve(int startYear, int endYear) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int dayOfWeek = 1; // 1 Jan 1900 was a Monday
        long sundayCount = 0;

        for (int year = 1900; year <= endYear; year++) {
            for (int month = 0; month < 12; month++) {
                if (year >= startYear && dayOfWeek == 0) {
                    sundayCount++;
                }

                int daysThisMonth = daysInMonth[month];
                if (month == 1 && isLeapYear(year)) {
                    daysThisMonth++; // February in a leap year
                }

                dayOfWeek = (dayOfWeek + daysThisMonth) % 7;
            }
        }

        return sundayCount;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int startYear = 1901;
        int endYear = 2000;
        long result = solve(startYear, endYear);
        System.out.println("Number of Sundays that fell on the first of the month during the twentieth century: " + result);
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " ms");
    }
}
