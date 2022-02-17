import java.util.Scanner;

class Main {
    // This is a specific way of printing in color in ANSI terminals. It is
    // outside the scope of this class (although, it is somewhat related to CS
    // 221).
    static String makeGreen(Object obj) {
        final String GREEN_MARKER = "\u001B[32m";
        final String RESET_MARKER = "\033[0m";
        return GREEN_MARKER + obj.toString() + RESET_MARKER;
    }
    
    static void printInGreen(Object obj) {
        System.out.print(makeGreen(obj));
    }
    
    // Helper function to print the board nicely
    static void printBoard(TicTacToe game) {
        for (int i = 0; i < game.SIZE; ++i) {
            // print the current row
            for (int j = 0; j < game.SIZE; ++j) {
                printInGreen(game.markAt(i, j));
                // Print the row separator
                if (j != game.SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            // print the row separator, this requires Java 11
            if (i != game.SIZE - 1) {
                System.out.println("-+".repeat(game.SIZE - 1) + "-");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What size board do you want to play on (enter a number)? ");
        TicTacToe game = new TicTacToe(scanner.nextInt());
        while (! game.isGameOver()) {
            printBoard(game);
            System.out.println("\nCurrent player: " + makeGreen(game.getCurrentPlayer()));
            System.out.print("Which row do you want to play? ");
            int i = scanner.nextInt();
            System.out.print("Which column do you want to play? ");
            int j = scanner.nextInt();
            if (! game.place(i, j)) {
                System.out.println("You did not enter a valid move.");
            }
        }

        printBoard(game);
        System.out.println("\nGAME OVER");
        System.out.println("The winner is " + game.getCurrentPlayer());
    }
}
