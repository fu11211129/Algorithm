// Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
// Example 1:
// Input:
// s = "abcxyz123"
// dict = ["abc","123"]
// Output:
// "<b>abc</b>xyz<b>123</b>"
// Example 2:
// Input:
// s = "aaabbcc"
// dict = ["aaa","aab","bc"]
// Output:
// "<b>aaabbc</b>c"
// Note:
// The given dict won't contain duplicates, and its length won't exceed 100.
// All the strings in input have length in range [1, 1000].

public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
        if(dict.length == 0) return s;

        int le = s.length();
        List<Interval> list = new ArrayList<> ();

        for(int i=0; i<le; ++i) {
            for(String word: dict) {
                if(s.startsWith(word, i)) {
                    list.add(new Interval(i, i + word.length()-1));
                }
            }
        }

        Collections.sort(list, new Comparator<Interval> () {
            @Override
            public int compare(Interval a, Interval b) {
                if(a.start == b.start) return a.end - b.end;
                return a.start - b.start;
            }
        });
        Stack<Interval> mergedList = mergeIntervals(list);
        boolean[] bold = new boolean[le];

        for(Interval it: mergedList) {
            for(int i=it.start; i<=it.end; ++i) bold[i] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); ++i) {
            if(bold[i] && (i == 0 || !bold[i-1])) sb.append("<b>");
            sb.append(s.charAt(i));
            if(bold[i] && (i == s.length()-1 || !bold[i+1])) sb.append("</b>");
        }

        return sb.toString();
    }

    private Stack<Interval> mergeIntervals(List<Interval> list) {
        Stack<Interval> st = new Stack<> ();
        if(list.isEmpty()) return st;

        st.push(list.get(0));

        for(int i=1; i<list.size(); ++i) {
            Interval curr = list.get(i);
            Interval prev = st.pop();
            if(curr.start <= prev.end) {
                prev.end = Math.max(prev.end, curr.end);
                st.push(prev);
            } else {
                st.push(prev);
                st.push(curr);
            }
        }

        return st;
    }

    class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
