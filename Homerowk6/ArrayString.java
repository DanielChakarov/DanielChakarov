package MonsterAdministration;
import com.sun.org.apache.xpath.internal.objects.XString;
import java.util.Scanner;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * Клас реализиращ обработването на масив от думи
 * @author Daniel Chakarov
 */
public class ArrayString {
    /**
     * Метод за входните данни
     */
    public static void arrayString(){

        Scanner programInput = new Scanner(System.in);

        System.out.print("Въведи колко на брой думи ще въвеждате: " );
        int arraySize = programInput.nextInt();
        String [] array = new String[arraySize];
        setValues(array);
    }

    /**
     * Метод чрез който запълваме масива с думи
     * @param array масив
     */
    public static void setValues(String[]array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(String.format(" Въведете дума %d = ", i+1));
            array[i] = MonsterAdministration.stringValidation();
        }
    }

    /**
     * Метод реализиращ визуализирането на обрнатите думите в масива
     * @param array масив
     */
    public  static  void revertWords(String[]array) {
        for (String word : array) {
            revertWord(word);
            System.out.println();
        }
    }

    /**
     * Метод реализиращ обръщането на думите в масива
     * @param word обърнатата дума
     */
    private static void revertWord(String word) {
        int wordSize = word.length();
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordSize / 2; i++) {
            char temporary = wordArray[i];
            wordArray[i] = wordArray[wordSize - i - 1];
            wordArray[wordSize - i - 1] = temporary;
        }
        for (int i = 0; i < wordSize; i++) System.out.print(wordArray[i]);
    }

    /**
     * Метод визуализиращ броя на символите в дадена дума
     * @param array масив
     */
    public static void wordsLength (String[] array ){
        for (String s : array) {
            System.out.println(String.format("%s - съдържа %d символа.", s, s.length()));
        }
    }
}

