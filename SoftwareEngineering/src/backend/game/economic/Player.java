package backend.game.economic;

public abstract class Player implements Economic{
	private int balance;
	private int doubleCount;
	private int prisonCount;
	private int position;
	private String name;
	
	abstract public int rollDice();
	abstract public boolean wish2trade(int landNum);
	abstract public int where2go();
	abstract public int drawCard(int range);
	
	protected Player(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.doubleCount = 0;
		this.prisonCount = 0;
		this.position = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int pay(Economic to, int amount) {
		int sum = to.paid(amount);// balance -= to.paid(amount) ==> bug
		balance -= sum;
		return balance > 0?0:1;
	}
	
	public int paid(int amount) {
		balance += amount;
		return amount;
	}
	
	public int getBalance() {
		return this.balance;
	}

	public boolean isImprisoned() {
		return prisonCount != 0;
	}
	
	public void imprison() {
		prisonCount++;
		if (prisonCount == 4)
			prisonCount = 0;
	}
	public void release() {
		prisonCount = 0;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int move(int distance) {
		this.position += distance;
		return position;
	}
	
	public boolean isBankrupt() {
		return balance <= 0;
	}
}
