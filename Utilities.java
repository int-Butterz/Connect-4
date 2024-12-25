public class Utilities {
    public static final String INNER_BOARD = "\n||%3s|%3s|%3s|%3s|%3s|%3s|%3s||";
    public static final String SEPERATOR = "=";

    public static String[][] board = new String[6][7];

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

        for (int i = 0; i < 6; i++) {
            String filledInnerBoard = String.format(INNER_BOARD, board[i][0], board[i][1], board[i][2], board[i][3], board[i][4], board[i][5], board[i][6]);
            body += filledInnerBoard + line;
        }
        String numberIndicator = String.format(INNER_BOARD, " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ");

        System.out.println(line + numberIndicator + line + body);
    }

    public static void refreshBoard(String[][] board) {
        final String SPACE_1 = "";
        final String SPACE_2 = " ";

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
}
