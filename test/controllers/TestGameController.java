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

    private String dir1, dir2, dir3, dir4;
    private List<String> dir1Expected, dir2Expected, dir3Expected, dir4Expected;
    private List<String> dir1Actual, dir2Actual, dir3Actual, dir4Actual;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        dir1 = System.getProperty("user.dir") + "/src/resources";
        dir2 = System.getProperty("user.dir") + "/";
        dir3 = System.getProperty("user.dir") + "/src/main";
        dir4 = System.getProperty("user.dir") + "/src/models";
        dir1Expected = Arrays
                .asList(new String[] { "asciititle.txt", "cloudavatar.txt", "ligthningavatar.txt", "sunavatar.txt",
                        "treeavatar.txt", "waveavatar.txt" });
        dir2Expected = Arrays.asList(new String[] {
                ".classpath", ".git", ".gitignore", ".gitlab-ci.yml", ".metadata", ".project", ".settings", "JARs",
                "README.md", "bin", "reports", "src", "test", "whileScript.sh" });
        dir3Expected = Arrays.asList(new String[] { "Main.java" });
        dir4Expected = Arrays.asList(new String[] { "Board.java", "EcoZoneSquare.java", "GoSquare.java",
                "InvestmentSquare.java", "Player.java", "Square.java" });

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
        dir4Actual = GameController.listFilesInDir(dir4, "");
        Collections.sort(dir1Expected);
        Collections.sort(dir2Expected);
        Collections.sort(dir3Expected);
        Collections.sort(dir4Expected);
        Collections.sort(dir1Actual);
        Collections.sort(dir2Actual);
        Collections.sort(dir3Actual);
        Collections.sort(dir4Actual);
        assertEquals(dir1Expected, dir1Actual);
        assertEquals(dir2Expected, dir2Actual);
        assertEquals(dir3Expected, dir3Actual);
        assertEquals(dir4Expected, dir4Actual);
    }
}
