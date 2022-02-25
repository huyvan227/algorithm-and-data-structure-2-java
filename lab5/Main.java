import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Main{
	public static void main(String args[]) throws IOException{
		boolean isDirected = false;
		AdjacencyMapGraph matrix = new AdjacencyMapGraph(isDirected);
		String in = "input.txt";
		String[][] adjmatrix = matrix.createMatrix(in);
		
		System.out.println(matrix.numVertices());
	}
}