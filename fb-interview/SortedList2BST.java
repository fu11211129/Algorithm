import java.util.*;

public class SortedList2BST {

    static TreeNode head = null;
    static TreeNode prev = null;

    static void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.left);
        if(head == null) {
            head = root;
            prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        flatten(root.right);
    }

    static TreeNode build(int n) {
        if(head == null || n <= 0) return null;

        TreeNode l = build(n / 2);
        TreeNode o = new TreeNode(head.value);
        o.left = l;
        head = head.right;
        o.right = build(n - n / 2 - 1);
        return o;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.right = new TreeNode(5);

        PrettyPrintTree.prettyPrintTree(root);

        flatten(root);
        TreeNode tail = prev;

        TreeNode newRoot = build(5);

        PrettyPrintTree.prettyPrintTree(root);
        Queue<TreeNode> q = new LinkedList<> ();
        q.offer(newRoot);
        while(q.size() > 0) {
            int size = q.size();
            for(int k = 0; k < size; k += 1) {
                TreeNode node = q.poll();
                System.out.print(node.value + " ");
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            } System.out.println();
        }
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            value = v;
            left = right = null;
        }
    }

    static class PrettyPrintTree {

        public TreeNode root;

        public PrettyPrintTree(List<Integer> list) {
            root = createTree(list, 0, list.size()-1);
        }

        public static TreeNode createTree(List<Integer> list, int l, int r) {

    		if(l > r) {
    			return null;
    		}

    		int mid = l + (r - l)/2;
    		TreeNode ro = new TreeNode(list.get(mid));

    		ro.left = createTree(list, l, mid-1);
    		ro.right = createTree(list, mid+1, r);

    		return ro;
    	}

        private static int getMaximumHeight(TreeNode node) {
            if (node == null)
                return 0;
            int leftHeight = getMaximumHeight(node.left);
            int rightHeight = getMaximumHeight(node.right);
            return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
        }

        private static String multiplyString(String string, int times) {
            StringBuilder builder = new StringBuilder(string.length() * times);
            for (int i = 0; i < times; ++i) {
                builder.append(string);
            }
            return builder.toString();
        }

        public static String getStartingSpace(int height) {
            return multiplyString("  ", ((int) Math.pow(2, height - 1)) / 2);
        }

        public static String getUnderScores(int height) {
            int noOfElementsToLeft = ((int) Math.pow(2, height) - 1) / 2;
            int noOfUnderScores = noOfElementsToLeft
                    - ((int) Math.pow(2, height - 1) / 2);

            return multiplyString("__", noOfUnderScores);
        }

        public static String getSpaceBetweenTwoNodes(int height) {
            if (height == 0)
                return "";

            int noOfNodesInSubTreeOfNode = ((int) Math.pow(2, height - 1)) / 2;
            /** Sum of spaces of the subtrees of nodes + the parent node */
            int noOfSpacesBetweenTwoNodes = noOfNodesInSubTreeOfNode * 2 + 1;

            return multiplyString("  ", noOfSpacesBetweenTwoNodes);
        }

        public static void printNodes(List<TreeNode> queueOfNodes,
                int noOfNodesAtCurrentHeight, int height) {
            StringBuilder nodesAtHeight = new StringBuilder();

            String startSpace = getStartingSpace(height);
            String spaceBetweenTwoNodes = getSpaceBetweenTwoNodes(height);

            String underScore = getUnderScores(height);
            String underScoreSpace = multiplyString(" ", underScore.length());

            nodesAtHeight.append(startSpace);
            for (int i = 0; i < noOfNodesAtCurrentHeight; i++) {
                TreeNode node = (TreeNode) queueOfNodes.get(i);
                if (node == null) {
                    nodesAtHeight.append(underScoreSpace)
                            .append("  ")
                            .append(underScoreSpace)
                            .append(spaceBetweenTwoNodes);
                } else {
                    nodesAtHeight
                            .append(node.left != null ? underScore
                                    : underScoreSpace)
                            .append(String.format("%2d", node.value))
                            .append(node.right != null ? underScore
                                    : underScoreSpace)
                            .append(spaceBetweenTwoNodes);
                }
            }

            System.out.println(nodesAtHeight.toString().replaceFirst("\\s+$", ""));
        }

        public static String getSpaceBetweenLeftRightBranch(int height) {
            int noOfNodesBetweenLeftRightBranch = ((int) Math.pow(2, height - 1) - 1);

            return multiplyString("  ", noOfNodesBetweenLeftRightBranch);
        }

        public static String getSpaceBetweenRightLeftBranch(int height) {
            int noOfNodesBetweenLeftRightBranch = (int) Math.pow(2, height - 1);

            return multiplyString("  ", noOfNodesBetweenLeftRightBranch);
        }

        public static void printBranches(List<TreeNode> queueOfNodes,
                int noOfNodesAtCurrentHeight, int height) {
            if (height <= 1)
                return;
            StringBuilder brachesAtHeight = new StringBuilder();

            String startSpace = getStartingSpace(height);
            String leftRightSpace = getSpaceBetweenLeftRightBranch(height);
            String rightLeftSpace = getSpaceBetweenRightLeftBranch(height);

            brachesAtHeight
                    .append(startSpace.substring(0, startSpace.length() - 1));

            for (int i = 0; i < noOfNodesAtCurrentHeight; i++) {
                TreeNode node = queueOfNodes.get(i);
                if (node == null) {
                    brachesAtHeight.append(" ")
                            .append(leftRightSpace)
                            .append(" ")
                            .append(rightLeftSpace);
                } else {
                    brachesAtHeight.append(node.left != null ? "/" : " ")
                            .append(leftRightSpace)
                            .append(node.right != null ? "\\" : " ")
                            .append(rightLeftSpace);
                }
            }

            System.out
                    .println(brachesAtHeight.toString().replaceFirst("\\s+$", ""));
        }

        public static void prettyPrintTree(TreeNode root) {
            LinkedList<TreeNode> queueOfNodes = new LinkedList<>();
            int height = getMaximumHeight(root);
            int level = 0;
            int noOfNodesAtCurrentHeight = 0;

            queueOfNodes.add(root);

            while (!queueOfNodes.isEmpty() && level < height) {
                noOfNodesAtCurrentHeight = ((int) Math.pow(2, level));

                printNodes(queueOfNodes, noOfNodesAtCurrentHeight, height - level);
                printBranches(queueOfNodes, noOfNodesAtCurrentHeight, height
                        - level);

                for (int i = 0; i < noOfNodesAtCurrentHeight; i++) {
                    TreeNode currNode = queueOfNodes.peek();
                    queueOfNodes.remove();
                    if (currNode != null) {
                        queueOfNodes.add(currNode.left);
                        queueOfNodes.add(currNode.right);
                    } else {
                        queueOfNodes.add(null);
                        queueOfNodes.add(null);
                    }
                }
                level++;
            }
        }
    }
}
