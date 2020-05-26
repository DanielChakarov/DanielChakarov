import javafx.geometry.Pos;

public class Position {
    private int height;
    private int width;


public void Adding(Position position){
    this.height += position.height;
    this.width  += position.width;
}

public boolean boardBorders(int boardHeight, int boardWidth){
    return this.getWidth() >= boardWidth ||
            this.getWidth() < 0 ||
            this.getHeight()>= boardHeight ||
            this.getHeight() < 0 ;
}

public int getHeight(){
    return this.height;
}

    public Position(int height, int width){
        this.height = height;
        this.width  = width;
    }
    public int getWidth(){
        return this.width;
    }

    public static boolean Equal(Position one, Position two){
        return one.height == two.height && one.width == two.width;
    }

public static Position Adding( Position one, Position two){
    return new Position(one.height + two.height ,one.width + two.width);
}

}
