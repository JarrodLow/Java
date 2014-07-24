/**
 * A driver for Homework 3 in CS 111C
 *
 * @author Jessica Masters, Richard Szeto
 *
 */
public class Homework3Driver {

    /***************Homework***************************/
    /*************Algorithm 1*************/
    public static double averageList(Node<Integer> firstNode)
    {
        Node<Integer> currentNode = firstNode;

        int sum = 0;

        int quantity = 0;

        while(currentNode != null)
        {
            sum += currentNode.getData();
            currentNode = currentNode.getNextNode();
            quantity++;
        }

        if(quantity > 0)
            return (double)sum/(double)quantity;
        else
            return 0;
    } // end averageList

    /*************Algorithm 2***************/
    public static boolean isAscendingList(Node<Integer> firstNode)
    {

        if(firstNode != null)
        {
            if(firstNode.getNextNode() == null)
                return true;
        }
        else
            return false;

        Node<Integer> previousNode = firstNode;
        Node<Integer> currentNode = firstNode.getNextNode();

        while(currentNode != null)
        {
            if(previousNode.getData() > currentNode.getData())
                return false;
            else
            {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
        }

        return true;
    } // end isAscendingList

    /************Algorithm 3*************/
    public static Node<Integer> reverseList(Node<Integer> firstNode)
    {
        if(firstNode != null) // at least one node in list
        {
            if(firstNode.getNextNode() != null) // at least two nodes in list
            {
                Node<Integer> firstNodeOther = firstNode.getNextNode();
                Node<Integer> nextNodeOther = firstNodeOther.getNextNode();

                firstNode.setNextNode(null); // detach first node to be tail of new list

                while(firstNodeOther != null)
                {
                    firstNodeOther.setNextNode(firstNode); // new head of original list becomes new head of new list

                    firstNode = firstNodeOther; // set head of new list

                    firstNodeOther = nextNodeOther; // set new head of original list

                    if(nextNodeOther != null) // make sure reference of second node of original list is not null before dereferencing   
                        nextNodeOther = nextNodeOther.getNextNode(); // set second node of original list
                }

                return firstNode; // return reversed list
            }
            else
                return firstNode; // only one node do nothing
        }
        else
            return null; // no list
    } // end reverseList

    public static void main(String[] args) {

        /***************Part 1********************/
        /* chapter 3/6, question 1 */
        String[] names = {"Abby", "Bobby", "Carla", "Doug"};
        LList<String> nameList = new LList(names, 4);
        
        /* chapter 3/6, question 3 */
        String[] newNames = {"Edgar", "Frank"};
        nameList.addAll(newNames);
        System.out.println("Output should be Abby, Bobby, Carla, Doug, Edgar, Frank");
        nameList.display();
        System.out.println();
        
        /* chapter 3/6, question 4 */
        System.out.println("Output should be 3: " + nameList.getPosition("Carla") + "\n");
        System.out.println("Output should be 0 or a negative number: " + nameList.getPosition("George") + "\n");

        /* chapter 3/6, question 5 */
        String[] compareArr1 = {"Abby", "Bobby", "Carla", "Doug", "Edgar", "Frank"};
        LList<String> compareList1 = new LList<String>(compareArr1, 6);
        String[] compareArr2 = {"Bobby", "Carla", "Doug", "Edgar", "Frank"};
        LList<String> compareList2 = new LList<String>(compareArr2, 5);
        String[] compareArr3 = {"Abby", "Bobby", "Carla", "Doug", "Edgar"};
        LList<String> compareList3 = new LList<String>(compareArr3, 5);
        String[] compareArr4 = {"Abby", "Bobby", "Carla", "Doug", "Edgar", "Frank", "Georgie"};
        LList<String> compareList4 = new LList<String>(compareArr4, 7);
        String[] compareArr5 = {"Abby", "Bobby", "Carla", "Chris", "Doug", "Edgar", "Frank"};
        LList<String> compareList5 = new LList<String>(compareArr5, 7);
        
        System.out.println("Output should be true: " + nameList.equals(compareList1));
        System.out.println("Output should be false: " + nameList.equals(compareList2));
        System.out.println("Output should be false: " + nameList.equals(compareList3));
        System.out.println("Output should be false: " + nameList.equals(compareList4));
        System.out.println("Output should be false: " + nameList.equals(compareList5) + "\n");
    
        
        /* 
         * to use the driver program to test chapter 3/6 question 6 and chapter 4/7 question 5, 
         * you need to change:
         *  
         * the class header from:
         *   LList<T>
         * to
         *   LList<T extends Comparable>
         *  
         * 
         */
        
        /* chapter 3/6, question 6 */ 
        nameList.add("Carrie");
        LList<String> newList = nameList.getAllLessThan("Doug");
        System.out.println("Output should be Abby, Bobby, Carla, Carrie");
        newList.display();
        System.out.println();

        /* chapter 4/7, question 1 */
        System.out.println("Output should be false: " + nameList.remove("Harry") + "\n");
        nameList.remove("Carrie");
        nameList.remove("Bobby");
        System.out.println("Output should be Abby, Carla, Doug, Edgar, Frank");
        nameList.display();
        System.out.println();
        
        /* chapter 4/7, question 3 */
        nameList.moveToEnd();
        System.out.println("Output should be Carla, Doug, Edgar, Frank, Abby");
        nameList.display();
        System.out.println();
        // suggestion: test this method with smaller, special-case lists!!
        
        /* chapter 4/7, question 5 */
        System.out.println("Output should be Abby: " + nameList.getMin());
        System.out.println("Output should be Abby: " + nameList.removeMin());;
        System.out.println("Output should be Carla: " + nameList.removeMin());;
        System.out.println("Output should be Doug, Edgar, Frank");
        nameList.display();
        System.out.println();

        /***********************Part 2************************/
        /*************Algorithm 1**************/
        // no list
        Node<Integer> currentNode;
        Node<Integer> firstNode = currentNode = null;
        System.out.println("Output should be 0.0: " + averageList(firstNode));
        // create list: 2,2,2,2
        firstNode = new Node<Integer>(2);
        currentNode = firstNode;
        currentNode.setNextNode(new Node<Integer>(2));
        currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node<Integer>(2));
        currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node<Integer>(2));
        currentNode = currentNode.getNextNode();
        System.out.println("Output should be 2.0: " + averageList(firstNode));
        // create list 1,2,3,4,7
        currentNode = firstNode;
        currentNode.setData(1);
        currentNode = currentNode.getNextNode();
        currentNode.setData(2);
        currentNode = currentNode.getNextNode();
        currentNode.setData(3);
        currentNode = currentNode.getNextNode();
        currentNode.setData(4);
        currentNode.setNextNode(new Node<Integer>(7));
        currentNode = currentNode.getNextNode();
        System.out.println("Output should be 3.4: " + averageList(firstNode));
        System.out.println();

        /**************Algorithm 2*****************/
        // list 1,2,3,4,7
        System.out.println("Output should be true: " + isAscendingList(firstNode));
        // create list 1,2,3,4,0
        currentNode.setData(0);
        System.out.println("Output should be false: " + isAscendingList(firstNode));
        // no list
        firstNode = null;
        System.out.println("Output should be false: " + isAscendingList(firstNode));
        System.out.println();

        /**********************Extra Credit***********************/
        /************question 6.7**************/
        // create list 1,2,3,4,5
        DoublyNode<Integer> doublyFirst = new DoublyNode<Integer>(1);
        DoublyNode<Integer> doublyCurrent = doublyFirst;
        doublyCurrent.setNextNode(new DoublyNode<Integer>(2,doublyCurrent));
        doublyCurrent = doublyCurrent.getNextNode();
        doublyCurrent.setNextNode(new DoublyNode<Integer>(3,doublyCurrent));
        doublyCurrent = doublyCurrent.getNextNode();
        doublyCurrent.setNextNode(new DoublyNode<Integer>(4,doublyCurrent));
        doublyCurrent = doublyCurrent.getNextNode();
        doublyCurrent.setNextNode(new DoublyNode<Integer>(5,doublyCurrent));
        doublyCurrent = doublyCurrent.getNextNode();

        doublyCurrent = doublyFirst;

        System.out.println("Output should be 1, 2, 3, 4, 5, 4, 3, 2, 1");
        System.out.println(doublyCurrent.getData()); // 1
        doublyCurrent = doublyCurrent.getNextNode();
        System.out.println(doublyCurrent.getData()); // 2
        doublyCurrent = doublyCurrent.getNextNode();
        System.out.println(doublyCurrent.getData()); // 3
        doublyCurrent = doublyCurrent.getNextNode();
        System.out.println(doublyCurrent.getData()); // 4
        doublyCurrent = doublyCurrent.getNextNode();
        System.out.println(doublyCurrent.getData()); // 5
        doublyCurrent = doublyCurrent.getPrevNode();
        System.out.println(doublyCurrent.getData()); // 4
        doublyCurrent = doublyCurrent.getPrevNode();
        System.out.println(doublyCurrent.getData()); // 3
        doublyCurrent = doublyCurrent.getPrevNode();
        System.out.println(doublyCurrent.getData()); // 2
        doublyCurrent = doublyCurrent.getPrevNode();
        System.out.println(doublyCurrent.getData()); // 1
        System.out.println();

        /**********Algorithm 3***********/
        // list 1,2,3,4,5
        firstNode = new Node<Integer>(1);
        currentNode = firstNode;
        currentNode.setNextNode(new Node<Integer>(2));
        currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node<Integer>(3));
        currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node<Integer>(4));
        currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node<Integer>(5));
        currentNode = currentNode.getNextNode();
        firstNode = reverseList(firstNode);
        currentNode = firstNode;
        System.out.println("Output should be 5, 4, 3, 2, 1");
        System.out.println(currentNode.getData()); // 5
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 4
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 3
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 2
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 1
        // list 43, 12, 1, 6, 9
        currentNode = firstNode;
        currentNode.setData(43);
        currentNode = currentNode.getNextNode();
        currentNode.setData(12);
        currentNode = currentNode.getNextNode();
        currentNode.setData(1);
        currentNode = currentNode.getNextNode();
        currentNode.setData(6);
        currentNode = currentNode.getNextNode();
        currentNode.setData(9);
        firstNode = reverseList(firstNode);
        System.out.println("Output should be 9, 6, 1, 12, 43");
        System.out.println(currentNode.getData()); // 9
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 6
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 1
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 12
        currentNode = currentNode.getNextNode();
        System.out.println(currentNode.getData()); // 43
    } // end main
} // end Homework3Driver
