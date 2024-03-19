package models;

public class EventSquare extends Square {
    private String eventDescription;

    public EventSquare(String name, String eventDescription) {
        super(name);
        this.eventDescription = eventDescription;
    }

    @Override
    public void landOn(Player player) {
        System.out.println(player.getName() + " landed on " + getName() + ": " + eventDescription);
    }
}
