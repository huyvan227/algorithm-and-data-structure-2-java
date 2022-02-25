public class AVLTree{
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
	public AVLTree(){
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
		//cau 5 a
		x.height = 1 + Math.max(height(x.right),height(x.left));
		return balance(x);
	}
	
	
	private int checkBalance(Node x){
		return height(x.left) - height(x.right);
	}
	
	private Node rotateLeft(Node x){
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}
	private Node rotateRight(Node x){
		Node y = x.left;
		x.left = y.right;
		y.right = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}
	private Node balance(Node x){
		if (checkBalance(x) <-1){
			if (checkBalance(x.right) > 0){
				x.right = rotateRight(x.right);
			}
			x = rotateLeft(x);
		}
		else if (checkBalance(x) > 1){
			if (checkBalance(x.left) < 0){
				x.left = rotateLeft(x.left);
			}
			x = rotateRight(x);
		}
		return x;
	}
	
	public void insert(Integer key){
		root = put(root,key);
		
	}
	//cau 1
	public void createTree(String strKey){ 
		String[] input = strKey.split(" ");
		int size = input.length;
		for(int i = 0;i<size; i++){
			this.insert(Integer.parseInt(input[i]));
		}	
	}
	public void nlr(Node x){
		if(x != null){
			System.out.print(x.key + " ");
			nlr(x.left);
			nlr(x.right);
		}
	}
	public void printnlr(){
		nlr(root);
	}
	//cau2
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
	//cau3
	public void rnl(Node x){
		if (x != null) {
			rnl(x.right);
			System.out.print(x.key + " ");
			rnl(x.left);
		}
	}
	public void printrnl(){
		rnl(root);
	}
	//cau 4


	public int height(Node x) {
		if (x == null) return 0;
		return x.height = 1 + Math.max(height(x.left), height(x.right));
	}
	//cau 5 c
	public int height() {
		if (root == null) return 0;
		return root.height;
	}
	
	
	
	private Node deleteMin(Node x) {
		if(x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x = balance(x);
		x.size =  size(x.left) + size(x.right) + 1;
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}
	public void deleteMin() {
		deleteMin(root);
	}
	
	private Node min(Node x) {
		Node minR = x;
		while(x.left != null) {
			minR = minR.left;
		}
		return minR;
	}

	private Node delete1(Node x, Integer key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete1(x.left,key);
			x = balance(x);
		}
		else if (cmp > 0) {
			x.right = delete1(x.right,key);
			x = balance(x);
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
			x = balance(x);
		}
		
		x.height = Math.max(height(x.left), height(x.right));
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	public void delete1(Integer key) {
		delete1(root,key);
	}

	
	
	public static void main(String[] args){
		AVLTree tree = new AVLTree();
		String a = "5 3 6 4 8 2 7 1 9";
		tree.createTree(a);
		System.out.println("NLR order: ");
		tree.printnlr();
		tree.delete1(4);
		System.out.println("\nNLR order after delete 4: \n");
		tree.printnlr();
		tree.delete1(6);
		System.out.println("\nNLR order after delete 6: \n");
		tree.printnlr();
		
	}		
}