package erik.study.frame;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @Date 2019-09-18
 * @Created by erik
 */

class GridJPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        System.out.println("doDrawing()");
        doDrawing(g);
    }

    void doDrawing(Graphics graphics) {
        System.out.println("doDrawing");
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.blue);
        g2d.drawLine(20, 5, 250, 5);

        int boxEgeLength = 20;

        int x = 10;
        int y = 10;

        for (int i = 0; i <= 9; i++) {
            g2d.drawLine(x, y + i * boxEgeLength, x + 9 * boxEgeLength, y + i * boxEgeLength);
        }

        for (int i = 0; i <= 9; i++) {
            g2d.drawLine(x + i * boxEgeLength, y, x + i * boxEgeLength, y + 9 * boxEgeLength);
        }
    }
}


public class DrawGrid extends JFrame {

    public DrawGrid() {
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            DrawGrid drawGrid = new DrawGrid();
            drawGrid.setVisible(true);
        });
    }

    void initUI() {
        GridJPanel gridJPanel = new GridJPanel();
        add(gridJPanel);

        setSize(350, 250);
        setTitle("Points");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
