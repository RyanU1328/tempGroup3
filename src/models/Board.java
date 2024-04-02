package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares = new ArrayList<>();

    public Board() {
        squares.add(new GoSquare("Go"));
        squares.add(new ResourceSquare("Green Forest", 100));
        squares.add(new InvestmentSquare("Solar Power Plant", 500, 50));
        squares.add(new ConservationArea("Local Park Conservation", 200, 50)); 
        squares.add(new PollutionPenaltySquare("Factory Pollution", 150));
        squares.add(new GreenTechnologyInvestmentSquare("Solar Farm", 300, 50, 100));
        squares.add(new CommunityOutreachSquare("Local School Partnership", 200, 50));
        squares.add(new RenewableEnergyProjectSquare("Wind Turbine", 400, 75, 150));
    }

    public Square getSquare(int position) {
        return squares.get(position % squares.size());
    }

    public int getSize() {
        return squares.size(); 
    }
}
