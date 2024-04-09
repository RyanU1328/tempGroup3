package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Board;
import models.EcoZoneSquare;
import models.GoSquare;
import models.InvestmentSquare;
import models.Player;
import models.Square;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameController {

    private String dir1, dir2, dir3;
    private List<String> dir1Expected, dir2Expected, dir3Expected;
    private List<String> dir1Actual, dir2Actual, dir3Actual;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        dir1 = System.getProperty("user.dir") + "/src/resources";
        dir2 = System.getProperty("user.dir") + "/src/main";
        dir3 = System.getProperty("user.dir") + "/src";
        dir1Expected = Arrays
                .asList(new String[] { "asciititle.txt", "cloudavatar.txt", "ligthningavatar.txt", "sunavatar.txt",
                        "treeavatar.txt", "waveavatar.txt", "Fines.txt", "GameObjective.txt", "Gameplay.txt",
                        "GameSetup.txt", "PurchasingAndDevelopment.txt", "Upgrading.txt", "Units.txt" });
        dir2Expected = Arrays.asList(new String[] { "Main.java" });
        dir3Expected = Arrays.asList(new String[] { "controllers", "main", "models", "resources", "utils" });

    }

    /**
     * Tests 'listFilesInDir' from 'GameController', if more files are added in the
     * tested directory they need added into this test
     * 
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    void testListFilesInDir() throws IOException, URISyntaxException {
        dir1Actual = GameController.listFilesInDir(dir1, "");
        dir2Actual = GameController.listFilesInDir(dir2, "");
        dir3Actual = GameController.listFilesInDir(dir3, "");
        Collections.sort(dir1Expected);
        Collections.sort(dir2Expected);
        Collections.sort(dir3Expected);
        Collections.sort(dir1Actual);
        Collections.sort(dir2Actual);
        Collections.sort(dir3Actual);
        assertEquals(dir1Expected, dir1Actual);
        assertEquals(dir2Expected, dir2Actual);
        assertEquals(dir3Expected, dir3Actual);
    }
}
