public class QueueFromDeque<T> implements QueueInterface<T>
{
    DequeInterface<T> myDeque;

    QueueFromDeque()
    {
        myDeque = new LinkedDeque<T>();
    }

    public void enqueue(T newEntry)
    {
        if(newEntry != null)
            myDeque.addToBack(newEntry);
    }

    public T dequeue()
    {
        if(!myDeque.isEmpty())
            return myDeque.removeFront();

        return null;
    }

    public T getFront()
    {
        if(!myDeque.isEmpty())
            return myDeque.getFront();

        return null;
    }

    public boolean isEmpty()
    {
        return myDeque.isEmpty();
    }

    public void clear()
    {
        myDeque.clear();
    }


}