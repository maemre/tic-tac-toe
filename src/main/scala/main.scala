package main

import scala.io.{StdIn, AnsiColor => Color}

object Main {
  def printBoard(game : TicTacToe) : Unit = {
    for (i <- 0 until game.SIZE) {
      for (j <- 0 until game.SIZE) {
        print(s"${Color.RED}${Color.GREEN}${game.markAt(i, j)}${Color.RESET}")
        if (j != game.SIZE - 1) {
        print("|")
        }
      }
      println()
      if (i != game.SIZE - 1) {
        println(("-+" * (game.SIZE - 1)) + "-")
      }
    }
  }

  def main(args: Array[String]) : Unit = {
    print("What size board do you want to play on (enter a number)? ")
    var game = new TicTacToe(StdIn.readInt);
    while (! game.isGameOver) {
      printBoard(game)
      println(s"\nCurrent player: ${Color.GREEN}${game.getCurrentPlayer}${Color.RESET}")
      print("Which row do you want to play? ")
      val i = StdIn.readInt
      print("Which column do you want to play? ")
      val j = StdIn.readInt
      if (! game.place(i, j)) {
        println("You did not enter a valid move.")
      }
    }

    printBoard(game)
    println(s"\nGAME OVER")
    println(s"The winner is ${game.getCurrentPlayer}")
  }
}
