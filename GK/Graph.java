import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Graph{
	public static void main(String[] args) throws IOException{
		String[] filename = {"input1.txt","input2.txt","input3.txt","input4.txt"};	
		for(int i = 0; i< filename.length;i++){
			Scanner myReader = new Scanner(new File (filename[i]));
			int N = myReader.nextInt();
			Kruskal adjmatrix1 = new Kruskal(N);
			Prim adjmatrix2 = new Prim(N);
			adjmatrix1.createMatrix(filename[i]);
			adjmatrix2.create(filename[i]);
			myReader.close();
			System.out.println("\nGraph "+(i+1));
			adjmatrix1.printMatrix();
			System.out.print("\nPrim method\n");
			adjmatrix2.prim();
			System.out.print("\nKruskal method\n");
			adjmatrix1.kruskalMST();
		}
	}
	
}