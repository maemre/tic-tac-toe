package main

import scala.io.StdIn

object Main {
  def printBoard(game : TicTacToe) : Unit = {
    for (i <- 0 until 3) {
      for (j <- 0 until 3) {
        print(s"${game.markAt(i, j)}")
        if (j != 2) {
        print("|")
        }
      }
      println()
      if (i != 2) {
        println("-+-+-")
      }
    }
  }

  def main(args: Array[String]) : Unit = {
    print("What size board do you want to play on (enter a number)? ")
    var game = new TicTacToe(StdIn.readInt);
    while (! game.isGameOver) {
      printBoard(game)
      println(s"Current player: ${game.getCurrentPlayer}")
      print("Which row do you want to play? ")
      val i = StdIn.readInt
      print("Which column do you want to play? ")
      val j = StdIn.readInt
      if (! game.place(i, j)) {
        println("You did not enter a valid move.")
      }
    }

    printBoard(game)
    println(s"GAME OVER")
    println(s"The winner is ${game.getCurrentPlayer}")
  }
}
