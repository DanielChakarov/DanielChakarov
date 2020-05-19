package MonsterAdministration;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import kotlin.collections.unsigned.UArraysKt;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class MonsterAdministration {
    public static void main(String[] args) {
    printIntegerMenu();
    }

    /**
     * Метод визуализиращ началното меню
     */
    public static void startMenu(){
        Scanner programInput = new Scanner(System.in);
        printStartMenu();
    System.out.println("Въведи своя избор:");
    int choice = programInput.nextInt();
       switch (choice){
           case 1:
                integerMenu(array);
               break;
           case 2:
                stringMenu(array);
               break;
           case 3:
               return;
           default:
               System.out.println("Невелиден избор! Опитай отново. ");
       }

}

    /**
     *  Метод за избор в менюто с думи
     */
    private static void stringMenu() {
        ArrayString.arrayString();
        printStringMenu();
        Scanner programInput = new Scanner (System.in);
        System.out.println("Въведи своя избор:");
        int choice = programInput.nextInt();
        switch  (choice){
            case 1:
               ArrayString.revertWords(array);
                break;
            case 2:
                System.out.println();
                // TODO: Не занам как да имплиментирам този метод.

                break;
            case 3:
                ArrayString.wordsLength(array);
                break;
            case 4:
                System.out.println();
                // TODO: Не занам как да имплиментирам този метод.

                break;
            case 5:
                printStringMenu();
                break;
            default:
                System.out.println("Невелиден избор! Опитай отново. ");

        }
    }

    /**
     * Метод за избор в менюто с числа
     */
    private static void integerMenu() {
        ArrayInteger.arrayInt();
        printIntegerMenu();
        Scanner programInput = new Scanner(System.in);
        System.out.println("Въведи своя избор:");
        int choice =  programInput.nextInt();
        switch (choice){
            case 1:
               ArrayInteger.primeNumbers(array);
                break;
            case 2:
                ArrayInteger.mostCommonElement(array);
                break;
            case 3:
                ArrayInteger.increasingNumberRow(array);
                break;
            case 4:
                ArrayInteger.decreasingNumberRow(array);
                break;
            case 5:
                ArrayInteger.numberRowEqualElements(array);
                break;
            case 6:
                System.out.println();
                // TODO: Не занам как да имплиментирам този метод.
                break;
            case 7:
                printStartMenu();
                break;
            default:
                System.out.println("Невалиден избор! Опитай отново!");
        }
    }

    /**
     * Метод визуализираш менюто за работа с числа
     */
    public static void printIntegerMenu(){
     System.out.println("1 Извеждане само на простите числа в масива.");
     System.out.println("2 Извеждане на най-често срещан елемент в масива.");
     System.out.println("3 Извеждане на максимална редица от нарастващи елементи в масива.");
     System.out.println("4 Извеждане на максимална редица от намаляващи елементи в масива.");
     System.out.println("5 Извеждане на максимална редица от еднакви елементи в масива.");
     System.out.println("6 Извеждане на последователност от числа от масива, които имат сума\n" +
             "равна на число, генерирано на случаен принцип");
     System.out.println("7 Връщане назад към основното меню.");
 }

    /**
     * Метод визуализираш менюто за работа с думи
     */
 public static  void printStringMenu(){
     System.out.println("1 Обърнете буквите на думите от масива наобратно и ги\n" +
             "визуализирайте в конзолата");
     System.out.println("2 Изведете броя на повтарящите се символи за всяка една от думите в\n" +
             "масива");
     System.out.println("3 Изведете броя на символите за всяка една от думите в масива");
     System.out.println("4 Изведете броя на повтарящите се думи от масива");
     System.out.println("5 Връщане назад към основното меню");
 }

    /**
     * Метод визуализираш главното меню
     */
    public static void printStartMenu(){
              System.out.println("1 Работа с числа");
              System.out.println("2 Работа с думи");
              System.out.println("3 Изход от програмата");
}

    /**
     * Метод за валидацията на думите
     * @return връща написаната от нас дума
     */
        public static String stringValidation(){
            Scanner scanner = new Scanner(System.in);

            boolean notValid = true;
            String word="";
            while (notValid) {
                word = scanner.next();
                notValid = word.length()>20;
            }
            return word;
        }
}
