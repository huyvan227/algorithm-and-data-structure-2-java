import java.util.*;
import java.lang.reflect.Array;
import java.io.*;
public class UFDS_Array{
	private int id[], parent[], rank[];
	
	public UFDS_Array(int n){
		makeSet(n);
	}
	
	public void makeSet(int n){
		id = new int[n];
		parent = new int[n];
		rank = new int[n];
		for(int i = 0; i<n;i++){
			id[i] = i;
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int findSet(int i){
		if (parent[i] == i){
			return i;
		}
		else{
			int ret = findSet(parent[i]);
			Array.set(parent,i,ret);
			return ret;
		}
	}
	
	
	public void unionSet(int x,int y){
		int id_x = findSet(x);
		int id_y = findSet(y);
		if (id_x != id_y){
			if(rank[id_x] > rank[id_y]){
				Array.set(parent,id_y,id_x);
			}
			else{
				Array.set(parent,id_x, id_y);
				if (rank[id_x] == rank[id_y]){
					Array.set(rank,id_y, rank[id_y]+1);
				}
			}		
		}
	}
	
	public void print(){
		System.out.print("i   : ");
		for(int i = 0; i<id.length;i++){
			System.out.print(id[i] + " ");
		}
		System.out.print("\nP[i]: ");
		for(int i = 0; i<id.length;i++){
			System.out.print(parent[i] + " ");
		}
		System.out.print("\nr[i]: ");
		for(int i = 0; i<id.length;i++){
			System.out.print(rank[i]+ " ");
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
		for (int i = 1; i < parent.length;i++){
			int j = 0;
			for (j = 0; j<i;j++)
				if(parent[i] == parent[j])
					break;
			if(i == j)
				count++;
		}
		return count;
	}
	
	
	public static void main(String[] args){
		UFDS_Array arr = new UFDS_Array(10);
		
		arr.unionSet(2,9);
		arr.unionSet(4,9);
		arr.unionSet(3,4);
		arr.unionSet(5,6);
		
		arr.print();
		System.out.println(arr.findSet(9));
		
		
		
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