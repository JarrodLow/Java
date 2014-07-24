class Homework9Driver
{
    // Part I

    /*********************Problem 5**********************/
    public static boolean isPalindrome(String s)
    {
        if(s != null)
        {
            DequeInterface<String> stack = new LinkedDeque<String>();

            String temp;

            if(s.length() >= 3)
            {
                temp = s.copyValueOf(s.toCharArray(), 1, s.length() - 2);
                stack.addToFront(temp);
            }
            else if(s.length() >= 1)
            {
                temp = s;
                stack.addToFront(temp);
            }
            else
                return false;

            while(temp.length() >= 3)
            {
                temp = temp.copyValueOf(temp.toCharArray(), 1, temp.length() - 2);
                stack.addToFront(temp);
            }

            while(!stack.isEmpty())
            {
                temp = stack.removeFront();
                if(temp.charAt(0) != temp.charAt(temp.length() - 1))
                    return false;
            }

            return true;
        }

        return false;
    }

    /********************main()********************/
    public static void main(String[] args)
    {
        // Part I

        // Problem 5
        System.out.println("Output should be true: " + isPalindrome("racecar"));
        System.out.println("Output should be true: " + isPalindrome("ee"));
        System.out.println("Output should be true: " + isPalindrome("a"));
        System.out.println("Output should be false: " + isPalindrome("elephant"));
        System.out.println("Output should be false: " + isPalindrome(null));
        System.out.println();

        // Part II

        // Problem 3
        LinkedQueue<String> myQueue = new LinkedQueue<String>();
        QueueInterface<String> anotherQueue = new LinkedQueue<String>();
        myQueue.enqueue("abc");
        myQueue.enqueue("def");
        anotherQueue.enqueue("ghi");
        anotherQueue.enqueue("jkl");
        myQueue.spliceInterface(anotherQueue);
        System.out.println("Output should be ghi, jkl");
        while(!anotherQueue.isEmpty())
            System.out.println(anotherQueue.dequeue());
        System.out.println("Output should be abc, def, ghi, jkl");
        while(!myQueue.isEmpty())
            System.out.println(myQueue.dequeue());
        myQueue.enqueue("abc");
        myQueue.enqueue("def");
        anotherQueue.enqueue("ghi");
        anotherQueue.enqueue("jkl");
        anotherQueue.enqueue("ghi");
        anotherQueue.enqueue("jkl");
        myQueue.spliceInterface(anotherQueue);
        System.out.println("Output should be ghi, jkl, ghi, jkl");
        while(!anotherQueue.isEmpty())
            System.out.println(anotherQueue.dequeue());
        System.out.println("Output should be abc, def, ghi, jkl, ghi, jkl");
        while(!myQueue.isEmpty())
            System.out.println(myQueue.dequeue());
        System.out.println();

        // Problem 4
        ArrayQueue<String> arrayQueue = new ArrayQueue<String>();
        ArrayQueue<String> anotherArray = new ArrayQueue<String>();
        arrayQueue.enqueue("abc");
        arrayQueue.enqueue("def");
        anotherArray.enqueue("ghi");
        anotherArray.enqueue("jkl");
        arrayQueue.spliceArray(anotherArray);
        System.out.println("Output should be ghi, jkl");
        while(!anotherArray.isEmpty())
            System.out.println(anotherArray.dequeue());
        System.out.println("Output should be abc, def, ghi, jkl");
        while(!arrayQueue.isEmpty())
            System.out.println(arrayQueue.dequeue());
        System.out.println();

        // Problem 5
        LinkedQueue<String> anotherLinked = new LinkedQueue<String>();
        myQueue.enqueue("abc");
        myQueue.enqueue("def");
        anotherLinked.enqueue("ghi");
        anotherLinked.enqueue("jkl");
        myQueue.spliceLinkedList(anotherLinked);
        System.out.println("Output should be ghi, jkl");
        while(!anotherLinked.isEmpty())
            System.out.println(anotherLinked.dequeue());
        System.out.println("Output should be abc, def, ghi, jkl");
        while(!myQueue.isEmpty())
            System.out.println(myQueue.dequeue());
        System.out.println();

        // Problem 6
        QueueInterface<String> abcQueue = new QueueFromList<String>();
        abcQueue.enqueue("abc");
        abcQueue.enqueue("def");
        abcQueue.enqueue("ghi");
        System.out.println("Output should be abc, def, ghi");
        while(!abcQueue.isEmpty())
            System.out.println(abcQueue.dequeue());
        System.out.println();

        // Problem 7
        QueueInterface<String> defQueue = new QueueFromDeque<String>();
        defQueue.enqueue("abc");
        defQueue.enqueue("def");
        defQueue.enqueue("ghi");
        System.out.println("Output should be abc, def, ghi");
        while(!defQueue.isEmpty())
            System.out.println(defQueue.dequeue());
        System.out.println();

        // Problem 8
        StackInterface<String> abcStack = new StackFromDeque<String>();
        abcStack.push("abc");
        abcStack.push("def");
        abcStack.push("ghi");
        System.out.println("Output should be ghi, def, abc");
        while(!abcStack.isEmpty())
            System.out.println(abcStack.pop());
        System.out.println();
    }
}