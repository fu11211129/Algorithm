import java.util.*;

public class VoteProblem {
    public static void main(String[] args) {
        List<Vote> ls = new ArrayList<>();
        ls.add(new Vote("a", 100));
        ls.add(new Vote("b", 150));
        ls.add(new Vote("b", 180));
        ls.add(new Vote("a", 200));

        Map<String, List<Integer> > cand2ts = new HashMap<> ();
        for(Vote vt: ls) {
            if(!cand2ts.containsKey(vt.cand)) cand2ts.put(vt.cand, new ArrayList<>());
            cand2ts.get(vt.cand).add(vt.ts);
        }

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int T = in.nextInt();
            getLeader(cand2ts, T);
        }
    }

    static void getTopKLeaders(Map<String, List<Integer> > cand2ts, int K, int T) {
        PriorityQueue<Vote> pq = new PriorityQueue<> (new VoteCmp());

        for(String cand: cand2ts.keySet()) {
            List<Integer> tsList = cand2ts.get(cand);
            int lb = Collections.binarySearch(tsList, T);
            int curVotes = 0;

            if(lb >= 0) {
                int ub = Collections.binarySearch(tsList, T + 1);
                if(ub < 0) ub = -(ub + 1);
                curVotes = ub;
            } else {
                lb = -(lb + 1);
                curVotes = lb;
            }

            System.out.println(cand + " has " + curVotes + " votes");

            if(pq.size() < K) pq.offer(new Vote(cand, curVotes));
            else {
                Vote top = pq.poll();
                if(top.votes < curVotes) {
                    top = new Vote(cand, curVotes);
                }
                pq.offer(top);
            }
        }

        while(!ps.isEmpty()) {
            Vote v = pq.poll();
            System.out.print(v.cand + " has " + v.votes + " votes");
        }
    }

    static void getLeader(Map<String, List<Integer> > cand2ts, int T) {
        Set<String> leaders = new HashSet<> ();
        int numVotes = 1;

        for(String cand: cand2ts.keySet()) {
            List<Integer> tsList = cand2ts.get(cand);
            int lb = Collections.binarySearch(tsList, T);
            int curVotes = 0;

            if(lb >= 0) {
                int ub = Collections.binarySearch(tsList, T + 1);
                if(ub < 0) ub = -(ub + 1);
                curVotes = ub;
            } else {
                lb = -(lb + 1);
                curVotes = lb;
            }

            System.out.println(cand + " has " + curVotes + " votes");

            if(curVotes > numVotes) {
                leaders.clear();
                numVotes = curVotes;
                leaders.add(cand);
            } else if(curVotes == numVotes) {
                leaders.add(cand);
            }
        }

        System.out.println(leaders);
        leaders.clear();
    }

    static class Vote {
        int ts;
        String cand;
        int votes;
        public Vote(String ca, int t) {
            cand = ca;
            ts = t;
        }

        public Vote(String ca, int vo) {
            cand = ca;
            votes = vo;
        }
    }

    static class VoteCmp implements Comparator<Vote> {
        public int compare(Vote v1, Vote v2) {
            return v1.votes - v2.votes;
        }
    }
}
