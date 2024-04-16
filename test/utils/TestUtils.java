/**
* @author : Ryan Urquhart
* @studentNumber : 40099112
* @email : rurquhart@qub.ac.uk
* @created : Apr 10, 2024 - 3:52:12 PM
**/
package utils;

import models.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class TestUtils {

    private static List<Arguments> nameNumberList;
    public static Random rand = new Random();
    private static int numTests = 10;

    public static String randomName() {
        String name = "";
        byte[] byteArray = new byte[rand.nextInt(99) + 1];
        for (int j = 0; j < byteArray.length; j++) {
            byteArray[j] = (byte) (rand.nextInt(122 - 97) + 97);
        }
        name = new String(byteArray, Charset.forName("UTF-8"));
        return name;
    }

    public static Stream<String> randomNameStream() {
        String[] names = new String[numTests];
        for (int i = 0; i < names.length; i++) {
            byte[] byteArray = new byte[rand.nextInt(99) + 1];
            for (int j = 0; j < byteArray.length; j++) {
                byteArray[j] = (byte) (rand.nextInt(122 - 97) + 97);
            }
            names[i] = new String(byteArray, Charset.forName("UTF-8"));
        }
        return Arrays.stream(names);
    }

    public static Stream<Arguments> randomTestNumbersAndNames() {
        nameNumberList = new LinkedList<>();
        for (int i = 0; i < numTests; i++) {
            String randomString = randomName();
            nameNumberList.add(Arguments.of(rand.nextInt(999) + 1, randomString));
        }
        return nameNumberList.stream();
    }

    private static Stream<Arguments> randomTestNamesPlayersNumbers() {
        nameNumberList = new LinkedList<>();
        List<Player> testPlayers = new ArrayList<>();
        for (int i = 0; i < numTests; i++) {
            String randomString = randomName();
            testPlayers.add(new Player(randomName()));
            nameNumberList.add(Arguments.of(rand.nextInt(999) + 1, randomString, testPlayers.get(i)));
        }
        return nameNumberList.stream();
    }
}
