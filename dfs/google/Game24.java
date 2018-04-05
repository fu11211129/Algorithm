import java.util.*;

public class Game24 {
    boolean res = false;
    final double eps = 0.001;

    public boolean judgePoint24(int[] nums) {
        List<Double> arr = new ArrayList<>();
        for(int n: nums) arr.add((double) n);
        return helper(arr);
    }

    private boolean helper(List<Double> arr){
        if(arr.size() == 0) return false;
        if(arr.size() == 1){
            if(Math.abs(arr.get(0) - 24.0) < eps) return true;
            else return false;
        }

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < i; j++) {
                List<Double> next = new ArrayList<>();
                Double p1 = arr.get(i), p2 = arr.get(j);
                next.addAll(Arrays.asList(p1+p2, p1-p2, p2-p1, p1*p2));
                if(Math.abs(p2) > eps)  next.add(p1/p2);
                if(Math.abs(p1) > eps)  next.add(p2/p1);

                arr.remove(i);
                arr.remove(j);
                for (Double n: next){
                    arr.add(n);
                    if(helper(arr)) return true;
                    arr.remove(arr.size()-1);
                }
                arr.add(j, p2);
                arr.add(i, p1);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Game24 g24 = new Game24();
        boolean b = g24.judgePoint24(new int[] {4, 1, 8, 7});
        System.out.println(b);
    }
}
