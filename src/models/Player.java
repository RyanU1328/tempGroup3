package models;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private int money;
	private int carbonDebt;
     private List<String> properties;
	private int position = 0;

	private static final int STARTING_CARBON = 500;
	private static final int STARTING_MONEY = 500;

	public Player(String name) {
		super();
		this.setName(name);
		this.setMoney(STARTING_MONEY);
		this.setCarbonDebt(STARTING_CARBON);
        this.properties = new ArrayList<>();
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

	public int[] getResources() {
		int[] resources = { money, carbonDebt };
		return resources;

	}
    public List<String> getProperties() {
		return properties;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int newPosition) {
		this.position = newPosition;
	}

	public void addResources(String kind, int collectResources) {
		if (kind.equals("money")) {
			this.money += collectResources;
		} else if (kind.equals("carbonDebt")) {
			// Ensure carbon debt does not go negative
			this.carbonDebt = Math.max(0, this.carbonDebt + collectResources);
		}
	}
	

	public void deductResources(String kind, int fee) {
		if (kind.equals("money")) {
			this.money -= fee;
		} else {
			this.carbonDebt -= fee;
		}
	}

    public void addProperty(String property) {
        properties.add(property);
    }

    
    public void removeProperty(String property) {
        properties.remove(property);
    }
    public void displayPlayerInfo() {
        System.out.println("Player: " + name);
        System.out.println("Money Resources: £" + money);
        System.out.println("Carbon Debt: " + carbonDebt);
        System.out.println("Properties owned:");
        for (String property : properties) {
            System.out.println("- " + property);
        }

}
}
