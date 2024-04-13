package models;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player {
  private String name;
  private int money;
  private int carbonDebt;
  private List<String> properties;
  private int position = 0;
  private String[] avatar;

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
    if (money > 0) {
      this.money = money;

    } else
      throw new IllegalArgumentException("Player cannot have zero money");

  }

  public int getCarbonDebt() {
    return carbonDebt;
  }

  public void setCarbonDebt(int carbonDebt) {
    if (carbonDebt > 0) {
      this.carbonDebt = carbonDebt;

    } else
      throw new IllegalArgumentException("Player has won!!");

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

  public boolean choosePaymentMethod(Scanner scanner) {
    while (true) {
      System.out.println("Choose payment method:");
      System.out.println("1. Pay by money");
      System.out.println("2. Pay by carbon debt");
      System.out.println("Enter your choice (1 or 2): ");

      try {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice == 1) {
          return true; // Pay by money
        } else if (choice == 2) {
          return false; // Pay by carbon debt
        } else {
          System.out.println("Invalid choice. Please enter 1 or 2.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // Consume invalid input
      }
    }
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

  /**
   * @return the avatar
   */
  public String[] getAvatar() {
    return avatar;
  }

  /**
   * @param avatar the avatar to set
   */
  public void setAvatar(String[] avatar) {
    this.avatar = avatar;
  }
}
