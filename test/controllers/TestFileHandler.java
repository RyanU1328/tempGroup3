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

import static org.junit.jupiter.api.Assertions.*;

public class TestFileHandler {

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
    List<String> dirActual = FileHandler.listFilesInDir(dir, "");
    Collections.sort(expectedList);
    Collections.sort(dirActual);
    assertEquals(expectedList, dirActual);
  }
}
