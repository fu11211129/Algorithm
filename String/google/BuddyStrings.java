public class BuddyStrings {
    static boolean isBuddyStrings(String a, String b) {
        if(a.length() != b.length()) return false;

        int la = a.length();
        int lb = b.length();

        StringBuilder sa = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        int[] ac = new int[256];
        int[] bc = new int[256];

        for(int i=0; i<la; ++i) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);

            if(ca != cb) {
                ac[ca] += 1;
                bc[cb] += 1;
            }
        }

        for(int i=0; i<256; ++i) {
            if(ac[i] != bc[i]) return false;
        }

        return true;
    }
}
