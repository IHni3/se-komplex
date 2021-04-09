package generate;

public class Position {

    private final Box[] level = new Box[3];

    private int lvlCounter;


    public void storeBox(Box box) {
        setBox(lvlCounter, box);
        lvlCounter++;
    }

    public String getBoxID(int lvl) {
        return level[lvl].getBoxID();
    }

    public Box[] getBoxArray() {
        return level;
    }

    public void setBox(int i, Box box) {
        level[i] = box;
    }

    public int getLvlCounter() {
        return lvlCounter;
    }

    public void setLvlCounter(int lvlCounter) {
        this.lvlCounter = lvlCounter;
    }
}
