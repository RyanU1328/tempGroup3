package models;

import java.util.Scanner;

public class CommunityOutreachSquare extends Square {
    private int investmentOption;
    private int donationOption;

    public CommunityOutreachSquare(String name, int investmentOption, int donationOption) {
        super(name);
        this.investmentOption = investmentOption;
        this.donationOption = donationOption;
    }

    @Override
    public void performAction(Player player, Scanner scanner) {
        System.out.println(player.getName() + " has landed on " + getName() + ". Do you want to invest "
                + investmentOption + " resources for future support or receive a donation of "
                + donationOption + " resources now? (invest/receive)");

        String choice = scanner.nextLine();
        if ("invest".equalsIgnoreCase(choice)) {
            if (player.getMoney() >= investmentOption) {
                player.deductResources("money", investmentOption);
                System.out.println(player.getName() + " chose to invest in the community, resources now: "
                        + player.getResources());
            } else {
                System.out.println("Not enough resources to invest.");
            }
        } else if ("receive".equalsIgnoreCase(choice)) {
            player.addResources("money", donationOption);
            System.out.println(
                    player.getName() + " chose to receive a donation, resources now: " + player.getResources());
        }
    }
}
