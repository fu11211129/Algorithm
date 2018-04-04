import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;

/**
 * Created by petergoldsborough on 10/03/15.
 */

public class RectangleIntersectionSearch {

	public static class Point implements Comparable<Point> {
		public Point(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}

		public Point(Point other) {
			this.x = other.x;
			this.y = other.y;
		}

		public int compareTo(Point other) {
			int result = x.compareTo(other.x);

			if (result != 0) return result;

			return y.compareTo(other.y);
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		public final Integer x;
		public final Integer y;

		public Line line;
	}

	public static class Line {
		public Line(Point bottom, Point top) {
			bottom.line = this;
			top.line = this;

			this.bottom = bottom;
			this.top = top;
		}

		@Override
		public String toString() {
			return bottom.toString() + " -- " + top.toString();
		}

		public final Point bottom;
		public final Point top;

		public Rectangle rectangle;
	}

	public static class Rectangle {
		public Rectangle(final Line left, final Line right) {
			left.rectangle = this;
			right.rectangle = this;

			this.left = left;
			this.right = right;
		}

		public Rectangle(final Point lowerLeft, final Point upperRight)
		{
			this(lowerLeft, upperRight, null);
		}

		public Rectangle(final Point lowerLeft, final Point upperRight, String id)
		{
			this.left = new Line(lowerLeft, new Point(lowerLeft.x, upperRight.y));
			this.right = new Line(new Point(upperRight.x, lowerLeft.y), upperRight);

			this.left.rectangle = this;
			this.right.rectangle = this;

			this.id = id;
		}

		@Override
		public String toString()
		{
			String start = id == null ? "" : id + ": ";

			return start + "[" + left.bottom.toString() + " -> " + right.top.toString() + "]";
		}

		public String id = null;

		public final Line left;
		public final Line right;
	}

	public static class Result {
		public Result(Rectangle left, Rectangle right, Rectangle intersection)
		{
			this.left = left;
			this.right = right;

			this.intersection = intersection;
		}

		@Override
		public String toString()
		{
			return left.toString() + " ∩ " + right.toString() + " = " + intersection.toString();
		}

		public final Rectangle left;
		public final Rectangle right;

		public final Rectangle intersection;
	}

	public static void main(String[] args)
	{
		ArrayList<Rectangle> rectangles = new ArrayList<>();

		rectangles.add(new Rectangle(new Point(1, 1), new Point(4, 4), "A"));
		rectangles.add(new Rectangle(new Point(5, 2), new Point(7, 6), "B"));

		rectangles.add(new Rectangle(new Point(3, 4), new Point(6, 5), "C"));
		rectangles.add(new Rectangle(new Point(2, 2), new Point(3, 3), "D"));

        rectangles.add(new Rectangle(new Point(1, 1), new Point(4, 3), "E"));

		for (Result result : RectangleIntersectionSearch.find(rectangles))
		{
			System.out.println(result);
		}
	}

	public static Iterable<Result> find(Collection<? extends Rectangle> rectangles)
	{
		ArrayList<Point> points = new ArrayList<>();

		for (Rectangle rectangle : rectangles)
		{
			points.add(rectangle.left.bottom);
			points.add(rectangle.right.top);
		}

		points.sort(Point::compareTo);

		IntervalSearchTree<Rectangle> tree = new IntervalSearchTree<>();

		ArrayList<Result> results = new ArrayList<>();

		for (Point point : points) {
			final Rectangle rectangle = point.line.rectangle;

			final Integer bottom = rectangle.left.bottom.y;
			final Integer top = rectangle.left.top.y;

			if (isLeft(point)) {
				for (Rectangle match : tree.intersectingValues(bottom, top)) {
					final Rectangle area = getIntersectionArea(rectangle, match);

					results.add(new Result(rectangle, match, area));
				}

				tree.insert(bottom, top, rectangle);
			}

			else tree.erase(bottom, top);
		}

		return results;
	}

	private static boolean isLeft(Point point)
	{
		return point.line == point.line.rectangle.left;
	}

	private static Rectangle getIntersectionArea(final Rectangle first,
	                                             final Rectangle second)
	{
		return getIntersectionArea(first, second, false);
	}

	private static Rectangle getIntersectionArea(final Rectangle first,
	                                             final Rectangle second,
	                                             boolean recursed)
	{
		byte points = 0;

		if (isInside(first.left.top, second)) points |= 0x1;

		if (isInside(first.right.top, second)) points |= 0x2;

		if (isInside(first.left.bottom, second)) points |= 0x4;

		if (isInside(first.right.bottom, second)) points |= 0x8;

		Point lowerLeft = null, upperRight = null;

		switch(points)
		{
			case 0:
			{
				if (recursed) throw new IllegalArgumentException("Rectangles don't intersect!");

				else return getIntersectionArea(second, first, true);
			}

			case 1:
			{
				Point point = first.left.top;

				lowerLeft = new Point(point.x, second.left.bottom.y);
				upperRight = new Point(second.right.bottom.x, point.y);

				break;
			}

			case 2:
			{
				lowerLeft = new Point(second.left.bottom);
				upperRight = new Point(first.right.top);

				break;
			}

			case 3:
			{
				lowerLeft = new Point(first.left.top.x, second.left.bottom.y);
				upperRight = new Point(first.right.top);

				break;
			}

			case 4:
			{
				lowerLeft = new Point(first.left.bottom);
				upperRight = new Point(second.right.top);

				break;
			}

			case 5:
			{
				Point point = first.left.top;

				lowerLeft = new Point(first.left.bottom);
				upperRight = new Point(second.right.bottom.x, point.y);

				break;
			}

			case 8:
			{
				Point point = first.right.bottom;

				lowerLeft = new Point(second.left.bottom.x, point.y);
				upperRight = new Point(point.x, second.left.top.y);

				break;
			}

			case 10:
			{
				Point point = first.right.bottom;

				lowerLeft = new Point(second.left.bottom.x, point.y);
				upperRight = new Point(first.right.top);

				break;
			}

			case 12:
			{
				Point point = first.right.bottom;

				lowerLeft = new Point(first.left.bottom);
				upperRight = new Point(point.x, second.left.top.y);

				break;
			}

			default:
			{
				lowerLeft = new Point(first.left.bottom);
				upperRight = new Point(first.right.top);

				break;
			}
		}

		return new Rectangle(lowerLeft, upperRight);
	}

	private static Boolean isInside(Point point, Rectangle rectangle)
	{
		if (point.y < rectangle.left.bottom.y ||
			point.y > rectangle.left.top.y) return false;

		if (point.x < rectangle.left.bottom.x ||
			point.y > rectangle.right.top.x) return false;

		return true;
	}


}
