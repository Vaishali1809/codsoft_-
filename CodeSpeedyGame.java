import java.util.Random;
import java.util.Scanner;

public class CodeSpeedyGame {

    public static void main(String[] args) {
        final int MIN_NUMBER = 1;
        final int MAX_NUMBER = 100;
        final int MAX_TURNS = 10;
        final int MAX_SCORE = 100;

        Random random = new Random();
        int rightGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int turnsTaken = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ". You will have " + MAX_TURNS + " turns!");
        System.out.println("Best of luck!");

        boolean win = false;
        while (!win && turnsTaken < MAX_TURNS) {
            int guess = scanner.nextInt();
            turnsTaken++;

            if (guess == rightGuess) {
                win = true;
            } else if (turnsTaken == MAX_TURNS) {
                System.out.println("You lose! The right answer was: " + rightGuess);
            } else if (guess < rightGuess) {
                System.out.println("Your guess is lower than the right guess! Turns left: " + (MAX_TURNS - turnsTaken));
            } else {
                System.out.println("Your guess is higher than the right guess! Turns left: " + (MAX_TURNS - turnsTaken));
            }
        }

        if (win) {
            int score = MAX_SCORE - (turnsTaken - 1) * 10;
            System.out.println("You win!");
            System.out.println("The number was " + rightGuess);
            System.out.println("You used " + turnsTaken + " turns to guess the right number");
            System.out.println("Your score is " + score + " out of " + MAX_SCORE);
        }

        scanner.close(); // Closing the scanner
    }
}
