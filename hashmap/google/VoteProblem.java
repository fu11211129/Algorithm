import java.util.*;

public class VoteProblem {
    public static void main(String[] args) {
        List<Vote> ls = new ArrayList<>();
        ls.add(new Vote("a", 100)); // leader = {"a"}, 100 -> "a"
        ls.add(new Vote("b", 150)); // leader = {"a", "b"}, 150 -> ("a", "b")
        ls.add(new Vote("b", 180)); // leader = {"b"}, 180 -> ("b")
        ls.add(new Vote("a", 180));
        ls.add(new Vote("a", 200)); // leader = {"a", "b"}, 200 -> ("a", "b")

        Map<String, Integer> counts = new HashMap<> ();
        int maxVotes = 0;
        Set<String> leaders = new HashSet<> ();
        List<Node> timeline = new ArrayList<> ();

        for(Vote vote: ls) {
            int ts = vote.ts;
            String cand = vote.cand;
            counts.put(cand, counts.getOrDefault(cand, 0) + 1);
            if(counts.getOrDefault(cand, 0) == maxVotes) {
                leaders.add(cand);
            } else if(counts.get(cand) > maxVotes) {
                maxVotes = counts.get(cand);
                leaders.clear();
                leaders.add(cand);
            }
            // System.out.println(leaders);
            timeline.add(new Node(new HashSet<>(leaders), ts));
        }

        for(Node node: timeline) {
            System.out.println(node.ts + "\t" + node.cands);
        }

        Set<String> winners = findLeadersSofar(timeline, 180);
        System.out.println("winners are: " + winners);
    }

    static Set<String> findLeadersSofar(List<Node> timeline, int T) {
        int n = timeline.size();
        if(n == 0) return new HashSet<> ();

        int l = 0, r = n-1, rid = -1;
        while(l <= r) {
            int mptr = (l + r) / 2;
            Node mid = timeline.get(mptr);
            if(mid.ts > T) {
                r = mptr - 1;
            } else if(mid.ts < T) {
                rid = mptr;
                l = mptr + 1;
            } else {
                rid = mptr;
                l += 1;
            }
        }

        return timeline.get(rid).cands;
    }

    static class Vote {
        int ts;
        String cand;

        public Vote(String ca, int t) {
            cand = ca;
            ts = t;
        }
    }

    static class Node {
        int ts;
        Set<String> cands;

        public Node(Set<String> c, int t) {
            cands = c;
            ts = t;
        }
    }

}
