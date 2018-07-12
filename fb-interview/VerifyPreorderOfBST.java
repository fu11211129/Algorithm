// given a array of integers
// determine if it is a preorder traversal of one of BST
public class VerifyPreorderOfBST {
    public boolean isValidPreorder(int[] preorder) {
        int n = preorder.length;
        if(n <= 1) return true;

        int precursor = -(1 << 31);
        int i = -1; // stack pointer, the range of [0: i] holds descending order of integer.
        for(int x: preorder) {
            // if the conditon of "left < root" get violated, means it si no longer a bst.
            if(precursor > x) return false;

            while(i >= 0 && preorder[i] < x) {
                precursor = preorder[i];
                i -= 1; // simulate poping stack
            }

            i += 1; // incr the stack pointer to hold x
            preorder[i] = x;
        }

        return true;
    }
}
