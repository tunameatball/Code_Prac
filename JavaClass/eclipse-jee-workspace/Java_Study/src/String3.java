
public class String3 {
	
	/// main() start
	public static void main(String[] args) {
		String s1 = "990101-1234567";
		String s2 = s1.replace("-", " ");
		System.out.println(s2.toString());
		Human h = new Human();
		h.setAge(30);
		System.out.println(h.toString());
		
	} /// main() end

}

/// Human class start
class Human{
	private int age;
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "My age is " + age;
	}
	
} /// Human class end

