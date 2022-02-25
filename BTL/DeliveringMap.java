//package vn.edu.tdt.it.dsa;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.*;

public class DeliveringMap {
	private String undirectedmatrix[][],directedmatrix[][];
	private String start, end, pitstop1,pitstop2;
	private String indexArr[];
	private int INF = 9999,size;
	private int parent[];
	private boolean found000 = false,found99 = false;
	private boolean[] color;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private ArrayList<Integer> pathlist = new ArrayList<Integer>();
	
	public String[] CreateIndexArr(String [] sourceArr, String[] destinationArr){
		int indexArrLength = sourceArr.length + destinationArr.length;
		int count = 0;
		String[] tempsourceArr = new String[indexArrLength];
		for(int i = 0;i<sourceArr.length;i++){
			tempsourceArr[i] = sourceArr[i];
			count++;
		}
		for(int j = 0;j<destinationArr.length;j++){
			tempsourceArr[count++] = destinationArr[j];
		}
		for(int m = 0; m < indexArrLength; m++){
			
			for(int n = m+1; n < indexArrLength; n++){
				if( tempsourceArr[m].equals(tempsourceArr[n])){
					tempsourceArr[n] = tempsourceArr[indexArrLength-1];
					indexArrLength--;
					n--;
				}
			}
		}
		String[] indexArr = Arrays.copyOf(tempsourceArr,indexArrLength);
		return indexArr;
	}
	public int findIndex(String temp, String[] array){
		for (int i = 0; i<array.length; i++){
			if(array[i].equals(temp)){
				return i;
			}
		}
		return -1;
	}
	public void convertgraph(String matrix[][]){
		for(int i = 0; i <matrix.length; i++){
			for(int j = 0; j<matrix.length;j++){
				if(matrix[i][j] == null){
					matrix[i][j] = "9999";
				}
			} 
			
		}
	}
	
	public void addUndirectedEgde(int source, int destination,String weight){
		undirectedmatrix[source][destination] = weight;
		undirectedmatrix[destination][source] = weight;
	}
	public void addDirectedEgde(int source, int destination,String weight){
		directedmatrix[source][destination] = weight;
		
	}
	public void printgraph(){
		for(int i = 0; i < undirectedmatrix.length; i++){
			for(int j = 0; j<undirectedmatrix.length;j++){
				System.out.print(undirectedmatrix[i][j]+ " ");
			} 
			System.out.print( " \n");
		}
		System.out.print( " \n");		
		for(int i = 0; i <directedmatrix.length; i++){
			
			for(int j = 0; j<directedmatrix.length;j++){
				System.out.print(directedmatrix[i][j]+ " ");
			} 
			System.out.print( " \n");
		}	
	}
	
	public int SumEdgeUndirected(){
		int sum = 0, edge = 0;
		for(int i = 0; i <undirectedmatrix.length; i++){
			for(int j = 0; j<i;j++){
				if(undirectedmatrix[i][j] != null){
					edge += 1;
					sum = Integer.parseInt(undirectedmatrix[i][j]) + sum;
				}
			} 
		}
		return sum + edge;
	}
	public int SumEdgeDirected(){
		int sum = 0, edge = 0;
		for(int i = 0; i <directedmatrix.length; i++){
			for(int j = 0; j<directedmatrix.length;j++){
				if(directedmatrix[i][j] !=  null ){
					edge += 1;
					sum = Integer.parseInt(directedmatrix[i][j]) + sum;
				}
			} 
		}
		return sum + edge;
	}
	
	public int minDistance(int distances[],Boolean visited[],String matrix[][]){
		int min = INF, min_index = -1;
		for(int v =0; v< matrix.length; v++){
			if(visited[v] == false && distances[v] <= min){
				min = distances[v];
				min_index = v;
			}
		}
		return min_index;
	}
	public int Dijkstra(int src,int dest,String matrix[][]){
		int distances[] = new int[matrix.length];
		Boolean visited[] = new Boolean[matrix.length];
		for (int i = 0 ;i < matrix.length; i++){
			distances[i] = INF;
			visited[i] = false;
		}
		
		distances[src] = 0;
		for(int count = 0; count < matrix.length - 1;count++){
			int u = minDistance(distances,visited,matrix);
			visited[u] = true;
			for(int v = 0; v<matrix.length;v++){
				if(!visited[v] && matrix[u][v] != null && distances[u] != INF && distances[u]+ Integer.parseInt(matrix[u][v]) < distances[v]){
						distances[v] = distances[u] + Integer.parseInt(matrix[u][v]);
						
				}
			}
		}
			
		
		
		return distances[dest];
		
	}
	public void dfs(int src, int dest, String matrix[][]){
		list.add(src);
		size++;
		color[src] = true;
		if(src == dest){
			
			for(Integer node : list){
				pathlist.add(node);
			}
			
            return;
		}
		for (int i = 1; i < matrix.length; i++){
			if(matrix[src][i] != null){
				if(color[i] == false){
					dfs(i,dest,matrix);
					color[i] = false;
					size--;
					list.remove(size);
				}
			}
		}
	}
	public int sumtotalpath(int src,int dest,String matrix[][],int level){
		dfs(src,dest,matrix);
		pathlist.add(src);
		Integer[] temp = new Integer[pathlist.size()];
		temp = pathlist.toArray(temp);	
		int sum = 0,count = 0,count000 = 0,count99 = 0,result = 0;
		int j = 0,k = 0,l = 0;
		boolean temp1 = false,temp2 = false, foundblock = false;
		int start = temp[0];
		for(Integer i : pathlist){
			
			if (i == start){
				count +=1;
			}	
			
		}
		for(int a = 0;a<temp.length-1;a++){
			if(temp[a+1] == start ){
				continue;
			}	
			if(matrix[temp[a]][temp[a+1]].equals("000")){
				temp1 = true;
				count000 += 1;
			}
			if(Integer.parseInt(matrix[temp[a]][temp[a+1]]) == 99){
				temp2 = true;
				count99 += 1;
			}
		}
		int[] path000 = new int[count000];
		int[] path99 = new int[count99];
		int[] sumofpath = new int[count-1];
		for(int i = 0;i<temp.length-1;i++){
			if(temp[i+1] == start ){
				sumofpath[j] = sum;
				sum = 0;
				j= j +1;
				continue;
			}	
			if(Integer.parseInt(matrix[temp[i]][temp[i+1]]) == 99){
				path99[l] = j;
				l += 1;
			}
			if(matrix[temp[i]][temp[i+1]].equals("000")){
				path000[k] = j;
				k += 1;
			}
			sum = sum + Integer.parseInt(matrix[temp[i]][temp[i+1]]);
		}
		
		
		if(level == 2 || level == 3 || level == 4){
			int min = sumofpath[0];
			for(int m = 0;m<sumofpath.length;m++){
				if(sumofpath[m] < min){
					min = sumofpath[m];
				}
			}
			if(temp1 == true){
				int min000 = sumofpath[path000[0]];
				for(int u = 0; u < path000.length;u++){
					if(sumofpath[path000[u]] < min000){
						min000 = sumofpath[path000[u]];
						
					}
				}
				result = min000;
			}
			else if(temp2 == true){
				for(int n = 0;n < path99.length;n++){
					if( min == sumofpath[path99[n]]){
						foundblock = true;
					}
				}
				if (foundblock == true){
					result = -99999;
				}
				else{
					result = min;
				}
			}
			else{
				result = min;
			}	
		}
		if(level == 5 || level == 6){
			int max = sumofpath[0];
			for(int m = 0;m<sumofpath.length;m++){
				if(sumofpath[m] > max){
					max = sumofpath[m];
				}
			}
			if(temp1 == true){
				int max000 = sumofpath[path000[0]];
				for(int u = 0; u < path000.length;u++){
					if(sumofpath[path000[u]] > max000){
						max000 = sumofpath[path000[u]];
						
					}
				}
				result = max000;
			}
			else if(temp2 == true){
				for(int n = 0;n < path99.length;n++){
					if( max == sumofpath[path99[n]]){
						foundblock = true;
					}
				}
				if (foundblock == true){
					result = -99999;
				}
				else{
					result = max;
				}
			}
			else{
				result = max;
			}
		}
		if(level == 7){
			int total = sumofpath[0];
			for(int m = 0;m<sumofpath.length;m++){
				if(sumofpath[m] > total){
					total = sumofpath[m];
				}
			}
			result = total;
		}
		return result;
	}
	
	public int countpath(int src,int dest, int k){
		if( k == 0 && src == dest){
			return 1;
		}
		if( k == 0 && directedmatrix[src][dest] != null){
			return 1;
		}
		if( k <= 0){
			return 0;
		}
		int count = 0;
		for(int i = 0; i < directedmatrix.length; i ++){
			if (directedmatrix[src][i] != null){
				count += countpath(i,dest,k-1);
			}
		}
		return count;
	}
	public boolean checkpath(){
		int src = findIndex(pitstop1,indexArr);
		int dest = findIndex(end,indexArr);
		int k = 1;
		int temp = countpath(src,dest,k) ;
		if (temp == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public int minKey(int key[],boolean mstSet[],String matrix[][]){
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < matrix.length;v++){
			if(mstSet[v] == false && key[v] < min){
				min = key[v];
				min_index = v;
			}
		
		}
		return min_index;
	}
	public int prim(String matrix[][]){
		int parent[] = new int[matrix.length];
		int key[] = new int[matrix.length];
		int sum = 0;
		boolean mstSet[] = new boolean[matrix.length];
		for (int i = 0; i<matrix.length;i++){
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		key[0] = 0;
		parent[0] = -1;
		for(int count = 0; count< matrix.length - 1;count++){
			int u = minKey(key,mstSet,matrix);
			mstSet[u] = true;
			for (int v = 0; v < matrix.length;v++){
				if(!matrix[u][v].equals("9999") && mstSet[v] == false && Integer.parseInt(matrix[u][v]) < key[v]){
					parent[v] = u;
					key[v] = Integer.parseInt(matrix[u][v]);
				}
			}
		}
		for(int i = 1; i< matrix.length;i++){
			sum = sum + Integer.parseInt(matrix[i][parent[i]]);
		}
		return sum;
	}
	public DeliveringMap(File file) throws IOException{
		Scanner myReader = new Scanner(file);
		int count = 0;
		while(myReader.hasNext()){
			String[] lines = myReader.nextLine().split(" ");
			count = count + lines.length;
		}
		myReader.close();
		
		Scanner sc = new Scanner(file);
		String[] array = new String[count];
		int i = 0;
		while(sc.hasNext()){
			String[] lines = sc.nextLine().split(" ");
			
			for(int j = 0; j<lines.length;j++){
				array[i] = lines[j];
				i++;
			}
		}
		
				
		sc.close();
		String[] sourceArr = new String[array.length];
		String[] destinationArr = new String[array.length];
		String[] weightArr = new String[array.length];
		
		for(i = 0; i < array.length; i++){
			sourceArr[i] = array[i].substring(0,2);
			destinationArr[i] = array[i].substring(5,7);
			weightArr[i] = array[i].substring(2,5);
			
		}
		start = sourceArr[0];
		end = destinationArr[array.length - 1];
		
		indexArr = CreateIndexArr(sourceArr,destinationArr);
		undirectedmatrix = new String[indexArr.length][indexArr.length];
		directedmatrix = new String[indexArr.length][indexArr.length];
		
		int source, destination;
		for( int k = 0; k< array.length; k++){
			source = findIndex(sourceArr[k],indexArr);
			destination = findIndex(destinationArr[k],indexArr);
			if (weightArr[k].equals("000")){
				pitstop1 = destinationArr[k];
				found000 = true;
			}
			if (weightArr[k].equals("099")){
				pitstop2 = destinationArr[k];
				found99 = true;
			}
			addUndirectedEgde(source,destination,weightArr[k]);
			addDirectedEgde(source,destination,weightArr[k]);
		}
		color = new boolean[array.length];
		Arrays.fill(color, false);
		
		
	}
	
	public int calculate(int level, boolean rushHour){
		int res = 0;
		if (rushHour == false){
			if (level == 0 || level == 1){
				res = level + SumEdgeUndirected();
				
			}
			if (level == 2 || level == 3 || level == 4){
				if(found000 == true && checkpath() == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,undirectedmatrix,level);
					if (weight > 100*level){
						res = 50*level - weight;
					}
					else{
						res = weight;
					}
		
				}
				else if(found99 == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,undirectedmatrix,level);
					if (weight == -99999){
						res = 99 - 70*level;
					}
					else{
						res = weight;
					}
				}
				else{
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = Dijkstra(src,dest,undirectedmatrix);
					if (weight > 100*level){
							res = 50*level - weight;
					}
					else{
						res = weight;
					}
				}
			}
			
			if (level == 5 || level == 6){
				if(found000 == true && checkpath() == true){
					
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,undirectedmatrix,level);
					if (weight >= 100*level){
						res = -1*level;
					}
					else{
						res = weight;
					}
					
		
				}
				else if(found99 == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,undirectedmatrix,level);
					if (weight == -99999){
						res = 99 - 70*level;
					}
					else{
						res = weight;
					}
				}
				else{
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,undirectedmatrix,level);
					if (weight >= 100*level){
							res = -1*level;
					}
					else{
						res = weight;
					}
				}
			}
			if (level == 7){
				int src = findIndex(start,indexArr);
				int dest = findIndex(end,indexArr);
				int longest = sumtotalpath(src,dest,undirectedmatrix,level);
				int shortest = Dijkstra(src,dest,undirectedmatrix);
				int sum = longest + shortest;
				double average = (double)(sum/2);
				int temp = (int)average;
				if(temp < 30*level){
					res = temp;
				}
				else{
					res = -21;
				}
				
			}
			
			if (level == 9){
				convertgraph(undirectedmatrix);
				res = prim(undirectedmatrix);

			}
			
		}
		else{
			if (level == 0 || level == 1){
				res = level + SumEdgeDirected();
			}
			
			if (level == 2 || level == 3 || level == 4){
				if(found000 == true && checkpath() == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int stop = findIndex(pitstop1,indexArr);
					int weight = Dijkstra(stop,dest,directedmatrix);
					if (weight > 100*level){
						res = 50*level - weight;
					}
					else{
						res = weight;
					}
		
				}
				else if(found99 == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					res = sumtotalpath(src,dest,directedmatrix,level);
				}
				else{
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = Dijkstra(src,dest,directedmatrix);
					if (weight > 100*level){
							res = 50*level - weight;
					}
					else{
						res = weight;
					}
				}
			}
			
			if (level == 5 || level == 6){
				if(found000 == true && checkpath() == true){
					
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int stop = findIndex(pitstop1,indexArr);
					res = sumtotalpath(stop,dest,directedmatrix,level);
					
		
				}
				else if(found99 == true){
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					res = sumtotalpath(src,dest,directedmatrix,level);
				}
				else{
					int src = findIndex(start,indexArr);
					int dest = findIndex(end,indexArr);
					int weight = sumtotalpath(src,dest,directedmatrix,level);
					if (weight >= 100*level){
							res = -1*level;
					}
					else{
						res = weight;
					}
				}
			}
			if (level == 7){
				int src = findIndex(start,indexArr);
				int dest = findIndex(end,indexArr);
				int longest = sumtotalpath(src,dest,directedmatrix,level);
				int shortest = Dijkstra(src,dest,directedmatrix);
				int sum = longest + shortest;
				double average = (double)(sum/2);
				int temp = (int)average;
				if(temp < 30*level){
					res = temp;
				}
				else{
					res = -21;
				}
				
			}
			
			
			if (level == 9){
				res = -1;
			}
			
		}
		 
		return res;
	}
	
	public static void main(String[] args) {
		try {
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
			// C1 level nhan vien la 0 hoac 1
			System.out.println("MAP: " + map.calculate(1, true));
			
			// C2 level nhan vien la 2 hoac 3 hoac 4
			map = new DeliveringMap(new File("map1.txt"));
			System.out.println("MAP1: " + map.calculate(3, false));

			map = new DeliveringMap(new File("map2.txt"));
			System.out.println("MAP2: " + map.calculate(3, false));
			
			// C3 level nhan vien la 5 hoac 6
			map = new DeliveringMap(new File("map3.txt"));
			System.out.println("MAP3: " + map.calculate(6, false));

			map = new DeliveringMap(new File("map4.txt"));
			System.out.println("MAP4: " + map.calculate(5, false));
			
			// C4 ma doan duong di co dang XX000YY		
			map = new DeliveringMap(new File("map5.txt"));
			System.out.println("MAP5: " + map.calculate(3, false));
			
			map = new DeliveringMap(new File("map6.txt"));
			System.out.println("MAP6: " + map.calculate(5, false));
			
			// C5 trong so cua doan duong la 99
			map = new DeliveringMap(new File("map7.txt"));
			System.out.println("MAP7: " + map.calculate(3, false));

			map = new DeliveringMap(new File("map8.txt"));
			System.out.println("MAP8: " + map.calculate(3, false));
			
			// C6 level nhan vien la 7
			map = new DeliveringMap(new File("map9.txt"));
			System.out.println("MAP9: " + map.calculate(7, false));

			map = new DeliveringMap(new File("map10.txt"));
			System.out.println("MAP10: " + map.calculate(7, false));

			// C7 level nhan vien la 9
			map = new DeliveringMap(new File("map11.txt"));
			System.out.println("MAP11: " + map.calculate(9, false));
		
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
}
