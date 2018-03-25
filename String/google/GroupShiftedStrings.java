import java.util.*;

public class GroupShiftedStrings {
    public static void main(String[] args) {
        GroupShiftedStrings gs = new GroupShiftedStrings();
        String[] strings = new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String> > groupedStrs = gs.groupStrings(strings);
        System.out.println(groupedStrs);
    }

    public List<List<String>> groupStrings(String[] strings) {

        Map<String, String> encoded = new HashMap<> ();
        Map<String, List<String> > decoded = new HashMap<> ();

        for(String s: strings) {
            StringBuilder sb = new StringBuilder();

            char first = s.charAt(0);
            sb.append('a');

            for(int i=1; i<s.length(); ++i) {
                char c = s.charAt(i);
                int offset = (c - first + 26) % 26;
                char bc = (char)(offset + 'a');
                sb.append(bc);
            }

            String enc = sb.toString();
            // System.out.println(enc);

            encoded.put(s, enc);
            decoded.putIfAbsent(enc, new ArrayList<>());
            decoded.get(enc).add(s);
        }

        List<List<String> > results = new ArrayList<> ();
        for(List<String> line: decoded.values()) {
            results.add(new ArrayList<>(line));
        }

        return results;
    }
}
