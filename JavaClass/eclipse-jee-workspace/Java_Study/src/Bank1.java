
public class Bank1 {
	
	// --- main() start ---
	public static void main(String[] args) {
		Bank0 my_account = new Bank0();
		
		my_account.deposit(1000);
		my_account.checkBalance();
		
		my_account.withdraw(500);
		my_account.checkBalance();

	} // --- main() end ---

}

// --- Bank0 class start ---
class Bank0{
	int balance = 0;
	
	public int deposit(int money) {
		balance += money;
		return balance;
	}
	
	public int withdraw(int money) {
		balance -= money;
		return balance;
	}
	
	public void checkBalance() {
		System.out.println(balance);
	}
} // --- Bank0 class end ---
