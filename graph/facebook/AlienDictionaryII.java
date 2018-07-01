public class AlienDictionaryII {
    static boolean isSorted(String[] A, int[] order) {
        for(int i=0; i<A.length - 1; ++i) {
            String first = A[i];
            String second = A[i + 1];
            if(first.equals(second)) continue;

            int index = 0;
            while(index < first.length() && index < second.length() && first.charAt(index) == second.charAt(index)) {
                index += 1;
            }
            if(index < first.length() && index < second.length()) {
                char a = first.charAt(index);
                char b = second.charAt(index);
                if(order[a] > order[b]) {
                    System.out.println(first + " " + second);
                    return false;
                }
            } else {
                if(first.length() - index > second.length() - index) {
                    System.out.println(first + " " + second);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] order = new int[256];
        order['c'] = 0;
        order['b'] = 1;
        order['a'] = 2;
        System.out.println("Case #1");
        String[] A = new String[] {"cc", "ab", "ca"};
        boolean success = isSorted(A, order);
        System.out.println(success);

        System.out.println("\nCase #2");
        A = new String[] {"cc", "bb", "bba", "apple"};
        success = isSorted(A, order);
        System.out.println(success);
    }
}
