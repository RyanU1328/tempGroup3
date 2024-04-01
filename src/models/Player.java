package models;

public class Player {
	private String name;
	private int money;
	private int carbonDebt;

	private static final int STARTING_CARBON = 500;
	private static final int STARTING_MONEY = 500;

	public Player(String name) {
		super();
		this.setName(name);
		this.setMoney(STARTING_MONEY);
		this.setCarbonDebt(STARTING_CARBON);
	}

	public String getName() {
		return name;
	}

	/*
	 * Player name must be at least one character and not null
	 */
	public void setName(String name) throws IllegalArgumentException, NullPointerException {
		if (name == null) {
			throw new NullPointerException("Name is null");
		}
		if (name.length() > 0) {
			this.name = name;
		} else
			throw new IllegalArgumentException("Player name must be at least one character");
		;

	}

	public int getMoney() {

		return money;
	}

	public void setMoney(int money) {

		this.money = money;
	}

	public int getCarbonDebt() {
		return carbonDebt;
	}

	public void setCarbonDebt(int carbonDebt) {

		this.carbonDebt = carbonDebt;
	}

	public int getResources() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getResources'");
	}

	public int getPosition() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
	}

	public void setPosition(int newPosition) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
	}

	public void addResources(int collectResources) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addResources'");
	}

	public void deductResources(int fee) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deductResources'");
	}

}
