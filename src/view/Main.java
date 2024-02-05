package view;


import java.io.IOException;
import control.Management;

public class Main {
    public static void main(String[] args) throws IOException {
        Management list = new Management();
        
        list.loadFile();
        int choice;
        do {
            choice = Menu.menu();
            switch (choice) {
                case 1 -> list.addHotel();
                case 2 -> list.checkExist();
                case 3 -> list.updateHotel();
                case 4 -> list.deleteHotel();
                case 5 -> list.searchHotel();
                case 6 -> list.displayAll();
                case 7 -> System.out.println("Exit.");
            }
        } while (choice != 7);
        list.saveFile();
    }
}

