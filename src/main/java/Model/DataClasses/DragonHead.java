package Model.DataClasses;

import java.util.Comparator;

public class DragonHead implements Comparable<DragonHead>{
    private int eyesCount = 2;

    public DragonHead(){}

    public DragonHead(int eyesCount) {
        this.eyesCount = eyesCount;
    }

    public int getEyesCount() {
        return eyesCount;
    }

    public static void checkEyes(int eyesCount) {

    }

    /**
     * Сравнить два DragonHead по количеству глаз
     */
    @Override
    public int compareTo(DragonHead o) {
        return Integer.compare(this.getEyesCount(), o.getEyesCount());
    }
}