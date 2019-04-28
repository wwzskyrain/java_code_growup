package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.locks.Lock;


/**
 * @author erik.wang
 * @date 2019/04/28
 **/
public class FamilyMember {

    protected Plate plate;
    protected Lock plateLock;

    public FamilyMember() {
    }

    public FamilyMember(Plate plate, Lock plateLock) {
        this.plate = plate;
        this.plateLock = plateLock;
    }


    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Lock getPlateLock() {
        return plateLock;
    }

    public void setPlateLock(Lock plateLock) {
        this.plateLock = plateLock;
    }
}
