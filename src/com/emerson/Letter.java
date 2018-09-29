package com.emerson;

public class Letter {
    private String  letter;
    private String placeHolder;
    private boolean status;

    public Letter(String letter){
        this.letter = letter;
        this.placeHolder = "_";
        this.status = false;
    }


    //This method checks every letter in the word and returns a letter or a placeholder underscore
    public String checkGuess(){
        if(this.status){
            return this.letter;
        }else {
            return this.placeHolder;
        }

    }


    //This method takes a letter as guess and checks it against each letter of the word
    public void takeGuess(String guess){
        if(this.letter.equals(guess)){
            this.status = true;
        }
    }
}
