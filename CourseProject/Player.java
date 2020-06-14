import java.util.ArrayList;
public class Player {
    int money = 1000;
    int id;
    int gains = 0;
    int position = 0;
    int cycle = 1;
    boolean isPlanActive;
    int[] trapPosition = new int[6];
    boolean bot;
    int planId = 0;
    ArrayList<ArrayList<String>> investedCompanies = new ArrayList<>();

    /**
     * Конструктор за играчите
     * @param id   идентификацинен номер на играча
     * @param isBot връща true ако играчът  Bot, в противен случай връща false
     */
    public Player(int id, boolean isBot) {
        this.id = id;
        this.bot = isBot;
    }

    /**
     * Смяна на позицията на играча
     */
    public void makeTurn() {
        int positionToMove = Main.randomNumberGenerator(1, 2);
        position += positionToMove;
    }


    public String getPlayerType() {
        return (bot) ? "Бот" : "Играч";
    }

    /**
     *Вземане видът на играча и неговото id
     * @return String съдържащ вида и id на играча
     */
    public String getPlayerIdentification() {
        return String.format("%s %d", getPlayerType(), id);
    }


    /**
     * Изчисляване на резултата от направените от играча инвестиции
     */
    private void calculatePlayerInvest() {
        ArrayList<String> temporary;
        for (ArrayList<String> investedCompany : investedCompanies) {
            temporary        = investedCompany;
            int investedMoney = Integer.parseInt(temporary.get(0));
            float returnMultiplier = Float.parseFloat(temporary.get(1));
            int minimalRange = Integer.parseInt(temporary.get(2));
            int maximalRange     = Integer.parseInt(temporary.get(3));
            int randomGenerator = Main.randomNumberGenerator(minimalRange, maximalRange);

            System.out.println(String.format("%d %d %d %d", investedMoney, minimalRange, maximalRange, randomGenerator));
            if (randomGenerator >= 0) gains += investedMoney * returnMultiplier + investedMoney;
        }
        investedCompanies = new ArrayList<>();
    }


    /**
     * Задаване на случаен зъл план
     */
    public void assignEvilPlan() {
        planId = Main.randomNumberGenerator(1, 3);
        isPlanActive = false;
    }
}
