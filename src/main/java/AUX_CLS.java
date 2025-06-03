import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AUX_CLS {
    public static void writeToJSONFile(List<Worker> workers, String filePath) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(workers);
        try {
            Files.writeString(Path.of(filePath), jsonString);
            JOptionPane.showMessageDialog(null, "Saved to " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Worker> readFromJSONFile(String filePath) {
        ArrayList<Worker> workers = new ArrayList<>();
        Gson gson = new Gson();
        try {
            String jsonString = Files.readString(Path.of(filePath));
            workers = gson.fromJson(jsonString, new TypeToken<List<Worker>>(){}.getType());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return workers;
    }

//    public static void writeToBinFile(List<Worker> workers, String filePath) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
//            oos.writeObject(workers);
//            JOptionPane.showMessageDialog(null, "Saved to " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static ArrayList<Worker> readFromBinFile(String filePath) {
//        ArrayList<Worker> workers = new ArrayList<>();
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
//            workers = (ArrayList<Worker>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        return workers;
//    }
}
