/** Task: Test HW 6
 *  @author Richard Szeto
 *  @course CS 111C
 *  @asmt   Homework 6
 *
 */
public class Homework6Driver
{
    public static <T extends Comparable<? super T>>
        SortedListInterface<T> merge(SortedListInterface<T> sList1, SortedListInterface<T> sList2)
    {
        // This algorithm is easy since we have no control over
        // what add does, and add places elements
        // in sorted order.
        SortedListInterface<T> newList = new SortedLinkedList<T>();

        if(sList1 != null && !sList1.isEmpty())
            for(int position = 1; position <= sList1.getLength(); position++)
                newList.add(sList1.getEntry(position));

        if(sList2 != null && !sList2.isEmpty())
            for(int position = 1; position <= sList2.getLength(); position++)
                newList.add(sList2.getEntry(position));

        return newList;
    }

    public static int firstLetter(SortedListInterface<String> sList, String comp)
    {
        if(sList != null && comp != null && !sList.isEmpty())
        {
            String firstChar = comp.substring(0,1); 
            String temp;
            boolean b = false;

            int sum = 0;

            for(int index = 1; index <= sList.getLength() &&
                (firstChar.compareTo(temp = sList.getEntry(index)) >= 0 ||
                    (b = temp.startsWith(firstChar))); index++)
                if(b)
                    sum++;

            return sum;
        }

        return 0;
    }

    public static void main(String[] args)
    {   
        // Part 1

        // Selection Sort
        System.out.println("Selection Sort");
        Integer[] selectionArray = {6,8,3,10,9,6,7,1};

        SortArray.selectionSort(selectionArray,selectionArray.length);

        System.out.println();

        // Insertion Sort
        System.out.println("Insertion Sort");
        Integer[] insertionArray = {6,8,3,10,9,6,7,1};

        SortArray.insertionSort(insertionArray,0,insertionArray.length - 1);

        System.out.println();

        // Shell Sort
        System.out.println("Shell Sort");
        Integer[] shellArray = {6,8,3,10,9,6,7,1};

        SortArray.shellSort(shellArray,0,shellArray.length - 1);

        System.out.println();

        // Shell Sort Improved
        System.out.println("Shell Sort Improved");
        Integer[] shellMArray = {6,8,3,10,9,6,7,1};

        SortArray.shellSortImproved(shellMArray,0,shellMArray.length - 1);

        System.out.println();

        // Merge Sort
        System.out.println("Merge Sort");
        Integer[] mergeArray = {6,8,3,10,9,6,7,1};

        SortArray.mergeSort(mergeArray,0,mergeArray.length - 1);
        
        System.out.println();

        // Quick Sort
        System.out.println("Quick Sort");
        Integer[] quickArray = {6,8,3,10,9,6,7,1};

        SortArray.quickSort(quickArray,0,quickArray.length - 1);

        System.out.println();

        System.out.println("Quick Sort Custom");
        Integer[] quickCustomArray = {5,3,7,4,3,2,3,1,8,3};

        SortArray.quickSort(quickCustomArray,0,quickCustomArray.length - 1);

        System.out.println();

        // Part II
        
        // Chapter 11 problem 11
        Integer[] notSorted = {5,3,1};
        Integer[] sorted = {1,3,5};
        Integer[] sortedPlus = {1,2,3,null,null};
        Integer[] empty = {};

        System.out.println("Output should be false: " + SortArray.isSorted(notSorted));
        System.out.println("Output should be true: " + SortArray.isSorted(sorted));
        System.out.println("Output should be true: " + SortArray.isSorted(sortedPlus));
        System.out.println("Output should be true: " + SortArray.isSorted(empty));
        System.out.println();

        // Chapter 13 problem 2
        SortedListInterface integerList = new SortedLinkedList<Integer>();
        System.out.println("Output should be true: " + integerList.add(new Integer(1)));
        System.out.println("Output should be false: " + integerList.add(new Integer(1)));
        integerList.add(new Integer(2));
        integerList.add(new Integer(3));
        System.out.println("Output should be false: " + integerList.add(new Integer(2)));
        System.out.println("Output should be false: " + integerList.add(new Integer(3)));
        System.out.println("Output should be false: " + integerList.add(new Integer(1)));
        System.out.println();

        // Chapter 13 problem 12
        System.out.println("Output should be false: " + integerList.contains(new Integer(4)));
        System.out.println("Output should be true: " + integerList.contains(new Integer(3)));
        integerList.add(new Integer(5));
        integerList.add(new Integer(6));
        System.out.println("Output should be false: " + integerList.contains(new Integer(4)));
        System.out.println("Output should be 3: " + integerList.remove(3));
        System.out.println("Output should be 2: " + integerList.getEntry(2));
        System.out.println("Output should be false: " + integerList.remove(new Integer(4)));
        System.out.println("Output should be true: " + integerList.remove(new Integer(1)));
        System.out.println("Output should be 3: " + integerList.getLength());
        System.out.println();

        // Chapter 13 problem 14c
        SortedListInterface list1 = new SortedLinkedList<Integer>();
        list1.add(new Integer(1));
        list1.add(new Integer(2));
        list1.add(new Integer(3));
        list1.add(new Integer(5));
        SortedListInterface list2 = new SortedLinkedList<Integer>();
        list2.add(new Integer(1));
        list2.add(new Integer(3));
        list2.add(new Integer(4));
        list2.add(new Integer(6));
        System.out.println("Output should be 1,2,3,5");
        list1.display();
        System.out.println("Output should be 1,3,4,6");
        list2.display();
        list1.merge(list2);
        System.out.println("Output should be 1,2,3,4,5,6");
        list1.display();
        System.out.println("Output should be 1,3,4,6");
        list2.display();
        list1.clear();
        System.out.println("Output should be nothing");
        list1.display();
        list1.merge(list2);
        System.out.println("Output should be 1,3,4,6");
        list1.display();
        list1.clear();
        list2.clear();
        list1.add(new Integer(1));
        list1.add(new Integer(2));
        list1.add(new Integer(3));
        list1.add(new Integer(4));
        list2.add(new Integer(5));
        list2.add(new Integer(6));
        list2.add(new Integer(7));
        list2.add(new Integer(8));
        System.out.println("Output should be 1,2,3,4");
        list1.display();
        System.out.println("Output should be 5,6,7,8");
        list2.display();
        list1.merge(list2);
        System.out.println("Output should be 1,2,3,4,5,6,7,8");
        list1.display();
        list2.merge(list1);
        System.out.println("Output should be 1,2,3,4,5,6,7,8");
        list2.display();
        list1.clear();
        list2.clear();
        list1.add(new Integer(5));
        list1.add(new Integer(6));
        list1.add(new Integer(7));
        list1.add(new Integer(8));
        list2.add(new Integer(1));
        list2.add(new Integer(2));
        list2.add(new Integer(3));
        System.out.println("Output should be 5,6,7,8");
        list1.display();
        System.out.println("Output should be 1,2,3");
        list2.display();
        list1.merge(list2);
        System.out.println("Output should be 1,2,3,5,6,7,8");
        list1.display();
        System.out.println();

        // test client merge
        list1.clear();
        list2.clear();
        list1.add(new Integer(1));
        list1.add(new Integer(3));
        System.out.println("Output should be 1, 3");
        list1.display();
        list2.add(new Integer(2));
        list2.add(new Integer(4));
        System.out.println("Output should be 2, 4");
        list2.display();
        SortedListInterface<Integer> list3 = merge(list1, list2);
        System.out.println("Output should be 1, 2, 3, 4");
        list3.display();
        System.out.println();

        // test firstLetter
        SortedListInterface<String> stringList = new SortedLinkedList<String>();
        stringList.add("alphabet");
        stringList.add("book");
        stringList.add("car");
        stringList.add("door");
        stringList.add("aaaaa");
        stringList.add("asdf");
        System.out.println("Output should be 3: " + firstLetter(stringList, "abc"));
        stringList.clear();
        System.out.println("Output should be 0: " + firstLetter(stringList, "abc"));
        stringList.add("bad");
        stringList.add("car");
        System.out.println("Output should be 0: " + firstLetter(stringList, "abc"));
        System.out.println("Output should be 0: " + firstLetter(null, null));
        System.out.println("Output should be 1: " + firstLetter(stringList, "cab"));
        stringList.add("dad");
        System.out.println("Output should be 1: " + firstLetter(stringList, "cab"));
        System.out.println();
    }
}