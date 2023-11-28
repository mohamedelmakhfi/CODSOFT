import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int NUMBER_OF_ATTEMPTS = 10;
    private static final int MAX_ROUNDS = 5;

    private int rounds;
    private int score;

    private final Random random;
    private final Scanner scanner;

    public NumberGuessingGame() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.rounds = 0;
        this.score = 0;
    }

    public void playGame() {
        do {
            playRound();
            displayScore();
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (playAgain.equals("yes")) {
                rounds++;
            }else {
                return;
            }

        } while (rounds > 0 && rounds < MAX_ROUNDS);

        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }

    private void playRound() {
        int targetNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("\nRound " + (rounds + 1));
        System.out.println("Guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND);

        while (attempts < NUMBER_OF_ATTEMPTS && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! Your guess is correct.");
                guessedCorrectly = true;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            attempts++;
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
        } else {
            score += NUMBER_OF_ATTEMPTS - attempts + 1;
        }
    }

    private void displayScore() {
        System.out.println("Your current score: " + score);
    }

}
