import java.util.*;

public class Quiz {
    private List<Question> questions;
    private int currentIndex;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        currentIndex = 0;
        score = 0;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public Question getCurrentQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex);
        }
        return null;
    }

    public void checkAnswer(int userChoice) {
        if (getCurrentQuestion().getCorrectOption() == userChoice) {
            score++;
        }
        currentIndex++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean isFinished() {
        return currentIndex >= questions.size();
    }
}
