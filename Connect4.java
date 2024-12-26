public class Connect4 {
    public static void main(String[] args) {
        menu();
        System.out.println(Utilities.SALUTATIONS);
        Validate.sc.close();
    }

    public static void menu() {
        boolean rematch = true;
        boolean finished = false;

        do {
            int option = Validate.selectOption();
            switch (option) {
                case 1:
                    while (rematch) {
                        Utilities.refreshBoard(Utilities.board);
                        game(Utilities.board);
                        rematch = again();
                    }
                    finished = true;
                    break;
                case 2:
                    finished = true;
                    break;
                default:
                    System.out.println(Utilities.INVALID_OPTION);
                    break;
            }
        } while (!finished);
    }

    public static void game(String[][] board) {
        int turnCounter = 0;
        String[] player;

        Utilities.boardAssembler();
        do {
            player = Utilities.turnTracker(turnCounter);
            Validate.columnSelection(Utilities.SELECT, board[0].length, 1, player);
            Utilities.boardAssembler();

            turnCounter++;
            if (!Utilities.hasSpace(board)) {
                player = Utilities.players[2];
                break;
            }
        } while (Utilities.diagonalChecker(board) && Utilities.straightLineChecker(board));

        scoreTracker(player[0]);
    }

    public static boolean again() {
        while (true) {
            System.out.print(Utilities.REMATCH);
            String input = Validate.sc.nextLine();
            if (Validate.isLetters(input)) {
                if (input.equalsIgnoreCase(Utilities.YES)) {
                    return true;
                } else if (input.equalsIgnoreCase(Utilities.NO)) {
                    return false;
                } else {
                    System.out.println(Utilities.INVALID_OPTION);
                }
            }
        }
    }

    public static void updateBoard(String[][] board, String[] player, int column) {
        try {
            for (int i = 0; i < board.length; i++) {
                if (board[5][column-1].equals(Utilities.SPACE_1) || board[5][column-1].equals(Utilities.SPACE_2)) { //Checks if the bottom space is empty or not
                    board[5][column-1] = player[1]; break;
                } else if (board[i][column-1].equals(Utilities.players[0][1]) || board[i][column-1].equals(Utilities.players[1][1])) { // Checks if the selected spot is already taken and places token above it if it is
                    board[i-1][column-1] = player[1]; break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This column is full, please select one that is empty.");
            Validate.columnSelection(Utilities.SELECT, board[0].length, 1, player);
        }
    }

    public static void scoreTracker(String player) {
        for (int i = 0; i < Utilities.players.length; i++) {
            if (player.equals(Utilities.players[i][0])) {
                int counter = Integer.parseInt(Utilities.players[i][2]);
                Utilities.players[i][2] = String.valueOf(++counter);
            }
        }

        String head = String.format(Utilities.playerScore, " " + Utilities.players[0][0], Utilities.players[2][0], " " + Utilities.players[1][0]);
        String body = String.format(Utilities.playerScore, Utilities.players[0][2], Utilities.players[2][2], Utilities.players[1][2]);
        String line = Utilities.lineMaker(head);


        System.out.printf(line + head + line + body + line);
    }
}