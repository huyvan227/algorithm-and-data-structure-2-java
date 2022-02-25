import java.util.*;
public class BinarySearchTree{
	private class Node{
		private Integer key;
		private Node left, right;
		private int height;
		private int size;
		public Node( Integer key, int size, int height){
			this.key = key;
			this.size = size;
			this.height = height;
			this.left = null;
			this.right = null;
		}
	} 
	Node root ;
	public BinarySearchTree(){
		root = null;
	}
	private int size(Node x) {
		if (x == null) return 0;
		return size(x.left) + size(x.right) + 1;
	}
	private Node put(Node x, Integer key) {
		if (x == null)
			return new Node(key,1, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left,key);	
		}
		else if (cmp > 0) {		
			x.right = put(x.right, key);
		}
		else {
			x.key = key;
		}
		x.size = 1 + size(x.left) + size(x.right); 
		x.height = 1 + Math.max(height(x.right),height(x.left));
		return x;
	}
	public void insert(Integer key){
		root = put(root,key);
	}

	public void createTree(String strKey){ 
		String[] input = strKey.split(" ");
		int size = input.length;
		for(int i = 0;i<size; i++){
			this.insert(Integer.parseInt(input[i]));
		}	
	}

	public void lnr(Node x){
		if (x != null) {
			lnr(x.left);
			System.out.print(x.key + " ");
			lnr(x.right);
		}
	}
	public void printlnr(){
		lnr(root);
	}
	public void printlnr(Node node){
		lnr(node);
	}
	
	public void lrn(Node x){
		if (x != null) {
			lnr(x.left);
			lnr(x.right);
			System.out.print(x.key + " ");
		}
	}
	public void printlrn(){
		lrn(root);
	}
	
	public Node findNode(Node x,Integer key){
		if (x == null){
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			return findNode(x.left, key);
		}
		else if (cmp >0){
			return findNode(x.right, key);
		}
		else{
			return x;
		}
	}
	public Node find(Integer key){
		return findNode(root,key);
	}
	public boolean get(Node x,Integer key){
		if (x == null){
			return false;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			return get(x.left, key);
		}
		else if (cmp >0){
			return get(x.right, key);
		}
		else{
			return true;
		}
	}
	public boolean contains(Integer key){
		return get(root,key);
	}

	public int height(Node x) {
		if (x == null) return 0;
		return x.height = 1 + Math.max(height(x.left), height(x.right));
	}

	public int height() {
		if (root == null) return 0;
		return root.height;
	}

	
	
	
	private Node min(Node x) {
		if(x.left == null){
			return x;
		}
		else{
			return min(x.left);
		}
	}
	private Node deleteMin(Node x) {
		if(x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size =  size(x.left) + size(x.right) + 1;
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}
	private Node delete(Node x, Integer key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete(x.left,key);
		
		}
		else if (cmp > 0) {
			x.right = delete(x.right,key);
			
		}
		else {
			if (x.right == null) 
				return x.left;
			if (x.left == null) 
				return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
			
		}
		
		x.height = Math.max(height(x.left), height(x.right));
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	
	public void delete(Integer key){
		root = delete(root,key);
	}
	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree();
		String a = "8 10 5 20 1 6 9";
		tree.createTree(a);
		System.out.print("LRN order: ");
		tree.printlrn();
		Scanner sc = new Scanner(System.in);
		System.out.print("\nnhap so k : ");
		int number = sc.nextInt();
		boolean find = tree.contains(number);
		if (find == true){
			System.out.printf("so %d co trong cay\n ",number);	
			tree.lnr(tree.find(number));
		}
		else{
			System.out.printf("so %d khong co trong cay\n",number);	
		}
		System.out.print("LNR order: ");
		tree.printlnr();
		
		System.out.print("\nnhap so k de xoa: ");
		int number2 = sc.nextInt();
		find = tree.contains(number2);
		if (find == true){
			System.out.printf("so %d co trong cay\n",number2);	
			tree.delete(number2);
			System.out.printf("cay sau khi xoa %d\n",number2);
			System.out.print("LRN order: ");
			tree.printlrn();
		}
		else{
			System.out.printf("so %d khong co trong cay \n ",number2);	
		}
		
		
		
		
		
	}		
}