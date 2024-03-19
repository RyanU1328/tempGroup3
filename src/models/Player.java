package models;

public class Player {
    private String name;
    private int resources; 
    private int position = 0;

    public Player(String name) {
        this.name = name;
        this.resources = 1000; 
    }


    public String getName() {
        return name;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    // Add methods for managing resources
    public void addResources(int amount) {
        this.resources += amount;
    }

    public void deductResources(int amount) {
        this.resources -= amount;
   
    }

    
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
