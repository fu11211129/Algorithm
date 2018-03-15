public class InsDelRandII {
    
    private HashMap<Integer, HashSet<Integer> > value2Pos = null;
    private ArrayList<Integer> list = null;
    
    private void swap(int x, int y) {
        if(x == y) return;
        int tmp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, tmp);
    }

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.value2Pos = new HashMap<Integer, HashSet<Integer> > ();
        this.list = new ArrayList<Integer> ();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = true;
        
        if(value2Pos.containsKey(val)) {
            flag = false;
        }
        
        if(!value2Pos.containsKey(val)) value2Pos.put(val, new HashSet<Integer> ());
        value2Pos.get(val).add(list.size());
        list.add(val);
        return flag;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!value2Pos.containsKey(val)) return false;
        HashSet<Integer> addrs = value2Pos.get(val);
        
        int last = list.get(list.size()-1);
        if(last == val) {
            addrs.remove(list.size() - 1);
            if(addrs.isEmpty()) value2Pos.remove(val);
            list.remove(list.size()-1);
            return true;
        }
        
        int p = addrs.iterator().next();
        swap(p, list.size()-1);
        value2Pos.get(last).remove(list.size() - 1);
        value2Pos.get(last).add(p);
        list.remove(list.size()-1);
        addrs.remove(p);
        if(addrs.isEmpty()) value2Pos.remove(val);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random rand = new Random();
        int ridx = rand.nextInt(list.size());
        return list.get(ridx);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */