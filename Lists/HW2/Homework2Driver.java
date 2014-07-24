

public class Homework2Driver {

    public static void main(String[] args) {
        String[] names = {"Abby","Bobby","Carla","Doug","Bobby"};

        /* question 1 */
        AList<String> nameTransfer = new AList<String>(names);

        System.out.println("Output should be Abby, Bobby, Carla, Doug, Bobby");
        nameTransfer.display();
        System.out.println();


        AList<String> nameList = new AList<String>();
        nameList.add("Abby");
        nameList.add("Bobby");
        nameList.add("Carla");
        nameList.add("Doug");
        nameList.add("Bobby");
        nameList.add("Bobby");

        AList nameL = new AList();

        /* question 2 */
        System.out.println("Output should be 3: " + nameList.getPosition("Carla"));
        System.out.println("Output should be 0 or a negative number: " + nameList.getPosition("Jessica"));
        System.out.println("Output should be 2: " + nameList.getPosition("Bobby") + "\n");

        /* question 3 */
        nameList.remove("Bobby");
        System.out.println("Output should be Abby, Carla, Doug, Bobby, Bobby");
        nameList.display();
        System.out.println("Output should be false: " + nameList.remove("Jessica"));
        System.out.println();
        
        /* question 4 */
        nameList.moveToEnd();
        System.out.println("Output should be Carla, Doug, Bobby, Bobby, Abby");
        nameList.display();
        System.out.println();

        /* question 6 */
        /* note the method must be renamed since "replace" already exists */
        nameList.replaceAndReturn(4, "Edgar");
        System.out.println("Output should be Carla, Doug, Bobby, Edgar, Abby");
        nameList.display();
        System.out.println();

        /*
         * to use the driver program to test question 7, you need to change:
         * 
         * the class header from: AList<T> to AList<T extends Comparable>
         * 
         * and the code in all of the constructors from: list = (T[]) new
         * Object[maxSize]; to list = (T[]) new Comparable[maxSize];
         */

        /* question 7a */
        System.out.println("Output should be Abby: " + nameList.getMin() + "\n");

        /* question 7b */
        nameList.removeMin();
        System.out.println("Output should be Carla, Doug, Bobby, Edgar");
        nameList.display();
        System.out.println();
        
        /* question 8 */
        AList<String> otherNames = new AList<String>();
        otherNames.add("Carla");
        otherNames.add("Doug");
        otherNames.add("Bobby");
        otherNames.add("Edgar");
        System.out.println("Output should be true: " + nameList.equals(otherNames));
        otherNames.replace(4,"Bobby");
        System.out.println("Output should be false: " + nameList.equals(otherNames));
        System.out.println();

        /* question 10 */
        ShrinkableArrayList<String> reduceNames = new ShrinkableArrayList<String>();
        reduceNames.add("Carla");
        reduceNames.add("Doug");
        reduceNames.add("Bobby");
        reduceNames.add("Edgar");
        System.out.println("Output should be 50: " + reduceNames.getArrayLength());
        reduceNames.remove(1);
        System.out.println("Output should be 38: " + reduceNames.getArrayLength());
        reduceNames.remove(1);
        System.out.println("Output should be 29: " + reduceNames.getArrayLength());

        
    }
}