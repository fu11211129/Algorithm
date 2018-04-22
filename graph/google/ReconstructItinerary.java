public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {

        Map<String, PriorityQueue<String> > flights = new HashMap<> ();
        for(String[] ticket: tickets) {
            String from = ticket[0], to = ticket[1];
            if(!flights.containsKey(from)) flights.put(from, new PriorityQueue<> ());
            flights.get(from).offer(to);
        }

        LinkedList<String> itenerary = new LinkedList<> ();
        dfs("JFK", itenerary, flights);

        return new ArrayList<String> (itenerary);
    }

    private void dfs(String from, LinkedList<String> itenerary, Map<String, PriorityQueue<String> > flights) {
        PriorityQueue<String> airports = flights.get(from);

        while(airports != null && airports.size() > 0) {
            String to = airports.poll();
            dfs(to, itenerary, flights);
        }

        itenerary.addFirst(from);
    }    
}
