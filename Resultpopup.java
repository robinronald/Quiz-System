import javax.swing.*;

public class Resultpopup {
    public Resultpopup(int score, int total) {
        JOptionPane.showMessageDialog(null, "Your Score: " + score + "/" + total, "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
