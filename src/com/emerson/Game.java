package com.emerson;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    Object newWord;
    int score;
    ArrayList<String> guessedLetters;
    Scanner scanner = new Scanner(System.in);


    public Game() {
        this.score = 0;
        this.guessedLetters = new ArrayList<>();
    }


    // generate random word from wordsBank class
    public void generateWord() {
        String randomWord = WordList.getRandomWord();
        System.out.println(randomWord);
        System.out.println("YOU GOT A NEW WORD!");
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
            System.out.println("You have already guessed " + guess + ". Try again");
            System.out.println("Letters already guessed:" + this.guessedLetters.toString());
            this.takeUserGuess();
        }
        this.guessedLetters.add(guess);
        ((Word) this.newWord).takeChar(guess)
                .trackStatus()
                .displayWord();
        this.checkWordStatus();
    }


    //validate that the user only enter a letter (A-Z)
    private void validateUserInput(String userInput) {
        if (!userInput.matches("[A-Za-z]")) {
            System.out.println("That's not a valid guess");
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
                System.out.println("G A M E  O V E R !");
                System.out.println("The word was " + ((Word) this.newWord).word);
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
