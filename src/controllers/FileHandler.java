/**
* @author : Ryan Urquhart
* @studentNumber : 40099112
* @email : rurquhart@qub.ac.uk
* @created : Apr 10, 2024 - 5:07:58 PM
**/
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static int getFileLength(File file) {
        FileReader fr;
        BufferedReader br;
        int fileLength = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                fileLength++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLength;
    }

    /**
     * Reads raw output from files. If data parsing is required it will have to be
     * done elsewhere.
     * 
     * @param filePath file path from root of project folder, i.e.
     *                 "/src/resources/asciititle.txt"
     *                 Please use forward slashes for OS compatibility
     */
    public static String[] getFileContents(String filePath) {
        File file;
        FileReader fr;
        BufferedReader br;
        file = new File(System.getProperty("user.dir") + filePath);
        String[] fileContents = new String[getFileLength(file)];
        ;
        // This code snippet is attempting to read and print the
        // contents of a file specified by the `filePath` parameter.
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            for (int i = 0; i < fileContents.length; i++) {
                fileContents[i] = line;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }

    public static void printFileContents(String filePath) {
        String[] fileContents = getFileContents(filePath);
        for (String line : fileContents) {
            System.out.println(line);
        }
    }

    /**
     * The `listFilesInDir` method in the `GameController` class is a public
     * method that takes two
     * parameters: `dir` representing the directory path and `pattern`
     * representing a pattern to match against file names.
     * 
     * @param dir     Directory of the files you wish to search
     * @param pattern Pattern to match files against, this matches if the filename
     *                contains the string
     * @return List<String> A list of the contents of the directory
     * @throws IOException
     */
    public static List<String> listFilesInDir(String dir, String pattern) throws IOException {
        List<String> fileList = new ArrayList<>();
        File file = new File(dir);
        String[] list = file.list();
        for (String i : list) {
            if (i.contains(pattern)) {
                fileList.add(i);
            }
        }
        return fileList;
    }
}
