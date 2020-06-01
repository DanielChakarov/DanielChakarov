package Homework8;

import java.util.ArrayList;

/**
 * Клас за управление на заявките
 */
public class InformationProcessing {

    public static ArrayList<String> userRequest = new ArrayList <String>();

    public static void processRequest(String userRequest) {

        if (userRequest.substring(0,6).equals("SELECT")) {
            if (!userRequest.contains("@")) {
                userRequest = userRequest.substring(userRequest.indexOf("SELECT") + 6);
                if (userRequest.startsWith( "{SPECIAL_PROPERTY}")) filtering(userRequest);
                else InformationProcessing.userRequest.add(userRequest);
            } else {
                userRequest = userRequest.substring(6);
                if (userRequest.startsWith("{SPECIAL_PROPERTY}")) {
                    filtering(userRequest);
                } else {
                    InformationProcessing.userRequest.add(userRequest.substring(0, userRequest.indexOf(']') + 1));
                    userRequest = userRequest.substring(userRequest.indexOf("]") + 2);
                    while (userRequest.contains("]")) {
                        if (userRequest.startsWith("@{SPECIAL_PROPERTY}")) {
                            filtering(userRequest);
                            break;
                        }
                        InformationProcessing.userRequest.add(userRequest.substring(1, userRequest.indexOf(']') + 1));
                        userRequest = userRequest.substring(userRequest.indexOf("]") + 1);
                    }
                }
            }
        } else {
            return;
        }
        dataSearching();
    }

    /**
     * Метод филтриращ характеристиките на човека
     * @param request заявката
     */
    private static void filtering(String request) {
        request = request.substring(request.indexOf("::"));
        if         (request.startsWith("{SALARY}=")
                || request.startsWith("{PENSION}=")) {
            userRequest.add(request);
        } else if (request.startsWith("{KID}=")) {

            while   (request.contains("KFNAME")
                    || request.contains("KLNAME")
                    || request.contains("KAGE")) {

                int position;

                if (request.contains("KLNAME")) {
                    position = request.indexOf("{KLNAME}=[");
                    userRequest.add(request.substring(position, request.indexOf("]")+1));
                    request = request.replace(request.substring(position, request.indexOf("]")+1), "");
                }
                if (request.contains("KFNAME")) {
                    position = request.indexOf("{KFNAME}=[");
                    userRequest.add(request.substring(position, request.indexOf("]")+1));
                    request = request.replace(request.substring(position, request.indexOf("]")+1), "");
                }
                if (request.contains("KAGE")) {
                    position = request.indexOf("{KAGE}=[");
                    userRequest.add(request.substring(position, request.indexOf("]")+1));
                    request = request.replace(request.substring(position, request.indexOf("]")+1), "");
                }
                if (request.contains("[@@]")) {
                    request = request.replace("[@@]","");
                }
            }
        }
    }

    /**
     * Метод за търсене в данните
     */
    private static void dataSearching() {
        boolean foundRequest           = true;
        ArrayList <String> dataClean   = Data.getCleanedData();
        ArrayList <String> dataValInfo = Data.getValidatedInformation();
        for (int i = 0; i < dataValInfo.size(); i++) {

            for (String s : userRequest) {
                if (!dataValInfo.get(i).contains(s)) {
                    foundRequest = false;
                    break;
                }
            }
            if (foundRequest) {
                System.out.println(dataClean.get(i));
            }
            foundRequest = true;

        }
        userRequest.clear();
    }
}