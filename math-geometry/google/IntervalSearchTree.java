import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by petergoldsborough on 10/03/15.
 */

public class IntervalSearchTree<Value>
{
	public static void main(String[] args)
	{
		// IntervalSearchTree<String> tree = new IntervalSearchTree<>();
        //
		// tree.insert(10, 15, "a");
		// tree.insert(7, 11, "b");
		// tree.insert(12, 16, "c");
		// tree.insert(8, 9, "d");
		// tree.insert(11, 12, "e");
		// tree.insert(13, 15, "f");
		// tree.insert(4, 6, "g");
		// tree.insert(1, 5, "h");
        //
		// tree.erase(8, 9);
        //
		// for (Interval interval : tree.intersections(8, 15))
		// {
		// 	System.out.printf("%d - %d\n", interval.start, interval.end);
		// }
	}

	public static class Interval implements Comparable<Interval>
	{
		Interval(Integer start, Integer end)
		{
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Interval other)
		{
			if (this.start < other.start) return -1;

			else if (this.start > other.start) return +1;

			else return 0;
		}

		Integer start;
		Integer end;
	}

	public void insert(Integer start, Integer end, Value value)
	{
		assertArguments(start, end);

		root = insert(root, new Interval(start, end), value);
	}

	public Value get(Integer start, Integer end)
	{
		assertArguments(start, end);

		Node node = get(root, new Interval(start, end));

		if (node == null) throw new NoSuchElementException("No such interval found!");

		return node.value;
	}

	public void erase(Integer start, Integer end)
	{
		// assertArguments(start, end);

		root = erase(root, new Interval(start, end));
	}

	public Boolean contains(Integer start, Integer end)
	{
		assertArguments(start, end);

		return contains(root, new Interval(start, end));
	}

	public Boolean intersectsAny(Integer start, Integer end)
	{
		assertArguments(start, end);

		return intersectsAny(root, new Interval(start, end));
	}

	public Integer numberOfIntersections(Integer start, Integer end)
	{
		assertArguments(start, end);

		return numberOfIntersections(root, new Interval(start, end));
	}

	public Iterable<Value> intersectingValues(Integer start, Integer end)
	{
		assertArguments(start, end);

		ArrayList<Value> values = new ArrayList<>();

		intersectingValues(root, new Interval(start, end), values);

		return values;
	}

	public Iterable<Interval> intersections(Integer start, Integer end)
	{
		assertArguments(start, end);

		ArrayList<Interval> found = new ArrayList<>();

		intersections(root, new Interval(start, end), found);

		return found;
	}

	public Integer size()
	{
		return size;
	}

	public Boolean isEmpty()
	{
		return size == 0;
	}

	private class Node
	{
		public Node(Interval interval, Value value)
		{
			this.interval = interval;
			this.maxEnd = interval.end;
			this.minStart = interval.start;

			this.value = value;
		}

		void checkEnds()
		{
			maxEnd = interval.end;
			minStart = interval.start;

			if (left != null)
			{
				if (left.maxEnd > maxEnd) maxEnd = left.maxEnd;

				minStart = left.minStart;
			}

			if (right != null && right.interval.end > maxEnd)
			{
				maxEnd = right.interval.end;
			}
		}

		public Interval interval;

		public Value value;

		public Integer maxEnd;
		public Integer minStart;

		public Node left = null;
		public Node right = null;

	}

	private Node insert(Node node, Interval interval, Value value)
	{
		if (node == null)
		{
			++size;

			return new Node(interval, value);
		}

		int compare = interval.compareTo(node.interval);

		if (compare < 0)
		{
			node.left = insert(node.left, interval, value);
		}

		else if (compare > 0)
		{
			node.right = insert(node.right, interval, value);
		}

		else
		{
			node.interval = interval;
			node.value = value;
		}

		node.checkEnds();

		return node;
	}

	private Node get(Node node, Interval interval)
	{
		if (node == null) return null;

		int compare = interval.compareTo(node.interval);

		if (compare < 0) return get(node.left, interval);

		else if (compare > 0) return get(node.right, interval);

		else return node;
	}

	private Node erase(Node node, Interval interval)
	{
		if (node == null) throw new NoSuchElementException("No such interval to erase!");

		int compare = interval.compareTo(node.interval);

		if  (compare < 0)
		{
			node.left = erase(node.left, interval);
		}

		else if (compare > 0)
		{
			node.right = erase(node.right, interval);
		}

		else
		{
			if (node.left == null) return node.right;

			if (node.right == null) return node.left;

			Node previous = null;
			Node successor = node.right;

			while (successor.left != null)
			{
				previous = successor;

				successor = node.left;
			}

			swap(node, successor);

			if (previous == null) successor.right = node.right;

			else previous.left = node.right;

			node = successor.right;

			while (node.left != null)
			{
				node.checkEnds();

				node = node.left;
			}

			// To call checkEnd() for the node below
			node = successor;

			--size;
		}

		node.checkEnds();

		return node;
	}

	private Boolean contains(Node node, Interval interval)
	{
		if (node == null) return false;

		int compare = interval.compareTo(node.interval);

		if  (compare < 0) return contains(node.left, interval);

		else if (compare > 0) return contains(node.right, interval);

		else return true;
	}

	private Integer numberOfIntersections(Node node, Interval interval)
	{
		if (node == null) return 0;

		Integer number = eitherFits(node.interval, interval) ? 1 : 0;

		if (node.left != null && node.left.maxEnd >= interval.start)
		{
			number += numberOfIntersections(node.left, interval);
		}

		if (node.right != null && node.right.minStart <= interval.end)
		{
			number += numberOfIntersections(node.right, interval);
		}

		return number;
	}

	private void intersections(Node node, Interval interval, ArrayList<Interval> found)
	{
		if (node == null) return;

		if (eitherFits(interval, node.interval)) found.add(node.interval);

		if (node.left != null && node.left.maxEnd >= interval.start)
		{
			intersections(node.left, interval, found);
		}

		if (node.right != null && node.right.minStart <= interval.end)
		{
			intersections(node.right, interval, found);
		}
	}

	private void intersectingValues(Node node, Interval interval, ArrayList<Value> values)
	{
		if (node == null) return;

		if (eitherFits(interval, node.interval)) values.add(node.value);

		if (node.left != null && node.left.maxEnd >= interval.start)
		{
			intersectingValues(node.left, interval, values);
		}

		if (node.right != null && node.right.minStart <= interval.end)
		{
			intersectingValues(node.right, interval, values);
		}
	}

	private Boolean intersectsAny(Node node, Interval interval)
	{
		if (node == null) return false;

		if (eitherFits(interval, node.interval)) return true;

		if (node.left == null || node.left.maxEnd < interval.start)
		{
			return intersectsAny(node.right, interval);
		}

		else return intersectsAny(node.left, interval);
	}

	private Boolean eitherFits(Interval interval, Interval target)
	{
		return fits(interval, target) || fits(target, interval);
	}

	private Boolean fits(Interval interval, Interval target)
	{
		if (interval.start >= target.start &&
			interval.start <= target.end) return true;

		if (interval.end >= target.start &&
			interval.end <= target.end) return true;

		return false;
	}

	private void swap(Node first, Node second)
	{
		Node temp = first.left;

		first.left = second.left;
		second.left = temp;

		temp = first.right;

		first.right = second.right;
		second.right = temp;
	}

	private void assertArguments(Integer start, Integer end)
	{
		if (start == null) throw new NullPointerException("Start must be integer, not null!");

		else if (end == null) throw new NullPointerException("End must be integer, not null!");

		else if (end <= start)
		{
			throw new IllegalArgumentException("End must be after start of interval!");
		}
	}

	private Node root = null;

	private Integer size = 0;
}
