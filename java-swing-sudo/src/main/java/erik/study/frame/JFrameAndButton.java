package erik.study.frame;

import javax.swing.*;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class JFrameAndButton {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("jframe Title");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);

        JButton jButton1 = new JButton("Button 1 text");
        JButton jButton2 = new JButton("Button 2 text");

        jFrame.getContentPane().add(jButton1);
        jFrame.getContentPane().add(jButton2);

        jFrame.setVisible(true);
    }

}
