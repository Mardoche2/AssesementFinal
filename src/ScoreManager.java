import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    private String filename;



    public ScoreManager(String filename) {

          this.filename = filename;

    }

    public void saveScore(User user, int score) {

        try   (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.getUsername() + "," + LocalDateTime.now() + "," + score);

            writer.newLine();

                   } catch (IOException e) {

            ErrorLogger.getInstance().logError("Error saving score: " + e.getMessage());
        }
    }

    public List<String> getUserHistory(User user) {

                     List<String> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;


                while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");


                if (parts[0].equals(user.getUsername())) {


                    history.add("Date: " + parts[1] + ", Score: " + parts[2]);
                }
            }
        } catch (IOException e) {

            ErrorLogger.getInstance().logError("Error reading history: " + e.getMessage());
        }
        return history;
    }
}
