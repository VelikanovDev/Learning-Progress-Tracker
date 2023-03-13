package dataManagers;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getLine() {
        var line = scanner.nextLine();
        return line.trim();
    }

}
