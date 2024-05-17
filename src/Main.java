import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AuthenticationManager authManager = AuthenticationManager.getInstance();

        ScoreManager scoreManager = new ScoreManager("scores.csv");

        System.out.println("Welcome ");

        System.out.println("register if you want to start:");

        // Registration
        System.out.print("Enter username: ");

            String username = scanner.nextLine();

        System.out.print("Enter password: ");

            String password = scanner.nextLine();

        try {
            authManager.registerUser(username, password);

            System.out.println("your Registration is a success");


        } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());

            return;
        }

        // Login
          System.out.println("Please log in:");

               System.out.print("username: ");

        String loginUsername = scanner.nextLine();

           System.out.print(" password: ");

         String loginPassword = scanner.nextLine();

        if (authManager.authenticateUser(loginUsername, loginPassword)) {

                      System.out.println("Authentication successful.");

            User currentUser = new User(loginUsername, loginPassword);

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Take a quiz");
                System.out.println("2. View quiz history");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Fetch quiz questions
                        System.out.println("Select quiz category:");
                        System.out.println("1. General Knowledge");
                        System.out.println("2. Science");
                        System.out.println("3. History");
                        System.out.println("4. All");

                        String category;
                        switch (scanner.nextInt()) {
                            case 1: category = "9"; break;

                            case 2: category = "17"; break;

                            case 3: category = "23"; break;

                            case 4:

                            default: category = ""; break;
                        }
                        scanner.nextLine(); // Consume newline

                        try {
                            String questionsJSON = QuestionFetcher.fetchQuestions(category);

                            List<Question> questions = (List<Question>) QuizParser.parseQuestions(questionsJSON);

                            Quiz quiz = new Quiz(questions);

                            quiz.startQuiz(currentUser);

                            scoreManager.saveScore(currentUser, quiz.getScore());

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        break;

                    case 2:
                        // l'ISTORIQUE OF QUiz
                        List<String> history = scoreManager.getUserHistory(currentUser);
                        System.out.println("Quiz History:");

                        for (String entry : history) {

                            System.out.println(entry);
                        }
                        break;
                    case 3:
                        System.out.println("Exiting. bye!");

                        return;
                    default:
                        System.out.println("Invalid choice.  try again.");
                        break;
                }
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }
}
