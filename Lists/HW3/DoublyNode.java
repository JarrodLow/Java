/**
 * A class that represents a node in a doubly chain
 *
 * @author Richard Szeto
 *
 */

public class DoublyNode<T>
{
	private T data;
	private DoublyNode<T> prev;
	private DoublyNode<T> next;

	public DoublyNode(T dataPortion)
	{
		data = dataPortion;
		prev = null;
		next = null;
	}

	public DoublyNode(T dataPortion, DoublyNode<T> previousNode)
	{
		data = dataPortion;
		prev = previousNode;
		next = null;
	}

	public DoublyNode(T dataPortion, DoublyNode<T> previousNode, DoublyNode<T> nextNode)
	{
		data = dataPortion;
		prev = previousNode;
		next = nextNode;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T dataPortion)
	{
		data = dataPortion;
	}

	public DoublyNode<T> getPrevNode()
	{
		return prev;
	}

	public void setPrevNode(DoublyNode<T> previousNode)
	{
		prev = previousNode;
	}

	public DoublyNode<T> getNextNode()
	{
		return next;
	}

	public void setNextNode(DoublyNode<T> nextNode)
	{
		next = nextNode;
	}
}