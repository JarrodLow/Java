import java.util.*;

public class Homework5Driver {

    /*******************Question 1*********************************/
    /*******************displayRowOfCharacters()*******************/
    public static void displayRowOfCharacters(char c, int num)
    {
        if(num >= 1)
        {
            System.out.print(c);
            displayRowOfCharacters(c, num - 1);
        }
    }

    /***********************Question 3****************************/
    /***********************readGoodInput()***********************/
    public static int readGoodInput()
    {
        System.out.print("Please enter an integer from 1 to 10: ");
        Scanner scanIn = new Scanner(System.in);
        int num = scanIn.nextInt(); // assumes input will always be an integer

        if(num >= 1 && num <= 10)
            return num;

        return readGoodInput();
    }

    /*******************Question 7a*********************/
    /*******************printBackwards1()***************/
    public static void printBackwards1(String forward)
    {
        if(forward != null)
        {
            if(forward.length() >= 1)
            {
                System.out.print(forward.charAt(forward.length() - 1));
                printBackwards1(forward.copyValueOf(forward.toCharArray(), 0, forward.length() - 1));
            }
        }
    }

    /*******************Question 7b*********************/
    /*******************printBackwards2()***************/
    public static void printBackwards2(String forward)
    {
        if(forward != null)
        {
            if(forward.length() >= 1)
            {
                printBackwards2(forward.copyValueOf(forward.toCharArray(), 1, forward.length() - 1));
                System.out.print(forward.charAt(0));
            }
        }
    }

    /******************Question 8***************************/
    /******************isPalindrome()***********************/
    public static boolean isPalindrome(String palin)
    {
        if(palin != null)
        {
            if(palin.length() >= 1)
            {
                if(palin.length() >= 3)
                {
                    if(!isPalindrome(palin.copyValueOf(palin.toCharArray(), 1, palin.length() - 2)))
                        return false;
                }
                if(palin.charAt(0) == palin.charAt(palin.length() - 1))
                    return true;
            }
        }

        return false; // if palin == null || palin.length < 1 || 
                      // palin.charAt(0) != palin.char(palin.length() - 1)
    }

    /*******************Question 11***********************/
    /*******************countLinkedNodes()****************/
    public static int countLinkedNodes(Node<Integer> head)
    {
        Node<Integer> currentNode = head;

        if(currentNode != null)
        {
            return 1 + countLinkedNodes(currentNode.getNextNode());
        }

        return 0; // if currentNode == null
    }

    /******************Question 15***********************/

    /******************sumArray1()***********************/
    public static int sumArray1(int[] array, int first, int last)
    {
        if(array != null)
        {
            if(first < array.length && last < array.length)
            {
                if(first < last)
                    return array[first] + sumArray1(array, first + 1, last);
                else if(first == last)
                    return array[first];
            }
        }

        return 0; // if array == null || first >= array.length || last >= array.length
    }

    /*****************sumArray2()*********************/
    public static int sumArray2(int[] array, int first, int last)
    {
        if(array != null)
        {
            if(first < array.length && last < array.length)
            {
                if(first < last)
                    return array[last] + sumArray2(array, first, last - 1);
                else if(first == last)
                    return array[last];
            }
        }

        return 0; // if array == null || first >= array.length || last >= array.length
    }

    /**********************sumArray3()***************************/
    public static int sumArray3(int[] array, int first, int last)
    {
        if(array != null)
        {
            if(first < array.length && last < array.length)
            {
                if(first < last)
                {
                    int mid = (first + last)/2;
                    return sumArray3(array, first, mid) + sumArray3(array, mid + 1, last);
                }
                else if(first == last)
                    return array[first];
            }
        }

        return 0; // if array == null || first >= array.length || last >= array.length
    }

    /*********************Question 17***************************/
    /*********************f()***********************************/
    public static int f(int n)
    {
        int result = 0;
        if (n <= 4)
            result = 1;
        else
            result = f(n / 2) + f(n / 4);
        return result;
    } // end f

    /***********************Question 18************************/

    /***********************findSecondSmallest()***************/
    public static int findSecondSmallest(int[] array)
    {
        if(array != null)
        {
            if(array.length > 1)
            {
                if(array[0] <= array[1])
                    return linearSearch(array, 2, array[0], array[1]);
                else
                    return linearSearch(array, 2, array[1], array[0]);
            }
        }

        return 999; // if array == null || array.length <= 1
    }

    /*********************linearSearch()**************************/
    // if there are two elements that are the smallest, the second smallest
    // will be the same as the smallest.
    private static int linearSearch(int[] array, int first, int smallest, int secondSmallest)
    {
        if(array != null)
        {
            int small = smallest;
            int secondSmall = secondSmallest;

            if(first >= 0 && first < array.length)
            {
                if(array[first] < small)
                {
                    secondSmall = small;
                    small = array[first];
                }
                else if(array[first] < secondSmall)
                {
                    secondSmall = array[first];
                }
            }

            if(first >= 0 && first < array.length - 1)
                return linearSearch(array, first + 1, small, secondSmall);
            else if(first >= array.length - 1)
                return secondSmall;
            else
                System.out.println("Some error in linearSearch");
        }

        return 999; // if array == null || first >= array.length
    }

    /*********************pseudocode for 18*************************/
    /*
    int findSecondSmallest(int[] array)
        if array length >= 2
            if first element <= second element
                return linearSearch(array, position of 3rd element, position of last element, first element, second element);
            else
                return linearSearch(array, position of 3rd element, position of last element, second element, first element);
        else
            return some arbitrary high number

        // add some error checking

    int linearSearch(int[] array, int first, int last, int smallest, int secondSmallest)
        int small <- smallest;
        int secondSmall <- secondSmallest;

        if first is not out of bounds and first <= last
            if array[first] < current smallest number
                shift previous smallest to previous second smallest
                set current smallest to array[first]
            else if array[first] < current second smallest number
                set second smallest to array[first]

        if first not out of bounds and first < last
            return linearSearch(array, position of second element, small, secondSmall);
        else
            return secondSmall;

        // add some error checking
    */

    /*****************findSecondSmallestBackward()**********************/
    public static int findSecondSmallestBackward(int[] array)
    {
        if(array != null)
        {
            if(array.length > 1)
            {
                if(array[array.length - 1] <= array[array.length - 2])
                    return linearSearchBackward(array, array.length - 3, array[array.length - 1], array[array.length - 2]);
                else
                    return linearSearchBackward(array, array.length - 3, array[array.length - 2], array[array.length - 1]);
            }
        }

        return 999;
    }

    /******************linearSearchBackward()****************************/
    private static int linearSearchBackward(int[] array, int last, int smallest, int secondSmallest)
    {
        if(array != null)
        {
            int small = smallest;
            int secondSmall = secondSmallest;

            if(last >= 0 && last < array.length)
            {
                if(array[last] < small)
                {
                    secondSmall = small;
                    small = array[last];
                }
                else if(array[last] < secondSmall)
                {
                    secondSmall = array[last];
                }
            }

            if(last > 0 && last <= array.length - 1)
                return linearSearchBackward(array, last - 1, small, secondSmall);
            else if(last == 0 || last == -1)
                return secondSmall;
            else
                System.out.println("Some error in linearSearchBackward");
        }

        return 999;
    }

    /***********************iterativeSecondSmallest()*****************/
    public static int iterativeSecondSmallest(int[] array)
    {
        if(array != null)
        {
            int smallest;
            int secondSmallest;
            if(array.length > 1)
            {
                if(array[0] <= array[1])
                {
                    smallest = array[0];
                    secondSmallest = array[1];
                }
                else
                {
                    smallest = array[1];
                    secondSmallest = array[0];
                }

                for(int i = 2; i < array.length; i++)
                {
                    if(array[i] < smallest)
                    {
                        secondSmallest = smallest;
                        smallest = array[i];
                    }
                    else if(array[i] < secondSmallest)
                    {
                        secondSmallest = array[i];
                    }
                }
                return secondSmallest;
            }  
        }

        return 999; // some arbitrary large number
                    // if something goes wrong
    }

    /***********************iterativeBackwardSecondSmallest()********/
    public static int iterativeBackwardSecondSmallest(int[] array)
    {
        if(array != null)
        {
            int smallest;
            int secondSmallest;
            if(array.length > 1)
            {
                if(array[array.length - 1] <= array[array.length - 2])
                {
                    smallest = array[array.length - 1];
                    secondSmallest = array[array.length - 2];
                }
                else
                {
                    smallest = array[array.length - 2];
                    secondSmallest = array[array.length - 1];
                }

                for(int i = array.length - 3; i >= 0; i--)
                {
                    if(array[i] < smallest)
                    {
                        secondSmallest = smallest;
                        smallest = array[i];
                    }
                    else if(array[i] < secondSmallest)
                    {
                        secondSmallest = array[i];
                    }
                }
                return secondSmallest;
            }  
        }

        return 999; // some arbitrary large number
                    // if something goes wrong
    }

    /*********************Extra Credit***************************/

    /*******************Question EC******************************/

    /*******************containsClient()*************************/
    public static <T> boolean containsClient(ListInterface<T> list, T anEntry)
    {
        if(list != null && anEntry != null)
        {
            if(list.isEmpty())
                return false;
            else
                return findEntry(list, anEntry, 1);
        }
        else
            return false;
    }

    // best case O(1), worst case O(n^2)
    /**********************findEntry()*******************************/
    private static <T> boolean findEntry(ListInterface<T> list, T anEntry, int first)
    {
        if(list != null && anEntry != null)
        {
            if(anEntry.equals(list.getEntry(first)))
                return true;
            else if(first < list.getLength())
                return findEntry(list, anEntry, first + 1);
            else
                return false;
        }
        else
            return false;
    }

    /************************main()*************************/
    public static void main(String[] args)
    {

        /*
         * #1 should output:********** and sssss
         */
         displayRowOfCharacters('*', 10);
         displayRowOfCharacters('s', 5);
         System.out.println();
         System.out.println();
         
        // #3
        int value = readGoodInput();
        System.out.println("The user entered: " + value);
        System.out.println();
         
        // #7
        String s = "stressed";
        System.out.print(s + " backwards is ");
        printBackwards1(s);
        System.out.println();
        System.out.print(s + " backwards is still ");
        printBackwards2(s);
        System.out.println();
        System.out.println("Output should be nothing");
        printBackwards1(null);
        System.out.println("Output should be nothing");
        printBackwards2(null);
        System.out.println();
        
        
        // #8
        String p = "anna";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        p = "ana";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        p = "a";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        p = "amanaplanacanalpanama";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        p = "ababb";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        p = "ab";
        System.out.println(p + " is a palindrome? " + isPalindrome(p));
        System.out.println("Output should be false: " + isPalindrome(null));
        System.out.println();

        // #11
        Node<Integer> intHead = new Node<Integer>(1); // 1
        Node<Integer> intCurrent = intHead;
        intCurrent.setNextNode(new Node<Integer>(2)); // 1, 2
        intCurrent = intCurrent.getNextNode();
        intCurrent.setNextNode(new Node<Integer>(3)); // 1, 2, 3
        intCurrent = intCurrent.getNextNode();
        intCurrent.setNextNode(new Node<Integer>(4)); // 1, 2, 3, 4
        intCurrent = intCurrent.getNextNode();
        System.out.println("Output should be 4: " + countLinkedNodes(intHead));
        System.out.println("Output should be 1: " + countLinkedNodes(intCurrent));
        System.out.println("Output should be 0: " + countLinkedNodes(null));
        System.out.println();
        
        // #15
        int arraySize = 10;
        int[] numsToSum = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            numsToSum[i] = i * 3;

        int sum1 = sumArray1(numsToSum, 0, arraySize - 1);
        int sum2 = sumArray2(numsToSum, 0, arraySize - 1);
        int sum3 = sumArray3(numsToSum, 0, arraySize - 1);
        System.out.println("The sum of the array with all three methods should be the same: ");
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
        System.out.println();

        // #17
        System.out.println("Output should be 3: " + f(16));
        System.out.println();
        
        // #18
        int[] randomNums = new int[arraySize];
        Random generator = new Random();
        for(int i=0; i<randomNums.length; i++)
        {
            randomNums[i] = generator.nextInt(100);
        }
        int[] myArray = new int[2];
        myArray[0] = 1;
        myArray[1] = 2;
        System.out.print("Here are the numbers in the array: ");
        for(int i=0; i<randomNums.length; i++)
        {
            System.out.print(randomNums[i] + " ");
        }
        System.out.println();
        int secondSmallest = findSecondSmallest(randomNums);
        System.out.println("The second smallest element is " + secondSmallest);
        System.out.print("Here are the numbers in the array: ");
        for(int i=0; i<randomNums.length; i++)
        {
            System.out.print(randomNums[i] + " ");
        }
        System.out.println();
        System.out.println("The second smallest element in myArray is: " + findSecondSmallest(myArray));
        System.out.println("The second smallest element is: " + findSecondSmallestBackward(randomNums));
        System.out.print("Here are the numbers in the array: ");
        for(int i=0; i<randomNums.length; i++)
        {
            System.out.print(randomNums[i] + " ");
        }
        System.out.println();
        System.out.println("The second smallest element in myArray is: " + findSecondSmallestBackward(myArray));
        System.out.println("The second smallest element is: " + iterativeSecondSmallest(randomNums));
        System.out.print("Here are the numbers in the array: ");
        for(int i=0; i<randomNums.length; i++)
        {
            System.out.print(randomNums[i] + " ");
        }
        System.out.println();
        System.out.println("The second smallest element in myArray is: " + iterativeSecondSmallest(myArray));
        System.out.println("The second smallest element is: " + iterativeBackwardSecondSmallest(randomNums));
        System.out.print("Here are the numbers in the array: ");
        for(int i=0; i<randomNums.length; i++)
        {
            System.out.print(randomNums[i] + " ");
        }
        System.out.println();
        System.out.println("The second smallest element in myArray is: " + iterativeBackwardSecondSmallest(myArray));
        System.out.println();
        
        // Extra Credit 13
        // Note: you need to use a revised AList file (with your contains method) to run this methods
        ListInterface<String> nameList = new AList<String>();
        System.out.println("AList:");
        System.out.println("Contains should return false (and not crash): " + nameList.contains("Fred"));
        
        nameList.add("Abby");
        nameList.add("Bobby");
        nameList.add("Carla");
        nameList.add("Doug");
        nameList.add("Bobby");
        nameList.add("Bobby");
        nameList.add("Edgar");
        
        System.out.println("Contains should return true: " + nameList.contains("Abby"));
        System.out.println("Contains should return true: " + nameList.contains("Doug"));
        System.out.println("Contains should return true: " + nameList.contains("Bobby"));
        System.out.println("Contains should return false: " + nameList.contains("Fred"));
        System.out.println();
        
        // Extra Credit 14
        // Note: you need to use a revised LList file (with your contains method) to run this methods
        nameList = new LList<String>();
        System.out.println("LList:");
        System.out.println("Contains should return false (and not crash): " + nameList.contains("Fred"));
                
        nameList.add("Abby");
        nameList.add("Bobby");
        nameList.add("Carla");
        nameList.add("Doug");
        nameList.add("Bobby");
        nameList.add("Bobby");
        nameList.add("Edgar");
                
        System.out.println("Contains should return true: " + nameList.contains("Abby"));
        System.out.println("Contains should return true: " + nameList.contains("Doug"));
        System.out.println("Contains should return true: " + nameList.contains("Bobby"));
        System.out.println("Contains should return false: " + nameList.contains("Fred"));
        System.out.println();
        
        // Extra Credit EC
        System.out.println("Client Side:");
        System.out.println("Contains should return true: " + containsClient(nameList, "Abby"));
        System.out.println("Contains should return true: " + containsClient(nameList, "Doug"));
        System.out.println("Contains should return true: " + containsClient(nameList, "Bobby"));
        System.out.println("Contains should return false: " + containsClient(nameList, "Fred"));
        nameList = new AList<String>();
        System.out.println("Contains should return false (and not crash): " + nameList.contains("Fred")); 
    }


}