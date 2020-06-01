package Homework8;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Scanner programInput = new Scanner(System.in);
        new FileReader("TextFile.txt");
        Data.validateDatabase(FileReader.getDatabase());
        String userInput = "";
        while (true) {
            userInput = programInput.nextLine();
            InformationProcessing.processRequest(userInput);
        }
    }
}
