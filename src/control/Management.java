package control;

import file.FileHandle;
import java.util.ArrayList;
import java.util.Collections;

import model.Hotel;

public class Management implements IHotel {
    private ArrayList<Hotel> hList;
    FileHandle f = new FileHandle();
    
    public Management() {
        this.hList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.hList.isEmpty();
    }

// Add Hotel =========================================================================================================================================

    @Override
    public void addHotel() {
        int choice = 1;
        while (choice == 1) {
            Hotel h = new Hotel();
            System.out.println("Enter data for new hotel ..... ");
            h.setHotel_id(Validation.getID("Enter hotel's id: ", hList, "^H[0-9]+"));
            h.setHotel_Name(Validation.getString("Enter hotel's name: ", "Hotel's name"));
            h.setHotel_Room_Available(Validation.getValue("Enter number of free room: ", 0, 100));
            h.setHotel_Address(Validation.getString("Enter hotel's address: ", "Hotel's address"));
            h.setHotel_Phone(Validation.getPhone("Enter hotel's phone: "));
            h.setHotel_Rating(Validation.getValue("Enter hotel's rating: ", 0, 6));
            if(this.hList.add(h)) {
                System.out.println("Success.");
            } else System.out.println("Fail.");
            choice = Validation.getValue("Do you want to add another (1- Yes | 2- No): ", 1, 2);
        }
    }

// Check Exist =========================================================================================================================================

    @Override
    public void checkExist() {
        String id;
        id = Input.inputString("Enter hotel's id that you want to check: ");
        boolean found = false;
        for (Hotel x : hList) {
            if (x.getHotel_id().equalsIgnoreCase(id)) {
                System.out.println("Exist Hotel.");
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("No Hotel Found.");
        }
    }

// Update Hotel =========================================================================================================================================

    @Override
    public void updateHotel() {
        String result = Input.inputString("Enter the hotel's id that you want to update: ");
        boolean found =false;
        for (Hotel h : hList) {
            if (h.getHotel_id().equalsIgnoreCase(result)) {
                System.out.println(h);
                h.setHotel_id(Validation.checkUpdate("UPdate id: ", "^H[0-9]+", h.getHotel_id()));
                h.setHotel_Name(Validation.checkUpdate("Update name: ", "[a-zA-Z\\s]+", h.getHotel_Name()));
                h.setHotel_Room_Available(Integer.parseInt(Validation.checkUpdate("Update room: ", "[0-9]+", h.getHotel_Room_Available() +"")));
                h.setHotel_Address(Validation.checkUpdate("Update address: ", "[0-9a-zA-Z\\s,]+", h.getHotel_Address()));
                h.setHotel_Phone(Validation.checkUpdate("Update phone: ", "0\\d{9}", h.getHotel_Phone()));
                h.setHotel_Rating(Integer.parseInt(Validation.checkUpdate("Update rating: ", "[0-6]", h.getHotel_Rating() +"")));
                System.out.println(h);
                found = true;
            } 
        }
        if(!found) {
            System.out.println("No Hotel Found.");
        }
    }

// Delete Hotel=========================================================================================================================================

    public boolean deleteByID(String id) {
        boolean result = false;
        Hotel x = null;
        for (Hotel h : hList) {
            if(h.getHotel_id().equalsIgnoreCase(id)) {
                x = h;
            }
        }
        if(x != null) {
            this.hList.remove(x);
            result = true;
        }
        return result;
    }

    @Override
    public void deleteHotel() {
        if (hList.isEmpty()) {
            System.out.println("No hotel in the list.");
        } else {
            String id = Input.inputString("Enter the hotel's id that you want to delete: ");
            int w = Validation.getValue("Do you ready want to delete this hotel (1- Yes | 2- No): ", 1, 2);
            if (w == 1) {
                if (deleteByID(id)) {
                    System.out.println("Delete success.");
                } else {
                    System.out.println("Delete fail.");
                }
            } else if (w == 2) {
                System.out.println("The deletion has been cancelled.");
            }
        }
    }

// Search Hotel=========================================================================================================================================


    public ArrayList<Hotel> searchById(ArrayList<Hotel> hotel, String id){
        ArrayList<Hotel> arrHotel = new ArrayList<>();
        for (Hotel x : hotel) {
            if (x.getHotel_id().contains(id.toUpperCase())) {
                arrHotel.add(x);
            }
        }
        Collections.sort(arrHotel, (o1, o2) -> o2.getHotel_id().compareToIgnoreCase(o1.getHotel_id()));
        return arrHotel;
    }
    
    public Hotel searchByName(String name) {
        Hotel x = null;
        for (Hotel h : hList) {
            if(h.getHotel_Name().equalsIgnoreCase(name)) {
                x = h;
            }
        }
        return x;
    }

    @Override
    public void searchHotel() {
        System.out.println("""
                           -  1- Search hotel by id. 
                           -  2- Search hotel by name.""");
        int n = Validation.getValue("Enter choice: ", 1, 2);
        if (n == 1) {
            String id = Input.inputString("Enter the hotel's id that you want to search: ");
            if(searchById(hList, id).isEmpty()) {
                System.out.println("No Hotel Found.");
            } else {
                for (Hotel x : searchById(hList, id)) {
                    System.out.println(x);
                }
            }
        } else if (n == 2) {
            String name = Input.inputString("Enter the hotel's name that you want to search: ");
            if(searchByName(name) != null) {
                System.out.println(searchByName(name));
            } else {
                System.out.println("No Hotel Found.");
            }
        }
    }

// Display Hotel =========================================================================================================================================

    @Override
    public void displayAll() {
        Collections.sort(hList, (o1, o2) -> o2.getHotel_Name().compareTo(o1.getHotel_Name()));
        System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("| Hotel ID | Hotel Name         | Room Available | Address                                                                     | Phone Number | Rating     |");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------------|");
        for(Hotel x : hList) {
            System.out.println(x);
            System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------------|");
        }
    }
    
// File ===================================================================================================================================================
    
    public void saveFile() {
        f.saveToFile(hList);
    }
    
    public void loadFile() {
        f.loadFromFile(hList);
    }
}
