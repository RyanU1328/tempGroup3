package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares = new ArrayList<>();

    public Board() {
        squares.add(new GoSquare("Go"));

        squares.add(new InvestmentSquare("Sunny Acres", 20, 10)); 
        squares.add(new InvestmentSquare("Radiant Ridge", 30, 15));
        squares.add(new InvestmentSquare("Windy Mills", 50, 25)); 
        squares.add(new InvestmentSquare("Gusto Grove", 60, 30));
        squares.add(new InvestmentSquare("Airy Heights", 70, 35)); 
        
        squares.add(new InvestmentSquare("Splashviille", 90, 45));
        squares.add(new InvestmentSquare("Torrential Terrace", 100, 50)); 
        squares.add(new InvestmentSquare("Hydro Haven", 110, 55));
        squares.add(new InvestmentSquare("Nucleo nest", 130, 65)); 
        squares.add(new InvestmentSquare("Atomic Oasis", 140, 70));

    }

    public Square getSquare(int position) {
        return squares.get(position % squares.size());
    }

    public int getSize() {
        return squares.size(); 
    }
}
