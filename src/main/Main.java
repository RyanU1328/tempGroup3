package main;

import java.io.IOException;
import java.net.URISyntaxException;

import controllers.GameController;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        GameController gameController = new GameController();
        gameController.startGame();

    }

}
