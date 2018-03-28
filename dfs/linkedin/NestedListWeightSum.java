public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        int depth = 1;
        int index = 0;
        Counter co = new Counter(0);
        dfs(nestedList, index, depth, co);
        return co.sum;
    }

    private void dfs(List<NestedInteger> nestedList, int index, int depth, Counter co) {
        if(index == nestedList.size()) {
            return;
        }

        NestedInteger ni = nestedList.get(index);
        if(ni.isInteger()) {
            co.sum += depth * ni.getInteger();
            dfs(nestedList, index + 1, depth, co);
        } else {
            dfs(ni.getList(), 0, depth + 1, co);
            dfs(nestedList, index + 1, depth, co);
        }
    }

    class Counter {
        int sum;
        public Counter(int s) {
            sum = s;
        }
    }    
}
