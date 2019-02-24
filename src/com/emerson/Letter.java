package com.emerson;

 class Letter {
    private String  letter;
    private String placeHolder;
    private boolean status;

     Letter(String letter){
        this.letter = letter;
        this.placeHolder = "_";
        this.status = false;
    }


    //This method checks every letter in the word and returns a letter or a placeholder underscore
     String checkGuess(){
       String character = "";
        if(this.status){
          character = letter;
        }else {
          character = placeHolder;
        }
        return character;
    }


    //This method takes a letter as guess and checks it against each letter of the word
     void takeGuess(String guess){
        if(this.letter.equals(guess)){
            this.status = true;
        }
    }
}
