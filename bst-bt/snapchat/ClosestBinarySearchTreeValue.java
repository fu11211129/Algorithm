public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        TreeNode closet = null;
        double minDif = Double.MAX_VALUE;
        while(root != null) {
            long lv = (long)root.val;
            double dif = Math.abs(lv - target);

            if(dif < 0.000001) {
                return root.val;
            } else if(root.val < target) {
                if(target - root.val < minDif) {
                    minDif = target - lv;
                    closet = root;
                }
                root = root.right;
            } else {
                if(root.val - target < minDif) {
                    minDif = lv - target;
                    closet = root;
                }
                root = root.left;
            }
        }

        return closet.val;
    }    
}
