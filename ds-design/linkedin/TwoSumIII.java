public class TwoSumIII {

    private Map<Integer, Integer> val2freq;

    /** Initialize your data structure here. */
    public TwoSum() {
        val2freq = new HashMap<> ();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        val2freq.putIfAbsent(number, 0);
        val2freq.put(number, val2freq.get(number) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int first: val2freq.keySet()) {
            int second = value - first;
            if(first == second) {
                if(val2freq.get(first) >= 2) return true;

            } else if(val2freq.get(second) != null) {
                return true;

            }
        }

        return false;
    }    
}
