
public class Accumulator {
	static int sum;
	
	static void add(int num) {
		sum += num;
	}
	
	static void showResult() {
		System.out.println(sum);
	}
	
	/// main() start
	public static void main(String[] args) {
		
		for(int i = 1; i <= 10; i++) {
			add(i);
		}
		showResult();
		
	} /// main() end

}
