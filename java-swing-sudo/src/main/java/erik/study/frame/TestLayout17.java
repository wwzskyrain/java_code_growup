package erik.study.frame;

import javax.swing.*;
import java.awt.*;

public class TestLayout17 {

    public static void main(String[] args) {
        new TestLayout17();
    }

    public TestLayout17() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);

            add(new JLabel("Label 1"), gridBagConstraints);
            gridBagConstraints.gridx++;
            add(new JLabel("Label 2"), gridBagConstraints);

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            add(new JTextField(10), gridBagConstraints);
            gridBagConstraints.gridx++;
            add(new JTextField(10), gridBagConstraints);

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.gridwidth = 2;
            add(new JButton("Click"), gridBagConstraints);

        }

    }

}