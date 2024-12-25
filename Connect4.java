public class Connect4 {


    public static void main(String[] args) {
        Utilities.refreshBoard(Utilities.board);
        System.out.println("Diag: " + Utilities.diagonalChecker(Utilities.board));
        System.out.println("Vert: " + Utilities.straightLineChecker(Utilities.board, true));
        System.out.println("Hori: " + Utilities.straightLineChecker(Utilities.board, false));
        Utilities.boardAssembler();
    }
}