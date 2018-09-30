package com.emerson;

import java.util.Arrays;

public class Word {
    private String word;
    private Object[] splittedLetters;
    private boolean status;
    private int feedback;
    private int attempts;

    public Word(String word) {
        this.word = word;
        this.splittedLetters = new Object[this.word.length()];
        this.status = false;
        this.feedback = 0;
        this.attempts = 0;
    }

    public String getWord() {
        return word;
    }

    public boolean isStatus() {
        return status;
    }

    public int getAttempts() {
        return attempts;
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
    public Word setAttempts() {
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
            System.out.println(Color.ANSI_GREEN + "You guessed it right!"+ Color.ANSI_RESET);
        }
    }


    //This method takes the user's guess(letter) and calls the Letter takeGuess method on it.
    public Word takeChar(String guess) {
        for (Object obj : this.splittedLetters) {
            ((Letter) obj).takeGuess(guess);
        }
        return this;
    }


    //This method tracks the status of the guesses remaining and updates if guess is wrong or CORRECT
    public Word trackStatus() {
        int track = 0;
        for (Object obj : this.splittedLetters) {
            if (!((Letter) obj).checkGuess().equals("_")) {
                track++;
            }
        }
        if (this.feedback != track) {
            System.out.println(Color.ANSI_GREEN + "CORRECT!" + Color.ANSI_RESET);
            this.feedback = track;
        } else {
            this.attempts--;
            System.out.println(Color.ANSI_RED + "INCORRECT!");
            String attemptOrAttempts = this.attempts >= 2 ? "attempts" : "attempt";
            System.out.println("You have " + this.attempts + " " + attemptOrAttempts + " remaining." + Color.ANSI_RESET);
        }
        int remainingNum = this.word.length() - track;
        String letterOrLetters = remainingNum >= 2 ? "letters" : "letter";
        System.out.println(Color.ANSI_GREEN + "... " + remainingNum + " more " + letterOrLetters + " remaining to guess it right." + Color.ANSI_RESET);
        return this;
    }

}



