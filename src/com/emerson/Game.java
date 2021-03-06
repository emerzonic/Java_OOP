package com.emerson;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Word newWord;
    private int score;
    private ArrayList<String> guessedLetters;
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        this.score = 0;
        this.guessedLetters = new ArrayList<>();
    }


    //Generates random word from wordsBank class
    void generateWord() {
        String randomWord = WordList.getRandomWord();
//        System.out.println(randomWord);//for testing only
        System.out.println(Color.green + "YOU GOT A NEW WORD!" + Color.reset);
        this.newWord = new Word(randomWord);
        this.newWord.splitLetters()
                .setAttempts()
                .displayWord();
        this.takeUserGuess();
    }

    private void takeUserGuess() {
        System.out.println("Guess a letter: ");
        String userInput = scanner.nextLine();
        this.validateUserInput(userInput);
        String guess = userInput.toLowerCase();
        if (this.guessedLetters.contains(guess)) {
            System.out.println(Color.red + "You have already guessed " + guess + ". Try again" + Color.reset+"\n"+
                                        "Letters already guessed: " + String.join(", ", guessedLetters));
            this.takeUserGuess();
        }
        this.guessedLetters.add(guess);
        this.newWord.takeChar(guess)
                .trackStatus()
                .displayWord();
        this.checkWordStatus();
    }

    private void validateUserInput(String userInput) {
        if (!userInput.matches("[A-Za-z]")) {
            System.out.println(Color.red + "That's not a valid guess" + Color.reset);
            this.takeUserGuess();
        }
    }

    private void checkWordStatus() {
        if (this.newWord.isStatus()) {
            this.score++;
            System.out.println("Your score is " + this.score);
            this.guessedLetters.clear();
            this.generateWord();
        } else {
            if (this.newWord.getAttempts() <= 0) {
                System.out.println(Color.red + "G A M E  O V E R !" + Color.reset+ "\n"+
                                        "The word was: " + this.newWord.getWord());
                this.resetGame();
            }
            this.takeUserGuess();
        }
    }

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
