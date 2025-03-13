import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quizapp {
    private JFrame frame;
    private JPanel panel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private int score = 0;
    private int questionIndex = 0;

    // Questions and Answers
    private String[][] questions = {
            {"What is the name of Real Madrid’s home stadium?", "Santiago Bernabéu", "Camp Nou", "Wanda Metropolitano", "Old Trafford"},
            {"Who is Real Madrid’s all-time top goalscorer?", "Cristiano Ronaldo", "Benzema", "Raul", "Alfredo Di Stéfano"},
            {"Which club is Real Madrid’s biggest rival in El Clásico?", "5", "8", "10", "7", "8"}
    };

    public Quizapp() {
        frame = new JFrame("Online Quiz");
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel questionLabel = new JLabel();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        nextButton = new JButton("Next");

        panel.add(questionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(nextButton);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loadQuestion(questionLabel);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                questionIndex++;
                if (questionIndex < questions.length) {
                    loadQuestion(questionLabel);
                } else {
                    new Resultpopup(score, questions.length);
                    frame.dispose();
                }
            }
        });
    }

    private void loadQuestion(JLabel questionLabel) {
        questionLabel.setText(questions[questionIndex][0]);
        option1.setText(questions[questionIndex][1]);
        option2.setText(questions[questionIndex][2]);
        option3.setText(questions[questionIndex][3]);
        option4.setText(questions[questionIndex][4]);
        optionsGroup.clearSelection();
    }

    private void checkAnswer() {
        String correctAnswer = questions[questionIndex][5];
        if (option1.isSelected() && option1.getText().equals(correctAnswer) ||
                option2.isSelected() && option2.getText().equals(correctAnswer) ||
                option3.isSelected() && option3.getText().equals(correctAnswer) ||
                option4.isSelected() && option4.getText().equals(correctAnswer)) {
            score++;
        }
    }

    public static void main(String[] args) {
        new Quizapp();
    }
}
