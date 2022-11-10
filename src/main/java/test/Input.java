package test;

import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString() {
        return sc.nextLine();
    }

    public static void close() {
        sc.close();
    }
}
