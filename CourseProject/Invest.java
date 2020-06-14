import java.util.ArrayList;
import java.util.Scanner;

public class Invest {
    int cashInvested = 0;

    /**
     * Изнизиализиране на полетата invest  с специфични за тях действия
     * @param player текущото място на което е позициониран играча
     */
    public void investment(Player player) {
        cashInvested = 0;
        checkEvilPlan(player);
        if (!player.bot) playerInvestHandler(player);
        else investBot(player);

    }

    /**
     * Метот чрез коити ботът трябва да реши дали иска да инжестрира, в коя компания и колко
     * @param player текущ Bot
     */
    private void investBot(Player player) {
        if (Main.randomNumberGenerator(0, 1) == 1) {
            ArrayList<ArrayList<String>> companies = new ArrayList<>();
            setRandomCompany(companies);
            for (ArrayList<String> company : companies) {
                int minMoney = Integer.parseInt(company.get(1));
                if (player.money >= minMoney && Main.randomNumberGenerator(1, 10) > 4) {

                    cashInvested = Main.randomNumberGenerator(minMoney, player.money);
                    player.money -= cashInvested;
                    ArrayList<String> tempComp = new ArrayList<>();
                    tempComp.add(Integer.toString(cashInvested));
                    tempComp.add(company.get(2));
                    tempComp.add(company.get(3));
                    tempComp.add(company.get(4));
                    player.investedCompanies.add(tempComp);
                    cashInvested = 0;
                }
            }
        }
    }

    /**
     * Инвестиционната логина на играча
     * @param player текущ играч   (не bot)
     */
    private void playerInvestHandler(Player player) {
        ArrayList<ArrayList<String>> companies = new ArrayList<>();
        setRandomCompany(companies);
        while (true) {
            int id = makeDecision(companies, player);
            if (id == 0) return;
        }

    }

    private int makeDecision(ArrayList<ArrayList<String>> companies, Player player) {
        int lowerLimit = 0;
        int upperLimit = 3;

        Scanner scannerInput= new Scanner(System.in);
        boolean validInput = true;
        int choice = 0;
        while (validInput) {
            displayMenu(companies);

            choice = scannerInput.nextInt();
            validInput = Main.isNumberValid(choice, lowerLimit, upperLimit);
            if (choice == 0) return 0;
            if (validInput) makeInvestments(companies.get(choice - 1), player);
        }
        return choice;
    }

    /**
     * Метод за въвеждане на сумата която игряът иска да инвестира и компаничта в която иска да инвестира
     * @param company  Компанията в която ще инвестира
     * @param player  Текущия играч
     */

    private void makeInvestments(ArrayList<String> company, Player player) {
        while (true) {
            System.out.println("Колко пари искаш да инвестираш?");
            Scanner scanner = new Scanner(System.in);
            cashInvested = scanner.nextInt();
            if (player.money < Integer.parseInt(company.get(1))) return;
            if (cashInvested >= Integer.parseInt(company.get(1)) && cashInvested <= player.money) {
                player.money -= cashInvested;
                ArrayList<String> tempComp = new ArrayList<>();
                tempComp.add(Integer.toString(cashInvested));
                tempComp.add(company.get(2));
                tempComp.add(company.get(3));
                tempComp.add(company.get(4));
                player.investedCompanies.add(tempComp);
                return;
            }
        }
    }


    private void setRandomCompany(ArrayList<ArrayList<String>> companies) {
        for (int i = 0; i < 3; i++) {
            int companyID = Main.randomNumberGenerator(1, 6);
            companies.add(getOptions(companyID));
        }
    }

    /**
     *  Показване на меню с информация която е нужна на играчът да направи инвестиця
     */
    private void displayMenu(ArrayList<ArrayList<String>> companies) {
        System.out.println(" Не ми се инвестира.");
        for (int i = 0; i < companies.size(); i++) {
            String companyName = companies.get(i).get(0);
            int minimalInvestment = Integer.parseInt(companies.get(i).get(1));
            System.out.println(String.format("(%d) %s | минимално: %d | риск/награда : %f", i + 1, companyName, minimalInvestment));
        }
    }

    /**
     * Четене на инф за компанията от текстовия файл
     * @param ID Id на желаната компания
     */
    private ArrayList<String> getOptions(int ID) {
        return Reader.reading("Invest", ID, 5);
    }


    /**
     * Активиране на злия план ако играчът има такъв и е активен
     */
    private void checkEvilPlan(Player player) {
        if (player.isPlanActive && player.planId == 2) {
            System.out.println("Зъл план активиран от " + player.getPlayerIdentification());
            player.money += 100;
            String x = Reader.reading("Steal", 2, 3).get(2);
            System.out.println(x);
        }
    }
}
