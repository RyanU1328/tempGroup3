package models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int moneyResources; 
    private int carbonDebt;
    private List<String> properties;
    private int position = 0;

    public Player(String name, int moneyResources, int carbonDebt) {
        this.name = name;
        this.moneyResources = 500; 
        this.carbonDebt = 500;
        this.properties = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public int getMoneyResources() {
        return moneyResources;
    }

    public void setMoneyResources(int resources) {
        this.moneyResources = resources;
    }

    public int getCarbonDebt() {
		return carbonDebt;
	}


	public void setCarbonDebt(int carbonDebt) {
		this.carbonDebt = carbonDebt;
	}


	public List<String> getProperties() {
		return properties;
	}


	public void setProperties(List<String> properties) {
		this.properties = properties;
	}


	// Add methods for managing money resources and carbon debt
    public void addMoneyResources(int amount) {
        this.moneyResources += amount;
    }

    public void deductMoneyResources(int amount) {
        this.moneyResources -= amount;
   
    }
    
    public void addCarbonDebt(int amount) {
        this.carbonDebt+= amount;
    }

    public void deductCarbonDebt(int amount) {
        this.carbonDebt -= amount;
   
    }
    public void addProperty(String property) {
        properties.add(property);
    }

    
    public void removeProperty(String property) {
        properties.remove(property);
    }

    
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


//Method to display player information
public void displayPlayerInfo() {
    System.out.println("Player: " + name);
    System.out.println("Money Resources: $" + moneyResources);
    System.out.println("Carbon Debt: " + carbonDebt);
    System.out.println("Properties owned:");
    for (String property : properties) {
        System.out.println("- " + property);
    }
}
}
