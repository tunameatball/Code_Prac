import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	int[] dstocks = {50, 35, 65, 40}; // 재고
	int[] coins;
	int[] drinkSale = new int[4];
	int[] opCoin = {50, 50, 50, 50, 50, 50, 50}; // 초기 잔고
	String[] coinName = {"m_10000", "m_5000", "m_1000", "m_500", "m_100", "m_50", "m_10"};
	String[] Pname = {"콜라", "환타", "커피", "물"};
	int[] cnm = {};
	int stock = 0, price = 0, dstock = 50;
	
	static int total = 0;
	String name;
	
	
	public void addOrder(Selector order) {
		System.out.println("주문을 DB에 저장 합니다.");
		
		switch(order.getName()) {
		case "콜라":
			name = order.getName();
			stock = dstocks[0];
			price = order.getPrice();
			break;
			
		case "환타":
			name = order.getName();
			stock = dstocks[1];
			price = order.getPrice();
			break;
			
		case "커피":
			name = order.getName();
			stock = dstocks[2];
			price = order.getPrice();
			break;
			
		case "물":
			name = order.getName();
			stock = dstocks[3];
			price = order.getPrice();
			break;
			
		default:
			break;
		}
		
		calCoins(order.getPrice());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
			conn.setAutoCommit(false);
			sql = "insert into drink (name, price, m_10000, m_5000, m_1000, m_500, m_100, m_50, m_10, dates) values(?,?,?,?,?,?,?,?,?,?);";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, toLatin(order.getName()));
			pstmt.setInt(2, order.getPrice());
			pstmt.setInt(3, coins[0]);
			pstmt.setInt(4, coins[1]);
			pstmt.setInt(5, coins[2]);
			pstmt.setInt(6, coins[3]);
			pstmt.setInt(7, coins[4]);
			pstmt.setInt(8, coins[5]);
			pstmt.setInt(9, coins[6]);
			pstmt.setDate(10, getCurrentJavaSqlDate());
			pstmt.executeUpdate();
			
			conn.commit();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
		
		totals();
		System.out.println("주문을 DB에 저장 했습니다.");
	}
	
	private static java.sql.Date getCurrentJavaSqlDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
	public void totals() {
		cnm = new int[7];
		
		for (int i = 0; i < 7; i++) {
			String s = coinName[i];
			cnm[i] = salesSum(s);
			System.out.println(s + "동전 토탈 확인 : " + cnm[i]);
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "", sqla = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
			sqla = "truncate t_sales";
			pstmt = conn.prepareStatement(sqla);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into t_sales (m_10000, m_5000, m_1000, m_500, m_100, m_50, m_10) values(?,?,?,?,?,?,?);";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnm[0]);
			pstmt.setInt(2, cnm[1]);
			pstmt.setInt(3, cnm[2]);
			pstmt.setInt(4, cnm[3]);
			pstmt.setInt(5, cnm[4]);
			pstmt.setInt(6, cnm[5]);
			pstmt.setInt(7, cnm[6]);
			pstmt.executeUpdate();
			
		}  catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	
	public int salesSum(String cn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		String sum = "";
		int value = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
			
			sql = "select sum(" + cn + ") from drink;";
			pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			result.next();
			sum = result.getString(1);
			value = Integer.parseInt(sum);
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
		
		return value;
	}
	
	public void opCoin() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "", sqla = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");

			
			sql = "select * from account;";
			pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			result.next();
			for (int i = 0; i < coinName.length; i++) {
				opCoin[i] = result.getInt(i+1);
			}
			pstmt.close();
			
		}  catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	
	public void account(int money, int change) {
		int[] coinAct = new int[7];
		int a,b,c,d,e,f,g;
		calCoins(money);
		a = opCoin[0] + coins[0];
		b = opCoin[1] + coins[1];
		c = opCoin[2] + coins[2];
		d = opCoin[3] + coins[3];
		e = opCoin[4] + coins[4];
		f = opCoin[5] + coins[5];
		g = opCoin[6] + coins[6];
		
		calCoins(change);
		coinAct[0] = a - coins[0];
		coinAct[1] = b - coins[1];
		coinAct[2] = c - coins[2];
		coinAct[3] = d - coins[3];
		coinAct[4] = e - coins[4];
		coinAct[5] = f - coins[5];
		coinAct[6] = g - coins[6];
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "", sqla = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
			sqla = "truncate account";
			pstmt = conn.prepareStatement(sqla);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into account (m_10000, m_5000, m_1000, m_500, m_100, m_50, m_10) values(?,?,?,?,?,?,?);";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coinAct[0]);
			pstmt.setInt(2, coinAct[1]);
			pstmt.setInt(3, coinAct[2]);
			pstmt.setInt(4, coinAct[3]);
			pstmt.setInt(5, coinAct[4]);
			pstmt.setInt(6, coinAct[5]);
			pstmt.setInt(7, coinAct[6]);
			pstmt.executeUpdate();
			
		}  catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	
	public boolean checkAct(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int value = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
//			for (int i = 0; i < 4; i++) {
//				conn.setAutoCommit(false);
//				sql = "select count(*) from drink where name = " + Pname[i];
//				
//				pstmt = conn.prepareStatement(sql);
//				conn.commit();
//				ResultSet result = pstmt.executeQuery();
//				result.next();
//				String sum = result.getString(1);
//				value = Integer.parseInt(sum);
//				System.out.println(Pname[i] + "판매개수 : " + value);
//				
//				switch (Pname[i]) {
//				case "콜라":
//					drinkSale[0] += value;
//					break;
//					
//				case "환타":
//					drinkSale[1] += value;
//					break;
//					
//				case "커피":
//					drinkSale[2] += value;
//					break;
//					
//				case "물":
//					drinkSale[3] += value;
//					break;
//
//				default:
//					break;
//				}
//				
//				if (value > 50) {
//					conn.rollback();
//					System.out.println("재고가 부족합니다. 관리자에게 문의 하세요.");
//					return false;
//				}
//				
//				pstmt.close();
//			}
			
			
			conn.setAutoCommit(false);
			sql = "select count(*) from drink where name = '" + name + "';";
			
			pstmt = conn.prepareStatement(sql);
			conn.commit();
			ResultSet result = pstmt.executeQuery();
			result.next();
			String sum = result.getString(1);
			value = Integer.parseInt(sum);
			System.out.println(name + "판매개수 : " + value);
			
			switch (name) {
			case "콜라":
				drinkSale[0] += value;
				break;
				
			case "환타":
				drinkSale[1] += value;
				break;
				
			case "커피":
				drinkSale[2] += value;
				break;
				
			case "물":
				drinkSale[3] += value;
				break;

			default:
				break;
			}
			
			if (value > 50) {
				conn.rollback();
				System.out.println("재고가 부족합니다. 관리자에게 문의 하세요.");
				return false;
			}
			
			pstmt.close();
		
		} catch(Exception e) {
			e.printStackTrace();
			return true;
		}
		
		drinkSales();
		
		try {
			for (int i = 0; i < 7; i++) {
				conn.setAutoCommit(false);
				sql = "select " + coinName[i]  + " from account;";
				
				PreparedStatement pst = conn.prepareStatement(sql);
				conn.commit();
				
				ResultSet result = pst.executeQuery();
				result.next();
				String sum = result.getString(1);
				value = Integer.parseInt(sum);
				System.out.println(coinName[i] + "재고 동전 : " + value);
				
				if (value == 0 ) {
					conn.rollback();
					System.out.println(coinName[i] + "의 잔고 부족. 관리자에게 문의 하세요.");
					return false;
				}
				pst.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			try {
				conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		return true;
	}
	
	
	void drinkSales() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending", "root", "apmsetup");
			sql = "truncate drinksale";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into drinksale (COKE, FANTA, COFFEE, WATER) values(?, ?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, drinkSale[0]);
			pstmt.setInt(2, drinkSale[1]);
			pstmt.setInt(3, drinkSale[2]);
			pstmt.setInt(4, drinkSale[3]);
			pstmt.executeUpdate();
			
			
		} catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(Exception ignored) {
				
			}
			
			try {
				conn.close();
			} catch(Exception ignored) {
				
			}
		}
	}
	
	void calCoins(int amt) {
		coins = new int[7];
		int tmp;
		coins[0] = amt / 10000;
		tmp = amt % 10000;
		
		coins[1] = tmp / 5000;
		tmp = tmp % 5000;
		
		coins[2] = tmp / 1000;
		tmp = tmp % 1000;
		
		coins[3] = tmp / 500;
		tmp = tmp % 500;
		
		coins[4] = tmp / 100;
		tmp = tmp % 100;
		
		coins[5] = tmp / 50;
		tmp = tmp % 50;
		
		coins[6] = tmp / 10;
		tmp = tmp % 10;
		
	}
	
	public String toLatin(String str) {
		try {
			byte[] b = str.getBytes();
			return new String(b,"ISO-8859-1");
		} catch(java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	public String toUnicode(String str) {
		try {
			byte[] b = str.getBytes("ISO-8859-1");
			return new String(b);
		} catch(java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
