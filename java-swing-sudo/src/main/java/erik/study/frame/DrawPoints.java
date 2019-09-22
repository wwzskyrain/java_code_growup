package erik.study.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

class DrawPanel extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        for (int i = 0; i <= 1000; i++) {

            Dimension size = getSize();
            Insets insets = getInsets();

            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            Random random = new Random();
            int x = Math.abs(random.nextInt()) % w;
            int y = Math.abs(random.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class DrawPoints extends JFrame {

    public DrawPoints() {
        
        initUI();
    }

    private void initUI() {

        DrawPanel drawPanel = new DrawPanel();
        add(drawPanel);

        setSize(350, 250);
        setTitle("Points");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            DrawPoints ex = new DrawPoints();
            ex.setVisible(true);
        });
    }
}