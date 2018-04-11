import java.util.*;

public class ConvulsionWord {

    static boolean isConvulsionWord(String convulsionWord, Map<Integer, Integer> map, int offset, Set<String> dict) {
        if(offset == 5) return false;

        String currWord = "";
        int index = 0;
        for(int startIndex: map.keySet()) {
            int endIndex = map.get(startIndex);

            currWord += convulsionWord.substring(index, startIndex);
            if(startIndex >= convulsionWord.length()) break;

            currWord += convulsionWord.substring(startIndex, endIndex + 1-offset);
            index = endIndex;
            if(index >= convulsionWord.length()) break;
        }
        if(index < convulsionWord.length()) currWord += convulsionWord.substring(index);
        System.out.println(currWord);

        if(dict.contains(currWord)) return true;

        for(int startIndex: map.keySet()) {
            int endIndex = map.get(startIndex);
            if(endIndex > startIndex) {
                // System.out.println(nextStr);
                // map.put(startIndex, map.get(startIndex) - 1);
                // if(map.get(startIndex) == startIndex) map.remove(startIndex);

                if(isConvulsionWord(convulsionWord, map, offset+1, dict)) return true;

                // map.put(startIndex, map.getOrDefault(startIndex, startIndex) + 1);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<> ();
        map.put(1, 3);
        map.put(6, 9);
        Set<String> dict = new HashSet<> ();
        dict.add("hello");

        boolean b = isConvulsionWord("heeelloooo", map, 0, dict);
        System.out.println(b);
    }
}
