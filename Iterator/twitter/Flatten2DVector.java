public class Flatten2DVector {
    LinkedList<Iterator<Integer> > list = new LinkedList<> ();

    public Vector2D(List<List<Integer>> vec2d) {
        for(List<Integer> line: vec2d) {
            if(!line.isEmpty())
                list.addLast(line.iterator());
        }
    }

    @Override
    public Integer next() {
        Iterator<Integer> it = list.pollFirst();
        int val = it.next();
        if(it.hasNext()) list.addFirst(it);
        return val;
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }    
}
