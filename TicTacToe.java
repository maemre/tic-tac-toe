/// Represents a game of Tic-Tac-Toe
public class TicTacToe {
    // The state of the board
    private char[][] board;
    // Character denoting the current player, 'x' or 'o'
    private char currentPlayer;
    // Size of the board. board is a SIZE × SIZE array
    public final int SIZE;

    public TicTacToe(int size) {
        SIZE = size;
        board = new char[SIZE][SIZE];
        currentPlayer = 'x';
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = ' ';
            }
        }
    }

    /// Check if it is valid to draw on given cell
    public boolean isValidMove(int i, int j) {
        if (i < 0 || i >= SIZE) {
            return false;
        }
        if (j < 0 || j >= SIZE) {
            return false;
        }
        return board[i][j] == ' ';
    }

    /// Checks if the given row is complete
    private boolean isRowComplete(int row) {
        boolean result = board[row][0] != ' ';
        // No need to compare the first column, so we start from 1
        for (int column = 1; column < board[row].length; ++column) {
            result = result && board[row][0] == board[row][column];
        }
        // we can also implement it like this:
        /*
          for (char cell : board[row]) {
              result = result && board[row][0] == cell;
          }
        */
        return result;
    }

    /// Checks if the given column is complete
    private boolean isColumnComplete(int column) {
        boolean result = board[0][column] != ' ';
        // No need to compare the first column, so we start from 1
        for (int row = 1; row < board[column].length; ++row) {
            result = result && board[0][column] == board[row][column];
        }
        return result;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /// Checks if the game is over
    ///
    /// Note: This method has a bug: it does not detect the case when the board
    /// is full and the game is a tie.
    public boolean isGameOver() {
        for (int i = 0; i < SIZE; ++i) {
            if (isRowComplete(i) || isColumnComplete(i)) {
                return true;
            }
        }

        // Finally, check both diagonals
        //
        // diag1 is the diagonal with \ shape (top-left to bottom-right)
        // diag2 is the diagonal with / shape (top-right to bottom-left)
        boolean isDiag1Complete = board[0][0] != ' ';
        boolean isDiag2Complete = board[0][SIZE - 1] != ' ';
        for (int i = 1; i < SIZE; ++i) {
            if (board[i][i] != board[0][0]) {
                isDiag1Complete = false;
            }
            if (board[i][SIZE - i - 1] != board[0][SIZE - 1]) {
                isDiag2Complete = false;
            }
        }

        return isDiag1Complete || isDiag2Complete;
    }

    /// Places given mark as the action of the current player, then switches the
    /// current player.
    ///
    /// Returns whether the given move was valid.
    public boolean place(int i, int j) {
        if (! isValidMove(i, j)) {
            return false;
        }

        board[i][j] = currentPlayer;

        if (! isGameOver()) {
            if (currentPlayer == 'o') {
                currentPlayer = 'x';
            } else {
                currentPlayer = 'o';
            }
        }
        
        return true;
    }

    /// Returns the mark at the given board location
    public char markAt(int i, int j) {
        return board[i][j];
    }
}
