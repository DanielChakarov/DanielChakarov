import java.util.ArrayList;
public class Steal {
    int planId=0;
    boolean isTaken=false;

    /**
     * Инициализиране на специфичните действия за steal полетата
     * @param player The current player placed on the tile
     */
    public void steal(Player player){
        checkEvilPlan(player);
        if(activeDeBuff(player)) return;
        if(planId==0 && !player.isPlanActive && !isTaken) setPlanForTile(player);
    }

    /**
     * Проверява дали играчът има  De-Buff активен което предотвратява активирането на полето
     * @param player текущ играч
     * @return връща true ако играчът има  De-Buff, в противен случай връща стойност false
     */
    private boolean activeDeBuff(Player player){
        if(player.trapPosition[4]>0){
            System.out.println("Активен Дебъф. Активиране на Злият план невъзможно!");
            player.trapPosition[4]-=1;
            return true;
        }
        return false;
    }



    private void setPlanForTile(Player player){
        ArrayList<String> data = Reader.reading("Steal",player.planId,3);
        for (String datum : data) System.out.println(datum);
        isTaken = true;
        planId = player.planId;
        player.isPlanActive = true;
    }

    /**
     * Метод за изчистване на полето за да се изпозва при завъртането на играта
     */
    public void clearTile(){
        this.planId = 0;
        isTaken = false;
    }

    /**
     * Активиране на Злия план ако играчът има такъв и е активен
     * @param player Текущият играч позициониран на дадето място
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==3) {
            player.money += 100;
            System.out.println("Зъл план активиран от "+ player.getPlayerIdentification());
            String desc= Reader.reading("Steal",3,3).get(2);
            System.out.println(desc);
        }
    }
}
