import java.util.Random;
import java.util.Scanner;

/**
 * Клас рализираш логиката на нашата игра
 */
public class Game {
    private int boardHeight;
    private int boardWidth;
    private int mines;
    private int tris;
    private int disposal;
    boolean playerIsDead = false;
    boolean isFinishReached;


    private final String[] optionMenu = new String[]{
            "1. Проба за мина",
            "2. Обезвреждане на мина",
            "3  (пре)Мини"
    };
    private GameBoard[][] bombDisposition;

    private final String[] positionMessage = new String[]{
            "Въведете координати ",
            "Ред на въвеждане -  височина(вертикал):широчина(хоризонтал)",
    };

    Scanner scannerInput = new Scanner(System.in);
    Position playerLocation;

    Position[] motions = new Position[]{
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 0),
            new Position(0, -1)
    };

    public Game() {
        fillValues();
    }

    private void fillValues() {
        Reader firstReading = new Reader("configurations.txt");
        this.tris = firstReading.getValue("probes_number");
        this.disposal = firstReading.getValue("disposal_number");

        Reader secondReading = new Reader("enemy_teritory.txt");
        this.boardWidth = secondReading.getValue("width");
        this.boardHeight = secondReading.getValue("height");
        this.mines = secondReading.getValue("mines");
    }

    private boolean validateConfigValues() {
        return tris >= 0 && disposal >= 0;
    }

    private boolean validateFieldValues() {
        return (boardWidth >= 4 && boardHeight >= 4) && (mines >= 0 && mines <= (boardHeight * boardWidth) - 2);
    }

    public boolean isDataValid() {
        return validateConfigValues() && validateFieldValues();
    }

    public boolean isDataInvalid() {
        return !isDataValid();
    }

    public void mineField() {
        bombDisposition = new GameBoard[boardHeight][boardWidth];
        markStartAndFinishPos();
        markMines();
        fillMine();
    }

    private void markStartAndFinishPos() {
        Random randomNum = new Random();

        int side = randomNum.nextInt(4);
        int height1 = 0;
        int width1 = 0;

        switch (side) {
            case 0:
                height1 = boardHeight - 1;
                width1 = randomNum.nextInt(boardWidth - 2) + 1;
                break;
            case 1:
                width1 = boardWidth - 1;
                height1 = randomNum.nextInt(boardHeight - 2) + 1;
                break;
            case 2:
                height1 = 0;
                width1 = randomNum.nextInt(boardWidth - 2) + 1;
                break;
            case 3:
                width1 = 0;
                height1 = randomNum.nextInt(boardHeight - 2) + 1;
                break;
        }

        int height2 = 0;
        int width2 = 0;

        switch (side) {
            case 0:
                height2 = boardHeight - 1;
                width2 = randomNum.nextInt(boardWidth - 2) + 1;
                break;
            case 1:
                width2 = 0;
                height2 = randomNum.nextInt(boardHeight - 2) + 1;
                break;
            case 2:
                width2 = boardWidth - 1;
                height2 = randomNum.nextInt(boardHeight - 2) + 1;
                break;
            case 3:
                height2 = 0;
                width2 = randomNum.nextInt(boardWidth - 2) + 1;
                break;
        }

        bombDisposition[height1][width1] = new GameBoard(true, false, true);
        playerLocation = new Position(height1, width1);
        bombDisposition[height2][width2] = new GameBoard(false, true, false);
    }

    public void fillMine() {
        for (int i = 0; i < boardHeight; i++) {
            System.out.print(" " + i);
            for (int j = 0; j < boardWidth; j++)
                System.out.print(bombDisposition[i][j].getDefinition() + " ");

            System.out.println();
        }
        System.out.println(" ");
        for (int i = 0 ; i < boardWidth; i++) System.out.print(" "+i);
        System.out.println();
    }

    private void markMines() {
        Random randomGenerator = new Random();
        while (mines > 0) {
            int height = randomGenerator.nextInt(boardHeight);
            int width = randomGenerator.nextInt(boardWidth);
            if (bombDisposition[height][width] == null) {
                bombDisposition[height][width] = new GameBoard(false, false, true);
                mines--;
            }
        }
    }

public void mainMenu() {
 Position position = getLocation();

 int selection = getSelection(optionMenu.length);
 switch (selection){
     case 1: makeTry(position);
        break;
     case 2: mineDefuse(position);
        break;
     case 3: changeLocation(position);
        break;
        }
    }

    public void  printInfomation(){
        System.out.printf("Брой проби:%d ",this.tris);
        System.out.printf("Брой обезвреждания:%d",this.disposal);
    }

    private void printLines(String[] lines){
        for (String line : lines)
            System.out.println(line);
    }

    private int getSelection(int size){
        boolean isValid;
        int value;
        do {
            printLines(optionMenu);
            value = scannerInput.nextInt();
            isValid = value>= 1 && value <=size;
        }while (!isValid);
            return value;
    }

    private Position getLocation(){
        int newHeigth;
        int newWidth;
        Position takePosition;
        do {
            printLines(positionMessage);
            String line = scannerInput.next();
            String [] parts = line.split(":");
            newHeigth = Integer.parseInt(parts[0]);
            newWidth  = Integer.parseInt(parts[1]);
            takePosition = new Position (newHeigth,newWidth);
        }while (takePosition.boardBorders(boardHeight,boardWidth) && getId(takePosition)!=-1);
        return takePosition;
    }

    private void makeTry (Position position){
        if (this.tris >0){
            this.tris--;
            if (this.playerLocation.getHeight()!= position.getHeight()){
                lookVertical(position);
                return;
            }
            if (this.playerLocation.getWidth()!= position.getWidth())
                lookHorizontal(position);
        }
    }

        private void lookHorizontal(Position position){
        int x = position.getWidth() - this.playerLocation.getWidth();
        for (int i = 0 ; i <= 1; i++){
            for (int j = this.playerLocation.getHeight()-1; j<=this.playerLocation.getHeight()+1; j++){
                if (!new Position(j, position.getWidth()+ i*x).boardBorders(boardHeight,boardWidth)){
                    bombDisposition[j][position.getWidth()+i * x].isChecked= true;
                }
            }
        }
    }

        private void lookVertical(Position position){
        int x = position.getHeight()-this.playerLocation.getHeight();
        for (int i = 0 ; i<=1; i++){
            for (int j = this.playerLocation.getWidth()-1;j<=this.playerLocation.getWidth()+ 1; j++){
                if (!new Position(j,position.getHeight() + i * x).boardBorders(boardHeight,boardWidth)){
                    bombDisposition[position.getHeight()+ i * x ][j]. isChecked = true;
                }
            }
        }
    }


        private void mineDefuse(Position position){
        if (bombDisposition[position.getHeight()][position.getWidth()].hasBomb && this.disposal>0){
            bombDisposition[this.playerLocation.getHeight()][this.playerLocation.getWidth()].Visited();
            this.playerLocation = position;
            this.disposal--;
            }else this.playerIsDead = true;
        }

        public void changeLocation(Position anotherPosition){
        int Id = getId(anotherPosition);

        bombDisposition[playerLocation.getHeight()][playerLocation.getWidth()].Visited();
        this.playerLocation.Adding(motions[Id]);
        if (bombDisposition[anotherPosition.getHeight()][anotherPosition.getWidth()].hasBomb)this.playerIsDead= true;
        if (bombDisposition[anotherPosition.getHeight()][anotherPosition.getWidth()].finish)this.isFinishReached = true;
         bombDisposition[anotherPosition.getHeight()][anotherPosition.getWidth()].playerLocation();
        }

        public boolean isDead(){
        return playerIsDead;
        }

        public boolean isFinished(){
        return isFinishReached;
        }

        public int getId(Position newPosition){
        for (int i = 0 ;i<motions.length;i++){
            if (Position.Equal(Position.Adding(this.playerLocation,motions[i]),newPosition))
                return i;
        }
                return -1;
    }
}