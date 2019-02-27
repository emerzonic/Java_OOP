package com.emerson;

class Letter {
  private String letter;
  private String placeHolder;
  private boolean status;

  Letter(String letter) {
    this.letter = letter;
    this.placeHolder = "_";
    this.status = false;
  }


  String checkGuess() {
    return status ? letter : placeHolder;
  }


  void takeGuess(String guess) {
    if (this.letter.equals(guess)) {
      this.status = true;
    }
  }
}
