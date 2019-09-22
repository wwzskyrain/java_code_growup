package erik.study.sudo;

import javax.swing.*;
import java.awt.*;

public class TestSize {
   public static void main(String[] args) {
      JFrame frame = new JFrame("Display Area");
      Container container = frame.getContentPane();
      container.setLayout(new FlowLayout());
      JButton btnHello = new JButton("Hello");
      btnHello.setPreferredSize(new Dimension(150, 80));
      container.add(btnHello);
 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 150);  // or pack() the components
      frame.setLocationRelativeTo(null);  // center the application window
      frame.setVisible(true);             // show it
 
      System.out.println(btnHello.getSize());
      System.out.println(btnHello.getLocation());
      System.out.println(btnHello.getLocationOnScreen());
 
      System.out.println(container.getSize());
      System.out.println(container.getLocation());
      System.out.println(container.getLocationOnScreen());
 
      System.out.println(frame.getSize());
      System.out.println(frame.getLocation());
      System.out.println(frame.getLocationOnScreen());
   }
}