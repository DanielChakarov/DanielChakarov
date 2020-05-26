
public class GameBoard {

    boolean start;
    boolean finish;
    boolean hasBomb;
    boolean isVisited;
    boolean isMyLocation;
    boolean isChecked;

    public GameBoard(boolean start, boolean finish, boolean hasBomb){

        this.start        = start;
        this.finish       = finish;
        this.hasBomb      = hasBomb;
        this.isVisited    = false;
        this.isMyLocation = start;
        this.isChecked    = false;

    }

    /**
     *Метод реализиращ означенията на полетата върху игралното поле
     */
    public char getDefinition(){

        if (start)        return 'S';
        if (finish)       return 'F';
        if (isChecked){
            if (hasBomb)  return 'Y';
            else return 'N';
        }
        if (isMyLocation) return 'O';
                          return 'X';
    }


    public  void Visited(){
        this.isMyLocation = false;
        this.hasBomb      = false;
        this.isChecked    = true;
    }


    public void playerLocation(){
        this.isMyLocation = true;
        this.hasBomb      = false;
        this.isChecked    = false;
    }

    public void bombDefusing(){
        this.hasBomb      = false;
        this.isMyLocation = true;
        this.isChecked    = false;
    }

}


