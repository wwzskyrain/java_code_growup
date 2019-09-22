package erik.study.frame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class TryBorderLayout {
  public static void main(String[] args) {
    JFrame jFrame = new JFrame("This is a Border Layout");
    jFrame.setBounds(30, 30, 300, 300); // Size
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    BorderLayout border = new BorderLayout(); // Create a layout manager
    Container content = jFrame.getContentPane(); // Get the content pane
    content.setLayout(border); // Set the container layout mgr
    EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED); // Button border
    // Now add five JButton components and set their borders
    JButton button;
    content.add(button = new JButton("EAST"), BorderLayout.EAST);
    button.setBorder(edge);
    content.add(button = new JButton("WEST"), BorderLayout.WEST);
    button.setBorder(edge);
    content.add(button = new JButton("NORTH"), BorderLayout.NORTH);
    button.setBorder(edge);
    content.add(button = new JButton("SOUTH"), BorderLayout.SOUTH);
    button.setBorder(edge);
    content.add(button = new JButton("CENTER"), BorderLayout.CENTER);
    button.setBorder(edge);
    jFrame.setVisible(true); // Display the window
  }
}