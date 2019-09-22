package erik.study.sudo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Set;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class Sudo {


    public static void main(String[] args) {
        JFrame jFrame = new JFrame("JFrame title");

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);

        jFrame.setLayout(null);
        SudoData sudoData = new SudoData("file/sudo-2");

        for (int i = 0; i < 9; i++) {
            Set<Element> elements = sudoData.getElementByGong(i);
            Block block = new Block(elements);
            int blockSize = block.getBlockSize();
            block.setLocation(i % 3 * blockSize, i / 3 * blockSize);
            jFrame.add(block);
        }
        jFrame.setVisible(true);
    }

}
