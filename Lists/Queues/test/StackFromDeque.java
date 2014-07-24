public class StackFromDeque<T> implements StackInterface<T>
{
    private DequeInterface<T> myDeque;
    StackFromDeque()
    {
        myDeque = new LinkedDeque<T>();
    }

    public void push(T newEntry)
    {
        if(newEntry != null)
            myDeque.addToFront(newEntry);
    }

    public T pop()
    {
        if(!myDeque.isEmpty())
            return myDeque.removeFront();

        return null;
    }

    public T peek()
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