import StackPackage.*;
import QueuePackage.*;
import TreePackage.*;

public class Homework10Driver
{
	public static void main(String[] args)
	{
		// Chapter 25 Problem 7
		BinaryTreeInterface<Integer> two = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> one = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> three = new BinaryTree<Integer>();
		two.setTree(new Integer(2));
		one.setTree(new Integer(1));
		three.setTree(new Integer(3), two, one);

		BinaryTreeInterface<Integer> four = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> six = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> five = new BinaryTree<Integer>();
		four.setTree(new Integer(4));
		six.setTree(new Integer(6));
		five.setTree(new Integer(5), four, six);

		BinaryTreeInterface<Integer> nine = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> seven = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> ten = new BinaryTree<Integer>();
		nine.setTree(new Integer(9));
		seven.setTree(new Integer(7));
		ten.setTree(new Integer(10), nine, seven);

		BinaryTreeInterface<Integer> eight = new BinaryTree<Integer>();
		eight.setTree(new Integer(8), three, five);

		BinaryTreeInterface<Integer> eleven = new BinaryTree<Integer>();
		eleven.setTree(new Integer(11), eight, ten);

		System.out.println("Preorder Traversal");
		((BinaryTree<Integer>)eleven).preorderTraverse();
		System.out.println("Postorder Traversal");
		((BinaryTree<Integer>)eleven).postorderTraverse();
		System.out.println("Inorder Traversal");
		((BinaryTree<Integer>)eleven).inorderTraverse();
		System.out.println();

		// Chapter 26 Problem 7
		System.out.println(((BinaryTree<Integer>)eleven).count(new Integer(2)));

		BinaryTreeInterface<Integer> tempTwo = new BinaryTree<Integer>();
		BinaryTreeInterface<Integer> tempTwoTwo = new BinaryTree<Integer>();
		tempTwo.setTree(new Integer(2));
		tempTwoTwo.setTree(new Integer(2));
		BinaryTreeInterface<Integer> tempTwoTwoTwo = new BinaryTree<Integer>();
		tempTwoTwoTwo.setTree(new Integer(2), tempTwo, tempTwoTwo);
		System.out.println(((BinaryTree<Integer>)tempTwoTwoTwo).count(new Integer(2)));

		BinaryTreeInterface<Integer> fifty = new BinaryTree<Integer>();
		fifty.setTree(new Integer(50), tempTwoTwoTwo, null);
		System.out.println(((BinaryTree<Integer>)fifty).count(new Integer(2)));
		
		System.out.println(((BinaryTree<Integer>)fifty).count(new Integer(1)));
		System.out.println();
	}
}