// LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.
//
// Rules and restrictions:
// You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
// The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called flights representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
// You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only take flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
// For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days representing this relationship. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
// You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.
//
// Example 1:
// Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
// Output: 12
// Explanation:
// Ans = 6 + 3 + 3 = 12.
//
// One of the best strategies is:
// 1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
// (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
// 2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
// 3rd week : stay at city 2, and play 3 days and work 4 days.
import java.util.*;

public class MaximumVacationDays {
    // solution: dp
    // define f[i][j] as the maximal vacation days spent so far at week i, city j.
    // f[i][j] = max(f[i-1][k] + days[j][i]), if at week of (i-1), we can made to city k
    // explanation: if staying at city k last week(i-1), and spent vacations at city j this week.

    // base case: f[0][j] = days[j][0]
    // a week 0, spent vacations on city j, the maximal vacations day = days[j][0].

    public int maxVacationDays(int[][] flights, int[][] days) {
        int cityNum = days.length;
        if(cityNum == 0) return 0;
        int weekNum = days[0].length;

        // f[i][j] = the maximal vacation days spent so far at week i, city j.
        int[][] f = new int[weekNum][cityNum];
        boolean[][] reachable = new boolean[weekNum][cityNum];
        int iniCity = 0;

        // for the first week, we can choose to stay city "0" through city "cityNum-1" if there is a flight between 0 to that city.
        for(int city=0; city<cityNum; ++city) {
            // if stays at the same city or
            // there is a flight between city 0 and city "x"
            if(city == iniCity || flights[iniCity][city] > 0) {
                // at week 0, spent days
                f[0][city] = days[city][0];
                reachable[0][city] = true;
            }
        }

        for(int week=1; week<weekNum; ++week) {
            for(int currCity=0; currCity<cityNum; ++currCity) {
                for(int prevCity=0; prevCity<cityNum; ++prevCity) {
                    // if at last week, it can made to city k
                    // and there is a flight between "prevCity" & "currCity";
                    if(reachable[week-1][prevCity] && (currCity == prevCity || flights[prevCity][currCity] > 0)) {
                        f[week][currCity] = Math.max(f[week][currCity], f[week-1][prevCity] + days[currCity][week]);
                        reachable[week][currCity] = true;
                    }
                }
            }
        }

        int result = 0;
        // find maximal vacations spent until final week when ending up with each of citites.
        for(int city=0; city<cityNum; ++city) {
            result = Math.max(result, f[weekNum-1][city]);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumVacationDays mvd = new MaximumVacationDays();
        int[][] flights = new int[][] {
            {0,1,1},
            {1,0,1},
            {1,1,0}
        };

        int[][] days = new int[][] {
            {1,3,1},
            {6,0,3},
            {3,3,3}
        };

        int result = mvd.maxVacationDays(flights, days);
        System.out.println(result);
    }
}
