public class ShortestCompletingWord {

    public static void main(String[] args) {
        String[] words = new String[] {"looks","pest","stew","show"};
        String licensePlate = "1s3 456";

        ShortestCompletingWord sc = new ShortestCompletingWord();
        String cw = sc.shortestCompletingWord(licensePlate, words);
        System.out.println(cw);
    }

    private boolean check(String w, String lic) {
        int[] wmap = new int[256];
        int[] lmap = new int[256];

        for(int i=0; i<w.length(); ++i) {
            char c = w.charAt(i);
            wmap[Character.toLowerCase(c)] += 1;
        }

        for(int i=0; i<lic.length(); ++i) {
            char c = lic.charAt(i);
            if(Character.isLetter(c)) lmap[Character.toLowerCase(c)] += 1;
        }

        for(int i=0; i<256; ++i) {
            if(lmap[i] != 0) {
                if(lmap[i] != wmap[i]) return false;
            }
        }

        return true;
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        for(String w: words) {
            boolean b = check(w, licensePlate);
            if(b) return w;
        }
        return "";
    }
}
