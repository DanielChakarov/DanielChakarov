import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Reader {
    /**
     * Метод за четене на данни от текстовия файл
     * @param tileType Видът на позицията 
     * @param amount броят на редовете за четене
     * @return прочетените редове от файла
     */
    public static ArrayList<String> reading(String tileType, int id, int amount) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File("Data.txt");
            FileInputStream filexs = new FileInputStream(file);
            Scanner newScanner = new Scanner(files);
            lines = readData(newScanner, tileType, id, amount);
            newScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Метод за намиране на иформация от файла
     * @param tileType Видът на позицията
     * @param amount броят на редовете за четене
     * @return прочетените редове от файла
     */
    private static ArrayList<String> readData(Scanner scannerInput, String tileType, int id, int amount) {
        boolean isDataLocated;
        boolean idLocated;
        boolean isFoundCorrectData;
        ArrayList<String> lines = new ArrayList<>();
        while (scannerInput.hasNextLine()) {
            isDataLocated = findBeginning(scannerInput, tileType);
            idLocated = findLineID(scannerInput, id);
            isFoundCorrectData = (isDataLocated && idLocated);
            if (isFoundCorrectData) {
                readCorrectData(amount, lines, scannerInput);
                break;
            }
        }
        return lines;
    }

    /**
     * Четене на точно определени редове
     * @param lines Контейнер в който се събират прочетените редове
     * @param amount Количетвото редове за четене
     */
    private static void readCorrectData(int amount, ArrayList<String> lines, Scanner scanner) {
        for (int i = 0; i < amount; i++) {
            lines.add(scanner.nextLine());
        }
    }

    private static boolean findLineID(Scanner sc, int ID) {
        String identification = "ID" +ID;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals(identification)) return true;
        }
        return false;
    }

    /**
     * Търсене на данни
     * @param tileType Видът на позицията
     */
    private static boolean findBeginning(Scanner scanner, String tileType) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals(tileType)) return true;
        }
        return false;
    }
}
