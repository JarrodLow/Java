/** Task: A node in a doubly linked list
 *  @author:     Richard Szeto
 *  @asmt:       Homework 8
 *  @course:     CS 111C
 *  @instructor: Jessica Masters
 */

public class DoublyNode<T>
{
    private T data;
    private DoublyNode<T> prev;
    private DoublyNode<T> next;

    DoublyNode(T newData)
    {
        data = newData;
        prev = null;
        next = null;
    }

    public final T getData()
    {
        return data;
    }

    public void setData(T newData)
    {
        data = newData;
    }

    public final DoublyNode<T> getPrevNode()
    {
        return prev;
    }

    public void setPrevNode(DoublyNode<T> newPrev)
    {
        prev = newPrev;
    }

    public final DoublyNode<T> getNextNode()
    {
        return next;
    }

    public void setNextNode(DoublyNode<T> newNext)
    {
        next = newNext;
    }
}