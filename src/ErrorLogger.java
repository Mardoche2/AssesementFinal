import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorLogger {

    private static ErrorLogger instance;

    private PrintWriter writer;


    private ErrorLogger() {

        try {

            writer = new PrintWriter(new FileWriter("error.log", true));
        } catch (IOException e) {

              e.printStackTrace();
        }
    }

    public static synchronized ErrorLogger getInstance() {
                  if (instance == null) {
                   instance = new ErrorLogger();
        }
        return instance;
    }

    public void logError(String message) {

        writer.println(message);

        writer.flush();
    }
}
