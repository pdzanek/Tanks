import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Chat extends Thread{
    volatile String text="";
    private Tanks game;
    final JTextArea t1 = new JTextArea(10, 10);
    Chat(Tanks game) {
        initComponents();
        this.game=game;
    }

    public void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
        });
    }
    private void initComponents() {

        JFrame jFrame = new JFrame("Czat...");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JLabel l1 = new JLabel("Czat gracza nr: "+Client.connectionNumber);
        JScrollPane pane = new JScrollPane(t1);
        JLabel l2 = new JLabel("Wiadomość");
        final JTextField t2 = new JTextField(10);
        JButton b1 = new JButton("Wyślij");

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(l1, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(pane, gc);

        GridBagConstraints gc2 = new GridBagConstraints();
        gc2.fill = GridBagConstraints.HORIZONTAL;
        gc2.weightx = 1;
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.ipadx = 10;
        p2.add(l2, gc2);

        gc2.gridx = 1;
        gc2.gridy = 0;
        p2.add(t2, gc2);

        gc2.gridx = 1;
        gc2.gridy = 1;
        p2.add(b1, gc2);

        b1.addActionListener(ev -> {
            game.messageToSend= t2.getText();
            t2.setText("");
            game.change=true;
        });

        jFrame.add(p1, BorderLayout.CENTER);
        jFrame.add(p2, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setVisible(true);

    }
    synchronized void makeTextAreaGreatAgain(){
        t1.setText(text);
    }
}