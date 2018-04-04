public class RandomSelectFromRectangle {
    class Rectangle {
        int x1, x2, y1, y2; //top left (x1, y1), bottom right (x2, y2)
    }

    class Point {
        int x, y;
        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    final Random rand = new Random();

    //Round2
    public Point randomSelectFrom(Rectangle rectangle) {
        return new Point(rectangle.x1 + rand.nextInt(rectangle.x2 - rectangle.x1 + 1),
                        rectangle.y2 + rand.nextInt(rectangle.y1 - rectangle.y2 + 1));
    }

    //Round2 follow-up
    //first of all decide which rectangle yields the point (randomly select a rectangle based on area as the weight)
    //then apply randomSelectFrom(rectangle) on the selected rectangle
    public Point randomSelectFrom(Rectangle[] rectangles) {
        int selected = -1, total = 0;
        for(int i = 0; i < rectangles.length; i++) {
            int area = (rectangles[i].x2 - rectangles[i].x1) * (rectangles[i].y1 - rectangles[i].y2);
            if(rand.nextInt(total + area) >= total) {
                selected = i;
            }
            total += area;
        }
        return randomSelectFrom(rectangles[selected]);
    }    
}
