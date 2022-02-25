import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Dijkstra{
	private int INF = 999;
	private int distances[], dimension;
	private String matrix[][];
	
	
	public Dijkstra(int n){
		matrix = new String[n][n];
		this.dimension = n;
		distances = new int[n];
		
		
	}
	public void createMatrix(String input) throws IOException {
		Scanner myReader = new Scanner(new File (input));
		myReader.nextLine();
		for (int i = 0; i< dimension ;i++){
			String[] lines = myReader.nextLine().split(" ");
			for (int j = 0; j<lines.length;j++){
					matrix[i][j] =lines[j];
			
				
				
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
	public int minDistance(Boolean sptSet[]){
		int min = INF, min_index = -1;
		for(int v =0; v< dimension; v++){
			if(sptSet[v] == false && distances[v] <= min){
				min = distances[v];
				min_index = v;
			}
		}
		return min_index;
	}
	public void prinSoluion(int n){
		for(int i = 0; i<dimension; i++){
			System.out.println(i + " - " +distances[i]);
		}
	} 
	public void Dijkstrapath(int src){
		Boolean sptSet[] = new Boolean[dimension];
		for (int i = 0 ;i < dimension; i++){
			distances[i] = INF;
			sptSet[i] = false;
		}
		distances[src] = 0;
		for(int count = 0; count < dimension - 1;count++){
			int u = minDistance(sptSet);
			sptSet[u] = true;
			for(int v = 0; v<dimension;v++){
				if(!sptSet[v] && Integer.parseInt(matrix[u][v]) != 0 && distances[u] != INF && distances[u] + Integer.parseInt(matrix[u][v]) < distances[v]){
					distances[v] = distances[u] + matrix[u][v];
				}
			}
			
		}
		prinSoluion(dimension);
	}
	
	public static void main(String[] args) throws IOException{
		Scanner myReader = new Scanner(new File ("input2.txt"));
		int N = myReader.nextInt();
		Dijkstra adjmatrix = new Dijkstra(N);
		adjmatrix.createMatrix("input2.txt");
		myReader.close();
		adjmatrix.printMatrix();
		adjmatrix.Dijkstrapath(0);
		
	}
	
}