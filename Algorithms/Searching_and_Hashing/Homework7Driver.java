/** Task: Test Homework 7
 *  @author Richard Szeto, Jessica Masters
 *  @course CS 111C
 *  @asmt   Homework 7
 */

public class Homework7Driver {

    /*****************Question 8****************/
    public static <T> SortedListInterface<Integer> listRepeatsUnSorted(T[] array, T anEntry)
    {
        SortedListInterface<Integer> sList = new SortedLinkedList<Integer>();

        if(array != null && anEntry != null)
        {
            for(int index = 0; index < array.length && array[index] != null; index++)
            {
                if(anEntry.equals(array[index]))
                    sList.add(new Integer(index));
            }
        }

        return sList;
    }

    /***************Question 9*****************/
    public static <T extends Comparable<? super T>> 
        SortedListInterface<Integer> listRepeatsSorted(T[] array, T anEntry)
    {
        SortedListInterface<Integer> sList = new SortedLinkedList<Integer>();

        if(array != null && anEntry != null)
        {
            int position = binarySearchRecursive(array, 0, array.length - 1, anEntry);

            if(position >= 0)
            {
                // decided to not call sequential search within binary search to
                // reduce the number of activation records stored on the Java Runtime stack

                // add to head of list since it is linked list to make O(1) for each add
                forwardSequentialSearchRecursive(array, position + 1, array.length - 1, anEntry, sList);

                backwardSequentialSearchRecursive(array, 0, position, anEntry, sList);
            }
        }

        return sList;
    }

    private static <T extends Comparable<? super T>>
        int binarySearchRecursive(T[] array, int first, int last, T desiredItem) {
        // YOUR METHOD HERE
        int position;
        int mid = (first + last) / 2;

        if(first > last)
            position = -1; // don't need bulk calculation,
                           // just need some negative int
        
        else if(desiredItem.compareTo(array[mid]) == 0)
            position = mid;
        else if(desiredItem.compareTo(array[mid]) < 0)
            position = binarySearchRecursive(array, first, mid - 1, desiredItem);
        else
            position = binarySearchRecursive(array, mid + 1, last, desiredItem);

        return position;
    }

    private static <T extends Comparable<? super T>>
        void backwardSequentialSearchRecursive(T[] array, int first, int last, T desiredItem, SortedListInterface<Integer> sList)
    {
        if(last >= 0 && last >= first && desiredItem.compareTo(array[last]) == 0)
        {
            sList.add(new Integer(last));
            backwardSequentialSearchRecursive(array, first, last - 1, desiredItem, sList);
        }
    }

    private static <T extends Comparable<? super T>>
        void forwardSequentialSearchRecursive(T[] array, int first, int last, T desiredItem, SortedListInterface<Integer> sList)
    {
        if(first < array.length && first <= last && desiredItem.compareTo(array[first]) == 0)
        {
            forwardSequentialSearchRecursive(array, first + 1, last, desiredItem, sList);
            sList.add(new Integer(first));
        }
    }

    /***************Part II*********************/
    public static int getHashIndex(String key, int tableLength)
    {
        int hashIndex = key.hashCode() % tableLength;
        if(hashIndex < 0)
            hashIndex = hashIndex + tableLength;

        return hashIndex;
    }

    /********************main()***********************/
    public static void main(String[] args) {
        AList<Integer> testList = new AList<Integer>();
        testList.add(1); // 1
        testList.add(3); // 1, 3
        testList.add(2); // 1, 2, 3
        testList.add(5); // 1, 2, 3, 5
        testList.add(7); // 1, 2, 3, 5, 7
        testList.add(2); // 1, 2, 2, 3, 5, 7
        testList.add(4); // 1, 2, 2, 3, 4, 5, 7 after sorted
        
        /* 
         * question 1
         * should output true, false, true
         * */
        System.out.println("testList contains 2? " + testList.containsRecursive(2));
        System.out.println("testList contains 2? " + testList.containsRecursive(8));
        System.out.println("testList contains 2? " + testList.containsRecursive(4));
        System.out.println("");
        
        /*
         * question 2a
         * should output true, false, true
         */
        testList.sort();
        System.out.println("testList contains 2? " + testList.containsSequentialIterative(2));
        System.out.println("testList contains 2? " + testList.containsSequentialIterative(8));
        System.out.println("testList contains 2? " + testList.containsSequentialIterative(4));
        System.out.println("");
        
        /*
         * question 2b
         * should output true, false, true
         */
        testList.sort();
        System.out.println("testList contains 2? " + testList.containsRecursiveSorted(2));
        System.out.println("testList contains 2? " + testList.containsRecursiveSorted(8));
        System.out.println("testList contains 2? " + testList.containsRecursiveSorted(4));
        System.out.println("");
    
        /*
         * question 5
         * should output 1, -8, 4
         */
        testList.sort();
        System.out.println(testList.findRecursive(2));
        System.out.println(testList.findRecursive(8));
        System.out.println(testList.findRecursive(4));
        System.out.println("Output should be -1: " + testList.findRecursive(0));
        System.out.println("Output should be -7: " + testList.findRecursive(6));
        System.out.println("");
        
        /*
         * question 6
         * should output true, false, true
         */
        testList.sort();
        System.out.println("testList contains 2? " + testList.containsIterativeSorted(2));
        System.out.println("testList contains 2? " + testList.containsIterativeSorted(8));
        System.out.println("testList contains 2? " + testList.containsIterativeSorted(4));
        System.out.println("");
        
        /*
         * question 7
         * should output 7
         */
        testList = new AList<Integer>();
        testList.add(1);
        testList.add(3);
        testList.add(2);
        testList.add(5);
        testList.add(7);
        testList.add(2);
        testList.add(4);
        System.out.println(testList.getMax());
        System.out.println();

        /*
         * question 8
         *
         */
        Integer[] anArray = {5, 6, 2, 1, 8, 9, 9, 10, 10, 2};
        SortedListInterface<Integer> sList = listRepeatsUnSorted(anArray, new Integer(2));
        System.out.println("Output should be 2, 9");
        sList.display();
        System.out.println();

        /*
         * question 9
         *
         */
        Integer[] anArray2 = {1, 2, 3, 3, 3, 4, 4, 4, 5};
        sList = listRepeatsSorted(anArray2, new Integer(4));
        System.out.println("Output should be 5, 6, 7");
        sList.display();
        System.out.println();

        /**********Part II***************/

        /*
         * question 2
         *
         */
        int tableLength = 11;
        String ccsf = "50 Phelan Avenue";
        System.out.println(ccsf + " " + getHashIndex(ccsf, tableLength));
        String cityHall = "1 Dr. Carlton B. Goodlett Place";
        System.out.println(cityHall + " " + getHashIndex(cityHall, tableLength));
        String bestBuy = "1717 Harrison Street";
        System.out.println(bestBuy + " " + getHashIndex(bestBuy, tableLength));
        String elMetate = "2406 Bryant Street";
        System.out.println(elMetate + " " + getHashIndex(elMetate, tableLength));
        String karate = "2095 Harrison Street";
        System.out.println(karate + " " + getHashIndex(karate, tableLength));
        System.out.println(ccsf.compareTo(bestBuy) < 0);
        System.out.println(cityHall.compareTo(karate) < 0);
        System.out.println();

        /*
         * question 4
         *
         */
        tableLength = 7;
        String a = "15 Irving";
        String b = "700 Ocean";
        String c = "65 California";
        String d = "135 Greenwhich";
        String e = "940 Mason";
        String f = "778 Judah";
        String g = "89 Brannan";
        String h = "210 Jones";
        System.out.println(a + " " + getHashIndex(a, tableLength));
        System.out.println(b + " " + getHashIndex(b, tableLength));
        System.out.println(c + " " + getHashIndex(c, tableLength));
        System.out.println(d + " " + getHashIndex(d, tableLength));
        System.out.println(e + " " + getHashIndex(e, tableLength));
        System.out.println(f + " " + getHashIndex(f, tableLength));
        System.out.println(g + " " + getHashIndex(g, tableLength));
        System.out.println(h + " " + getHashIndex(h, tableLength));
        System.out.println();

        /*
         * question 7
         *
         */
        tableLength = 11;
        System.out.println(a + " " + getHashIndex(a, tableLength));
        System.out.println(b + " " + getHashIndex(b, tableLength));
        System.out.println(c + " " + getHashIndex(c, tableLength));
        System.out.println(d + " " + getHashIndex(d, tableLength));
        System.out.println(e + " " + getHashIndex(e, tableLength));
        System.out.println(f + " " + getHashIndex(f, tableLength));
        System.out.println(g + " " + getHashIndex(g, tableLength));
        System.out.println(h + " " + getHashIndex(h, tableLength));
        System.out.println();
        
    }
    
}
