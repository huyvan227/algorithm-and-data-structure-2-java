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

	public Integer sum(Node x){
		if (x == null) return 0;
		return x.key + sum(x.left) + sum(x.right);
	}
	
	public Integer sum(){
		return sum(root);
	}
	
	private Node deleteMax(Node x) {
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.size = size(x.left) + size(x.right);
		x.height =  Math.max(height(x.left), height(x.right));
		return x;
	}
	public Node max(Node x) {
		Node maxL = x;
		while(x.right != null) {
			maxL = maxL.right;
		}
		return maxL;
	}
	public void deleteMAX(){
		deleteMax(root);
	}
	
	public Node delete1(Node x, Integer key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			x.left = delete1(x.left,key);
		else if (cmp > 0)
			x.right = delete1(x.right,key);
		else {
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
		}
		x.size =  size(x.left) + size(x.right) + 1;
		return x;
	}
	public void delete1(Integer key){
		delete1(root,key);
	}
	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree();
		String a = "8 9 5 20 1 45 88 45 23 50 13";
		tree.createTree(a);
		System.out.print("LNR order: ");
		tree.printlnr();
		System.out.print("\nRNL order: ");
		tree.printrnl();
		System.out.println("find 7: "+tree.contains(7));
		System.out.println("find 88: "+tree.contains(88));
		System.out.println("height: "+tree.height());
		System.out.println("sum: "+tree.sum());
		
		tree.delete1(8);
		System.out.println("LNR order after delete 8: ");
		tree.printlnr();
		tree.deleteMAX();
		System.out.println("\nLNR order after delete max number: ");
		tree.printlnr();
		
	}		
}