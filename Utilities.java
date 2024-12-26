import java.util.Objects;

public class Utilities {

    public static String[][] board = new String[6][7];

    // Methods for the board
    static final String SPACE_1 = "";
    static final String SPACE_2 = " ";
    public static final String SELECT = "\nSelect the column that you to place a token inside of: ";
    public static final String PLAYER_TURN = "\u001B[0m" + "\n%s's turn";
    public static String playerScore = "\n|%-10s|%-10s|%-10s|";
    //Board Output
    public static final String INNER_BOARD = "\n||%3s|%3s|%3s|%3s|%3s|%3s|%3s||";
    public static final String SEPERATOR = "=";
    //Error Messages
    public static final String INVALID_OPTION = "Invalid option!";
    public static final String INVALID_INPUT = "Invalid input!";
    public static final String INVALID_SPACE = "Selected space is either invalid or already claimed.";
    //Yes or No
    public static final String REMATCH = "\nWould you like to play again? (Y/N): ";
    public static final String YES = "Y";
    public static final String NO = "N";
    //Menu
    public static final String TITLE = "\nWelcome to Connect 4! What would you like to do?";
    public static final String OPTIONS = "\n1. Play game\n2. Exit\n";
    public static final String SALUTATIONS = "\nThank you for playing!";
    // Victory
    public static final String WINNER = "\n%s is the winner!\n";
    public static final String STALEMATE = "\nThe game ended on a stalemate.";

    public static String lineMaker(String ref) {
        String line = "\n";
        for (int i = 0; i < ref.length()-1; i++) {
            line += SEPERATOR;
        }
        return line;
    }

    public static void boardAssembler() {
        String body = "";
        String line = "\u001B[34m" + lineMaker(INNER_BOARD);
        String numberIndicator = String.format(INNER_BOARD, " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ");

        for (int i = 0; i < 6; i++) {
            String filledInnerBoard = String.format(INNER_BOARD, board[i][0], board[i][1], board[i][2], board[i][3], board[i][4], board[i][5], board[i][6]);
            body += filledInnerBoard + line;
        }
        System.out.println(line + numberIndicator + line + body);
    }

    public static void refreshBoard(String[][] board) {
        for (int i = 0; i < board.length; i+=2) {
            for (int j = 0; j < board[i].length; j++) {
                if (j < 3 || j > 5) {
                    board[i][j] = SPACE_1;
                    board[i+1][j] = SPACE_2;
                } else {
                    board[i][j] = SPACE_2;
                    board[i+1][j] = SPACE_1;
                }
            }
        }
    }

    // Methods for Winner detection

    public static String[][] players = {
            {"Player 1", "\u001B[31m" + " X " + "\u001B[34m", "0"},
            {"Player 2", "\u001B[33m" + " O " + "\u001B[34m", "0"},
            {"Stalemates", "", "0"},
    };

    public static boolean equivalent(String value1, String value2, String value3, String value4) {
        if (value1.equals(SPACE_1) || value1.equals(SPACE_2)) {
            return false;
        } else if (value1.equals(value2) && value2.equals(value3) && value3.equals(value4)) {
            return true;
        }
        return false;
    }

    public static boolean diagonalChecker(String[][] board) {
        boolean result = true;
        for (int i = 3; i < board.length; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if (equivalent(board[i][j-3], board[i-1][j-2], board[i-2][j-1], board[i-3][j])) { // checks for /
                    result = false; break;
                } else if (equivalent(board[i-3][j-3], board[i-2][j-2], board[i-1][j-1], board[i][j])) { // checks for \
                    result = false; break;
                }
            }
        }
        return result;
    }

    public static boolean straightLineChecker(String[][] board) {
        boolean result = true;
        for (int i = 0; i < board[0].length; i++) { // Checks for |
            for (int j = 3; j < board.length; j++) {
                if (equivalent(board[j][i], board[j-1][i], board[j-2][i], board[j-3][i])) {
                    result = false; break;
                }
            }
        }
        for (int i = 0; i < board.length; i++) { // Checks for ---
            for (int j = 3; j < board[0].length; j++) {
                if (equivalent(board[i][j], board[i][j-1], board[i][j-2], board[i][j-3])) {
                    result = false; break;
                }
            }
        }
        return result;
    }

    public static boolean hasSpace(String[][] board) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(SPACE_1) || board[i][j].equals(SPACE_2)) {
                    result = true; break;
                }
            }
        }
        return result;
    }

    public static String[] turnTracker(int turnCounter) {
        if (turnCounter % 2 == 0) {
            System.out.printf(PLAYER_TURN, players[0][0]);
            return players[0];
        } else {
            System.out.printf(PLAYER_TURN, players[1][0]);
            return players[1];
        }
    }
}
