
public class Array4 {

	/// main() start
	public static void main(String[] args) {
		int[][] arr1 = new int[][] {{10, 20, 30, 40}, {11, 22, 33, 44}, {1, 2, 3, 4}};
		
		for(int i = 0; i < arr1.length; i++) {
			for( int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr1[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	} /// main() end

}
