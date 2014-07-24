/** Task: Circular linked queue
 *
 *  @author     Richard Szeto
 *  @course     CS 111C
 *  @instructor Jessica Masters
 *  @asmt       Lab D
 *  @date       April 14, 2014
 */

public class CircularLinkedQueue<T> implements QueueInterface<T>
{
    private Node<T> lastNode;

    CircularLinkedQueue()
    {
        lastNode = null;
    }

    public void enqueue(T newEntry)
    {
        if(newEntry != null)
        {
            if(isEmpty())
            {
                lastNode = new Node<T>(newEntry);
                lastNode.setNextNode(lastNode);
            }
            else
            {
                Node<T> firstNode = lastNode.getNextNode();
                lastNode.setNextNode(new Node<T>(newEntry, firstNode));
                lastNode = lastNode.getNextNode();
            }
        }
    }

    public T dequeue()
    {
        if(!isEmpty())
        {
            T data;
            if(lastNode == lastNode.getNextNode())
            {
                data = lastNode.getData();
                lastNode = null;
            }
            else
            {
                Node<T> firstNode = lastNode.getNextNode();
                Node<T> secondNode = firstNode.getNextNode();
                data = firstNode.getData();
                lastNode.setNextNode(secondNode);
            }

            return data;
        }

        return null;
    }

    public T getFront()
    {
        if(!isEmpty())
            return lastNode.getNextNode().getData();

        return null;
    }

    public boolean isEmpty()
    {
        return lastNode == null;
    }

    public void clear()
    {
        lastNode = null;
    }
}