import java.util.*;

public class EncodeDecodeStrings {

    public static void main(String[] args) {
        EncodeDecodeStrings ed = new EncodeDecodeStrings();
        String encoded = ed.encode(new ArrayList(Arrays.asList("0")));
        System.out.println(encoded);
        List<String> decoded = ed.decode(encoded);
        System.out.println(decoded);
    }

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for(String s: strs) {
            int len = s.length();
            sb.append(Integer.toString(len));
            sb.append('#');
            sb.append(s);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        int i = 0;
        List<String> parsed = new ArrayList<> ();
        if(s.equals("")) return parsed;

        while(i < s.length()) {
            int markIdx = s.indexOf('#', i);
            String lenStr = s.substring(i, markIdx);
            int len = Integer.parseInt(lenStr);

            String word = s.substring(markIdx + 1, markIdx + len + 1);
            parsed.add(word);
            i = markIdx + len + 1;
        }

        return parsed;
    }
}
