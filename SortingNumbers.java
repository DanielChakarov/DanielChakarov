import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Даниел Чакъров
 */
public class SortingNumbers {
    static boolean exit;
    final static int ELEMENT_NOT_FOUND = -1;

    public static void main(String[] args) {
        Scanner programInput = new Scanner(System.in);
        System.out.print("Въведете размера на масива: ");
        int arraySize = programInput.nextInt();
        int[] array = new int [arraySize];
        arrayFilling(arraySize, array);
        System.out.print("Масив:");
        showArray(array);
        System.out.println();

        while(!exit){
            System.out.println();
            printMenu();
            System.out.println("Въведи своя избор:");
            makeYourSelection(array);
        }
    }

    /**
     * Метод изкарващ на екрана възможностите, които може да прави нашата програма.
     */
    public static void printMenu() {
        System.out.println("                ...Меню... ");
        System.out.println(" 1. Сортиране на въведените числа във възходящ ред");
        System.out.println(" 2. Сортиране на въведените числа в низходящ ред");
        System.out.println(" 3. Търсене на позиция на конкретно число");
        System.out.println(" 4. Разбъркване на числата");
        System.out.println(" 5. Изчисляване на сбора на всички числа");
        System.out.println(" 6. Намиране на най-голямото число");
        System.out.println(" 7. Намиране на най-малкото число");
        System.out.println(" 8. Намиране средно-аритметично на числата");
        System.out.println(" 9. Проверка за симетричност на масива от числа");
        System.out.println(" 10. Обръщане на масива от числа");
        System.out.println(" 11. Визуализирай въведените числа");
        System.out.println(" 12. Изход");
        System.out.println();
    }

    /**
     * Метод чрез който запълвa масива с избраната от нас дължина
     *
     * @param arraySize израната от нас дължина на масива
     * @param array     масив
     */
    public static void arrayFilling(int arraySize, int[] array) {
        for (int i = 0; i < arraySize; i++) {
            Scanner programInput = new Scanner(System.in);
            int number;
            do {
                System.out.print("Въведи стойност за индекс " + i + ": ");
                array[i] = programInput.nextInt();
                number = array[i];
            } while (number > 100 || number < 0);
        }
    }

    /**
     * Метод изкарващ на екрана елементите на масива
     */
    public static void showArray(int[] array) {
        for (int components : array) {
            System.out.print(components + ",");
        }
    }

    /**
     * Метод реализиращ подреждането на числа от най-голямо към най-малко
     *
     * @param array разбъркан масив
     */
    public static void fromHighestToLowest(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int element = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = element;
                }
            }
        }


    }

    /**
     * Метод реализиращ подреждането на числа от най-малкото към най-голямото
     */
    public static void fromLowestToHighest(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int p = 0;p < array.length -i - 1; p++) {
                if (array[p] > array[p+1]) {
                    int element = array[p];
                    array[p] = array[p+1];
                    element = array[p+1];

                }
            }
        }
    }

    public static void positionSearch(int[] array) {
        Scanner programInput = new Scanner(System.in);
        System.out.println("\nВъвете число чиято позиция искате да намерите:");
        int elementToFind = programInput.nextInt();
        int foundElementIndex = binarySearch(elementToFind, array);

        if (foundElementIndex == ELEMENT_NOT_FOUND) {
            System.err.print("Елементът не е намерен\n");
        } else {
            System.out.printf("Елементът е намерен на позиция: %d\n",
                    (foundElementIndex + 1));

        }
    }

    /**
     * Метод реализиращ двоично върсене в масив
     * @param elementToFind търсеният от нас елемент
     * @param array масив
     * @return несъществуващ в масива елемене
     */
    public static int binarySearch(int elementToFind, int[] array) {

        int low = 0;
        int high = array.length - 1;
        int middle = (low + high) / 2;
        while (low <= high) {
            if (elementToFind == array[middle]) {
                return middle;
            } else if (elementToFind < array[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
            middle = (low + high) / 2;
        }
        return ELEMENT_NOT_FOUND;

    }

    /**
     * Метод реализираш събирането на всички числа от масива
     */
    public static void sumArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }

        System.out.println("\nСборът на числата от масива е :" + sum);
    }

    /**
     * Метод реализиращ визуализирането на най-голямото число от масива
     */
    public static void maxNumberInArray(int[] array) {
        int max = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("\nНай-голямото число от масива е:" + max);

    }

    /**
     * Метод реализиращ визуализирането на най-малкото число от масива
     */
    public static void minNumberInArray(int[] array) {
        int min = 101;
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        System.out.println("\nНай-малкото число от масива е:" + min);
    }

    /**
     * Метод реализиращ визуализирането на средно аритметичното на числата от масива
     */
    public static void average(int[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        System.out.println("\nСредно аритметичното на числата от масива е :" + sum / array.length);
    }

    /**
     *Метод казващ ни дали масиват е симетричен или не е
     */
    public static void ifArrayHasSymmetry(int[] array) {
for (int i = 0; i < array.length; i++){
    if(i > array.length/2){
        System.out.print("\nМасивът е симетричен" + Arrays.toString(array) );
        break;
    }else if(array[i] != array[array.length-1-i]){
        System.out.println("\nМасивът е несиметричен" + Arrays.toString(array));
        break;
            }
        }
    }

    /**
     *Метод объщащ масива на обратно
     */
    public static void arrayInversion (int[]array) {
        for (int i = 0; i <= array.length/2; i++) {
            int number = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = number;
        }System.out.println("Обънатият масив е:" + Arrays.toString(array));
    }

    /**
     * Метод разбъркващ направеният от нас масив
     */
    public static void arrayShuffle(int[]array){
        Random arrayRandom = new Random();
        for (int i = 0; i < array.length; i++){
            int position    = arrayRandom.nextInt(array.length);
            int number      =  array[position];
            array[position] = array[i];
            array[i]        = number;
        }
        System.out.println("\nРазбъканият масив е :"+Arrays.toString(array));
    }

    public static void makeYourSelection(int[]array) {
        Scanner programInput = new Scanner (System.in);
        int choice = programInput.nextInt();
        switch (choice){
            case 1:
                fromLowestToHighest(array);
                break;
            case 2:
                fromHighestToLowest(array);
                break;
            case 3:
                positionSearch(array);
                break;
            case 4:
                arrayShuffle(array);
                break;
            case 5:
                sumArray(array);
                break;
            case 6:
                maxNumberInArray(array);
                break;
            case 7:
                minNumberInArray(array);
                break;
            case 8:
                average(array);
                break;
            case 9:
                ifArrayHasSymmetry(array);
                break;
            case 10:
                arrayInversion(array);
                break;
            case 11:
                showArray(array);
                break;
            case 12:
                exit = true;
                System.out.println("Благодарим че използвахте нашaтa програма!");
                break;
            default:printMenu();
        }

    }

}




