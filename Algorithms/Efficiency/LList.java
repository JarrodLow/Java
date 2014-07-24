/**
 * A class that implements the ADT list by using a chain of nodes.
 * 
 * @author Frank M. Carrano, Richard Szeto
 * @version 2.0
 */
public class LList<T extends Comparable> implements ListInterface<T>
{
    private Node<T> firstNode; // reference to first node
    private Node<T> lastNode; // reference to last node
    private int length; // number of entries in list

    public LList()
    {
        clear();
    } // end default constructor
    
    public final void clear() // NOTICE clear cannot be final in interface
    {
        firstNode = null;
        lastNode = null;
        length = 0;
    } // end clear

    public boolean add(T newEntry) // OutOfMemoryError possible
    {
        Node<T> newNode = new Node<T>(newEntry);

        if (isEmpty())
            firstNode = newNode;
        else // add to end of nonempty list
        {
            lastNode.setNextNode(newNode); // make last node reference new node
        } // end if

        lastNode = newNode;
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
                lastNode = newNode;
            }
            else if(newPosition == length + 1) // case 2: add to end of list
            {
                lastNode.setNextNode(newNode);
                lastNode = newNode;
            }
            else // case 3: list is not empty, newPosition > 1 and newPosition < length + 1
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

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();

            if (givenPosition == 1) // case 1: remove first entry
            {
                result = firstNode.getData(); // save entry to be removed
                firstNode = firstNode.getNextNode();
                if(length == 1)
                    lastNode = null;
            }
            else // case 2: givenPosition > 1
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNextNode();
                Node<T> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter); // disconnect the node to be
                                                // removed
                result = nodeToRemove.getData(); // save entry to be removed

                if(givenPosition == length)
                    lastNode = nodeBefore;
            } // end if

            length--;
        } // end if

        return result; // return removed entry, or
                        // null if operation fails
    } // end remove

    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            assert !isEmpty();

            if(givenPosition == 1)
            {
                firstNode.setData(newEntry);
            }
            else if(givenPosition == length)
            {
                lastNode.setData(newEntry);
            }
            else
            {
                Node<T> desiredNode = getNodeAt(givenPosition);
                desiredNode.setData(newEntry);
            }
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

            if(givenPosition == 1)
                result = firstNode.getData();
            else if(givenPosition == length)
                result = lastNode.getData();
            else
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
            assert firstNode == null && lastNode == null;
            result = true;
        }
        else
        {
            assert firstNode != null && lastNode != null;
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

        if(givenPosition == 1)
        {
            //do nothing
        }
        else if(givenPosition == length)
        {
            currentNode = lastNode;
        }
        else
        {
            // traverse the list to locate the desired node
            for (int counter = 1; counter < givenPosition; counter++)
                currentNode = currentNode.getNextNode();
        }

        assert currentNode != null;
        return currentNode;
    } // end getNodeAt
} // end LList