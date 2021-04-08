package generate;

public class Pallet {

    int id;
    int boxCounter;
    private final Position[][] position = new Position[2][2];


    public Pallet(int id) {
        this.id = id;
        position[0][0] = new Position();
        position[0][1] = new Position();
        position[1][0] = new Position();
        position[1][1] = new Position();
    }

    public void storeBox(Box box) {
        if (boxCounter < 4) {
            if (boxCounter < 2) {
                position[0][boxCounter].storeBox(box);
                boxCounter++;
            } else {
                position[1][boxCounter - 2].storeBox(box);
                boxCounter++;
            }
        } else {
            // next level
            boxCounter = 0;
            storeBox(box);
        }
    }

    public int getID() {
        return id;
    }

    public String getBoxID(int x, int y, int lvl) {
        return position[x][y].getBoxID(lvl);
    }

    public Position[][] getPosition() {
        return position;
    }

    public void resetCounter() {
        boxCounter = 0;

        position[0][0].setLvlCounter(0);
        position[0][1].setLvlCounter(0);
        position[1][0].setLvlCounter(0);
        position[1][1].setLvlCounter(0);
    }
}
