import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Graph{
	private String matrix[][];
	private int dimension;
	private boolean visited[];
	public Graph(int n){
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
	public void BFS(int source){
		boolean[] visited = new boolean[dimension];
		visited[source-1] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		System.out.println("BFS order: ");
		while(!queue.isEmpty()){
			System.out.print(queue.peek()+ " ");
			int x = queue.poll();
			int i;
			for(i=0; i<dimension;i++){
				int num = Integer.parseInt(matrix[x-1][i]);
				if(num == 1 && visited[i] == false){
					queue.add(i+1);
					visited[i] = true;
				}
			}
		}
    }
	public void DFS(int source){
		boolean[] visited = new boolean[dimension];
		visited[source] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(source);
		System.out.println("\nDFS order: ");
		System.out.print(source+ " ");
		int x;
		while(!stack.isEmpty()){
			x = stack.pop();
			for(int i=0; i<dimension;i++){
				int num = Integer.parseInt(matrix[x][i]);
				if(num == 1 && visited[i] == false){
					stack.push(x);
					visited[i] = true;
					System.out.print(i+1+ " ");
					x = i + 1;
					i = -1;
				}
			}
		}
    }
	public static void main(String[] args) throws IOException{
		Scanner myReader = new Scanner(new File ("input.txt"));
		int N = myReader.nextInt();
		Graph adjmatrix = new Graph(N);
		adjmatrix.create("input.txt");
		myReader.close();
		adjmatrix.print();
		adjmatrix.BFS(0);
		adjmatrix.DFS(0);
		
	}
	
}