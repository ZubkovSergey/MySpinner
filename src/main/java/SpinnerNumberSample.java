import javax.swing.*;
import java.awt.*;

public class SpinnerNumberSample {
    public static void main(String args[]) {
        JFrame frame = new JFrame("JSpinner Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        SpinnerModel model1 = new SpinnerNumberModel();
        JSpinner spinner1 = new JSpinner(model1);

        JLabel label1 = new JLabel("Numbers");
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(label1, BorderLayout.WEST);
        panel1.add(spinner1, BorderLayout.CENTER);
        frame.add(panel1, BorderLayout.SOUTH);
//-----------------------------------------------------
        SpinnerNumberModel model = new SpinnerNumberModel(0,0,null,1000);
        ZSpinner spinner = new ZSpinner(model);
        frame.add(spinner, BorderLayout.NORTH);
//-----------------------------------------------------
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 90);
        frame.setVisible(true);
    }
}
