public class ThroneSuccession {

    public Map<String, ThroneCandidate> nameMap = new HashMap<> ();
    public String ancester = null;

    public void birth(String father, String baby) {
        ThroneCandidate fatherNode = nameMap.get(father);
        ThroneCandidate childNode = nameMap.get(baby);

        childNode.father = fatherNode;
        // ancester = fatherNode;
    }

    public void death(String name) {
        nameMap.get(name).father.remove(name);
        nameMap.remove(name);
    }

    public String printOrder() {
        String anyName = nameMap.keySet().iterator).next();
        ThroneCandidate node = nameMap.get(anyName);
        while(node.father != null) {
            node = node.father; // suppose all childs comes from only one ancestor
        }

        return findFirstSuccessor(node);
    }

    private String findFirstSuccessor(ThroneCandidate node) {
        return node.dll.head.next.name;
    }

    class ThroneCandidate {
        public ThroneCandidate father;
        public String name;

        public Map<String, ThroneCandidate> map;

        public DoubleLinkedList dll;

        public void death(String name) {
            ThroneCandidate tc = this.dll.remove(this.map.get(name));
        }
    }
}
