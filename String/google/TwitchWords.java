public class TwitchWords {
    // function:
    // heeelloooo {1, 3}, {6, 9}

    void dfs(currStr, map) {
        if(found) return;
        if(dict.find(currStr) == true) {
            result = currStr;
            return;
        }

        for(int idx: node.map.keySet()) {
            int endIdx = idx + node.map.get(idx);
            if(endIdx == idx) continue;

            String nextStr = s.substring(0, idx) + s.substring(idx, idx+len-1) + s.susbtring(endIdx+1);
            map.put(idx, endIdx-1);

            dfs(nextStr, map);

            map.put(idx, endIdx);
        }

    }
}
