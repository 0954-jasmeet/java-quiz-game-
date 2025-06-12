import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame {
    private Quiz quiz;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private int selectedOption = -1;

    public QuizApp() {
        quiz = new Quiz();
        setTitle("Online Quiz App");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set plain black background
        getContentPane().setBackground(Color.BLACK);

        // Add Questions
        addQuestions();

        // Title
        JLabel title = new JLabel("📝 Online Quiz App", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        questionLabel = new JLabel("Question goes here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 26));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        centerPanel.add(questionLabel);

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 22));
            options[i].setForeground(Color.WHITE);
            options[i].setBackground(Color.BLACK);
            options[i].setFocusPainted(false);
            centerPanel.add(Box.createVerticalStrut(10));
            centerPanel.add(options[i]);
            optionGroup.add(options[i]);

            int index = i + 1;
            options[i].addActionListener(e -> selectedOption = index);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        nextButton = new JButton("Next ➡");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.setBackground(new Color(66, 133, 244));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        nextButton.addActionListener(e -> handleNext());
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        loadNextQuestion();
    }

    private void addQuestions() {
        quiz.addQuestion(new Question("Capital of India?",
                new String[]{"Mumbai", "Delhi", "Kolkata", "Chennai"}, 2));
        quiz.addQuestion(new Question("Which company created Java?",
                new String[]{"Apple", "Sun Microsystems", "Google", "IBM"}, 2));
        quiz.addQuestion(new Question("Keyword for inheritance?",
                new String[]{"this", "extends", "import", "final"}, 2));
        quiz.addQuestion(new Question("Default int value?",
                new String[]{"null", "0", "1", "undefined"}, 2));
        quiz.addQuestion(new Question("Which is not a Java feature?",
                new String[]{"Object-Oriented", "Portable", "Fast", "Secure"}, 3));
    }

    private void loadNextQuestion() {
        if (!quiz.isFinished()) {
            Question q = quiz.getCurrentQuestion();
            questionLabel.setText(q.getQuestion());
            String[] opts = q.getOptions();
            for (int i = 0; i < 4; i++) {
                options[i].setText(opts[i]);
            }
            optionGroup.clearSelection();
            selectedOption = -1;
        } else {
            showResult();
        }
    }

    private void handleNext() {
        if (selectedOption == -1) {
            JOptionPane.showMessageDialog(this, "Please select an option!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        quiz.checkAnswer(selectedOption);
        loadNextQuestion();
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "🎉 Quiz Completed!\n\nYour Score: " + quiz.getScore() + "/" + quiz.getTotalQuestions(),
                "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().setVisible(true));
    }
}
