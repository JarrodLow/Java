package TreePackage;import java.util.Iterator;import java.util.NoSuchElementException;import StackPackage.*;import QueuePackage.*;public class BinaryTree<T> implements BinaryTreeInterface<T>,                                         java.io.Serializable{    private BinaryNodeInterface<T> root;        public BinaryTree()    {        root = null;    } // end default constructor        public BinaryTree(T rootData)    {        root = new BinaryNode<T>(rootData);    } // end constructor        public BinaryTree(T rootData, BinaryTree<T> leftTree,                                     BinaryTree<T> rightTree)    {        privateSetTree(rootData, leftTree, rightTree);    } // end constructor        public void setTree(T rootData)    {        root = new BinaryNode<T>(rootData);    } // end setTree        public void setTree(T rootData, BinaryTreeInterface<T> leftTree,                                        BinaryTreeInterface<T> rightTree)    {        privateSetTree(rootData, (BinaryTree<T>)leftTree,                                     (BinaryTree<T>)rightTree);    } // end setTree    private void privateSetTree(T rootData, BinaryTree<T> leftTree,                                         BinaryTree<T> rightTree)    {        root = new BinaryNode<T>(rootData);          if ((leftTree != null) && !leftTree.isEmpty())            root.setLeftChild(leftTree.root);            if ((rightTree != null) && !rightTree.isEmpty())        {            if (rightTree != leftTree)                root.setRightChild(rightTree.root);            else                root.setRightChild(rightTree.root.copy());        } // end if          if ((leftTree != null) && (leftTree != this))            leftTree.clear();             if ((rightTree != null) && (rightTree != this))            rightTree.clear();    } // end privateSetTree        public T getRootData()    {        T rootData = null;          if (root != null)            rootData = root.getData();            return rootData;    } // end getRootData    public boolean isEmpty()    {        return root == null;    } // end isEmpty    public void clear()    {        root = null;    } // end clear    protected void setRootData(T rootData)    {        root.setData(rootData);    } // end setRootData    protected void setRootNode(BinaryNodeInterface<T> rootNode)    {        root = rootNode;    } // end setRootNode    protected BinaryNodeInterface<T> getRootNode()    {        return root;    } // end getRootNode    public int getHeight()    {        return root.getHeight();    } // end getHeight    public int getNumberOfNodes()    {        return root.getNumberOfNodes();    } // end getNumberOfNodes    public Iterator<T> getInorderIterator()    {        return new InorderIterator();    } // end getInorderIterator    public void inorderTraverse()    {        StackInterface<BinaryNodeInterface<T>> nodeStack =                            new LinkedStack<BinaryNodeInterface<T>>();        BinaryNodeInterface<T> currentNode = root;          while (!nodeStack.isEmpty() || (currentNode != null))        {            // find leftmost node with no left child            while (currentNode != null)            {                nodeStack.push(currentNode);                currentNode = currentNode.getLeftChild();            } // end while                // visit leftmost node, then traverse its right subtree            if (!nodeStack.isEmpty())            {                BinaryNodeInterface<T> nextNode = nodeStack.pop();                assert nextNode != null; // since nodeStack was not empty                               // before the pop                System.out.println(nextNode.getData());                currentNode = nextNode.getRightChild();            } // end if        } // end while    } // end inorderTraverse    private class InorderIterator implements Iterator<T>    {        private StackInterface<BinaryNodeInterface<T>> nodeStack;        private BinaryNodeInterface<T> currentNode;          public InorderIterator()        {            nodeStack = new LinkedStack<BinaryNodeInterface<T>>();            currentNode = root;        } // end default constructor              public boolean hasNext()         {            return !nodeStack.isEmpty() || (currentNode != null);        } // end hasNext              public T next()        {            BinaryNodeInterface<T> nextNode = null;                    // find leftmost node with no left child            while (currentNode != null)            {                nodeStack.push(currentNode);                currentNode = currentNode.getLeftChild();            } // end while                    // get leftmost node, then move to its right subtree            if (!nodeStack.isEmpty())            {                nextNode = nodeStack.pop();                assert nextNode != null; // since nodeStack was not empty                                   // before the pop                currentNode = nextNode.getRightChild();            }            else                throw new NoSuchElementException();                      return nextNode.getData();         } // end next              public void remove()        {            throw new UnsupportedOperationException();        } // end remove    } // end InorderIterator    public Iterator<T> getPreorderIterator()    {        return new PreorderIterator();    } // end getPreorderIterator    public void preorderTraverse()    {        StackInterface<BinaryNodeInterface<T>> nodeStack =                            new LinkedStack<BinaryNodeInterface<T>>();        BinaryNodeInterface<T> currentNode = root;        while (!nodeStack.isEmpty() || (currentNode != null))        {            // find root node            if(currentNode != null)                nodeStack.push(currentNode);            // visit root node, then traverse its left subtree            if(!nodeStack.isEmpty())            {                BinaryNodeInterface<T> nextNode = nodeStack.pop();                assert nextNode != null;                System.out.println(nextNode.getData());                // push rightChild onto stack                if(nextNode.getRightChild() != null)                    nodeStack.push(nextNode.getRightChild());                currentNode = nextNode.getLeftChild();            } // end if        } // end while    } // end preorderTraverse    private class PreorderIterator implements Iterator<T>    {        private StackInterface<BinaryNodeInterface<T>> nodeStack;        private BinaryNodeInterface<T> currentNode;          public PreorderIterator()        {            nodeStack = new LinkedStack<BinaryNodeInterface<T>>();            currentNode = root;        } // end default constructor              public boolean hasNext()         {            return !nodeStack.isEmpty() || (currentNode != null);        } // end hasNext              public T next()        {            BinaryNodeInterface<T> nextNode = null;                    // find root node            if(currentNode != null)                nodeStack.push(currentNode);            // visit root node, then traverse its left subtree            if(!nodeStack.isEmpty())            {                nextNode = nodeStack.pop();                assert nextNode != null;                System.out.println(nextNode.getData());                // push rightChild onto stack                if(nextNode.getRightChild() != null)                    nodeStack.push(nextNode.getRightChild());                currentNode = nextNode.getLeftChild();            } // end if            else                throw new NoSuchElementException();                      return nextNode.getData();         } // end next              public void remove()        {            throw new UnsupportedOperationException();        } // end remove    } // end PreorderIterator    public Iterator<T> getPostorderIterator()    {        return new PostorderIterator();    } // end getPostorderIterator    public void postorderTraverse()    {        StackInterface<BinaryNodeInterface<T>> nodeStack =                            new LinkedStack<BinaryNodeInterface<T>>();        BinaryNodeInterface<T> currentNode = null;        BinaryNodeInterface<T> previousNode = null;        if(root != null)            nodeStack.push(root);        while(!nodeStack.isEmpty())        {            currentNode = nodeStack.peek();            // want to traverse left subtree, then right subtree            if(previousNode == null ||                previousNode.getLeftChild() == currentNode ||                    previousNode.getRightChild() == currentNode)            {                if(currentNode.getLeftChild() != null)                    nodeStack.push(currentNode.getLeftChild());                else if(currentNode.getRightChild() != null)                    nodeStack.push(currentNode.getRightChild());            }            // if we just visited left child, then traverse right subtree            else if(previousNode != null &&                 currentNode.getLeftChild() == previousNode)            {                if(currentNode.getRightChild() != null)                    nodeStack.push(currentNode.getRightChild());            }            // visit a node when cannot traverse (visit root)            else            {                System.out.println(currentNode.getData());                nodeStack.pop();            } // end if            previousNode = currentNode;        } // end while    } // end postorderTraverse    private class PostorderIterator implements Iterator<T>    {        private StackInterface<BinaryNodeInterface<T>> nodeStack;        private BinaryNodeInterface<T> previousNode;        private BinaryNodeInterface<T> currentNode;          public PostorderIterator()        {            nodeStack = new LinkedStack<BinaryNodeInterface<T>>();            previousNode = null;            currentNode = null;            if(root != null)                nodeStack.push(root);        } // end default constructor              public boolean hasNext()         {            return !nodeStack.isEmpty();        } // end hasNext              public T next()        {            boolean done = false;            while(!done || !nodeStack.isEmpty())            {                currentNode = nodeStack.peek();                if(previousNode == null ||                    previousNode.getLeftChild() == currentNode ||                        previousNode.getRightChild() == currentNode)                {                    if(currentNode.getLeftChild() != null)                        nodeStack.push(currentNode.getLeftChild());                    else if(currentNode.getRightChild() != null)                        nodeStack.push(currentNode.getRightChild());                }                else if(previousNode != null &&                     currentNode.getLeftChild() == previousNode)                {                    if(currentNode.getRightChild() != null)                        nodeStack.push(currentNode.getRightChild());                }                else                {                    done = true;                    nodeStack.pop();                } // end if                previousNode = currentNode;            } // end while                        if(!done)                throw new NoSuchElementException();                      return currentNode.getData();         } // end next              public void remove()        {            throw new UnsupportedOperationException();        } // end remove    } // end PostorderIterator    // LevelOrder methods don't work, just used as placeholders    // for interface    public Iterator<T> getLevelOrderIterator()    {        return new LevelOrderIterator();    } // end getLevelOrderIterator    //public void levelOrderTraverse()    public void levelOrderTraverse()    {    }    //private class LevelOrderIterator implements Iterator<T>    private class LevelOrderIterator implements Iterator<T>    {        private QueueInterface<BinaryNodeInterface<T>> nodeQueue;        private BinaryNodeInterface<T> currentNode;          public LevelOrderIterator()        {            nodeQueue = new LinkedQueue<BinaryNodeInterface<T>>();            currentNode = root;        } // end default constructor              public boolean hasNext()         {            return !nodeQueue.isEmpty() || (currentNode != null);        } // end hasNext              public T next()        {            BinaryNodeInterface<T> nextNode = null;            nextNode = nodeQueue.dequeue();             return nextNode.getData();         } // end next              public void remove()        {            throw new UnsupportedOperationException();        } // end remove    } // end PostorderIterator    /**************Homework 10*******************/    // Chapter 26 Problem 7    public int count(T anObject)    {        return count(root, anObject);    }    private int count(BinaryNodeInterface<T> node,        T anObject)    {        int result = 0;        if(node != null)        {            if(node.getData().equals(anObject))            {                result = 1;            }            result = count(node.getLeftChild(), anObject) +                     result +                     count(node.getRightChild(), anObject);        }        return result;    }} // end BinaryTree