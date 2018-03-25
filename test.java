import java.util.*;

public class test {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<> ();
        for(int i=0; i<1000000; ++i) set.add(i);

        long prev = System.currentTimeMillis();
        set.clear();
        long curr = System.currentTimeMillis();
        System.out.println(curr - prev);
    }
}
