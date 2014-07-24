/** Task: Used in homework 8 for problems and testing
 *  @author:     Richard Szeto
 *  @asmt:       Homework 8
 *  @course:     CS 111C
 *  @instructor: Jessica Masters
 */


public class Homework8Driver
{
    /*********************Question 6********************/
    /*********************printInAddOrder()*************/
    public static <T> void printInAddOrder(StackInterface<T> stack)
    {
        if(stack != null && !stack.isEmpty())
        {
            StackInterface<T> temp = new LinkedStack<T>();

            while(!stack.isEmpty())
            {
                temp.push(stack.pop());
            }

            while(!temp.isEmpty())
            {
                T top = temp.pop();

                System.out.println(top);

                stack.push(top);
            }
        }
    }

    /*******************Question 7**********************/
    /*******************isPalindrome()******************/
    public static boolean isPalindrome(String s)
    {
        if(s != null)
        {
            StackInterface<String> stack = new LinkedStack<String>();

            String temp;

            if(s.length() >= 3)
            {
                temp = s.copyValueOf(s.toCharArray(), 1, s.length() - 2);
                stack.push(temp);
            }
            else if(s.length() >= 1)
            {
                temp = s;
                stack.push(temp);
            }
            else
                return false;

            while(temp.length() >= 3)
            {
                temp = temp.copyValueOf(temp.toCharArray(), 1, temp.length() - 2);
                stack.push(temp);
            }

            while(!stack.isEmpty())
            {
                temp = stack.pop();
                if(temp.charAt(0) != temp.charAt(temp.length() - 1))
                    return false;
            }

            return true;
        }

        return false;
    }

    /*********************main()***************************/
    public static void main(String[] args)
    {
        // Part I

        // #6
        StackInterface<String> myStack = new LinkedStack<String>();
        myStack.push("Edgar");
        myStack.push("Alberto");
        myStack.push("Robert");
        myStack.push("Patricia");
        System.out.println("Output should be Edgar, Alberto, Robert, Patricia");
        printInAddOrder(myStack);
        System.out.println("Output should be nothing");
        printInAddOrder(null);
        System.out.println();

        // #7
        System.out.println("Output should be true: " + isPalindrome("racecar"));
        System.out.println("Output should be true: " + isPalindrome("1991"));
        System.out.println("Output should be true: " + isPalindrome("anna"));
        System.out.println("Output should be false: " + isPalindrome("abcdba"));
        System.out.println("Output should be true: " + isPalindrome("aa"));
        System.out.println("Output should be true: " + isPalindrome("a"));
        System.out.println("Output should be false: " + isPalindrome(""));
        System.out.println("Output should be false: " + isPalindrome(null));

        // Part II

        // # EC
        StackInterface<String> myDoubleStack = new DoublyLinkedStack<String>();
        myDoubleStack.push("abc");
        myDoubleStack.push("def");
        myDoubleStack.push("ghi");
        myDoubleStack.push("jkl");
        myDoubleStack.push("mno");
        myDoubleStack.push("pqr");
        myDoubleStack.push("stu");
        myDoubleStack.push("vwx");
        myDoubleStack.push("yz");
        System.out.println("Output should be yz, vwx, stu, pqr, mno, jkl, ghi, def, abc");
        while(!myDoubleStack.isEmpty())
            System.out.println(myDoubleStack.pop());
        System.out.println();
    }
}