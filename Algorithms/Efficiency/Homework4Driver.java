/** Driver for Homework 4
 * @author     Richard Szeto
 * @course     CS 111C
 * @assignment Homework 4
 * @date       February 17, 2014
 */

class Homework4Driver
{   
    /******************Question 3a********************/
    /******************displayArray()*****************/
    public static void displayArray(Integer[] myArray)
    {
        if(myArray != null)
        {
            for(int index = 0; index < myArray.length && myArray[index] != null; index++)
            {
                System.out.println(myArray[index]);
            }
        }
    }

    /*****************Question 3b***********************/
    /*****************displayLinkedList()******************/
    public static void displayLinkedList(Node<Integer> firstNode)
    {
        Node<Integer> currentNode = firstNode;
        while(currentNode != null)
        {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
    }

    /********************Question 3c*********************/
    /*******************displayNthArray()******************/
    // Assume position starts at 1
    public static void displayNthArray(Integer[] myArray, int position)
    {
        if(myArray != null)
        {
            if(position <= myArray.length && position >= 1 && myArray[position - 1] != null)
                System.out.println(myArray[position - 1]);
        }
    }

    /******************Question 3d******************/
    /*****************sumFirstNEven()****************/
    public static Integer sumFirstNEven(Integer[] myArray, int numEven)
    {
        Integer sum = 0;
        if(myArray != null)
        {
            for(int index = 0; index < myArray.length && numEven > 0 && myArray[index] != null; index++)
            {
                if(myArray[index]%2 == 0)
                {
                    sum += myArray[index];
                    numEven--;
                }
            }
        }
        return sum;
    }

    /**********************main()*********************/
    public static void main(String[] args)
    {
        /***********Question 3a************/
        Integer[] integerArray = new Integer[10];
        final int integerArrayLength = integerArray.length - 5;
        for(int i = 0; i < integerArrayLength; i++)
        {
            integerArray[i] = i + 1;
        }
        System.out.println("Output should be 1, 2, 3, 4, 5");
        displayArray(integerArray);
        System.out.println("Output should be nothing");
        displayArray(null);
        System.out.println();

        /***********Question 3b************/
        Node<Integer> integerFirstNode = new Node<Integer>(1); // 1
        Node<Integer> integerCurrentNode = integerFirstNode;
        integerCurrentNode.setNextNode(new Node<Integer>(2)); // 1, 2
        integerCurrentNode = integerCurrentNode.getNextNode();
        integerCurrentNode.setNextNode(new Node<Integer>(3)); // 1, 2, 3
        integerCurrentNode = integerCurrentNode.getNextNode();
        System.out.println("Output should be 1, 2, 3");
        displayLinkedList(integerFirstNode);
        System.out.println("Output should be nothing");
        displayLinkedList(null);
        System.out.println();

        /***********Question 3c*************/
        System.out.println("Output should be 5");
        displayNthArray(integerArray, 5);
        System.out.println("Output should be nothing");
        displayNthArray(integerArray, 6);
        System.out.println("Output should be nothing");
        displayNthArray(null, 10);
        System.out.println("Output should be nothing");
        displayNthArray(integerArray, -1);
        System.out.println();

        /***********Question 3d*************/
        System.out.println("Output should be 6: " + sumFirstNEven(integerArray, 2));
        System.out.println("Output should be 6: " + sumFirstNEven(integerArray, 3));
        System.out.println("Output should be 2: " + sumFirstNEven(integerArray, 1));
        System.out.println("Output should be 0: " + sumFirstNEven(integerArray, 0));
        System.out.println("Output should be 0: " + sumFirstNEven(integerArray, -1));
        System.out.println("Output should be 0: " + sumFirstNEven(null, 10));
        System.out.println();

        /**********Question 5**************/
        ListInterface<String> listHead = new LList<String>();
        listHead.add("Bob");
        listHead.add("Molly");
        System.out.println("Output should be Bob, Molly");
        listHead.display();
        listHead.add(3, "Guy");
        System.out.println("Output should be Bob, Molly, Guy");
        listHead.display();
        System.out.println("Output should be Bob: " + listHead.remove(1));
        System.out.println("Output should be Molly: " + listHead.remove(1));
        System.out.println("Output should be Guy: " + listHead.remove(1));
        System.out.println("Output should be null: " + listHead.remove(1));
        listHead.add("Bob");
        listHead.add("Molly");
        listHead.add("Guy");
        listHead.replace(3, "Alan");
        System.out.println("Output should be Alan: " + listHead.getEntry(3));
        System.out.println();
    }
}