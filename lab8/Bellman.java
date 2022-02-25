import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Bellman{
	private int INF = 999;
	private int distances[];
	private int dimension;
	private String matrix[][];
	
	
	public Bellman(int n){
		matrix = new String[n][n];
		this.dimension = n;
		distances = new int[n + 1];
		
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
					
					System.out.print(matrix[i][j]+ " ");
					
					
				}
			}
		}
	}
	public void BellmanFord(int source){
		for (int i = 0; i<dimension;i++){
			distances[i] = INF;
		}
		distances[source] = 0;
		for (int node = 0; node < dimension - 1; node++){
			for(int sourcenode = 0; sourcenode < dimension;sourcenode++){
				for(int destinationnode = 0; destinationnode < dimension; destinationnode++){
					if(!matrix[sourcenode][destinationnode].equals("0")){
						  
						if(distances[destinationnode] > distances[sourcenode] + Integer.parseInt(matrix[sourcenode][destinationnode])){
							distances[destinationnode] = distances[sourcenode] + Integer.parseInt(matrix[sourcenode][destinationnode]) ;
						}
					}
				}
			}
		}
		for(int sourcenode = 0; sourcenode < dimension;sourcenode++){
			for(int destinationnode = 0; destinationnode < dimension; destinationnode++){
				if(!matrix[sourcenode][destinationnode].equals("0")){
					if(distances[destinationnode] > distances[sourcenode] + Integer.parseInt(matrix[sourcenode][destinationnode])){
						System.out.println("the graph contain negative edge cycle") ;
					}
				}
			}
		}
		for(int vertex = 0; vertex < dimension;vertex++){
			System.out.println("distance of source " + source + " to " + vertex + " is " + distances[vertex]);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		Scanner myReader = new Scanner(new File ("input1.txt"));
		int N = myReader.nextInt();
		Bellman adjmatrix = new Bellman(N);
		adjmatrix.createMatrix("input1.txt");
		myReader.close();
		adjmatrix.printMatrix();
		adjmatrix.BellmanFord(0);
		
	}
	
}