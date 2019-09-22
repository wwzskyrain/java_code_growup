package erik.study.sudo;

import com.alibaba.fastjson.JSON;

import java.util.Set;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class Element implements Comparable<Element> {

    private Set<Integer> candidateNumbers;
    private Integer value;
    private SudoData sudoData;
    private int index;

    public Element(Set<Integer> candidateNumbers, SudoData sudoData, int index) {
        this.candidateNumbers = candidateNumbers;
        this.sudoData = sudoData;
        this.index = index;
    }

    public Element(Set<Integer> candidateNumbers) {
        this.candidateNumbers = candidateNumbers;
        this.sudoData = null;
    }

    public Set<Integer> getCandidateNumbers() {
        return candidateNumbers;
    }

    public void setCandidateNumbers(Set<Integer> candidateNumbers) {
        this.candidateNumbers = candidateNumbers;
        if (candidateNumbers.size() == 1) {
            value = candidateNumbers.iterator().next();
            sudoData.decreaseBlankCount();
        }
    }

    public SudoData getSudoData() {
        return sudoData;
    }

    public void setSudoData(SudoData sudoData) {
        this.sudoData = sudoData;
    }

    @Override
    public String toString() {
        return "Element{" +
                "candidateNumbers=" + candidateNumbers +
                ", value=" + value +
                ", index=" + index +
                '}';
    }

    @Override
    public int compareTo(Element o) {
        return Integer.valueOf(index).compareTo(o.index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void updateValue() {
        if (value == null) {
            sudoData.updateValueOfElement(this);
        }


    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
