import java.util.ArrayList;
import java.util.Scanner;

public class Trap {
    int playerLocation = 0;
    int trapID;
    boolean isActive= false;
    int[]trapPrice  = new int[6];

    /**
     * Задаване на стойностите на всички капани
     */
    public Trap(){
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> chance = Reader.reading("Trap", i, 4);
            int value = Integer.parseInt(chance.get(3));
            trapPrice[i] = value;
        }
    }

    /**
     * Инициализиране на специфичните действия за полетата с капани
     * @param player текущият играч
     */
    public void activate(Player player){
        if(isActive) setOff(player);
        else placeTrap(player);
    }

    /**
     * Задействане на капанът върху това квадратче
     * @param player Текущичт играч позициониран върху полето
     */
    private void setOff(Player player){
        int dice = Main.randomNumberGenerator(1,10);

        if(player.id == playerLocation && dice%3==0) {
            System.out.println("Вие попаднахте в свой капан, но избегнахте последствията!");
            return;
        }
        System.out.println("Капан бе задействан от "+player.getPlayerIdentification());
        playerLocation = 0;
        isActive = false;
        player.trapPosition[trapID]++;
        trapID = 0;
    }


    /**
     * Позволява поставянето на капан според типа на текущите играчи
     * @param player Текущата позиция на играча
     */
    private void placeTrap(Player player){
        if(player.trapPosition[3]>0) return;

        if(player.bot) botPlacesTrap(player);
        else playerPlacesTrap(player);
    }

    /**
     * Подканва играча да избере дали да постави капан или не
     * @param player Текущата позиция на играча
     */
    private void botPlacesTrap(Player player){

        if (Main.randomNumberGenerator(0,1)==1){
            int currentTrap = Main.randomNumberGenerator(1,5);
            selectTrap(player,currentTrap);
            return;
        }
        System.out.println("Капан не е поставен от bot " + player.id);
    }

    /**
     Проверява дали ботът може да постави капан, aко може  той го поставя, в противен случай не го прави
     * @param player Текущата позиция на играча
     * @param selectedTrap Избраното от бота място да позиционира капана
     */
    private void selectTrap(Player player,int selectedTrap){
        if(player.money <trapPrice[selectedTrap]) return;
        trapID = selectedTrap;
        player.money -= trapPrice[trapID];
        playerLocation = player.id;
        isActive       = true;
        System.out.println("Капан е поставен от Бот " + player.id);
    }

    private void playerPlacesTrap(Player player){
        int trapChoice = makeChoice(0,5);

        if (trapChoice > 0 && player.money >= trapPrice[trapChoice]){
            player.money -=trapPrice[trapChoice];
            playerLocation =player.id;
            isActive=true;
            System.out.println("Капан е поставен от "+player.getPlayerIdentification());
        }
        else System.out.println("Капан не е поставен от "+player.getPlayerIdentification());
    }

    /**
     * Отпечатва менюто, съдържащо цялата информация, необходима на играча да вземе решение дали да постави капан или не
     */
    private void printMenu() {
        System.out.println("Не, благодаря, не вярвам в злото");
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> chance = Reader.reading("Trap", i, 4);
            String trapName = chance.get(0);
            int cost = Integer.parseInt(chance.get(3));
            System.out.println(String.format("(%d) : %s (%d)",i,trapName,cost));
        }
    }

    /**
     \
     * @param lowerNumber Най-малкото число от менюто
     * @param upperNumber Най-голямата стойност от менюто
     * @return The options selected by the player
     */
    public int makeChoice(int lowerNumber, int upperNumber){
        Scanner scannerInput = new Scanner(System.in);
        int option = 0;
        boolean correctInput = false;
        while (!correctInput){
            printMenu();
            option = scannerInput.nextInt();
            correctInput= Main.isNumberValid(option,lowerNumber,upperNumber);
        }
        return option;
    }
}
