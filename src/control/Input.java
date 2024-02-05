package control;

import java.util.Scanner;

public class Input {

    public static String inputString(String msg) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        System.out.print(msg);
        s = sc.nextLine();
        return s;
    }

    public static int inputInt(String msg) {
        int n = 0;
        String tmp = inputString(msg);
        n = Integer.parseInt(tmp);
        return n;
    }
}
