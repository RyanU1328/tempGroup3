package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import models.Board;
import models.EcoZoneSquare;
import models.GoSquare;
import models.InvestmentSquare;
import models.Player;
import models.Square;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameController {

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {

    }

    /**
     * Tests 'listFilesInDir' from 'GameController', if more files are added in the
     * tested directory they need added into this test
     * 
     * @throws IOException
     * @throws URISyntaxException
     */
    @ParameterizedTest
    @CsvSource(value = { "/src/main:Main.java", "/src:controllers,main,models,utils,resources" }, delimiter = ':')
    void testListFilesInDir(String dir, String expected) throws IOException, URISyntaxException {
        dir = System.getProperty("user.dir") + dir;
        System.out.println(expected);
        String[] tempArray = expected.split(",");
        List<String> expectedList = Arrays.asList(tempArray);
        List<String> dirActual = GameController.listFilesInDir(dir, "");
        Collections.sort(expectedList);
        Collections.sort(dirActual);
        assertEquals(expectedList, dirActual);
    }
}
