import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Prim{
	static int INF = Integer.MAX_VALUE;
	private String matrix[][];
	private int dimension;
	private boolean visited[];
	public Prim(int n){
		matrix = new String[n][n];
		this.dimension = n;
	}
	
	public void create(String input) throws IOException {
		Scanner myReader = new Scanner(new File (input));
		myReader.nextLine();
		for (int i = 0; i< dimension ;i++){
			String[] lines = myReader.nextLine().split(" ");
			for (int j = 0; j<lines.length;j++){
				matrix[i][j] = lines[j];
			}
		}
	}

	public void print(){
		for (int i = 0; i< dimension;i++){
			for (int j = 0; j<=dimension;j++){
				if (j == dimension){
					System.out.print( " \n");
				}
				else{
					System.out.print(matrix[i][j]+ " ");
				}
			}
		}
	}
	
    
	public int minKey(int key[],boolean mstSet[]){
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < dimension;v++){
			if(mstSet[v] == false && key[v] < min){
				min = key[v];
				min_index = v;
			}
		
		}
		return min_index;
	}
	public void printMST(int parent[], String matrix[][]){
		System.out.println("Edge ");
		for(int i = 1; i< dimension;i++){
			System.out.println(parent[i]+" - "+ i + "\t"+ matrix[i][parent[i]]);
		}
		
		
	}
	public void prim(){
		int parent[] = new int[dimension];
		int key[] = new int[dimension];
		boolean mstSet[] = new boolean[dimension];
		for (int i = 0; i<dimension;i++){
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		for(int count = 0; count< dimension - 1;count++){
			int u = minKey(key,mstSet);
			mstSet[u] = true;
			for (int v = 0; v < dimension;v++){
				if(!matrix[u][v].equals("0") && mstSet[v] == false && Integer.parseInt(matrix[u][v]) < key[v]){
					parent[v] = u;
					key[v] = Integer.parseInt(matrix[u][v]);
				}
			}
		}
		printMST(parent,matrix);
	}
	public static void main(String[] args) throws IOException{
		Scanner myReader = new Scanner(new File ("input.txt"));
		int N = myReader.nextInt();
		Prim adjmatrix = new Prim(N);
		adjmatrix.create("input.txt");
		myReader.close();
		adjmatrix.print();
		adjmatrix.prim();
		
	}
	
}