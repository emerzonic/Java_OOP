package com.emerson;

public class Letter {
    String  letter;
    String placeHolder;
    boolean status;

    public Letter(String letter){
        this.letter = letter;
        this.placeHolder = "_";
        this.status = false;
    }

    public String checkGuess(){
        if(this.status){
            return this.letter;
        }else {
            return this.placeHolder;
        }

    }

    public void takeGuess(String guess){
        if(this.letter.equals(guess)){
            this.status = true;
        }
    }
}
