import java.util.Scanner;

public class Validate {
    public static Scanner sc = new Scanner(System.in);

    public static void columnSelection(String message, int max, int min, String[] player) {
        System.out.println(message);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number > max || number < min) {
                    System.out.println(Utilities.INVALID_OPTION);
                } else {
                    Connect4.updateBoard(Utilities.board, player, number); break;
                }
            } catch (NumberFormatException e) {
                System.out.println(Utilities.INVALID_INPUT);
            }
        }
    }

    public static boolean isLetters(String input) {
        try {
            Double.parseDouble(input); // Tries to convert input into double
            System.out.println(Utilities.INVALID_INPUT); // if it's anything but numbers
            return false;
        } catch (NumberFormatException e) { // If it is a string
            for (int i = 0; i < input.length(); i++) { // It verifies that there are only letters
                if (!Character.isLetter(input.charAt(i))) {
                    System.out.println(Utilities.INVALID_INPUT);
                    return false;
                }
            }
            return true;
        }
    }

    public static int selectOption() {
        int number = 0;
        boolean valid = false;
        String line = Utilities.lineMaker(Utilities.TITLE);

        while (!valid) {
            System.out.printf(line + Utilities.TITLE + line + Utilities.OPTIONS);
            try {
                number = Integer.parseInt(sc.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println(Utilities.INVALID_INPUT);
            }
        }
        return number;
    }
}
