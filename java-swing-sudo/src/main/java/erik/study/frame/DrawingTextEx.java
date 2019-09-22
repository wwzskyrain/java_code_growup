package erik.study.frame;

import sun.awt.CGraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

class DrawTextPanel extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Font font = new Font("URW Chancery L", Font.BOLD, 21);
        g2d.setFont(font);

        g2d.drawString("55555555", 20, 30);
        g2d.setFont(new Font("TimesNewRomanPS-BoldMT", Font.PLAIN, 40));
        g2d.drawString("time", 40,70);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class DrawingTextEx extends JFrame {

    public DrawingTextEx() {

        initUI();
    }

    private void initUI() {

        DrawTextPanel drawPanel = new DrawTextPanel();
        add(drawPanel);

        setSize(500, 470);
        setTitle("Sonnet55");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            DrawingTextEx ex = new DrawingTextEx();
            ex.setVisible(true);
        });

        CGraphicsEnvironment cGraphicsEnvironment = new CGraphicsEnvironment();
        for (Font font : cGraphicsEnvironment.getAllFonts()) {
            System.out.println(font);
        }
    }
}