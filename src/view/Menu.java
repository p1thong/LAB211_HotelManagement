package view;

import control.Validation;

public class Menu {

    public static int menu() {
        int result =0;
        // menu
        System.out.println("-----------------------------------------------------------");
        System.out.println("-  1- Adding new hotel                                    -");
        System.out.println("-  2- Checking exist hotel                                -");
        System.out.println("-  3- Updating hotel information                          -");
        System.out.println("-  4- Deleting hotel                                      -");
        System.out.println("-  5- Searching hotel                                     -");
        System.out.println("-  6- Display a hotel list                                -");
        System.out.println("-  7- Exit the program                                    -");
        System.out.println("-----------------------------------------------------------");
        
        // enter choice
        result = Validation.getValue("Enter choice: ", 1, 7);
        return result;
    }
}
