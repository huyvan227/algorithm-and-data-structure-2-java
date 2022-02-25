
public class AVL3 {
	private class Node {
		private Integer key;	
		private Node left, right;
		private int size;
		private int height;
		public Node(Integer key, int size, int height){
			this.key = key;
			this.size = size;
			this.height = height;
			left = right = null;
		}
	}
	Node root = null;
	AVL3(){
		root = null;

	}
	public void createTree(String strKey){ 
		String[] input1 = strKey.split(" ");
		for(String i:input1){
			this.puT(Integer.parseInt(i));	
		}		
	}
	private int size(Node x) {
		if (x == null) return 0;
		return size(x.left) + size(x.right) + 1;
	}
	void puT(Integer key){		
		root = put(root,key);
	}
	Node put(Node x, Integer key) {		
		if (x == null)
			return new Node(key,1, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left,key);
			x = balance(x);
		}
		else if (cmp > 0) {		
			x.right = put(x.right, key);
			x = balance(x);
		}
		else {
			x.key = key;
		}
		x.height = 1 + Math.max(height(x.left), height(x.right));
		x.size = 1 + size(x.left) + size(x.right); 
		return x;
		
	}
	private int height(Node x) {
		if (x == null) return 0;
		return x.height = 1 + Math.max(height(x.left), height(x.right));
	}
	void lnR(){
		lnr(root);
	}
	public void lnr(Node x) {
		if (x != null) {
			lnr(x.left);
			System.out.print(x.key + " ");
			lnr(x.right);
		}
	}	
	private int checkBalance(Node x) {
		return height(x.left) - height(x.right);
	}
	private Node rotateLeft(Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	private Node rotateRight(Node x) {
		Node y = x.left;
		x.left = y.right;
		y.right = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	private Node balance(Node x) {
		if (checkBalance(x) < -1) {
			if (checkBalance(x.right) > 0) {
				x.right = rotateRight(x.right);
			}
			x = rotateLeft(x);
		} else if (checkBalance(x) > 1) {
			if (checkBalance(x.left) < 0) {
				x.left = rotateLeft(x.left);
			}
			x = rotateRight(x);
		}	
		return x;
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
	public void deleteMax() {
		deleteMax(root);
	}
	private Node deleteMax(Node x) {
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x = balance(x);
		x.size = size(x.left) + size(x.right) ;
		x.height = Math.max(height(x.left), height(x.right));
		return x;
	}
	private Node min(Node x) {
		Node minR = x;
		while(x.left != null) {
			minR = minR.left;
		}
		return minR;
	}
	private Node max(Node x) {
		Node maxL = x;
		while(x.right != null) {
			maxL = maxL.right;
		}
		return maxL;
	}
	private Node delete(Node x, Integer key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete(x.left,key);
			x = balance(x);
		}
		else if (cmp > 0) {
			x.right = delete(x.right,key);
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
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
			x = balance(x);
		}
		x.height = Math.max(height(x.left), height(x.right));
		x.size =  size(x.left) + size(x.right) + 1;
		return x;
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
	public static void main(String[] args){
		AVL3 tree = new AVL3();
		String a = "23 9 5 12 41 32 50 48 60";
		tree.createTree(a);
		
		tree.nlr(tree.root);
		tree.delete(tree.root,41);
		System.out.print("\n");
		tree.nlr(tree.root);
		
	}		
}
