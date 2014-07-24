import java.util.Hashtable;
import java.lang.Math;

/** Task: Circular Array Queue that does not allow duplicates, uses
 *        hashtable to search for existing entries in queue
 *
 *  Precondition: Object type T has hashCode() and equals(Object obj)
 *                that follow Java conventions
 *
 *  @author     Richard Szeto, Frank Carrano
 *  @course     CS 111C
 *  @instructor Jessica Masters
 *  @asmt       Lab D
 *  @date       April 14, 2014
 */

public class NoDuplicatesArrayQueue<T>
    implements NoDuplicatesQueueInterface<T>
{
    private T[] queue; // circular array of queue entries and one unused
                                         // location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    private Hashtable<T, Integer> myHashTable;
    private static final double HASH_LOAD_FACTOR = 0.75;
    private boolean[] primes;

    // hopefully there is a prime number that is greater than or equal to
    // DEFAULT_INITIAL_CAPACITY / HASH_LOAD_FACTOR
    // and less than or equal to the above times 2
    private static final int PRIMES_SIZE = (int)Math.ceil((DEFAULT_INITIAL_CAPACITY /
        HASH_LOAD_FACTOR) * 2);
    
    public NoDuplicatesArrayQueue()
    {
        this(DEFAULT_INITIAL_CAPACITY);
    } // end default constructor
    
    public NoDuplicatesArrayQueue(int initialCapacity)
    {
        queue = (T[]) new Object[initialCapacity + 1];
        frontIndex = 0;
        backIndex = initialCapacity;

        // prime numbers array
        primes = new boolean[PRIMES_SIZE];

        calculatePrimes();

        int hashTableSize = (int)Math.ceil(DEFAULT_INITIAL_CAPACITY / HASH_LOAD_FACTOR);

        while(hashTableSize < primes.length &&
            primes[hashTableSize] == false)
        {
            hashTableSize++;
        }

        if(hashTableSize < primes.length && primes[hashTableSize] == true)
        {
            // don't need primes array anymore
            // want to let garbage collector
            // remove the large array from
            // memory
            primes = null;

            // create hashtable with initial buckets equal to
            // the smallest prime number greater than or equal to
            // (initial queue array length) / (load factor)
            // using a load factor to determine
            // when to resize the hashtable
            // The data in the queue is the key
            // The array position of the data is the value
            myHashTable = new Hashtable<T, Integer>(hashTableSize, (float)HASH_LOAD_FACTOR);
        }
        else
        {
            System.err.println("Error: prime number array too small in constructor");
            System.exit(1);
        }        
    } // end constructor
                
    public void enqueue(T newEntry)
    {
        if(!myHashTable.containsKey(newEntry))
        {
            if (isArrayFull()) // isArrayFull and 
                doubleArray();   // doubleArray are private
                
            backIndex = (backIndex + 1) % queue.length;
            queue[backIndex] = newEntry;

            // insert entry into hashtable
            myHashTable.put(newEntry, backIndex);
        } // end if
    } // end enqueue

    public T getFront()
    {
        T front = null;
        
        if (!isEmpty())
            front = queue[frontIndex];
            
        return front;
    } // end getFront
    
    public T dequeue()
    {
        T front = null;
        
        if (!isEmpty())
        {
            front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;

            // remove entry from hashtable
            if(myHashTable.remove(front) == null)
            {
                System.err.println("Error: dequeue no such entry inserted into hashtable");
                System.exit(2);
            }
        } // end if
        
        return front;
    } // end dequeue

    public boolean isEmpty()
    {
        // also check if hashtable is empty to be consistent
        return frontIndex == ((backIndex + 1) % queue.length) &&
            myHashTable.isEmpty();
    } // end isEmpty

    public void clear()
    {
        if (!isEmpty())
        { // deallocates only the used portion
            for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
            {
                    queue[index] = null;
            } // end for
            
            queue[backIndex] = null;
        } // end if
        
        frontIndex = 0;
        backIndex = queue.length - 1;

        // also clear hashtable
        myHashTable.clear();
    } // end clear

    public void moveToBack(T entry)
    {
        if(entry != null)
        {
            if(!myHashTable.containsKey(entry))
                enqueue(entry);
            else
            {
                // get array position from hashtable
                Integer position = myHashTable.get(entry);

                if(position != null)
                    // remove the gap starting from found position
                    removeGap(position);
                else
                {
                    System.err.println("Error: something wrong with hashtable in moveToBack");
                    System.exit(3);
                }

                // move the entry to the end of the queue
                moveEnqueue(entry);
            } // end if
        }
    } // end moveToBack

    public void display()
    {
        if(!isEmpty())
        {
            for(int i = frontIndex; i != (backIndex + 1) % queue.length;
                i = (i + 1) % queue.length)
            {
                System.out.println(queue[i]);
            } // end for
        } // end if
    } // end display
    
    private boolean isArrayFull()
    {
        return frontIndex == ((backIndex + 2) % queue.length);
    } // end isArrayFull

    private void doubleArray()
    {
        T[] oldQueue = queue;
        int oldSize = oldQueue.length;
        
        queue = (T[]) new Object[2 * oldSize];
        
        for (int index = 0; index < oldSize - 1; index++)
        {
            queue[index] = oldQueue[frontIndex];
            frontIndex = (frontIndex + 1) % oldSize;

            // update array positions in hashtable
            myHashTable.put(queue[index], index);
        } // end for
        
        frontIndex = 0;
        backIndex = oldSize - 2;
    } // end doubleArray

    private void calculatePrimes()
    {
        if(primes != null)
        {
            // code from http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
            // initially assume all integers are prime
            for(int i = 0; i < primes.length; i++)
                primes[i] = true;

            // set the first two non prime numbers
            primes[0] = false;
            primes[1] = false;

            for(int i = 2; i < primes.length; i++)
            {
                // if the number is prime,
                // all its multiples are not prime
                if(primes[i])
                {
                    for(int j = 2; i*j < primes.length; i++)
                        primes[i*j] = false;
                }
            }
        }
        else
        {
            System.err.println("Error: primes array references null");
            System.exit(4);
        }
    }

    private void removeGap(int index)
    {
        assert index >= 0 && index < queue.length;

        if(!isEmpty())
        {
            // to check bounds
            if((frontIndex <= backIndex && index >= frontIndex && index <= backIndex) ||
                (frontIndex > backIndex && index >= frontIndex || index <= backIndex))
            {
                int queueSize = 0;

                // determine if best to shift from front or back

                // determine the queue size
                if(backIndex >= frontIndex)
                    queueSize = backIndex - frontIndex + 1;
                else
                    queueSize = queue.length - frontIndex + backIndex + 1;

                // determine if index is at wrap around or not
                if(index >= frontIndex)
                {
                    // index is not at wrap around
                    if(index - frontIndex < queueSize / 2)
                        shiftFrontHalf(index);
                    else
                        shiftBackHalf(index);
                }
                else
                {
                    // index is at wrap around
                    if(backIndex - index >= queueSize / 2)
                        shiftFrontHalf(index);
                    else
                        shiftBackHalf(index);
                } // end if
            }
            else
            {
                System.err.println("Error: removeGap out of bounds");
                System.exit(5);
            }
        } // end if
    } // end removeGap

    private void shiftFrontHalf(int index)
    {
        for(int i = index; i != frontIndex;
            i = (i - 1) % queue.length)
        {
            queue[i] = queue[(i - 1) % queue.length];

            //update array positions in hashtable
            myHashTable.put(queue[i], i);
        }// end for

        frontIndex = (frontIndex + 1) % queue.length;
    } // end shiftFrontHalf

    private void shiftBackHalf(int index)
    {
        for(int i = index; i != backIndex; 
            i = (i + 1) % queue.length)
        {
            queue[i] = queue[(i + 1) % queue.length];

            // update array positions in hashtable
            myHashTable.put(queue[i], i);
        } // end for

        backIndex = (backIndex - 1) % queue.length;
    } // end shiftBackHalf

    private void moveEnqueue(T entry)
    {
        // ignore checking the hashtable
        if(entry != null)
        {
            // this if statement should be false
            // for every call to this method,
            // but will be here for redundancy
            if (isArrayFull()) // isArrayFull and 
                doubleArray();   // doubleArray are private

            backIndex = (backIndex + 1) % queue.length;
            queue[backIndex] = entry;

            // add entry to hashtable
            myHashTable.put(entry, backIndex);
        } // end if
    } // end moveEnqueue
} // end NoDuplicatesArrayQueue