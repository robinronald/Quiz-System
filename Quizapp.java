import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quizapp {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel, timerLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private int score = 0;
    private int questionIndex = 0;
    private Timer timer;
    private int timeLeft = 15;

    private String[][] questions = {
            {"What is the name of Real Madrid’s home stadium?", "Camp Nou", "Wanda Metropolitano", "Old Trafford", "Santiago Bernabéu"},
            {"Who is Real Madrid’s all-time top goalscorer?", "Benzema", "Raul", "Alfredo Di Stéfano", "Cristiano Ronaldo"},
            {"Which club is Real Madrid’s biggest rival in El Clásico?", "Athletico Madrid", "Juventus", "Manchester United", "FC Barcelona"},
            {"Which country won the FIFA World Cup 2018?", "Brazil", "Germany", "Croatia", "France"},
            {"Who won the Ballon d'Or in 2023?", "Mbappé", "Benzema", "Messi", "Messi"},
            {"Which club does Erling Haaland play for (as of 2024)?", "Barcelona", "Real Madrid", "PSG", "Manchester City"},
            {"What position does Luka Modrić play?", "Forward", "Defender", "Goalkeeper", "Midfielder"},
            {"Who is known as 'The Egyptian King'?", "Hakimi", "Salah", "Mahrez", "Salah"},
            {"Which club has the most UEFA Champions League titles?", "AC Milan", "Barcelona", "Bayern Munich", "Real Madrid"},
            {"Which nation hosted the 2022 FIFA World Cup?", "Russia", "USA", "Brazil", "Qatar"}
    };

    public Quizapp() {
        frame = new JFrame("Football Quiz App");
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(173, 216, 230)); // Light sky blue
        questionLabel.setPreferredSize(new Dimension(400, 50));

        timerLabel = new JLabel("Time left: 15", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.RED);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        Font optionFont = new Font("Arial", Font.PLAIN, 14);
        option1.setFont(optionFont);
        option2.setFont(optionFont);
        option3.setFont(optionFont);
        option4.setFont(optionFont);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(30, 144, 255));
        nextButton.setForeground(Color.WHITE);

        panel.add(questionLabel);
        panel.add(timerLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(new JLabel()); // spacer
        panel.add(nextButton);

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loadQuestion();

        nextButton.addActionListener(e -> {
            timer.stop();
            checkAnswer();
            questionIndex++;
            if (questionIndex < questions.length) {
                loadQuestion();
            } else {
                new Resultpopup(score, questions.length);
                frame.dispose();
            }
        });
    }

    private void loadQuestion() {
        questionLabel.setText("Q" + (questionIndex + 1) + ": " + questions[questionIndex][0]);
        option1.setText(questions[questionIndex][1]);
        option2.setText(questions[questionIndex][2]);
        option3.setText(questions[questionIndex][3]);
        option4.setText(questions[questionIndex][4]);
        optionsGroup.clearSelection();
        timeLeft = 15;
        timerLabel.setText("Time left: " + timeLeft);
        startTimer();
    }

    private void checkAnswer() {
        String correctAnswer = questions[questionIndex][4];
        if ((option1.isSelected() && option1.getText().equals(correctAnswer)) ||
                (option2.isSelected() && option2.getText().equals(correctAnswer)) ||
                (option3.isSelected() && option3.getText().equals(correctAnswer)) ||
                (option4.isSelected() && option4.getText().equals(correctAnswer))) {
            score++;
        }
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up for this question!");
                    questionIndex++;
                    if (questionIndex < questions.length) {
                        loadQuestion();
                    } else {
                        new Resultpopup(score, questions.length);
                        frame.dispose();
                    }
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Quizapp();
    }
}
