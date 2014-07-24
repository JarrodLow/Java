/** 
 *  Task: Create a SortedList using a BinarySearchTree
 *
 *  @author     Richard Szeto
 *  @course     CS 111C
 *  @instructor Jessica Masters
 *  @assignment Lab E
 *  @date       May 5, 2014
 */
import java.util.Iterator;

public class BSTSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {
	
	private BinarySearchTree<T> tree;
	
	public BSTSortedList() {
		tree = new BinarySearchTree<T>();
	}


	public boolean add(T newEntry) {
		if(tree.add(newEntry) == null) {
			return true;
		}

		return false;
	}

	public boolean remove(T anEntry) {
		if(tree.remove(anEntry) != null) {
			return true;
		}

		return false;
	}

	public int getPosition(T anEntry) {
		Iterator<T> inorderIterator = tree.getInorderIterator();
		int position;
		T currentData;

		for(position = 1; inorderIterator.hasNext(); position++) {
			currentData = inorderIterator.next();

			if(currentData.compareTo(anEntry) == 0) {
				return position;
			}
			else if(currentData.compareTo(anEntry) > 0) {
				return -1 * position;
			}
		}

		return -1 * position;
	}

	public T getEntry(int givenPosition) {
		Iterator<T> inorderIterator = tree.getInorderIterator();
		int currentPosition;

		for(currentPosition = 1; inorderIterator.hasNext(); 
			currentPosition++) {
			T currentData = inorderIterator.next();

			if(currentPosition == givenPosition) {
				return currentData;
			}
		}

		return null;
	}

	public boolean contains(T anEntry) {
		return tree.contains(anEntry);
	}

	public T remove(int givenPosition) {
		T currentData = tree.remove(getEntry(givenPosition));

		if(currentData != null) {
			return currentData;
		}

		return null;
	}

	public void clear() {
		tree.clear();
	}

	public int getLength() {
		Iterator<T> inorderIterator = tree.getInorderIterator();
		int length;

		for(length = 0; inorderIterator.hasNext(); length++) {
			inorderIterator.next();
		}

		return length;
	}

	public boolean isEmpty() {
		return tree.isEmpty();
	}

	public boolean isFull() {
		return false;
	}

	public void display() {
		Iterator<T> inorderIterator = tree.getInorderIterator();

		while(inorderIterator.hasNext()) {
			System.out.println(inorderIterator.next().toString());
		}
	}

}