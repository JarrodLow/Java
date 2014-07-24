// ENABLE ASSERTIONS

import java.io.Serializable;
import java.lang.Math;

/**
 * A class that implements the ADT list by using a reducable-size array.
 * 
 * @author Frank M. Carrano, Richard Szeto
 * @version 2.0
 * @course CS 111C
 */
public class ShrinkableArrayList<T extends Comparable> implements ListInterface<T>, Serializable
{
    private T[] list; // array of list entries
    private int length; // current number of entries in list
    private static final int MAX_SIZE = 50; // max length of list

    /*****************public ShrinkableArrayList()***************************/
    public ShrinkableArrayList()
    {
        this(MAX_SIZE);
    } // end default constructor

    /*****************public ShrinkableArrayList(int maxSize)****************/
    public ShrinkableArrayList(int maxSize)
    {
        length = 0;
        list = (T[]) new Comparable[maxSize]; // necessary cast to generic type
    } // end constructor

    /*****************public boolean add(T newEntry)***********/
    public boolean add(T newEntry)
    {
        boolean isSuccessful = true;

        if (!isFull())
        {
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
    } // end add

    /****************public boolean add(int newPosition, T newEntry)**********/
    public boolean add(int newPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if (!isFull() && (newPosition >= 1) && (newPosition <= length + 1))
        {
            makeRoom(newPosition);
            list[newPosition - 1] = newEntry;
            length++;
        }
        else
            isSuccessful = false;

        return isSuccessful;
    } // end add

    /*****************public void clear()*****************/
    public void clear()
    {
        for (int index = 0; index < length; index++)
            // loop is part of Q4
            list[index] = null;

        length = 0; // no need to create a new array
    } // end clear

    /******************public boolean replace(int givenPosition, T newEntry)*************************/
    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length)) // test catches
                                                                // empty list
        {
            assert !isEmpty();
            list[givenPosition - 1] = newEntry;
        } else
            isSuccessful = false;

        return isSuccessful;
    } // end replace

    /*************public T getEntry(int givenPosition)**************/
    public T getEntry(int givenPosition)
    {
        T result = null; // result to return

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();
            result = list[givenPosition - 1];
        } // end if

        return result;
    } // end getEntry

    /*************public boolean contains(T anEntry)*************/
    public boolean contains(T anEntry)
    {
        boolean found = false;

        for (int index = 0; !found && (index < length); index++)
        {
            if (anEntry.equals(list[index]))
                found = true;
        } // end for

        return found;
    } // end contains

    /********************public int getLength()*********************/
    public int getLength()
    {
        return length;
    } // end getLength

    /********************public int getArrayLength()******************/
    public int getArrayLength()
    {
        return list.length;
    }

    /*******************public boolean isEmpty()**********************/
    public boolean isEmpty()
    {
        return length == 0;
    } // end isEmpty

    /*******************public boolean isFull()**********************/
    public boolean isFull()
    {
        return length == list.length;
    } // end isFull

    /******************public void display()*******************/
    public void display()
    {
        for (int index = 0; index < length; index++)
            System.out.println(list[index]);
    } // end display

    /*****************private void makeRoom(int newPosition)***********************/
    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= length + 1; length is list's length before addition.
     */
    private void makeRoom(int newPosition)
    {
        assert (newPosition >= 1) && (newPosition <= length + 1);

        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        // move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom

    /*******************private void removeGap(int givenPosition)*********************/
    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: list is not empty; 1 <= givenPosition <
     * length; length is list's length before removal.
     */
    private void removeGap(int givenPosition)
    {
        assert (givenPosition >= 1) && (givenPosition < length);

        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of list
        int removedIndex = givenPosition - 1;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    } // end removeGap

    /*********************************************************************************/
    /****************************Homework*********************************************/

    /* Problem 1 */
    /******************public ShrinkableArrayList(T[] givenObjects)*******************/
    public ShrinkableArrayList(T[] givenObjects)
    {
        list = (T[]) new Comparable[MAX_SIZE];

        for(int index = 0; index < givenObjects.length && index < MAX_SIZE; index++)
            list[index] = givenObjects[index];

        if(givenObjects.length > MAX_SIZE)
            length = MAX_SIZE;
        else
            length = givenObjects.length;
    }

    /* Problem 2 */
    /******************public int getPosition(T anObject)************************/
    public int getPosition(T anObject)
    {
        int position = -1;
        for(int index = 0; index < length && position == -1; index++)
        {
            if(anObject.equals(list[index]))
            {
                position = index + 1;
            }
        }

        return position;
    }

    /* Problem 3 */
    /*******************public boolean remove(T anObject)*******************/
    public boolean remove(T anObject)
    {
        boolean objectFound = false;

        for(int index = 0; index < length && !objectFound; index++)
        {
            assert !isEmpty();
            if(anObject.equals(list[index]))
            {
                remove(index + 1);
                objectFound = true;
            }
        }
        return objectFound;
    }

    /* Problem 4 */
    /*******************public void moveToEnd()************************/
    public void moveToEnd()
    {
        if(length > 1)
        {
            assert !isEmpty();
            T objectToMove = remove(1);
            add(objectToMove);
        }
    }

    /* Problem 6 */
    /******************public T replaceAndReturn(int givenPosition, T newEntry)****************/
    public T replaceAndReturn(int givenPosition, T newEntry)
    {
        T original = null;
        if((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();
            original = list[givenPosition - 1];
            list[givenPosition - 1] = newEntry;
        }

        return original;
    }

    /* Problem 7a */
    /*******************public T getMin()********************/
    public T getMin()
    {
        T smallest = null;
        if(!isEmpty())
        {
            assert length > 0;
            smallest = list[0];
            for(int index = 1; index < length; index++)
                if(list[index].compareTo(smallest) < 0) // list[index] < smallest
                    smallest = list[index];
        }

        return smallest;
    }

    /* Problem 7b */
    /******************public T removeMin()****************/
    public T removeMin()
    {
        T smallest = null;
        int position = -1;
        if(!isEmpty())
        {
            assert length > 0;
            smallest = list[0];
            position = 0;

            for(int index = 1; index < length; index++)
                if(list[index].compareTo(smallest) < 0) // list[index] < smallest
                {
                    smallest = list[index];
                    position = index;
                }
        }

        if(position != -1)
        {
            remove(position + 1);
        }

        return smallest;
    }

    /* Problem 8 */
    /********************public boolean equals(Object obj)*******************/
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        int index;

        ListInterface<T> otherList = (ListInterface<T>)obj;

        if(!isEmpty() && !otherList.isEmpty() && length == otherList.getLength())
        {
            assert length > 0 && otherList.getLength() > 0;
            for(index = 0; index < length && list[index].equals(otherList.getEntry(index + 1)); index++);

            if(index == length)
                result = true;
        }

        return result;
    }

    /* Problem 10 */
    /*******************private boolean isTooBig()*******************/
    private boolean isTooBig()
    {
        if(length < (int)Math.ceil(0.5*list.length) && list.length > 20)
            return true;
        else
            return false;
    }

    /*******************private void reduceArray()********************/
    private void reduceArray()
    {
        assert (int)Math.ceil(0.75*list.length) >= length;

        int newLength = (int)Math.ceil(0.75*list.length);

        T[] tmp = (T[]) new Comparable[newLength];

        for(int index = 0; index < length; index++)
            tmp[index] = list[index];

        list = tmp;
    }

    /* Only applied the array reduction to this remove method.
       Left the other remove methods as is */
    /********************public T remove(int givenPosition)*****************/
    public T remove(int givenPosition)
    {
        T result = null; // return value

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();
            result = list[givenPosition - 1]; // get entry to be removed

            // move subsequent entries toward entry to be removed,
            // unless it is last in list
            if (givenPosition < length)
                removeGap(givenPosition);

            length--;

            if(isTooBig())
                reduceArray();
        } // end if

        return result; // return reference to removed entry, or
                        // null if either list is empty or givenPosition
                        // is invalid
    } // end remove
} // end ShrinkableArrayList

