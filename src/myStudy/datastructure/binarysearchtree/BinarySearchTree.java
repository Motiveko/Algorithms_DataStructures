package myStudy.datastructure.binarysearchtree;

import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
public class BinarySearchTree<T extends Comparable<T>> {

	private int nodeCount = 0;
	private Node root = null;
	private class Node{
		T data;
		Node left, right;
		
		public Node(Node left, Node right, T data) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	public boolean isEmpty() {
		return size()==0;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public boolean add(T elem) {
		if(contains(elem)) return false;
		else {
			root = add(root, elem);
			nodeCount++;
			return true;
		}
	}
	
	private Node add(Node node, T elem) {
		
		if(node == null) return new Node(null, null, elem);
		
		if(node.data.compareTo(elem)>0) {
			node.left = add(node.left, elem);
		} else node.right = add(node.right, elem);
		
		return node;
	}
	
	public boolean contains(T elem) {
		if(root == null) return false;
		return contains(root, elem);
	}
	
	public boolean remove( T elem) {
		
		if(!contains(elem)) return false;
		else {
			root = remove(root, elem);
			nodeCount--;
			return true;
		}
	}
	
	private Node remove(Node node, T elem) {
		
		if( node.data.compareTo(elem) >0 ) {
			node.left = remove(node.left, elem);
		} else if( node.data.compareTo(elem) < 0){
			node.right = remove(node.right, elem); 
		} else {
			if(node.left == null) {
				Node rightChild = node.right;
				node.data = null;
				node = null;
				return  rightChild;
			} else if(node.right == null) {
				Node leftChild = node.left;
				node.data = null;
				node = null;
				return leftChild;
			} else {
				
				Node tmp = findMin(node.right);
				node.data = tmp.data;
				node.right = remove(node.right, tmp.data);
			}
		}
		
		return node;
		
	}
	
	private Node findMax(Node node) {
		//right digging
		if(node.right != null) return findMax(node.right);
		else return node;
	}
	private Node findMin(Node node) {
		//Left digging
		if(node.left!=null) return findMin(node.left);
		else return node;
	}
	private boolean contains(Node node, T elem) {
		if(node == null) return false;
		
		int cmp = elem.compareTo(node.data);
		
		if(cmp > 0) return contains(node.right, elem);
		if(cmp <0 ) return contains(node.left, elem);
		
		else return true;
	}
	
	public int heigth() {
		return height(root);
	}
	private int height(Node node) {
		if(node.right ==null && node.left ==null) return 1;
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	public java.util.Iterator<T> traverse(TreeTraversalOrder order){
		
		switch(order) {
		case PRE_ORDER:
			return preOrderTraversal();
		case IN_ORDER:
			return inOrderTraversal();
		case POST_ORDER:
			return postOrderTraversal();
		case LEVEL_ORDER:
			return levelOrderTraversal();
		default:
			return null;
		}
		
	}
	
	private Iterator<T> preOrderTraversal(){
		
		Stack<Node> stack = new Stack<>();
		int expectedNodeCount = nodeCount;
		stack.push(root);
		
		return new Iterator<T>() {
			
			@Override
			public boolean hasNext() {
				if( expectedNodeCount != nodeCount) throw new ConcurrentModificationException("tree가 수정되었습니다");
	
				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				Node node = stack.pop();
				if( node.right != null) stack.push(node.right);
				if( node.left != null) stack.push(node.left);
			
				return node.data;
			}
		};
	}
	
	private Iterator<T> inOrderTraversal(){
		
		Stack<Node> stack = new Stack<>();

		stack.push(root);
		int expectedNodeCount = nodeCount;
		return new Iterator<T>() {
			
			Node trav = root;
			@Override
			public boolean hasNext() {
				if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException("tree has been changed!");
				return root!=null && !stack.isEmpty();
			}

			@Override
			public T next() {

				while( trav.left!=null) {
					stack.push(trav.left);
					trav = trav.left;
				}
				
				Node node = stack.pop();
				
				if(node.right != null) {
					stack.push(node.right);
					trav = node.right;
				}

				return node.data;
			}
		};
	}
	
	private Iterator<T> postOrderTraversal(){
		
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		
		stack1.push(root);
		int expectedNodeCount = nodeCount;
		
		while(!stack1.isEmpty()) {
			Node node = stack1.pop();
			stack2.push(node);
			if(node.left !=null) stack1.push(node.left);
			if(node.right !=null) stack1.push(node.right);
		}
		
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException("Tree has been changed!");
				return root!=null && !stack2.isEmpty();
			}

			@Override
			public T next() {
		        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
				return stack2.pop().data;
			}
			
		};
	}
	
	private Iterator<T> levelOrderTraversal(){
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		
		int expectedNodeCount = nodeCount;
		
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException("Tree has been changed");
				return root!=null && ! queue.isEmpty();
			}

			@Override
			public T next() {
				Node node = queue.poll();
				if(node.left!=null) queue.offer(node.left);
				if(node.right!=null) queue.offer(node.right);
				return node.data;
			}
		};
	}
	//Test

	public static void main(String[] args) {
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		tree.add(13);
		tree.add(10);
		tree.add(47);
		tree.remove(47);
		tree.add(18);
		tree.add(17);
		tree.add(20);
		tree.add(19);
		tree.add(23);
		tree.add(3);
		tree.add(12);
		tree.add(11);
		
		Iterator<Integer> preItr = tree.traverse(TreeTraversalOrder.PRE_ORDER);
		Iterator<Integer> inItr =tree.traverse(TreeTraversalOrder.IN_ORDER);
		Iterator<Integer> postItr =tree.traverse(TreeTraversalOrder.POST_ORDER);
		Iterator<Integer> levelItr =tree.traverse(TreeTraversalOrder.LEVEL_ORDER);

		System.out.print("Pre Order {");
		while(preItr.hasNext()) {
			System.out.print(preItr.next() + ", ");
		}
		System.out.println("}");
		
		System.out.print("In Order {");
		while(inItr.hasNext()) {
			System.out.print(inItr.next() + ", ");
		}
		System.out.println("}");
		
		System.out.print("Post Order {");
		while(postItr.hasNext()) {
			System.out.print(postItr.next() + ", ");
		}
		System.out.println("}");
		
		System.out.print("Level Order{");
		while(levelItr.hasNext()) {
			System.out.print(levelItr.next() + ", ");
		}
		System.out.println("}");
	}
	
	
}
