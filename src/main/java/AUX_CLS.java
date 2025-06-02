import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AUX_CLS {
    public static void writeToFile(List<Worker> workers, String filePath) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(workers);
        try {
            Files.writeString(Path.of(filePath), jsonString);
            JOptionPane.showMessageDialog(null, "Saved to " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Worker> readFromFile(String filePath) {
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
}
