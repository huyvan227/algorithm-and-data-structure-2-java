import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Kruskal{
	private int INF = Integer.MAX_VALUE;
	private int matrix[][];
	private int dimension;
	private int parent[];
	
	public Kruskal(int n){
		matrix = new int[n][n];
		this.dimension = n;
		parent = new int[n];
	}
	public void createMatrix(String input) throws IOException {
		Scanner myReader = new Scanner(new File (input));
		myReader.nextLine();
		for (int i = 0; i< dimension ;i++){
			String[] lines = myReader.nextLine().split(" ");
			for (int j = 0; j<lines.length;j++){
				matrix[i][j] = Integer.parseInt(lines[j]);
				
			}
		}
	}
	public void printMatrix(){
		for (int i = 0; i< dimension;i++){
			for (int j = 0; j<=dimension;j++){
				if (j == dimension){
					System.out.print( " \n");
				}
				else{
					if(matrix[i][j] == INF){
						System.out.print(0+ " ");
					}
					else{
						System.out.print(matrix[i][j]+ " ");
					}
					
				}
			}
		}
	}
	public int find(int i){
		while(parent[i] != i){
			i = parent[i];
		}
		return i;
	}
    public void union(int i,int j){
		int a = find(i);
		int b = find(j);
		parent[a] = b;
	}
	public void kruskalMST(){
		int mincost = 0;
		for(int i = 0; i< dimension;i++){
			parent[i] = i;
		}
		int edge_count = 0;
		while(edge_count <dimension-1){
			int min = INF, a= -1, b= -1;
			for (int i = 0;i<dimension;i++){
				for (int j = 0;j<dimension;j++){
					if (find(i) != find(j) && matrix[i][j] < min && matrix[i][j]!= 0){
						min = matrix[i][j];
						a =i;
						b = j;
					}
				}
			}
			union(a,b);
			System.out.printf("Edge %d: (%d,%d) cost: %d \n", edge_count++,a,b,min);
			mincost += min;
		}
		System.out.printf("\nMininum cost = %d",mincost);
	}
	
}