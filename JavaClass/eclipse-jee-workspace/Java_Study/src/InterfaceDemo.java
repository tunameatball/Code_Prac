
public class InterfaceDemo {
	
	/// main() start
	public static void main(String[] args) {
		MountainBike mb = new MountainBike(20, 10, 1);
		System.out.println("speed = " + mb.getSpeed() + ", seatHeight = " + mb.getSeatHeight()
		+ ", gear = " + mb.getGear());
		
		mb.applyBrake(1);
		System.out.println(mb.getSpeed());
		
		mb.speedup(10);
		System.out.println(mb.getSpeed());
	} /// main() end

}

/// Bicycle interface start
interface Bicycle{

	public void applyBrake(int decreament);

	public void speedup(int increament);
} /// Bicycle interface end

/// MountainBike class start
class MountainBike implements Bicycle{
	
	private int seatHeight, speed, gear;
	
	public MountainBike(int startHeight, int startSpeed, int startGear) {
		seatHeight = startHeight;
		speed = startSpeed;
		gear = startGear;
	}
	
	@Override
	public void applyBrake(int decreament) {
		speed -= decreament;
	}
	
	@Override
	public void speedup(int increament) {
		speed += increament;
	}

	public int getSeatHeight() {
		return seatHeight;
	}

	public void setSeatHeight(int seatHeight) {
		this.seatHeight = seatHeight;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}
	
	
} /// MountainBike class end
