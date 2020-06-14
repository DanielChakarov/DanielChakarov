import java.util.Random;

/**
 * @author  Даниел Чакъров
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setBotQuantity();
        playGame(game);
    }

    public static void playGame(Game game) {
        while (true) {
            game.displaceBoard();
            game.makeCycle();
            if (endGame(game)) return;
        }
    }

    public static boolean endGame(Game game) {
        if (game.hasWinner) {
            System.out.println("Победител е " + game.winner.getPlayerIdentification());
            return true;
        }
        return false;
    }

    /**
     * Генериране да случайно число
     */
    public static int randomNumberGenerator(int lowerBound, int upperBound) {
        Random random = new Random();
        int randomNumber;
        if (lowerBound < 0) {
            randomNumber = random.nextInt(Math.abs(lowerBound) + Math.abs(upperBound) + 1);
            return randomNumber - Math.abs(lowerBound);
        } else {
            randomNumber = random.nextInt(upperBound - lowerBound + 1);
            return randomNumber + lowerBound;
        }
    }

    /**
     Проверява дали числото е в допустимите стойности
     */
    public static boolean isNumberValid(int number, int lowerLimit, int upperLimit) {
        return number >= lowerLimit && number <= upperLimit;
    }
    }

