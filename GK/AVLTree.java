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



	public int height(Node x) {
		if (x == null) return 0;
		return x.height = 1 + Math.max(height(x.left), height(x.right));
	}

	public int height() {
		if (root == null) return 0;
		return root.height;
	}
	
	
	
	public void max(Node x) {
		Node max = x;
		while(max.right != null) {
			max = max.right;
		}
		System.out.printf("\nmax value: %d",max.key);
	}
	
	
	public void findMax(){
		max(root);
	}
	
	
	public static void main(String[] args){
		AVLTree tree = new AVLTree();
		String a = "5 3 7 44 12 87 ";
		tree.createTree(a);
		System.out.println("LNR order: ");
		tree.printlnr();
		tree.findMax();
		
	}		
}