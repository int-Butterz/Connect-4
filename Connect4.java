public class Connect4 {


    public static void main(String[] args) {
        Utilities.refreshBoard(Utilities.board);
        System.out.println(Utilities.diagonalChecker(Utilities.board));
        Utilities.boardAssembler();
    }
}
