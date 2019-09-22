package erik.study.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class LayoutGridExample {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("JFrame title");

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);

        JButton jButton1 = new JButton("button 1");
        JButton jButton2 = new JButton("button 2");
        JButton jButton3 = new JButton("button 3");
        JButton jButton4 = new JButton("button 4");
        JButton jButton5 = new JButton("button 5");

        jFrame.add(jButton1);
        jFrame.add(jButton2);
        jFrame.add(jButton3);
        jFrame.add(jButton4);
        jFrame.add(jButton5);

        jFrame.setLayout(new GridLayout(4,5));
        jFrame.setVisible(true);

    }

}
