/**
 * A driver for Lab B
 *
 * @author Richard Szeto
 * @course CS 111C Jessica Masters
 */

public class LabBDriver
{
    public static void main(String[] args)
    {
        EntryWayListInterface<String> list = new LinkedList<String>();

        /* display an empty list */
        System.out.println("Output should be nothing");
        list.display();
        System.out.println();

        /* add five entries to the list- some at the head and some at the tail */
        list.insertHead("Bob"); // Bob
        list.insertHead("Lisa"); // Lisa, Bob
        list.insertTail("Carlos"); // Lisa, Bob, Carlos
        list.insertHead("Maria"); // Maria, Lisa, Bob, Carlos
        list.insertTail("Alice"); // Maria, Lisa, Bob, Carlos, Alice
        System.out.println("Output should be Maria, Lisa, Bob, Carlos, Alice");

        /* display the list */
        list.display();
        System.out.println();

        /* Remove first entry */
        list.deleteHead(); // Lisa, Bob, Carlos, Alice

        /* Remove last entry */
        list.deleteTail(); // Lisa, Bob, Carlos
        System.out.println("Output should be Lisa, Bob, Carlos");

        /* display the list */
        list.display();
        System.out.println();

        /* test to see if elements are in the list (one that is and one that isn't) */
        System.out.println("Output should be 2: " + list.contains("Bob"));
        System.out.println("Output should be -1: " + list.contains("Maria"));
        System.out.println();

        /* remove the last three elements in the list */
        list.deleteTail();
        list.deleteHead();
        list.deleteHead();
        System.out.println("Output should be nothing");
        list.display();
        System.out.println();

        /* try to remove an element from the empty list */
        System.out.println("Output should be null: " + list.deleteHead());
        System.out.println();

        /* test if empty */
        System.out.println("Output should be true: " + list.isEmpty());
        list.insertHead("Trevor");
        System.out.println("Output should be false: " + list.isEmpty());
        System.out.println();

        /* test if full */
        System.out.println("Output should be false: " + list.isFull());
        for(int position = 2; position <= 50; position++)
        {
            if(list.isFull())
            {
                System.out.println("Something is wrong in LabBDriver's for loop");
                break;
            } // end if

            list.insertTail("Trevor");
        } // end for

        System.out.println("Output should be true: " + list.isFull());
    } // end main
} // end LabBDriver