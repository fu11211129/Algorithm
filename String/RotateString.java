public class RotateString {
    public static void main(String[] args) {

    }

    public boolean rotateString(String A, String B) {
        if(A.length() != B.length()) return false;

        StringBuilder AA = new StringBuilder();
        AA.append(A);
        AA.append(A);

        int idx = AA.indexOf(B);
        return idx >= 0;
    }
}
