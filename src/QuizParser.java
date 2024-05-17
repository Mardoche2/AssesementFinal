
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizParser {
    public static Quiz parseQuestions(String questionsJSON) {
        List<Question> questions = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(questionsJSON);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject questionObject = results.getJSONObject(i);
            String questionText = questionObject.getString("question");
            String correctAnswer = questionObject.getString("correct_answer");
            JSONArray incorrectAnswers = questionObject.getJSONArray("incorrect_answers");

            // Add multiple choice question
            if (incorrectAnswers.length() > 1) {
                List<String> options = new ArrayList<>();
                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    options.add(incorrectAnswers.getString(j));
                }
                options.add(correctAnswer);
                Collections.shuffle(options);
                questions.add(new MultipleChoiceQuestion(questionText, options, correctAnswer));
            }
            // Add true/false question
            else {
                boolean isTrue = correctAnswer.equalsIgnoreCase("True");
                questions.add(new TrueFalseQuestion(questionText, isTrue));
            }
        }

        // Assuming Quiz is a class that takes a List<Question> in its constructor
        return new Quiz(questions);
    }
}