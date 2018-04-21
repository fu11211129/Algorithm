import java.util.*;

public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath la = new LongestAbsoluteFilePath();
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int result = la.lengthLongestPath("dir\n        file.txt".replaceAll(" ", "\t"));
        System.out.println(result);
    }

    public int lengthLongestPath(String input) {

        String[] tokens = input.split("\n");
        Stack<Integer> pathLen = new Stack<> ();
        int result = 0;

        for(String s: tokens) {
            int level = s.lastIndexOf("\t") + 2;
            // System.out.println(level);
            while(!pathLen.empty() && pathLen.size() >= level) pathLen.pop();
            

            int currPathLen = pathLen.empty()? (s.length() - level + 2): (pathLen.peek() + s.length() - level + 2);
            pathLen.push(currPathLen);

            // System.out.println(pathLen);

            if(s.indexOf(".") >= 0) result = Math.max(result, currPathLen - 1);

            //System.out.println(pathLen);
        }

        return result;
    }
}
