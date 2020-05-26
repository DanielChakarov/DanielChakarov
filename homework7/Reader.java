import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Клас реализиращ четееето на днни за нашата програма
 */
public class Reader {
    private Scanner scannerInput;

    public Reader (String nameOfFile){
        File file = new File(nameOfFile);
        try {
            FileInputStream fileInput = new FileInputStream(file);
            scannerInput = new Scanner (fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
public int getValue(String nameValue){
        while(scannerInput.hasNextLine()){
            String line = scannerInput.nextLine();
            if (line.contains(nameValue)) return extractValue(line);
        }
    return -1;
}

public int extractValue(String line){

        String[] lineSection = line.split(":");
        return Integer.parseInt(lineSection[1]);
    }
}
