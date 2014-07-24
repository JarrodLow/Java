public class QueueFromList<T> implements QueueInterface<T>
{
    ListInterface<T> myList;

    QueueFromList()
    {
        myList = new LList<T>();
    }

    public void enqueue(T newEntry)
    {
        if(newEntry != null)
            myList.add(myList.getLength() + 1, newEntry);
    }

    public T dequeue()
    {
        if(!myList.isEmpty())
            return myList.remove(1);

        return null;
    }

    public T getFront()
    {
        if(!myList.isEmpty())
            return myList.getEntry(1);

        return null;
    }

    public boolean isEmpty()
    {
        return myList.isEmpty();
    }

    public void clear()
    {
        myList.clear();
    }
}