import java.util.ArrayList;

public class Chance {

    public void activateChance(Player player) {
        checkEvilPlan(player);
        getPositiveChance(player);
    }

    /**
     * Извеждане на добър  или лош шанс
     * @param player Играчът позициониран на квадратчето
     */
    private void getPositiveChance(Player player) {
        int diceOne = activeDeBuff(player);
        int diceTwo = Main.randomNumberGenerator(1, 100);

        if (diceOne % 2 == 0) positiveChance(diceTwo, player);
        else negativeChance(diceTwo, player);

    }


    private int activeDeBuff(Player player) {
        if (player.trapPosition[5] == 0) return Main.randomNumberGenerator(1, 10);

        player.trapPosition[5] -= 1;
        return 1;
    }

    /**
     Активиране на добрия шанс
     * @param randomNum    Случайно число
     */
    private void positiveChance(int randomNum, Player player) {
        findChance(randomNum, 5, player);
    }

    /**
     Активиране на лошия шанс
     * @param randomNum Случайно число
     */
    private void negativeChance(int randomNum, Player player) {
        findChance(randomNum, 0, player);
    }


    private void checkEvilPlan(Player player) {
        if (player.isPlanActive && player.planId == 1) {
            System.out.println("Злият план е активиран от играч " + player.getPlayerIdentification());
            player.money += 100;
            String x = Reader.reading("Steal", 1, 3).get(2);
            System.out.print(x);
        }
    }

    /**
     * Четене на шансът, който играчът има и прилагането му
     *
     * @param randomNum  Слъчайно число определчщо шансът
     * @param player Настоящия играя
     */
    private void findChance(int randomNum, int shift, Player player) {
        for (int j = 1 + shift; j <= 5 + shift; j++) {
            ArrayList<String> chance = Reader.reading("Chance", j, 5);
            String chanceName = chance.get(0);
            String chanceDesc = chance.get(1);
            int minimal = Integer.parseInt(chance.get(2));
            int maximal = Integer.parseInt(chance.get(3));
            int money = Integer.parseInt(chance.get(4));

            if (randomNum >= minimal && randomNum <= maximal) {
                player.money += money;
                System.out.println(chanceName);
                System.out.println(chanceDesc);
                return;
            }
        }
    }
}
