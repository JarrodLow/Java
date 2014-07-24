

public class LabEPartADriver {

	public static void main(String[] args) {
		System.out.println("Traditional Binary Search Tree- No Dups");
		BinarySearchTree<String> nonDupTree = new BinarySearchTree<String>();
		nonDupTree.add("E");
		nonDupTree.add("B");
		nonDupTree.add("C");
		nonDupTree.add("A");
		nonDupTree.add("H");
		nonDupTree.add("D");
		nonDupTree.add("F");
		nonDupTree.add("G");
		System.out.println("Should print \nABCDEFGH");
		nonDupTree.inorderTraverse();
		System.out.println("\nShould print \nEBACDHFG");
		nonDupTree.preorderTraverse();
		nonDupTree.add("B");
		nonDupTree.add("F");
		nonDupTree.add("E");
		System.out.println("\nShould print \nABCDEFGH");
		nonDupTree.inorderTraverse();
		
		System.out.println("\n\nBinary Search Tree With Dups");
		BinarySearchTreeWithDups<String> dupTree = new BinarySearchTreeWithDups<String>();
		dupTree.add("E");
		dupTree.add("B");
		dupTree.add("C");
		dupTree.add("A");
		dupTree.add("H");
		dupTree.add("D");
		dupTree.add("F");
		dupTree.add("G");
		System.out.println("Should print \nABCDEFGH");
		dupTree.inorderTraverse();
		System.out.println("\nShould print \nEBACDHFG");
		dupTree.preorderTraverse();
		dupTree.add("G");
		dupTree.add("B");
		dupTree.add("B");
		dupTree.add("D");
		dupTree.add("E");
		System.out.println("\nShould print \nABBBCDDEEFGGH");
		dupTree.inorderTraverse();
		System.out.println("\nShould print \nEBABBCDDEHFGG");
		dupTree.preorderTraverse();
		System.out.println();
		
		System.out.println("Should print \n[B, B, B]\n" + dupTree.getAllEntries("B"));
		System.out.println("Should print \n[C]\n" + dupTree.getAllEntries("C"));
		System.out.println("Should print \n[]\n" + dupTree.getAllEntries("I"));
		
	}
	
}
