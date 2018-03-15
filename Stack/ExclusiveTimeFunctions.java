class ExclusiveTimeFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<> ();
        int prev = 0;

        // [Start, end]
        for(String log: logs) {
            String[] token = log.split(":");
            int funcId = Integer.parseInt(token[0]);
            boolean start = token[1].equals("start");
            int ts = Integer.parseInt(token[2]);

            if(start) {
                if(!stack.isEmpty()) result[stack.peek()] += ts - prev;
                stack.push(funcId);
                prev = ts;
            } else {
                if(!stack.isEmpty()) result[stack.pop()] += ts - prev + 1;
                prev = ts + 1;
            }

        }

        return result;
    }
}