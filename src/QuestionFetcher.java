import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuestionFetcher {

    public static String fetchQuestions(String category) throws Exception {

           String urlString = "https://opentdb.com/api.php?amount=10";

        if (!category.isEmpty()) {

              urlString += "&category=" + category;
        }

        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

           conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

           String inputLine;

          StringBuilder content = new StringBuilder();

          while ((inputLine = in.readLine()) != null) {

            content.append(inputLine);
        }

        in.close();

        conn.disconnect();

        return content.toString();
    }
}
