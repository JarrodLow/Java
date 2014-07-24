/**
 * A class that implements the ADT list by using a chain of nodes.
 * 
 * @author Frank M. Carrano, Richard Szeto
 * @version 2.0
 */
public class LList<T extends Comparable> implements ListInterface<T>
{
    private Node<T> firstNode; // reference to first node
    private int length; // number of entries in list

    public LList()
    {
        clear();
    } // end default constructor
    
    public final void clear() // NOTICE clear cannot be final in interface
    {
        firstNode = null;
        length = 0;
    } // end clear

    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        Node<T> newNode = new Node<T>(newEntry);

        if (isEmpty())
            firstNode = newNode;
        else // add to end of nonempty list
        {
            Node<T> lastNode = getNodeAt(length);
            lastNode.setNextNode(newNode); // make last node reference new node
        } // end if

        length++;
        return true;
    } // end add

    public boolean add(int newPosition, T newEntry) // OutOfMemoryError possible
    {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= length + 1)) {
            Node<T> newNode = new Node<T>(newEntry);

            if (isEmpty() || (newPosition == 1)) // case 1: add to beginning of
                                                    // list
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else // case 2: list is not empty and newPosition > 1
            {
                Node<T> nodeBefore = getNodeAt(newPosition - 1);
                Node<T> nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if

            length++;
        } else
            isSuccessful = false;

        return isSuccessful;
    } // end add

    public T remove(int givenPosition)
    {
        T result = null; // return value

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            assert !isEmpty();

            if (givenPosition == 1) // case 1: remove first entry
            {
                result = firstNode.getData(); // save entry to be removed
                firstNode = firstNode.getNextNode();
            }
            else // case 2: givenPosition > 1
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNextNode();
                Node<T> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter); // disconnect the node to be
                                                // removed
                result = nodeToRemove.getData(); // save entry to be removed
            } // end if

            length--;
        } // end if

        return result; // return removed entry, or
                        // null if operation fails
    } // end remove

    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            assert !isEmpty();

            Node<T> desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        } else
            isSuccessful = false;

        return isSuccessful;
    } // end replace

    public T getEntry(int givenPosition)
    {
        T result = null; // result to return

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();
            result = getNodeAt(givenPosition).getData();
        } // end if

        return result;
    } // end getEntry

    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node<T> currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while

        return found;
    } // end contains

    public int getLength()
    {
        return length;
    } // end getLength

    public boolean isEmpty()
    {
        boolean result;

        if (length == 0) // or getLength() == 0
        {
            assert firstNode == null;
            result = true;
        }
        else
        {
            assert firstNode != null;
            result = false;
        } // end if

        return result;
    } // end isEmpty

    public boolean isFull()
    {
        return false;
    } // end isFull

    public void display()
    {
        // iterative
        Node<T> currentNode = firstNode;
        while (currentNode != null)
        {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        } // end while

        // recursive
        // displayChain(firstNode);
        // System.out.println();
    } // end display

    private void displayChain(Node nodeOne)
    {
        if (nodeOne != null)
        {
            System.out.print(nodeOne.getData() + " ");
            displayChain(nodeOne.getNextNode());
        } // end if
    } // end displayChain

    /**
     * Task: Returns a reference to the node at a given position. Precondition:
     * List is not empty; 1 <= givenPosition <= length.
     */
    private Node<T> getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= length);
        Node<T> currentNode = firstNode;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();

        assert currentNode != null;
        return currentNode;
    } // end getNodeAt

    // Decided to use Node.java instead for convention and safety
    /*private class Node
    {
        private T data; // entry in list
        private Node next; // link to next node

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node*/
    /*******************Homework***********************/
    /****************Question 6.1************************/
    public LList(T[] items, int numberOfItems)
    {
        clear();

        if(items != null)
        {
            if(numberOfItems <= items.length)
                for (int index = numberOfItems - 1; index >= 0; index--)
                    add(1, items[index]); // more efficient to add to beginning of list
        }
    } // end constructor

    /***************Question 6.3*********************/
    public void addAll(T[] items)
    {
        Node<T> tail = null;

        if(items != null)
        {
            if(!isEmpty())
            {
                assert firstNode != null;
                tail = getNodeAt(getLength()); // set reference to last node to avoid looping entire list after every add
                if(items.length >= 1)
                {
                    tail.setNextNode(new Node<T>(items[0]));
                    tail = tail.getNextNode();
                    length++;
                }
            }
            else
            {
                if(items.length >= 1)
                {
                    tail = firstNode = new Node(items[0]);
                    length = 1;
                }
            }

            for(int index = 1; index < items.length; index++)
            {
                tail.setNextNode(new Node<T>(items[index]));
                tail = tail.getNextNode();
                length++;
            }
        }
    }

    /***************Question 6.4*********************/
    public int getPosition(T anObject)
    {
        Node<T> currentNode = firstNode;
        int position;

        if(anObject != null)
        {
            for(position = 1; position <= length && !currentNode.getData().equals(anObject); position++)
                currentNode = currentNode.getNextNode();

            if(currentNode != null)
            {
                if(currentNode.getData().equals(anObject))
                    return position;
                else
                    return -1;
            }
            else
                return -1;
        }
        else
            return -1;
    }

    /***************Question 6.5******************/
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;

        ListInterface<T> otherList = (ListInterface<T>)obj;

        Node<T> currentNode = firstNode;

        int index;

        if(obj != null)
        {
            if(!isEmpty() && !otherList.isEmpty() && length == otherList.getLength())
            {
                assert length > 0 && otherList.getLength() > 0;
                for(index = 1; index <= length && currentNode.getData().equals(otherList.getEntry(index)); index++)
                    currentNode = currentNode.getNextNode();

                if(index == length + 1)
                    result = true;
            }
            else if (isEmpty() && otherList.isEmpty())
                result = true;
        }

        return result;
    }

    /***************Question 6.6****************/
    public LList<T> getAllLessThan(Comparable<T> anObject)
    {
        LList<T> lessThanList = new LList<T>();

        Node<T> currentNode = firstNode;

        if(anObject != null)
        {
            while(currentNode != null)
            {
                if(currentNode.getData().compareTo(anObject) < 0)
                    lessThanList.add(currentNode.getData());

                currentNode = currentNode.getNextNode();
            }
        }

        return lessThanList;
    }

    /*************Question 7.1*****************/
    public boolean remove(T anObject)
    {
        if(!isEmpty() && anObject != null)
        {
            assert firstNode != null;
            T removedData = remove(getPosition(anObject));

            if(removedData != null)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    /***************Question 7.3***************/
    public void moveToEnd()
    {
        if(length > 1)
        {
            assert !isEmpty();
            Node<T> move = firstNode;
            Node<T> tail = getNodeAt(length);
            firstNode = firstNode.getNextNode();
            move.setNextNode(null);
            tail.setNextNode(move);
        }
    }

    /*************Question 7.5***************/
    public T getMin()
    {
        T smallest;

        Node<T> currentNode = firstNode;

        if(currentNode != null)
        {
            assert !isEmpty();
            smallest = currentNode.getData();

            currentNode = currentNode.getNextNode();

            while(currentNode != null)
            {
                if(currentNode.getData().compareTo(smallest) < 0)
                    smallest = currentNode.getData();

                currentNode = currentNode.getNextNode();
            }
        }
        else
            smallest = null;

        return smallest;
    }

    public T removeMin()
    {
        T smallest = getMin();

        if(remove(smallest))
            return smallest;
        else
            return null;
    }
} // end LList

