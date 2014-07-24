/** Task: Array list
 *  @author Frank Carrano, Jessica Masters, Richard Szeto
 *  @course CS 111C
 *  @asmt   Homework 7
 */

// ENABLE ASSERTIONS

import java.io.Serializable;
import java.util.Arrays;

/**
 * A class that implements the ADT list by using a fixed-size array.
 * 
 * @author Frank M. Carrano
 * @version 2.0
 */
public class AList<T extends Comparable> implements ListInterface<T>,
        Serializable {
    private T[] list; // array of list entries
    private int length; // current number of entries in list
    private static final int MAX_SIZE = 50; // max length of list

    public AList() {
        this(MAX_SIZE); // call next constructor
    }

    public AList(int maxSize) {
        length = 0;
        list = (T[]) new Comparable[maxSize];
    }

    public AList(T[] items, int numberOfItems, int maxSize) {
        length = numberOfItems;
        list = (T[]) new Comparable[maxSize];

        for (int index = 0; index < numberOfItems; index++)
            list[index] = items[index];
    }

    public boolean add(T newEntry) {
        boolean isSuccessful = true;

        if (!isFull()) {
            // Assertion: Length of list < length of array
            assert length < list.length;

            // position of new entry will be after last entry in list,
            // that is, at position length+1; corresponding array index is
            // 1 less than this position, so index is length
            list[length] = newEntry;
            length++;
        } else
            isSuccessful = false;

        return isSuccessful;
    }

    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if (!isFull() && (newPosition >= 1) && (newPosition <= length + 1)) {
            makeRoom(newPosition);
            list[newPosition - 1] = newEntry;
            length++;
        } else
            isSuccessful = false;

        return isSuccessful;
    }

    public T remove(int givenPosition) {
        T result = null; // return value

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            assert !isEmpty();
            result = list[givenPosition - 1]; // get entry to be removed

            // move subsequent entries toward entry to be removed,
            // unless it is last in list
            if (givenPosition < length)
                removeGap(givenPosition);

            length--;
        } // end if

        return result; // return reference to removed entry, or
                        // null if either list is empty or givenPosition
                        // is invalid
    }

    public boolean remove(T anObject) {
        boolean isSuccessful = false;
        int position;

        // use method created in question 2:
        position = getPosition(anObject);

        if ((position >= 1) && (position <= length)) {
            remove(position);
            isSuccessful = true;
        }

        return isSuccessful;
    }

    public void moveToEnd() {
        if (length > 1) {
            T first = list[0];
            for (int i = 1; i < length; i++)
                list[i - 1] = list[i];

            list[length - 1] = first;
        }
    }

    public void clear() {
        for (int index = 0; index < length; index++)
            // loop is part of Q4
            list[index] = null;

        length = 0; // no need to create a new array
    }

    public int getPosition(T anObject) {
        int position = 0;
        for (int index = 0; index < length; index++)
            if (list[index].equals(anObject)) {
                position = index + 1;
                break;
            }
        return position;
    }

    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length)) // test catches
                                                                // empty list
        {
            assert !isEmpty();
            list[givenPosition - 1] = newEntry;
        } else
            isSuccessful = false;

        return isSuccessful;
    }

    public T replaceAndReturn(int givenPosition, T newEntry) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= length)) {
            assert !isEmpty();
            result = list[givenPosition - 1];
            list[givenPosition - 1] = newEntry;
        }
        return result;
    }

    public T getMin() {
        T smallest = null;

        if (length > 0) {
            smallest = list[0];
            for (int index = 1; index < length; index++) {
                T item = list[index];
                if (item.compareTo(smallest) < 0)
                    smallest = item;
            } // end for
        } // end if

        return smallest;
    } // end getMin

    public T removeMin() {
        T smallest = null;

        if (!isEmpty()) {
            smallest = getEntry(1);
            int smallestPosition = 1;
            for (int index = 2; index <= length; index++) {
                T item = getEntry(index);
                if (item.compareTo(smallest) < 0) // if item is smaller than
                                                    // smallest
                {
                    smallest = item;
                    smallestPosition = index;
                } // end if
            } // end for
            remove(smallestPosition);
        } // end if

        return smallest;
    } // end removeMin

    public T getEntry(int givenPosition) {
        T result = null; // result to return

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            assert !isEmpty();
            result = list[givenPosition - 1];
        } // end if

        return result;
    }

    public boolean contains(T anEntry) {
        boolean found = false;

        for (int index = 0; !found && (index < length); index++) {
            if (anEntry.equals(list[index]))
                found = true;
        } // end for

        return found;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == list.length;
    }

    public void display() {
        for (int index = 0; index < length; index++)
            System.out.println(list[index]);
    }

    /* QUESTION 1 */
    public boolean containsRecursive(T anEntry) {
        if(anEntry != null && !isEmpty())
            return searchRecursive(0, length - 1, anEntry);
        else
            return false;
    }

    private boolean searchRecursive(int first, int last, T desiredItem) {
        // YOUR METHOD HERE
        boolean found;

        if(first > last)
            found = false;
        else if(desiredItem.equals(list[last]))
            found = true;
        else
            found = searchRecursive(first, last - 1, desiredItem);

        return found;
    }

    /* QUESTION 2A */
    public void sort() {
        Arrays.sort(list, 0, length);
    }

    public boolean containsSequentialIterative(T anEntry) {
        // YOUR METHOD HERE
        if(anEntry != null && !isEmpty())
        {
            int index;
            for(index = 0; index < length && anEntry.compareTo(list[index]) > 0; index++);

            if(index < length && anEntry.compareTo(list[index]) == 0)
                return true;
            else
                return false;
        }
        else
            return false;
    } 

    /* QUESTION 2B */
    public boolean containsRecursiveSorted(T anEntry) {
        if(anEntry != null && !isEmpty())
            return searchRecursiveSorted(0, length - 1, anEntry);
        else
            return false;
    } 

    private boolean searchRecursiveSorted(int first, int last, T desiredItem) {
        // YOUR METHOD HERE
        boolean found;

        if(first > last || desiredItem.compareTo(list[first]) < 0)
            found = false;
        else if(desiredItem.compareTo(list[first]) == 0)
            found = true;
        else
            found = searchRecursiveSorted(first + 1, last, desiredItem);

        return found;
    } 

    /* QUESTION 5 */
    public int findRecursive(T anEntry) {
        if(anEntry != null)
            return binarySearchRecursive(0, length - 1, anEntry);
        else
            return 0;
    }

    private int binarySearchRecursive(int first, int last, T desiredItem) {
        // YOUR METHOD HERE
        int position;
        int mid = (first + last) / 2;

        if(first > last)
        {
            if(first >= 0 && first < length && last >= 0 && last < length)
            {
                if(desiredItem.compareTo(list[last]) < 0)
                    position = (-1)*(last + 1);
                else
                    position = (-1)*(first + 1);
            }
            else if(first >= 0 && first < length)
            {
                if(desiredItem.compareTo(list[first]) > 0)
                    position = (-1)*(first + 2);
                else
                    position = (-1)*(first + 1);
            }
            else
            {
                if(desiredItem.compareTo(list[last]) < 0)
                    position = (-1)*(last + 1);
                else
                    position = (-1)*(last + 2);
            }
        }
        else if(desiredItem.compareTo(list[mid]) == 0)
            position = mid;
        else if(desiredItem.compareTo(list[mid]) < 0)
            position = binarySearchRecursive(first, mid - 1, desiredItem);
        else
            position = binarySearchRecursive(mid + 1, last, desiredItem);

        return position;
    }

    /* QUESTION 6 */
    public boolean containsIterativeSorted(T anEntry) {
        if(anEntry != null)
            return binarySearchIterative(0, length - 1, anEntry);
        else
            return false;
    }

    private boolean binarySearchIterative(int first, int last, T desiredItem) {
        // YOUR METHOD HERE
        int newFirst = first;
        int newLast = last;
        int newMid;

        while(newFirst <= newLast)
        {
            newMid = (newFirst + newLast) / 2;

            if(desiredItem.compareTo(list[newMid]) == 0)
                return true;
            else if(desiredItem.compareTo(list[newMid]) < 0)
                newLast = newMid - 1;
            else
                newFirst = newMid + 1;
        }

        return false;
    } 

    /* QUESTION 7 */
    public T getMax() {
        return findMaxRecursive(0, length - 1);
    } 

    private T findMaxRecursive(int first, int last) {
        // YOUT METHOD HERE
        if(first == last)
            return list[first];
        else
        {
            int mid = (first + last) / 2;
            T left = findMaxRecursive(first, mid);
            T right = findMaxRecursive(mid + 1, last);

            if(left.compareTo(right) >= 0)
                return left;
            else
                return right;
        }
    }

    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= length + 1);

        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        // move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < length);

        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of list
        int removedIndex = givenPosition - 1;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    }

}
