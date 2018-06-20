public class FindDuplicateTree {

    public Map<String, Integer> map;

    public String serialize(TreeNode o) {
        if(o == null) return "#";

        String s = (o.val) + "," + serialize(o.left) + "," + serialize(o.right);

        map.putIfAbsent(s, 0);
        map.put(s, map.get(s) + 1);
        if(map.get(s) >= 2) re.add(o);

        return s;
    }
}
