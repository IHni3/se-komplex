package generate;

public class Position {

    Box[] level = new Box[3];
    int lvlCounter;


    public void storeBox(Box box) {
        level[lvlCounter] = box;
        lvlCounter++;
    }

    public String getBoxID(int lvl) {
        return level[lvl].getId();
    }

    public Box[] getBoxs() {
        return level;
    }

    public void setBox(int i, Box box) {
        level[i] = box;
    }
}
