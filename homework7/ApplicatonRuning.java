/**
 * @author  Данеил Чакъров
 * Клас реализираш стартирането на нашата игра
 */
public class ApplicatonRuning {
    public static void main(String[] args) {
        Game application = new Game();
        if (application.isDataInvalid())return;
        application.mineField();
        while (!application.playerIsDead){
            application.fillMine();
            application.printInfomation();
            application.mainMenu();
            if (application.isFinishReached){
                System.out.println("Поздравления ти си победител!");
            return;
            }
        }

        System.out.println("Ти загуби!");
    }
}
