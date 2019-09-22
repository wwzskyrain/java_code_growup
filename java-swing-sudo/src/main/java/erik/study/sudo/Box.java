package erik.study.sudo;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class Box extends JPanel {

    public static final int NUMBER_SIZE = 20;
    public static final int BORDER_WIDTH = 1;
    private Element element;

    public Box(List<Integer> elements) {
        super();
        this.element = new Element(new HashSet<>(elements));
        init();
    }

    public Box(Element element) {
        super();
        this.element = element;
        init();
    }

    public void init() {
        setSize(NUMBER_SIZE * 3, NUMBER_SIZE * 3);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_WIDTH));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Box box = (Box) e.getSource();
                Element element = box.getElement();
                System.out.println("鼠标点击事件" + element.toString());
                System.out.println("更新之前" + element);
                element.updateValue();
                System.out.println("更新之后" + element);
                box.repaint();
            }
        });
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    public static int getBoxSize() {
        return NUMBER_SIZE * 3 + 2 * BORDER_WIDTH;
    }

    private void doDrawing(Graphics g) {
        Graphics2D graphics2D = ((Graphics2D) g);
        Integer value = element.getValue();
        if (value != null) { //只有一个数字了，写大点,放在中央
            graphics2D.setFont(new Font("TimesNewRomanPS-BoldMT", Font.PLAIN, NUMBER_SIZE * 2));
            graphics2D.drawString(String.valueOf(value), NUMBER_SIZE * 0.85f, NUMBER_SIZE * 2.2f);
            return;
        }
        Set<Integer> numbers = element.getCandidateNumbers();
        for (int i = 0; i < 9; i++) {
            int targetNumber = i + 1;
            if (numbers.contains(targetNumber)) {
                graphics2D.setFont(new Font("TimesNewRomanPS-BoldMT", Font.PLAIN, NUMBER_SIZE));
                graphics2D.drawString(String.valueOf(targetNumber), NUMBER_SIZE * (i % 3 + 0.15f), NUMBER_SIZE * (i / 3 + 0.8f));
            }
        }

    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("title");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setLocation(400, 100);
        jFrame.setLayout(null);

        Container contentPane = jFrame.getContentPane();


        System.out.println(contentPane.getSize());

        Box box1 = new Box(new Element(new HashSet<>(Arrays.asList(1, 2, 3, 5, 6, 8))));
        Box box2 = new Box(new Element(new HashSet<>(Arrays.asList(5))));
        Box box3 = new Box(Arrays.asList(8));
        box1.setLocation(10, 10);
        box2.setLocation(15 + 3 * NUMBER_SIZE, 15 + 3 * NUMBER_SIZE);
        box3.setLocation(15 + 2 * 3 * NUMBER_SIZE, 15 + 2 * 3 * NUMBER_SIZE);
        contentPane.add(box1);
        contentPane.add(box2);
        contentPane.add(box3);

        jFrame.setVisible(true);
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
