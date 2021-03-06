package com.emerson;

import java.util.Arrays;

public class Word {
    private String word;
    private Letter[] splittedLetters;
    private boolean status;
    private int feedback;
    private int attempts;

    public Word(String word) {
        this.word = word;
        this.splittedLetters = new Letter[this.word.length()];
        this.status = false;
        this.feedback = 0;
        this.attempts = 0;
    }

    String getWord() {
        return word;
    }

    boolean isStatus() {
        return status;
    }

    int getAttempts() {
        return attempts;
    }


    Word splitLetters() {
        String[] tempArray = this.word.split("");
        int index = 0;
        for (String item : tempArray) {
            Letter letterObject = new Letter(item);
            this.splittedLetters[index] = (letterObject);
            index++;
        }
        return this;
    }


    Word setAttempts() {
        this.attempts = this.word.length() * 3;
        System.out.println("You have " + this.attempts + " fail attempts to make on this word.");
        return this;
    }


    void displayWord() {
        StringBuilder displayWord = new StringBuilder();
        for (Letter letter : this.splittedLetters) {
            String character = " " + letter.checkGuess();
            displayWord.append(character);
        }
        System.out.println(displayWord);
        if (!displayWord.toString().contains("_")) {
            this.status = true;
            System.out.println(Color.green + "You guessed it right!" + Color.reset);
        }
    }


    Word takeChar(String guess) {
        for (Letter letter : this.splittedLetters) {
            letter.takeGuess(guess);
        }
        return this;
    }


    Word trackStatus() {
        int tracker = 0;
        for (Letter letter : this.splittedLetters) {
            if (!letter.checkGuess().equals("_")) {
                tracker++;
            }
        }
        if (this.feedback != tracker) {
            System.out.println(Color.green + "CORRECT!" + Color.reset);
            this.feedback = tracker;
        } else {
            this.attempts--;
            System.out.println(Color.red + "INCORRECT!");
            String attemptOrAttempts = this.attempts >= 2 ? "attempts" : "attempt";
            System.out.println("You have " + this.attempts + " " + attemptOrAttempts + " remaining." + Color.reset);
        }
        int remainingLetters = this.word.length() - tracker;
        String letterOrLetters = remainingLetters >= 2 ? "letters" : "letter";
        System.out.println(Color.green + "... " + remainingLetters + " more " +
                letterOrLetters + " remaining to guess it right." + Color.reset);
        return this;
    }
}





