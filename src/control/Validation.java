package control;

import model.Hotel;
import java.util.ArrayList;
import java.util.regex.Pattern;



public class Validation {

    public static String getID(String msg, ArrayList<Hotel> hList, String pattern) {
        String id = "";
        Pattern p = Pattern.compile(pattern);
        while (true) {
            try {
                id = Input.inputString(msg);
                if (checkID(id, hList) == null && !id.isEmpty() && p.matcher(id).matches()) {
                    break;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("ID must be unique, not null and begin with 'H'.");
            }
        }
        return id;
    }

    public static Hotel checkID(String id, ArrayList<Hotel> hList) {
        for (Hotel x : hList) {
            if (x.getHotel_id().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

// =========================================================================================================================================

    public static String getPhone(String msg) {
        String phone;
        Pattern pattern = Pattern.compile("0\\d{9}");
        do {
            phone = Input.inputString(msg);
            if (!pattern.matcher(phone).matches()) {
                System.out.println("Please enter the correct format of the phone.");
            } else {
                return phone;
            }
        } while (true);
    }

// =========================================================================================================================================

    public static int getValue(String msg, int min, int max) {
        int choice = 0;
        while (true) {
            try {
                choice = Input.inputInt(msg);
                if (min <= choice && choice <= max) {
                    break;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Please enter the value between " + min + " and " + max + ".");
            }
        }
        return choice;
    }

// =========================================================================================================================================

    public static String getString(String msg, String att) {
        String result = "";
        while (true) {
            try {
                result = Input.inputString(msg);
                if (!result.trim().isEmpty()) {
                    break;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println(att + " can not be null. Try again!");
            }
        }
        return result;
    }

// =========================================================================================================================================
    
    public static String checkUpdate(String msg, String pattern, String old) {
       String input;
       Pattern p = Pattern.compile(pattern);
       while(true) {
           input = Input.inputString(msg);
           if(input.trim().isEmpty()) {
               return old;
           } else if(!p.matcher(input).matches()) {
               System.out.println("Please input the correct format.");
           } else {
               return input;
           }
       }
    }

}
