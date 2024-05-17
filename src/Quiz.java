import java.util.List;
import java.util.Scanner;

public class Quiz {

    private List<Question> questions;

    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public void startQuiz(User user) {
             Scanner scanner = new Scanner(System.in);
        score = 0;

             for (Question question : questions) {
            System.out.println(question.getQuestionText());

            if (question instanceof MultipleChoiceQuestion) {

                MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;

                List<String> options = mcQuestion.getOptions();

                for (int i = 0; i < options.size(); i++) {

                    System.out.println((i + 1) + ". " + options.get(i));
                }
                int answerIndex = scanner.nextInt() - 1;

                scanner.nextLine();

                String answer = options.get(answerIndex);

                if (question.checkAnswer(answer)) {
                    score++;
                }
            } else if (question instanceof TrueFalseQuestion) {
                System.out.print("Enter 'True' or 'False': ");
                String answer = scanner.nextLine();
                if (question.checkAnswer(answer)) {
                    score++;
                }
            }
        }

        System.out.println(user.getUsername() + ", your score is: " + score + "/" + questions.size());
    }

    public int getScore() {
        return score;
    }
}
