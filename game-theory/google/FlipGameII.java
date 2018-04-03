public class FlipGameII {
    public boolean canWin(String s) {
        Map<String, Boolean> winMap = new HashMap<> ();
        return canWinUtil(s, winMap);
    }

    public boolean canWinUtil(String s, Map<String, Boolean> winMap) {
        if(winMap.containsKey(s)) return winMap.get(s);

        for(int i=0; i<s.length()-1; ++i) {
            if(s.startsWith("++", i)) {
                String replace = s.substring(0, i) + "--" + s.substring(i+2);
                if(!canWinUtil(replace, winMap)) { // if another will fail the game
                    winMap.put(s, true);
                    return true;
                }
            }
        }
        winMap.put(s, false);
        return false;
    }    
}
