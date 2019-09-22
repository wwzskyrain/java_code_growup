package erik.study.sudo;

import com.google.common.collect.Sets;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class SudoData {

    private Element[] elements = new Element[81];

    private AtomicInteger blankElementCount = new AtomicInteger(81);

    public SudoData() {
    }

    public SudoData(String filePath) {

        init();
        try {
            URL url = getClass().getClassLoader().getResource(filePath);
            FileReader fileReader = new FileReader(new File(url.getFile()));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String lineData = null;
                while ((lineData = bufferedReader.readLine()) != null) {
                    String[] data = lineData.trim().split(",");
                    int rowNumber = Integer.valueOf(data[0]);
                    int columnNumber = Integer.valueOf(data[1]);
                    int value = Integer.valueOf(data[2]);
                    elements[(rowNumber - 1) * 9 + columnNumber - 1].setValue(value);
                    blankElementCount.decrementAndGet();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(new HashSet<>(), this, i);
        }
    }


    public SudoData(Element[] elements) {
        this.elements = elements;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }

    public void updateValueOfElement(Element element) {
        int index = element.getIndex();
        Set<Integer> candidateNumbers = getCandidateNumbersForElement(index);
        element.setCandidateNumbers(candidateNumbers);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            stringBuilder.append(elements[i]).append(",");
            if (i % 9 == 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public Set<Integer> getCandidateNumbersForElement(int index) {
        Set<Integer> candidateNumbers = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int X = index / 9;
        int Y = index % 9;
        Set<Integer> hasFilledNumbers = findValueByRowNo(X);
        hasFilledNumbers.addAll(findValuesByColumnNo(Y));
        hasFilledNumbers.addAll(findValuesByGongNo(convertToGongNo(index)));
        candidateNumbers.removeAll(hasFilledNumbers);
        return candidateNumbers;
    }

    /**
     * 获取指定列的element
     *
     * @param rowNo
     * @return
     */
    public Set<Integer> findValueByRowNo(int rowNo) {

        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            if (i / 9 == rowNo) {
                Integer value = elements[i].getValue();
                if (value != null) {
                    result.add(value);
                }

            }
        }
        return result;
    }

    /**
     * 获取指定列的element
     *
     * @param columnNo
     * @return
     */
    public Set<Integer> findValuesByColumnNo(int columnNo) {

        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            if (i % 9 == columnNo) {
                Integer value = elements[i].getValue();
                if (value != null) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    public Set<Integer> findValuesByGongNo(int gongNo) {
        Set<Integer> result = new HashSet<>();
        Set<Element> elements = getElementByGong(gongNo);
        for (Element element : elements) {
            Integer value = element.getValue();
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }

    public int convertToGongNo(int index) {
        return index / 27 * 3 + index % 9 / 3;
    }

    /**
     * 返回指定宫的九个元素
     *
     * @param gongNo
     * @return
     */
    public Set<Element> getElementByGong(int gongNo) {
        Set<Element> gongElement = new HashSet<>();
        int firstElementIndex = gongNo / 3 * 27 + gongNo % 3 * 3;
        for (int i = 0; i < 3; i++) {
            gongElement.add(elements[firstElementIndex]);
            gongElement.add(elements[firstElementIndex + 1]);
            gongElement.add(elements[firstElementIndex + 2]);
            firstElementIndex += 9;
        }
        return gongElement;
    }

    public Element getElement(int index) {
        return elements[index];
    }

    public void decreaseBlankCount() {
        blankElementCount.decrementAndGet();
        if (blankElementCount.get() <= 0) {
            System.out.println("success !!!");
        }

    }

}
