/**
 * A class that implements the ADT EntryWayList by using a chain of nodes
 *
 * @author Richard Szeto
 * @course CS 111C Jessica Masters
 *
 * @param <T>
 */

public class LinkedList<T> implements EntryWayListInterface<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int length;
    private final int MAX_SIZE = 50;

    public LinkedList()
    {
        clear();
    } // end default constructor

    private final void clear()
    {
        head = null;
        tail = null;
        length = 0;
    } // end clear

    public boolean insertHead(T newEntry)
    {
        if(head == null && tail == null && length == 0)
        {
            assert isEmpty();
            head = tail = new Node<T>(newEntry);
            length++;
            return true;
        }
        else if(head != null && tail != null && length > 0 && length < MAX_SIZE)
        {
            assert !isFull() && !isEmpty();
            Node<T> newHead = new Node<T>(newEntry,head);
            head = newHead;
            length++;
            return true;
        }
        else if(length == MAX_SIZE)
        {
            assert isFull();
            return false;
        }
        else
        {
            System.out.println("Error: problem in insertHead");
            return false;
        } // end if
    } // end insertHead

    public boolean insertTail(T newEntry)
    {
        if(head == null && tail == null && length == 0)
        {
            assert isEmpty();
            head = tail = new Node<T>(newEntry);
            length++;
            return true;
        }
        else if(head != null && tail != null && length > 0 && length < MAX_SIZE)
        {
            assert !isFull() && !isEmpty();
            tail.setNextNode(new Node<T>(newEntry));
            tail = tail.getNextNode();
            length++;
            return true;
        }
        else if(length == MAX_SIZE)
        {
            assert isFull();
            return false;
        }
        else
        {
            System.out.println("Error: problem in insertTail");
            return false;
        } // end if
    } // end insertTail

    public T deleteHead()
    {
        if(head == null && tail == null && length == 0)
        {
            assert isEmpty();
            return null;
        }
        else if(head != null && tail != null && length > 0)
        {
            assert !isEmpty();
            if(head == tail)
            {
                assert length == 1;
                clear();
                return null;
            }
            else
            {
                assert length > 1;
                Node<T> removed = head;
                head = head.getNextNode();
                removed.setNextNode(null);
                length--;
                return removed.getData();
            } // end if
        }
        else
        {
            System.out.println("Error: problem in deleteHead");
            return null;
        } // end if
    } // end deleteHead

    public T deleteTail()
    {
        if(head == null && tail == null && length == 0)
        {
            assert isEmpty();
            return null;
        }
        else if(head != null && tail != null && length > 0)
        {
            assert !isEmpty();
            if(head == tail)
            {
                assert length == 1;
                clear();
                return null;
            }
            else
            {
                assert length > 1;
                Node<T> removed = tail;
                Node<T> currentNode = head;

                while(currentNode.getNextNode() != tail)
                    currentNode = currentNode.getNextNode();

                tail = currentNode;
                tail.setNextNode(null);
                length--;
                return removed.getData();
            } // end if
        }
        else
        {
            System.out.println("Error: problem in deleteTail");
            return null;
        } // end if
    } // end deleteTail

    public void display()
    {
        Node<T> currentNode = head;

        while(currentNode != null)
        {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        } // end while
    } // end display

    /* assumes that anEntry has equals method */
    public int contains(T anEntry)
    {
        Node<T> currentNode = head;
        int position = 0;
        boolean found = false;

        while(currentNode != null && !found)
        {
            if(anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
            // end if

            position++;
        } // end while

        if(found)
            return position;
        else
            return -1;
        // end if
    } // end contains

    public boolean isEmpty()
    {
        if(head == null && tail == null && length == 0)
            return true;
        else if(head != null && tail != null && length > 0 && length <= MAX_SIZE)
            return false;
        else
        {
            System.out.println("Error: problem in isEmpty");
            return false;
        } // end if
    } // end isEmpty

    public boolean isFull()
    {
        if(head == null && tail == null && length == 0)
            return false;
        else if(head != null && tail != null && length > 0 && length < MAX_SIZE)
            return false;
        else if(head != null && tail != null && length == MAX_SIZE)
            return true;
        else
        {
            System.out.println("Error: problem in isFull");
            return false;
        } // end if
    } // end isFull
}// end LinkedList