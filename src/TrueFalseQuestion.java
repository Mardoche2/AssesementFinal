public class TrueFalseQuestion extends Question {
    private boolean isTrue;

    public TrueFalseQuestion(String questionText, boolean isTrue) {
        super(questionText);

        this.isTrue = isTrue;
    }

    @Override
        public boolean checkAnswer(String answer) {

        return (isTrue && answer.equalsIgnoreCase("True")) || (!isTrue && answer.equalsIgnoreCase("False"));
    }
}
