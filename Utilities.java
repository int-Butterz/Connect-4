import java.util.Objects;

public class Utilities {
    public static final String INNER_BOARD = "\n||%3s|%3s|%3s|%3s|%3s|%3s|%3s||";
    public static final String SEPERATOR = "=";
    public static String[][] board = new String[6][7];

    // Methods for the board
    static final String SPACE_1 = "";
    static final String SPACE_2 = " ";

    public static String lineMaker(String ref) {
        String line = "\n";
        for (int i = 0; i < ref.length()-1; i++) {
            line += SEPERATOR;
        }
        return line;
    }

    public static void boardAssembler() {
        String body = "";
        String line = lineMaker(INNER_BOARD);
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
            {"Player 1", " X ", "0"},
            {"Player 2", " O ", "0"},
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
        boolean result = false;
        for (int i = 3; i < board.length; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if (equivalent(board[i][j-3], board[i-1][j-2], board[i-2][j-1], board[i-3][j])) { // checks for \
                    result = true; break;
                } else if (equivalent(board[i-3][j-3], board[i-2][j-2], board[i-1][j-1], board[i][j])) { // checks for /
                    result = true; break;
                }
            }
        }
        return result;
    }
}
