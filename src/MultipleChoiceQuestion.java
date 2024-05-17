import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;

    private String correctAnswer;


    public MultipleChoiceQuestion(String questionText, List<String> options, String correctAnswer) {
           super(questionText);

        this.options = options;

         this.correctAnswer = correctAnswer;
    }

       public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
