package com.emerson;

import java.util.Arrays;

public class Word {
    String word;
    Object[] splittedLetters;
    boolean status;
    int feedback;
    int attempts;

    public Word(String word) {
        this.word = word;
        this.splittedLetters = new Object[this.word.length()];
        this.status = false;
        this.feedback = 0;
        this.attempts = 0;

    }

    //This method takes a word and splits the letters into objects
    public Word splitLetters() {
        String[] tempArray = this.word.split("");
        int index = 0;
        for (String item : tempArray) {
            Object letterObject = new Letter(item);
            this.splittedLetters[index] = (letterObject);
            index++;
        }
        return this;
    }

    //This method generates the number of attempts base on the length of the random word
    public Word generateAttempts() {
        this.attempts = this.word.length() * 3;
        System.out.println("You have " + this.attempts + " fail attempts to make on this word.");
        return this;
    }


    //This method takes each letter object and calls the Letter checkGuess method and returns a word and status of user guesses left.
    public void displayWord() {
        String displayWord = "";
        for (Object obj : this.splittedLetters) {
            displayWord += " " + ((Letter) obj).checkGuess();
        }
        System.out.println(displayWord);
        if (!displayWord.contains("_")) {
            this.status = true;
            System.out.println("You guessed it right!");
        }
    }

    //This method takes the user's guess(letter) and calls the Letter takeGuess method on it.
    public Word takeChar(String guess) {
        for (Object obj : this.splittedLetters) {
            ((Letter) obj).takeGuess(guess);
        }
        return this;
    }

    public Word trackStatus() {
        int track = 0;
        for (Object obj : this.splittedLetters) {
            if (!((Letter) obj).checkGuess().equals("_")) {
                track++;
            }
        }
        if (this.feedback != track) {
            System.out.println("CORRECT!");
            this.feedback = track;

        } else {
            this.attempts--;
            System.out.println("INCORRECT!");
            String attemptOrAttempts = this.attempts >= 2 ? "attempts" : "attempt";
            System.out.println("You have " + this.attempts + " " + attemptOrAttempts + " remaining.");
        }
        int remainingNum = this.word.length() - track;
        String letterOrLetters = remainingNum >= 2 ? "letters" : "letter";
        System.out.println("... " + remainingNum + " more " + letterOrLetters + " remaining to guess it right.");
        return this;
    }

}



