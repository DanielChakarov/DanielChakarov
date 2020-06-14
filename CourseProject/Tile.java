public class Tile {
    Invest invest = null;
    Steal steal = null;
    Chance chance = null;
    Trap trap = null;

    int id;
    int position;
    String tileSymbol;

    /**
     * Конструктор за полето. Създаване на специфични за полето правила
     */
    public Tile(int id, int position) {
        this.id = id;
        this.position = position;
        createTile();
    }

    /**
     * Активиране на полето и неговият ефект когато играч попадне върху него
     * @param player Текущият играч постъпил върху полето
     */
    public void setTile(Player player) {
        switch (id) {
            case 0:
                player.calculateRemainingMoney();break;
            case 1:
                invest.investment(player);break;
            case 2:
                steal.steal(player);break;
            case 3:
                chance.activateChance(player);break;
            case 4:
                startParty(player);break;
            case 5:
                trap.activate(player);break;
        }
    }

    /**
     * Премахване на парите когато играчът попадне върху поле party
     *
     * @param player Текущото място на играча върху полето
     */
    private void startParty(Player player) {
        System.out.println("Парто е започнато от " + player.getPlayerIdentification());
        player.money = 25;
    }

    private void createTile() {
        switch (id) {
            case 0:
                tileSymbol = "S "; break;
            case 1:
                invest = new Invest();
                tileSymbol = "Iv"; break;
            case 2:
                steal = new Steal();
                tileSymbol = "St"; break;
            case 3:
                chance = new Chance();
                tileSymbol = "Ch "; break;
            case 4:
                tileSymbol = "Pl "; break;
            case 5:
                trap = new Trap();
                tileSymbol = "Tr "; break;
        }
    }

    /**
     * Размяна на две позиции (полета)
     * @param gameBoard       Масив садържащ всички полета
     * @param i               Текуща позиция на полето
     * @param newPosition     Нова позиция на кполето
     */
    public static void switchTilePosition(Tile[] gameBoard, int i, int newPosition) {

        Tile tempTile = gameBoard[newPosition];
        gameBoard[newPosition] = gameBoard[i];
        gameBoard[newPosition].position = newPosition;
        gameBoard[i] = tempTile;
        gameBoard[i].position = i;

    }
}
