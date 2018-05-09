public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        List<Node> list = new ArrayList<> ();
        UnionFind uf = new UnionFind();
        //Map<String, TreeSet<String> > name2emails = new HashMap<> ();
        Map<String, List<Integer> > email2id = new HashMap<> ();

        for(int i=0; i<accounts.size(); ++i) {
            List<String> account = accounts.get(i);

            String name = account.get(0);
            TreeSet<String> emails = new TreeSet<> ();

            for(int j=1; j<account.size(); ++j) {
                emails.add(account.get(j));
                if(!email2id.containsKey(account.get(j))) email2id.put(account.get(j), new ArrayList<> ());
                email2id.get(account.get(j)).add(i);
            }

            list.add(new Node(name, emails));
        }

        for(String email: email2id.keySet()) {
            // System.out.println(email + "\t" + email2id.get(email));
            List<Integer> indexs = email2id.get(email);
            if(indexs.size() == 1) continue;

            Node first = list.get(indexs.get(0));
            for(int i=1; i<indexs.size(); ++i) {
                Node curr = list.get(indexs.get(i));
                uf.union(curr, first);
            }
        }

        Set<Node> roots = new HashSet<> ();
        for(Node node: list) {
            roots.add(uf.find(node));
        }


        List<List<String> > re = new ArrayList<> ();
        for(Node node: roots) {
            // System.out.println(node.name);
            List<String> account = new ArrayList<> ();
            account.add(node.name);
            account.addAll(node.emails);
            re.add(account);
        }

        return re;
    }

    class UnionFind {
        public boolean union(Node x, Node y) {
            Node fx = find(x);
            Node fy = find(y);

            if(fx != fy) {
                if(fx.rank > fy.rank) {
                    fy.father = fx;
                    fx.emails.addAll(fy.emails);
                }
                else if(fx.rank < fy.rank) {
                    fx.father = fy;
                    fy.emails.addAll(fx.emails);
                }
                else {
                    fy.father = fx;
                    fx.emails.addAll(fy.emails);
                    fx.rank += 1;
                }
                return true;
            }
            return false;
        }

        public Node find(Node x) {
            if(x.father != x) {
                x.father = find(x.father);
            }
            return x.father;
        }
    }

    class Node {
        String name;
        TreeSet<String> emails;
        Node father;
        int rank;

        public Node(String na, TreeSet<String> es) {
            name = na;
            emails = new TreeSet<> ();
            emails.addAll(es);
            father = this;
            rank = 1;
        }
    }    
}
