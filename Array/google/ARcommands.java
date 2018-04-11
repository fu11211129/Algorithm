import java.util.*;

public class ARcommands {

    public String getPath(int targetPosition){
        // assuming position is greater than 0
        if(targetPosition == 1){
            return "A";
        }
        StringBuilder path = new StringBuilder();
        int currSpeed = 1;
        int currPosition = 0;
        while(currPosition < targetPosition){
            currPosition += currSpeed;
            currSpeed <<= 1;
            path.append('A');
            if(currPosition == targetPosition){
                return path.toString();
            }
            else if(currPosition > targetPosition){
                break;
            }
        }
        int diff = currPosition - targetPosition;
        // List<Integer> reversePos = getReversePosition(diff);
        // Collections.sort(reversePos, Collections.reverseOrder());
        // for(int pos : reversePos){
        //     System.out.print(pos + " ");
        //     path.insert(pos+1, "R");
        // } System.out.println();
        // // path.insert(0,'R');

        Map<Integer, String> map = new HashMap<> ();
        map.put(1, "RAR");
        map.put(3, "RAAR");
        map.put(7, "RAAAR");
        map.put(15, "RAAAAR");

        while(!(Math.abs(diff*1.0) < 0.001)) {
            double log2x = Math.log(diff*1.0) / Math.log(2.0);
            int approx = (int)(log2x);
            int d = (int) Math.pow(2.0, approx*1.0);
            d -= 1;
            System.out.println(d);
            path.append(" " + map.get(d) + " ");
            diff -= d;
            //break;
        }

        return path.toString();
    }

    private List<Integer> getReversePosition(int diff){
        Map<Integer, Integer> map = buildMap(diff); // key is the power and the value is index-google 1point3acres
        for(int k: map.keySet()) {
            System.out.println(k + " " + map.get(k));
        }
        List<Integer>[] table = new List[diff + 1];
        for(int i = 1; i <= diff; i++){
            table[i] = new LinkedList<>();
            if(map.containsKey(i)){
                table[i].add(map.get(i));
            }
            else{
                for(int j = 1; j <= i - 1; j++){
                    if(map.containsKey(i - j)){
                        table[i].addAll(table[j]);
                        table[i].add(map.get(i - j));
                        break;
                    }
                }
            }
        }
        return table[diff];
    }

    private  Map<Integer, Integer> buildMap(int limit){
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        int power = 1;
        while(power <= limit){
            map.put(power, index);
            index += 1;
            power <<= 1;
        }
        return map;
    }

    private int getLocation(String path, int iniSpeed) {
        int currSpeed = iniSpeed, location = 0;
        boolean direction = true;

        for(char c: path.toCharArray()) {
            if(c == 'A') {
                location += direction? currSpeed: 0-currSpeed;
                currSpeed *= 2;
                // System.out.println(currSpeed + ", " + location + "  ");
            } else if(c == 'R') {
                currSpeed = iniSpeed;
                direction = !direction;
                // location += direction? currSpeed: 0-currSpeed;
                // System.out.println(currSpeed + ", " + location + "  ");
            } else {
                continue;
            }
        }System.out.println();

        return location;
    }

    public static void main(String[] args){
        ARcommands test = new ARcommands();
        int targetposition = 21;
        String path = test.getPath(targetposition);
        System.out.println(path);

        int location = test.getLocation("AAAAA RAAAR RAAR", 1);
        System.out.println(location);

        double log2x = Math.log(16*1.0) / Math.log(2.0);
        int approx =(int)(log2x);
        System.out.println(approx);

        // b - a = 10
        // c - b = 100;
        // c - a = c - b+ b - a = 110
        // c - b = c - a - (b - a) = c - b;

        // 1 +    2 +   4 + 8 +  16 - (8 + 2) = 21
        // A    A(RAR)   A   ARAAA
        //
        // AAAA, speed = 16, dist = 15
        // RAAAR, speed = -8, dist = 15 - 7 = 8
        // RAR, dsit = 8 - 1
        // 21 = 15 + 15 - 7 - 1 -1
        // 21 = 31 - 10 = 31 - 7 - 3 AAAAA RAAAR RAAR
        // 15 - 7 - 1 + 15 - 1
    }

}
