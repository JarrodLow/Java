/** Task: Stack made of a doubly linked list
 *  @author:     Richard Szeto
 *  @asmt:       Homework 8
 *  @course:     CS 111C
 *  @instructor: Jessica Masters
 */

public class DoublyLinkedStack<T> implements StackInterface<T>,
                                         java.io.Serializable
{
	private DoublyNode<T> topNode;

	public DoublyLinkedStack()
	{
		topNode = null;
	} // end DoublyLinkedStack

	public void push(T newEntry)
	{
		DoublyNode<T> newNode = new DoublyNode<T>(newEntry);

		if(topNode != null)
			topNode.setNextNode(newNode);

		newNode.setPrevNode(topNode);

		topNode = newNode;
	} // end push

	public T peek()
	{
		T top = null;

		if(topNode != null)
			top = topNode.getData();

		return top;
	} // end peek

	public T pop()
	{
		T top = peek();

		if(topNode != null)
			topNode = topNode.getPrevNode();

		return top;
	} // end pop

	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty

	public void clear()
	{
		topNode = null;
	} // end clear
} // end DoublyLinkedStack