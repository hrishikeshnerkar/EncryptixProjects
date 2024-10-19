import java.util.Random;
import java.util.Scanner;

public class Number_Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            totalRounds++;
            int attempts = 0;
            int maxAttempts = 7;
            int min = 1;
            int max = 100;
            int randomNumber = random.nextInt(max - min + 1) + min;

            System.out.println("\nRound " + totalRounds + ": Guess the number between " + min + " and " + max + ".");
            boolean hasGuessedCorrectly = false;

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number!");
                    totalScore += (maxAttempts - attempts + 1);
                    hasGuessedCorrectly = true;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was " + randomNumber + ".");
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");

            System.out.println("Your current score: " + totalScore + " points.");
        }

        System.out.println("\nGame over! Total rounds played: " + totalRounds + ". Final score: " + totalScore + " points.");
        scanner.close();
    }
}
