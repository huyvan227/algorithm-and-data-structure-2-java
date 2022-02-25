import java.util.ArrayList; 
import java.util.Iterator; 
public class lab9{
	static void lds(int[] arr)
    {   
		int n = arr.length;
		int F[] = new int [n];
		int M[] = new int [n];
		for(int i = 0;i<n;i++){
			F[i]= 1;
			M[i]= -1;
		}


		for(int i = 1;i<n;i++){
			for (int j = 0;j<i;j++){
				if (arr[i] > arr[j] && F[i] < F[i] + 1){
					F[i] = F[j] + 1;
					M[i] =  j ;
				}
			}
		}

		int u = 1,max = 0;
		for(int i = 0;i<n;i++){
			if (max < F[i]){
				max = F[i];
				u = i;
			}
		}
		System.out.print("\nday khong tang\n");
		do{
			System.out.print(arr[u]+" ");
			u = M[u] ;
		}while (u > 0);
	
 
                  
    }
	static void lis(int[] arr){
		int n = arr.length;
		int F[] = new int [n];
		int M[] = new int [n];
		for(int i = 0;i<n;i++){
			F[i]= 1;
			M[i]= -1;
		}


		for(int i = 1;i<n;i++){
			for (int j = 0;j<i;j++){
				if (arr[i] < arr[j] && F[i] < F[i] + 1){
					F[i] = F[j] + 1;
					M[i] = j;
				}
			}
		}
		int u = 1,max = 0;
		for(int i = 0;i<n;i++){
			if (max < F[i]){
				max = F[i];
				u = i;
			}
		}
		System.out.print("\nday khong giam\n");
		do{
			System.out.print(arr[u]+" ");
			u = M[u] ;
		}while (u > 0);
	
	}
	public static void main(String[] args){
		int[] array1 = new int[]{  6, 12, 9, 4,11, -2, 5, 18, 27, 20};
		lds(array1);
		lis(array1);
		
		
	}
}