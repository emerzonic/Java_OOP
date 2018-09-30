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
        System.out.println(Color.green + "YOU GOT A NEW WORD!" + Color.reset);
        this.newWord = new Word(randomWord);
        ((Word) this.newWord).splitLetters()
                .setAttempts()
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
            System.out.println(Color.red + "You have already guessed " + guess + ". Try again" + Color.reset);
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
            System.out.println(Color.red + "That's not a valid guess" + Color.reset);
            this.takeUserGuess();
        }
    }

    //Checks if all the letters the word have been guess and also the player fail attempts remaining.
    private void checkWordStatus() {
        if (((Word) this.newWord).isStatus()) {
            this.score++;
            System.out.println("Your score is " + this.score);
            this.guessedLetters.clear();
            this.generateWord();
        } else {
            if (((Word) this.newWord).getAttempts() <= 0) {
                System.out.println(Color.red + "G A M E  O V E R !" + Color.reset);
                System.out.println("The word was: " + ((Word) this.newWord).getWord());
                this.resetGame();
            }
            this.takeUserGuess();
        }
    }


    //Asks the user to continue playing or not after game is over
    private void resetGame() {
        System.out.println("WOULD YOU LIKE TO PLAY AGAIN? y/n");
        String userInput = scanner.nextLine();
        String userResponse = userInput.toLowerCase();
        if (userResponse.equals("y")) {
            this.guessedLetters.clear();
            this.generateWord();
        } else {
            System.out.println("THANKS FOR PLAYING!");
            System.exit(0);
        }
    }
}
