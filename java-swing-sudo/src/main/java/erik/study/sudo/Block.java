package erik.study.sudo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @Date 2019-09-22
 * @Created by erik
 */
public class Block extends JPanel {

    private Box[] boxes = new Box[9];
    private int boxSize = Box.getBoxSize();
    private int blockBorderSize = 2;

    public Block(Box[] boxes) {
        super();
        this.boxes = boxes;
        init();
    }

    public Block(Set<Element> elements) {

        List<Element> elementArrayList = new ArrayList<>(elements);
        Collections.sort(elementArrayList);
        for (int i = 0; i < elementArrayList.size(); i++) {
            boxes[i] = new Box(elementArrayList.get(i));
        }
        init();
    }

    public void setBoxes(Box[] boxes) {
        this.boxes = boxes;
    }

    public int getBlockSize() {
        return boxSize * 3 + 2 * blockBorderSize;
    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("title");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setLocation(400, 100);
        jFrame.setLayout(null);

        Container contentPane = jFrame.getContentPane();


        System.out.println(contentPane.getSize());
        Box[] boxes = new Box[9];
        for (int i = 0; i < 8; i++) {
            boxes[i] = new Box(Arrays.asList(i));
        }
        boxes[8] = new Box(Arrays.asList(1, 2, 3));
        contentPane.add(new Block(boxes));

        jFrame.setVisible(true);
    }

    private void init() {
        setLayout(null);
        setSize(getBlockSize(), getBlockSize());
        for (int i = 0; i < boxes.length; i++) {
            boxes[i].setLocation(i % 3 * boxSize + blockBorderSize, i / 3 * boxSize + blockBorderSize);
            add(boxes[i]);
        }
        setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        setVisible(true);
    }

    @Override
    public String toString() {

        return Arrays.toString(boxes);
    }
}
