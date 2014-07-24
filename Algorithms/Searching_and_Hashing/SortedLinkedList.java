/** Task: Govern Sorted Linked List
 *  @author Richard Szeto, Frank Carrano
 *  @course CS 111C
 *  @asmt   Homework 7
 *
 */
public class SortedLinkedList<T extends Comparable<? super T>>
                         implements SortedListInterface<T>
{
    private Node<T> firstNode; // reference to first node of chain
    private int  length;    // number of entries in sorted list
    
    public SortedLinkedList()
    {
        firstNode = null;
        length = 0;
    } // end default constructor

    /*****************Chapter 13 Problem 2*********************/
    public boolean add(T newEntry)
    {
        if(newEntry != null)
        {
            Node<T> currentNode = firstNode;
            Node<T> nodeBefore = null;

            while(currentNode != null && newEntry.compareTo(currentNode.getData()) > 0)
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if(currentNode != null)
            {
                if(newEntry.compareTo(currentNode.getData()) == 0)
                    return false;
                else
                    return addPrivate(newEntry,nodeBefore);
            }
            else
            {
                if(isEmpty())
                    return addPrivate(newEntry,null);
                else
                    return addPrivate(newEntry,nodeBefore);
            }
        }
        else
            return false;
    }

    public boolean addPrivate(T newEntry, Node<T> nodeBefore)
    {
        if(newEntry != null)
        {
            Node<T> newNode = new Node<T>(newEntry);
    
            if (isEmpty() || (nodeBefore == null)) // add at beginning
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else                                   // add after nodeBefore
            {
                Node<T> nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if
    
            length++;
            return true;
        }
        else
            return false;
    } // end add

    /****************Chapter 13 Problem 12****************/
    public boolean contains(T anEntry)
    {
        if(anEntry != null && !isEmpty())
        {
            Node<T> currentNode = firstNode;

            int position = 1;

            while(currentNode != null && anEntry.compareTo(currentNode.getData()) > 0)
            {
                currentNode = currentNode.getNextNode();
                position++;
            }

            if(currentNode != null)
            {
                if(anEntry.compareTo(currentNode.getData()) == 0)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    /***************Chapter 13 Problem 14c****************/
    public void merge(SortedListInterface<T> sList)
    {
        if(sList != null)
        {
            Node<T> currentNode = firstNode;
            int positionRemote = 1;
            if(!sList.isEmpty())
            {
                if(currentNode != null)
                {
                    T dataRemote = sList.getEntry(positionRemote);

                    Node<T> newHead = null;

                    int newLength = 0;

                    Node<T> newCurrentNode = null;

                    T newDataBefore = null;

                    // when newLength == 0
                    if(currentNode.getData().compareTo(dataRemote) <= 0)
                    {
                        newHead = new Node<T>(currentNode.getData());
                        newCurrentNode = newHead;
                        newDataBefore = currentNode.getData();
                        currentNode = currentNode.getNextNode();
                        newLength++;
                    }
                    else
                    {
                        newHead = new Node<T>(dataRemote);
                        newCurrentNode = newHead;
                        newLength++;
                        positionRemote++;
                        newDataBefore = dataRemote;
                        dataRemote = sList.getEntry(positionRemote);
                    }

                    while(currentNode != null && positionRemote <= sList.getLength())
                    {
                        assert dataRemote != null;

                        if(currentNode.getData().compareTo(dataRemote) <= 0)
                        {
                            if(currentNode.getData().compareTo(newDataBefore) != 0)
                            {
                                newCurrentNode.setNextNode(new Node<T>(currentNode.getData()));
                                newCurrentNode = newCurrentNode.getNextNode();
                                newDataBefore = currentNode.getData();
                                currentNode = currentNode.getNextNode();
                                newLength++;
                            }
                            else
                            {
                                currentNode = currentNode.getNextNode();
                            }
                        }
                        else
                        {
                            if(dataRemote.compareTo(newDataBefore) != 0)
                            {
                                newCurrentNode.setNextNode(new Node<T>(dataRemote));
                                newCurrentNode = newCurrentNode.getNextNode();
                                newLength++;
                                positionRemote++;
                                newDataBefore = dataRemote;
                                dataRemote = sList.getEntry(positionRemote);
                            }
                            else
                            {
                                positionRemote++;
                                dataRemote = sList.getEntry(positionRemote);
                            }
                        }
                    }

                    // one of the lists has reached its end
                    if(currentNode == null)
                    {
                        // copy rest of sList to new Linked List
                        while(positionRemote <= sList.getLength() && dataRemote.compareTo(newDataBefore) != 0)
                        {
                            newCurrentNode.setNextNode(new Node<T>(dataRemote));
                            newCurrentNode = newCurrentNode.getNextNode();
                            newLength++;
                            positionRemote++;
                            newDataBefore = dataRemote;
                            dataRemote = sList.getEntry(positionRemote);
                        }
                    }
                    else if(positionRemote > sList.getLength())
                    {
                        // copy rest of linked list to new Linked List
                        while(currentNode != null && currentNode.getData().compareTo(newDataBefore) != 0)
                        {
                            newCurrentNode.setNextNode(new Node<T>(currentNode.getData()));
                            newCurrentNode = newCurrentNode.getNextNode();
                            newDataBefore = currentNode.getData();
                            currentNode = currentNode.getNextNode();
                            newLength++;
                        }
                    }

                    assert currentNode == null && positionRemote > sList.getLength();

                    firstNode = newHead;

                    length = newLength;
                }
                else
                {
                    // just copy everything from sList
                    firstNode = new Node<T>(sList.getEntry(positionRemote));
                    currentNode = firstNode;
                    positionRemote++;
                    length++;

                    for(; positionRemote <= sList.getLength(); positionRemote++)
                    {
                        currentNode.setNextNode(new Node<T>(sList.getEntry(positionRemote)));
                        currentNode = currentNode.getNextNode();
                        length++;
                    }
                }
            }
        }
    }

    public boolean remove(T anEntry)
    {
        if(anEntry != null && !isEmpty())
        {
            Node<T> currentNode = firstNode;
            Node<T> nodeBefore = null;

            while(currentNode != null && anEntry.compareTo(currentNode.getData()) > 0)
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if(currentNode != null)
            {
                if(anEntry.compareTo(currentNode.getData()) == 0)
                {
                    if(nodeBefore != null)
                    {
                        Node<T> nodeAfter = currentNode.getNextNode();

                        nodeBefore.setNextNode(nodeAfter);

                        length--;

                        return true;
                    }
                    else
                    {
                        Node<T> tempNode = firstNode;
                        firstNode = firstNode.getNextNode();

                        tempNode.setNextNode(null);

                        length--;

                        return true;
                    }
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    public int getPosition(T anEntry)
    {
        if(anEntry != null && !isEmpty())
        {
            Node<T> currentNode = firstNode;

            int position = 1;

            while(currentNode != null && currentNode.getData().compareTo(anEntry) != 0)
            {
                currentNode = currentNode.getNextNode();
                position++;
            }

            if(currentNode != null)
                return position;
            else
                return -1;
        }
        else
            return -1;
    }

    public T getEntry(int givenPosition)
    {
        if(givenPosition >= 1 && givenPosition <= length && !isEmpty())
        {
            int position;

            Node<T> currentNode = firstNode;

            for(position = 1; position < givenPosition; position++)
            {
                currentNode = currentNode.getNextNode();
            }

            return currentNode.getData();
        }
        else
            return null;
    }

    public T remove(int givenPosition)
    {
        if(givenPosition >= 1 && givenPosition <= length && !isEmpty())
        {
            int position;

            Node<T> currentNode = firstNode;

            Node<T> nodeBefore = null;

            for(position = 1; position < givenPosition; position++)
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }

            Node<T> nodeAfter = currentNode.getNextNode();

            nodeBefore.setNextNode(nodeAfter);

            length--;

            return currentNode.getData();
        }
        else
            return null;
    }

    public void clear()
    {
        firstNode = null;
        length = 0;
    }

    public int getLength()
    {
        return length;
    }

    public boolean isEmpty()
    {
        if(length == 0 && firstNode == null)
            return true;
        else if(length != 0 && firstNode != null)
            return false;
        else
        {
            System.out.println("Something wrong with isEmpty in SortedLinkedList");
            System.exit(2);
        }

        return false;
    }

    public boolean isFull()
    {
        return false;
    }

    public void display()
    {
        Node<T> currentNode = firstNode;

        while(currentNode != null)
        {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
    }

} // end SortedLinkedList
