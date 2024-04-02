package loginpage;

import javax.swing.*;
import java.awt.*;

public class loginpage extends JFrame {
    private JTextField usertext;
    private JPanel panel1;
    private JPasswordField password;
    private JButton loginButton;
    private JFrame frame;

    public loginpage(){
        frame = new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension( 250, 200));
        frame.setResizable(false);

//now add the panel
        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
