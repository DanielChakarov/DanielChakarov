import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Scanner inputScanner = new Scanner(System.in);
    int gameCycle = 0;
    Player winner;
    boolean hasWinner = false;
    ArrayList<Player> players = new ArrayList<>();
    Tile[] gameBoard = new Tile[20];

    /**
     * Създаване на игралното поле
     */
    public Game() {
        boardInitialization();
    }

    /**
     * Задаване на началните позиции на квадратчетата
     */
    private void boardInitialization() {
        int id = 1, i;
        gameBoard[0] = new Tile(0, 0);
        for (i = 1; i <= 3; i++)
            gameBoard[i] = new Tile(id, i);
        id = 2;
        for ( i = 1; i <= 6; i++)
            gameBoard[i] = new Tile(id, i);
        id = 3;
        for (i = 1; i <= 9; i++)
            gameBoard[i] = new Tile(id, i);
        id = 4;
        for (i = 1; i <= 12; i++)
            gameBoard[i] = new Tile(id, i);
        id = 5;
        for (i = 1; i <= 19; i++)
            gameBoard[i] = new Tile(id, i);
    }

    /**
     * Избиране на броя на играчите, с които ще се съревноваваш и инициализаиране на масива от играчи
     */
    public void setBotQuantity() {
        System.out.print("Въведи броя на играчите, с които ще играеш: ");
        int amount = inputScanner.nextInt();
        initPlayers(amount);
    }

    /**
     * Метод за инициализиране на позициите и грачите и техните номера
     */
    private void initPlayers(int amount) {
        players.add(new Player(1, false));
        for (int i = 0; i < amount; i++) players.add(new Player(i + 1, true));
    }

    /**
     * Метод за обикола на игралното поле
     */
    public void makeCycle() {
        giveEvilPlans();
        gameCycle++;
        boolean isCycleOver = false;
        while (!isCycleOver) {
            printBoard();

            isCycleOver = playerCycle();
        }

        sortingPlayers();
        resetBuffs();
    }


    private void resetBuffs() {
        for (Player player : players)
            Arrays.fill(player.trapPosition, 0);
    }



    /**
     * Задаване на зъл план на всеки играч
     */
    private void giveEvilPlans() {
        for (Player player : players) player.assignEvilPlan();
    }


    public boolean playerCycle() {
        int playersCycle = 0;
        removePlayersWithoutMoney();
        if (hasWinner()) return true;

        for (Player currentPlayer : players) {
            if (currentPlayer.cycle == gameCycle) {
                currentPlayer.makeTurn();
                if (currentPlayer.position > 19) {
                    currentPlayer.position = 0;
                    currentPlayer.cycle++;
                    System.out.println(currentPlayer.getPlayerIdentification() + " се премести на позиция " + currentPlayer.position);
                    continue;
                }
                System.out.println(currentPlayer.getPlayerIdentification() + " се премести на позиция " + currentPlayer.position);
            } else playersCycle++;
        }
        return playersCycle == players.size();
    }

    /**
     * Метод за премахване на всички фалирали играчи
     */
    private void removePlayersWithoutMoney() {
        ArrayList <Player> removed = new ArrayList<>();
        for (Player currentPlayer : players) {
            if (checkPlayerBill(currentPlayer)) removed.add(currentPlayer);
        }

        for (Player player : removed) players.remove(player);
    }

    /**
     * Проверяване дали има печеливш играч
     * @return  връща стойност true, ако има победител в противен случай връща стойност false
     */
    private boolean hasWinner() {
        if (players.size() == 1) {
            hasWinner = true;
            winner = players.get(0);
            return true;
        }
        return false;
    }

    /**
     *Метод проверяващ дали някой от играчите не е с отрицателана или равна на 0 сметка
     * @return връща true ако играчът има отрицателна или = на 0 сметка в противен случай връща стойност false
     */
    private boolean checkPlayerBill(Player currentPlayer) {
        return currentPlayer.money <=0;
    }

    /**
     * Сортиране на играчите в намаляващ ред според  техните пари.
     */
    public void sortingPlayers() {
        int n = players.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (players.get(j).money < players.get(j + 1).money) {
                    Player temporaryPlayer = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temporaryPlayer);
                }
    }

    /**
     * Разместване на квадратчетата в игралното поле
     */
    public void displaceBoard() {
        int newPosition;
        for (int i = 1; i < gameBoard.length; i++) {
            newPosition = Main.randomNumberGenerator(1, 19);
            Tile.switchTilePosition(gameBoard, i, newPosition);

        }
    }


    /**
     * Изпечатване на редовете
     **/
    private void printTiles(int begin, int end, boolean back) {
        System.out.print(String.format("|%s|%s|%s|"));
    }


    private String[] getRoll(int begin, int end, boolean reverse) {
        String[] tiles = new String[end - begin + 1];
        for (int i = 0; i < tiles.length; i++)
            tiles[i] = (reverse) ? gameBoard[end - i].tileSymbol : gameBoard[begin + i].tileSymbol;
        return tiles;
    }


    private void printNumbers(int begin, int end, int offset, boolean reverse) {
        System.out.println(String.format("|%s|%s|%s|"));
    }

    private String[] array(int firstPosition, int lastPosition, boolean reverse) {
        String[] newArray = new String[lastPosition - firstPosition + 1];
        for (int i = 0; i < newArray.length; i++)
            newArray[i] = (reverse) ? convertNumIntoString(lastPosition - i) : convertNumIntoString(firstPosition + i);

        return newArray;
    }

    /**
     *Преобразуване на int в string стойности
     * @param num Числото коети ще се конвертира
     * @return връща string стойността
     */
    private String convertNumIntoString(int num) {
        return (num > 9) ? String.valueOf(num) : (num) + " ";
    }

    /**
     * принтира игралното поле
     */
    public void printBoard() {
        int shift = 13;
        printLine(60);
        printUpBoard(shift);
        printLowBoard();
        printLine(60);
    }

    /**
     * Извеждане на горната част на игралното поле
     */
    private void printUpBoard(int offset) {
        printNumbers(10, 17, offset, false);
        printTiles(10, 17, false);
    }



    private void printLowBoard(int offset) {
        printTiles(0, 7, true);
        printNumbers(0, 7, offset, true);
    }



    public void printLine(int repetitions) {
        System.out.println("=".repeat(repetitions));
    }
}
