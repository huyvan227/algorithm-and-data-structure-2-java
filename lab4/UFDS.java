import java.util.*;
public class UFDS{
	private Vector<Integer> id, parent, rank;
	
	public UFDS(int n){
		makeSet(n);
	}
	
	public void makeSet(int n){
		id = new Vector<Integer>(n);
		parent = new Vector<Integer>(n);
		rank = new Vector<Integer>(n);
		for (int i = 0; i < n; i++){
			id.add(i);
			parent.add(i);
			rank.add(0);
		}
	}
	
	public int findSet(int i){
		if (parent.get(i) == i){
			return i;
		}
		else{
			int ret = findSet(parent.get(i));
			parent.set(i,ret);
			return ret;
		}
	}
	
	public void unionSet(int x,int y){
		int id_x = findSet(x);
		int id_y = findSet(y);
		if (id_x != id_y){
			if(rank.get(id_x) > rank.get(id_y)){
				parent.set(id_y,id_x);
			}
			else{
				parent.set(id_x, id_y);
				if (rank.get(id_x) == rank.get(id_y)){
					rank.set(id_y, rank.get(id_y) + 1);
				}
			}		
		}
	}
	public void print(){
		System.out.print("i   : ");
		System.out.println(id);
		System.out.print("P[i]: ");
		System.out.println(parent);
		System.out.print("r[i]: ");
		System.out.println(rank);
		System.out.println(parent.size());
		for (int i = 0; i<parent.size(); i ++){
			System.out.println(parent.get(i));
		}
	}
	public Boolean isSameSet(int x,int y){
		int id_x = findSet(x);
		int id_y = findSet(y);
		if (id_x == id_y){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int countSet(){
		int count = 1;
		for (int i = 1; i < parent.size();i++){
			int j = 0;
			for (j = 0; j<i;j++)
				if(parent.get(i) == parent.get(j))
					break;
			if(i == j)
				count++;
		}
		return count;
	}
	
	
	public static void main(String[] args){
		UFDS arr = new UFDS(10);
		arr.unionSet(2,9);
		arr.unionSet(4,9);
		arr.unionSet(3,4);
		arr.unionSet(5,6);
		arr.print();
		System.out.println("is 6 and 9 same set");
		Boolean val = arr.isSameSet(6,9);
		System.out.println(val);
		System.out.println("is 2 and 9 same set");
		val = arr.isSameSet(2,9);
		System.out.println(val);
		System.out.println("number of distinct set: ");
		System.out.println(arr.countSet());
	}
	
}