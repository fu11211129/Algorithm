import java.util.*;

public class NumberOfAtom {

    public static void main(String[] args) {
        String formula = "K4(ON(SO3)2)2";
        NumberOfAtoms sol = new NumberOfAtoms();
        String parsedFormula = sol.countOfAtom(formula);
        System.out.println(parsedFormula);
    }

    public String countOfAtoms(String formula) {
        Counter c = new Counter(0);
        TreeMap<String, Integer> counts = countOfAtoms(formula, c);

        //System.out.println(counts);

        StringBuilder sb = new StringBuilder();
        for(String atom: counts.keySet()) {
            sb.append(atom);
            sb.append(counts.get(atom) == 1? "": counts.get(atom));
        }
        return sb.toString();
    }

    private TreeMap<String, Integer> countOfAtoms(String formula, Counter c) {
        TreeMap<String, Integer> counts = new TreeMap<> ();

        while(c.index < formula.length()) {
            if(formula.charAt(c.index) == '(') {
                c.index += 1;
                TreeMap<String, Integer> subCounts = countOfAtoms(formula, c);

                int times = getCount(formula, c);
                for(String atom: subCounts.keySet()) {
                    int curr = subCounts.get(atom);
                    int prev = counts.getOrDefault(atom, 0);
                    counts.put(atom, prev + curr * times);
                }
            } else if(formula.charAt(c.index) == ')') {
                c.index += 1;
                return counts;
            } else {
                String atom = getName(formula, c);
                int times = getCount(formula, c);
                counts.put(atom, counts.getOrDefault(atom, 0) + times);
            }
        }

        return counts;
    }

    private String getName(String formula, Counter c) {
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(c.index));

        c.index += 1;
        while(c.index < formula.length() && Character.isLowerCase(formula.charAt(c.index))) {
            sb.append(formula.charAt(c.index));
            c.index += 1;
        }
        return sb.toString();
    }

    private int getCount(String formula, Counter c) {
        int times = 0;
        while(c.index < formula.length() && Character.isDigit(formula.charAt(c.index))) {
            times =  times * 10 + (formula.charAt(c.index) - '0');
            c.index += 1;
        }
        return times == 0? 1: times;
    }

    class Counter {
        public int index;

        public Counter(int i) {
            index = i;
        }
    }
}
