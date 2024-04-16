package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares = new ArrayList<>();

    public Board() {
        // Go square
        squares.add(new GoSquare("Go"));
        // Solar field
        squares.add(new InvestmentSquare("Sunny Acres", 20, 1));
        squares.add(new InvestmentSquare("Radiant Ridge", 30, 1));
        // Wind field
        squares.add(new InvestmentSquare("Windy Mills", 50, 2));
        squares.add(new InvestmentSquare("Gusto Grove", 60, 2));
        squares.add(new InvestmentSquare("Airy Heights", 70, 2));
        // Nothing Square
        squares.add(new EcoZoneSquare("Eco Zone"));
        // Hydro field
        squares.add(new InvestmentSquare("Splashville", 90, 3));
        squares.add(new InvestmentSquare("Torrential Terrace", 100, 3));
        squares.add(new InvestmentSquare("Hydro Haven", 110, 3));
        // Nuclear field
        squares.add(new InvestmentSquare("Nucleo Nest", 130, 4));
        squares.add(new InvestmentSquare("Atomic Oasis", 140, 4));
    }

    public Square getSquare(int position) {
        return squares.get(position % squares.size());
    }

    public int getSize() {
        return squares.size();
    }
}
