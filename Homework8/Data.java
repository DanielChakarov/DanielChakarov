package Homework8;

import java.util.ArrayList;
public class Data {

    private static String [] features       = {"{TYPE}=[","{FNAME}=[","{LNAME}=[","{AGE}=[","{SEX}=[","{ADDRESS}=[","{PHONE}=["};
    private static String [] parentFeatures = {"{KFNAME}=[", "{KLNAME}=[", "{KAGE}=["};
    private static String workerFeatur      = "{SALARY}=[";
    private static ArrayList<String> databaseValidation  = new ArrayList <String>();
    private static ArrayList<String> cleanedData         = new ArrayList <String>();

    /**
     * Метод за валидация на всички характеристики на един човек
     */
    public static void validateDatabase(ArrayList<String> realDatabase) {
        for (String man : realDatabase) {
            int x = 0; ArrayList<String> temporaryArrayList = new ArrayList<String>(); String normalString = man;
            do {
                if (man.startsWith(features[x])) {
                    temporaryArrayList.add(man.substring(features[x].length(), man.indexOf("]")));
                    x++;
                }
                man = man.substring(1);
            } while (x != 7);

            if (temporaryArrayList.get(0).equals("M")) {
                x = 0;
                while (man.contains("KFNAME") || man.contains("KLNAME") || man.contains("KAGE")) {
                    if (man.startsWith(parentFeatures[x])) {
                        temporaryArrayList.add(man.substring(parentFeatures[x].length(), man.indexOf("]")));
                        x++;
                        if (x > 2) x = 0;
                    }
                    man = man.substring(1);
                }
            } else if (temporaryArrayList.get(0).equals("W")) {
                x = 0;
                while (x != 1) {
                    if (man.startsWith(workerFeatur)) {
                        temporaryArrayList.add(man.substring(workerFeatur.length(), man.indexOf("]")));
                    }
                    man = man.substring(1);
                }
            }

            if(validateProperties(temporaryArrayList)) {
                databaseValidation.add(normalString);
                String temporaryString = "";
                for (String filteredProperty: temporaryArrayList) {
                    temporaryString += filteredProperty;
                    temporaryString += "|";
                }
                cleanedData.add(temporaryString);
            }
        }
    }

    /**
     * Метод контролиращ валидацията на имената използвайки стойностите на char  в ascii таблицата
     * @return връща true , ако условията са изпълнени
     */
    private static boolean validateProperties(ArrayList<String> person) {
        String Name = person.get(1); String lName = person.get(2);
        int Age = Integer.parseInt(person.get(3)); String Address = person.get(5);

        if (Name.charAt(0) < 'A' || Name.charAt(0) > 'Z' ||
                lName.charAt(0) < 'a' || lName.charAt(0) > 'z' ||
                Address.charAt(0) < 'a' || Address.charAt(0) > 'z' ||
                Age < 0 || Age > 100) {
            return false;
        }
        for (int j = 0; j < lName.length(); j++) {
            if (lName.charAt(j) >= 65 && lName.charAt(j) <= 90 ||
                    lName.charAt(j) >= 97 && lName.charAt(j) <= 122) {
                continue;
            }
            return false;
        }

        for (int i = 0; i < Name.length(); i++) {
            if (Name.charAt(i) >= 65 && Name.charAt(i) <= 90 ||
                    Name.charAt(i) >= 97 && Name.charAt(i) <= 122) {
                continue;
            }
            return false;
        }

        for (int k = 0; k < Address.length(); k++) {
            if (Address.charAt(k) >= 65 && Address.charAt(k) <= 90 ||
                    Address.charAt(k) >= 97 && Address.charAt(k) <= 122) {
                continue;
            }return false;

        }return true;
    }

    public static ArrayList <String> getCleanedData() {
        return cleanedData;
    }

    public static ArrayList <String> getValidatedInformation() {
        return databaseValidation;
    }
}
