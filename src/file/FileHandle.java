package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import model.Hotel;


public class FileHandle {
     
    File file = new File("D:\\NetBeansProjects\\Vs_HotelManagement\\HotelManagement.dat");
    
    public void saveToFile(ArrayList<Hotel> hList) {
        try {
            
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (Hotel hotel : hList) {
                oos.writeObject(hotel);
            }
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadFromFile(ArrayList<Hotel> hList) {
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            Hotel ht = null;
            while(true) {
                ht = (Hotel) ois.readObject();
                if(ht == null) {
                    break;
                } else {
                    hList.add(ht);
                }
            }
            ois.close();
        } catch (Exception e) {
        }
    }

}
