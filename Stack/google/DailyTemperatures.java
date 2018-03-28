import java.util.*;

public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] temperatures = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dt.dailyTemperatures(temperatures);
        System.out.println(Arrays.asList(result));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<> ();

        for(int i=0; i<n; ++i) {
            // maintain decrseasing order in stack
            while(!stack.empty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i-idx;
            }
            stack.push(i);
        }

        return result;
    }
}
