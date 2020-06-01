package Homework8;

import java.io.FileInputStream;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class FileReader {

    private static ArrayList<String> database = new ArrayList<String>();

    /**
     * Метод отварящ тексторият файл
     * @param fileName името на файла
     */
    public FileReader(String fileName) {
        try {
            File file                       = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner fileScanner             = new Scanner(fileInputStream);
            readDatabase(fileScanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getDatabase() {
        return database;
    }

    /**
     * Метод за прочитане на файла
     */
    private void readDatabase(Scanner fileScanner) {
        String temporary = "";
        while (fileScanner.hasNextLine()) {
            temporary = fileScanner.nextLine();
            database.add(temporary);
        }
    }

}