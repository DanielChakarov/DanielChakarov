
import javax.swing.*;
import java.text.Format;
import java.util.Scanner;

public class UnicornChess {

    static final int GREATEST_INPUT = 6;
    static final int LOWEST_INPUT = 1;

    static final int PLAYER_WHITE_ID = 1;
    static final int PLAYER_BLACK_ID = 2;


    public static void main(String[] args) {
        Scanner gameInputScanner = new Scanner(System.in);

        boolean isGameOver = false;
        boolean isGameRunning = true;
        int activePlayerId = 1;

        int[][] gameBoard = new int [GREATEST_INPUT][GREATEST_INPUT];

        System.out.println(" A   B   C   D    E   F");
        System.out.println("wDw  wM  wK  wQ  wD  wDw");
        renderGameBoard(gameBoard);
        System.out.println("bDw  bD  bQ  bK  bM  bDw");
        System.out.println();

        while (isGameRunning){
            System.out.println("Input row");
            int pieceRowPositionInput = gameInputScanner.nextInt();

            if(isGameBoardInputInvalid(pieceRowPositionInput)) {
                System.out.println("Incorrect input please try again with data between 1 and 6");
                continue;
            }

            System.out.println("Input col:");
            int pieceColPositionInput = gameInputScanner.nextInt();

            if(isGameBoardInputInvalid(pieceColPositionInput)) {

                System.out.println("Incorrect input please try again with between 1 and 6");
                continue;
            }
        }


    }
    public static int getActivePlayerId(int currentId) {

        return (currentId == PLAYER_BLACK_ID) ? PLAYER_WHITE_ID :
                PLAYER_BLACK_ID;
    }

    public static boolean isGameBoardInputValid(int inputData) {

        return  inputData >= LOWEST_INPUT &&
                inputData <= GREATEST_INPUT;
    }
    public static boolean isGameBoardInputInvalid(int inputData) {

        return inputData < LOWEST_INPUT ||
                inputData > GREATEST_INPUT;
    }


   public static int getGameBoardPlayerId(int[][] gameBoard, int row, int col) {

       return gameBoard[row][col];
   }

    public static String getGameBoardMark(int[][] gameBoard, int row, int col) {

        int playerId = getGameBoardPlayerId(gameBoard, row, col);
        if(playerId == PLAYER_BLACK_ID    ) return "X ";
        if(playerId == PLAYER_WHITE_ID  ) return "& ";
        return " X  ";
    }
    public static void renderGameBoard(int[][] gameBoard) {

        for (int row = 0; row < GREATEST_INPUT; row++) {

            System.out.println("=======================");
            for (int col = 0; col < GREATEST_INPUT; col++) {
                System.out.print(getGameBoardMark(gameBoard, row, col));
            }
            {
                System.out.println(" " + (char) (row + 'A'));

            }

        }
    }


}

