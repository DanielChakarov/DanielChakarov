package MonsterAdministration;
import java.util.Scanner;
/**
 * Клас обработващ масива от числа
 */
public class ArrayInteger {

    public static void arrayInt() {
        Scanner programInput = new Scanner(System.in);
        System.out.print("Въведи колко на брой числа ще въвеждате: " );

        int arraySize = programInput.nextInt();
        int[] array = new int[arraySize];

        arrayValidation(array, arraySize);
        printArray(array);
    }

    /**
     * Метод реализиращ валидацията на входните данни
     *
     * @param array     масив
     * @param arraySize вължината на масива
     */
    public static void arrayValidation(int[] array, int arraySize) {
        Scanner programInput = new Scanner(System.in);
            for (int i = 0; i < arraySize; i++) {
            int number;
            do {
                System.out.print("Въведи стойност за индекс " + i + ": " );
                array[i] = programInput.nextInt();
                number = array[i];
            } while (number > 10000 || number < 0);
        }
    }

    /**
     * Метод изкарваш на екрана емелементи на масива
     *
     * @param array масив
     */
    public static void printArray(int[] array) {
        System.out.print("Масив:" );
        for (int element : array) {
            System.out.print(element);
            System.out.print(" " );
        }
    }

    /**
     * Метод реализиращ извеждането на всички естествени числа от масива
     *
     * @param array масив
     */
    public static void primeNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean isPrame = true;
            for (int j = 2; j < array[i]; j++) {
                if (array[i] % j == 0) {
                    isPrame = false;
                    break;
                }
            }
            if (isPrame) {
                System.out.print(array[i] + "," );
            }
        }
        System.out.print(" са простите числа в масива." );
    }

    /**
     * Метод служещ за извеждането на най-срещаното число в масива
     *
     * @param array масив
     * @return връща най-често повтаряната стойност в масива
     */
    public static int mostCommonElement(int[] array) {
        int counter = 1;

        int temporaryCounter;
        int popular = array[0];
        int temporary = 0;
        for (int i = 0; i < (array.length - 1); i++) {
            temporary = array[i];
            temporaryCounter = 0;
            for (int j = 0; j < array.length; j++) {
                if (temporary == array[j]) {
                    temporaryCounter++;
                }
                if (temporaryCounter > counter) {
                    popular = temporary;
                    counter = temporaryCounter;
                }
            }
        }
        System.out.println(String.format("Най-срещаното число е %d среща се %d пъти.", popular, counter));
        return popular;
    }

    /**
     * Метод реализиращ изкарването на най-дългата нарастваща редица в масива
     *
     * @param array числова редица
     */
    public static void increasingNumberRow(int[] array, int n  ) {
        int subarrayLength = 1;
        int lengthOfLongest = 1;
        int maxIndex = 0;
        for (int i = 1; i < n; i++)
        {
            if (array[i] > array[i-1])
                lengthOfLongest++;
            else
            {
                if (subarrayLength < lengthOfLongest)
                {
                    subarrayLength = lengthOfLongest;
           maxIndex = i - subarrayLength;
                }
                lengthOfLongest = 1;
            }
        }
        if (subarrayLength < lengthOfLongest)
        {
            subarrayLength = lengthOfLongest;
            maxIndex = n - subarrayLength;
        }
       System.out.print("Максималната нарастваща редица е: ");
        for (int i = maxIndex; i < subarrayLength+maxIndex; i++){
            System.out.print(array[i] + " ");}
    }

    /**
     * Метод реализираш извежането на най-дългата намаляваща числова редица в масива
     * @param array масив (числова редица)
     */
    public static void decreasingNumberRow(int [] array,int n){
        int subarrayLength = 1;
        int lengthOfLongest = 1;
        int maxIndex = 0;
        for (int i = 1; i < n; i++)
        {
            if (array[i] < array[i-1])
                lengthOfLongest++;
            else
            {
                if (subarrayLength < lengthOfLongest)
                {
                    subarrayLength = lengthOfLongest;
                    maxIndex = i - subarrayLength;
                }
                lengthOfLongest = 1;
            }
        }
        if (subarrayLength < lengthOfLongest)
        {
            subarrayLength = lengthOfLongest;
            maxIndex = n - subarrayLength;
        }
        System.out.print("Максималната намаляваща редица е: ");
        for (int i = maxIndex; i < subarrayLength+maxIndex; i++){
            System.out.print(array[i] + " ");
        }
    }

    /**
     * Метод реализиращ извеждането на най-дългата редица от еднакви елементи в масива
     * @param array масив
     */
    public static void numberRowEqualElements(int[]array, int n){
                int subarrayLength = 1;
                int lengthOfLongest = 1;
                int maxIndex = 0;
                for (int i = 1; i < n; i++)
                {
                    if (array[i] == array[i-1])
                        lengthOfLongest++;
                    else
                    {
                        if (subarrayLength < lengthOfLongest)
                        {
                            subarrayLength = lengthOfLongest;
                            maxIndex = i - subarrayLength;
                        }
                        lengthOfLongest = 1;
                    }
                }
                if (subarrayLength < lengthOfLongest)
                {
                    subarrayLength = lengthOfLongest;
                    maxIndex = n - subarrayLength;
                }
                System.out.print("Максималната еднаква редица е: ");
                for (int i = maxIndex; i < subarrayLength+maxIndex; i++){
                    System.out.print(array[i] + " ");
                }
            }

}


