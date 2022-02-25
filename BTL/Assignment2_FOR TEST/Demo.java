import java.io.File;
public class Demo {
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

