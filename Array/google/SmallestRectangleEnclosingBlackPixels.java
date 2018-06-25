public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        int top = searchVertical(image, 0, x, true);
        int bottom = searchVertical(image, x, image.length - 1, false);
        int left = searchHorizontal(image, 0, y, true);
        int right = searchHorizontal(image, y, image[0].length - 1, false);

        int area = (bottom - top + 1) * (right - left + 1);
        return area;
    }

    public int searchVertical(char[][] image, int top, int bottom, boolean searchTop) {
        int re = searchTop? bottom: top;
        while(top <= bottom) {
            int mid = (top + bottom) / 2;
            int s = 0;
            // determine if this horizontal line contains 1 or not
            for(int j=0; j<image[mid].length; ++j) s += (image[mid][j] - '0');
            if(s >= 1) {
                re = mid;
                if(searchTop) bottom = mid - 1;
                else top = mid + 1;
            } else {
                if(searchTop) top = mid + 1;
                else bottom = mid - 1;
            }
        }
        return re;
    }

    public int searchHorizontal(char[][] image, int left, int right, boolean searchLeft) {
        int re = searchLeft? right: left;
        while(left <= right) {
            int mid = (left + right) / 2;
            int s = 0;
            for(int i=0; i<image.length; ++i) s += (image[i][mid] - '0');
            if(s >= 1) {
                re = mid;
                if(searchLeft) right = mid - 1;
                else left = mid + 1;
            } else {
                if(searchLeft) left = mid + 1;
                else right = mid - 1;
            }
        }
        return re;
    }    
}
