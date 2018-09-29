package com.emerson;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private Object newWord;
    private int score;
    private ArrayList<String> guessedLetters;
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        this.score = 0;
        this.guessedLetters = new ArrayList<>();
    }


    //generates random word from wordsBank class
    public void generateWord() {
        String randomWord = WordList.getRandomWord();
//        System.out.println(randomWord);//for testing only
        System.out.println(Color.ANSI_GREEN + "YOU GOT A NEW WORD!" + Color.ANSI_RESET);
        Word newWord = new Word(randomWord);
        this.newWord = newWord;
        ((Word) this.newWord).splitLetters()
                .generateAttempts()
                .displayWord();
        this.takeUserGuess();
    }

    //This method takes the user guess and controls the game
    private void takeUserGuess() {
        System.out.println("Guess a letter: ");
        String userInput = scanner.nextLine();
        this.validateUserInput(userInput);
        String guess = userInput.toLowerCase();
        if (this.guessedLetters.contains(guess)) {
            System.out.println(Color.ANSI_RED + "You have already guessed " + guess + ". Try again" + Color.ANSI_RESET);
            System.out.println("Letters already guessed:" + this.guessedLetters.toString());
            this.takeUserGuess();
        }
        this.guessedLetters.add(guess);
        ((Word) this.newWord).takeChar(guess)
                .trackStatus()
                .displayWord();
        this.checkWordStatus();
    }


    //validates that the user only enter a letter (A-Z)
    private void validateUserInput(String userInput) {
        if (!userInput.matches("[A-Za-z]")) {
            System.out.println(Color.ANSI_RED + "That's not a valid guess" + Color.ANSI_RESET);
            this.takeUserGuess();
        }
    }

    //Checks if all the letters the word have been guess and also the player fail attempts remaining.
    private void checkWordStatus() {
        if (((Word) this.newWord).status) {
            this.score++;
            System.out.println("Your score is " + this.score);
            this.guessedLetters.clear();
            this.generateWord();
        } else {
            if (((Word) this.newWord).attempts <= 0) {
                System.out.println(Color.ANSI_RED + "G A M E  O V E R !" + Color.ANSI_RESET);
                System.out.println("The word was: " + ((Word) this.newWord).word);
                this.resetGame();
            }
            this.takeUserGuess();
        }
    }


    //Asks the user to continue playing or not after game is over
    private void resetGame() {
        System.out.println("WOULD YOU LIKE TO PLAY AGAIN? y/n");
        String userResponse = scanner.nextLine();
        userResponse.toLowerCase();
        if (userResponse.equals("y")) {
            this.guessedLetters.clear();
            this.generateWord();
        } else {
            System.out.println("THANKS FOR PLAYING!");
            System.exit(0);
        }
    }
}
