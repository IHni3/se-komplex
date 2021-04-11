package physicals;

public class Pallet {

    int palletID;
    int boxCounter;
    private final Position[][] position = new Position[2][2];


    public Pallet(int id) {
        this.palletID = id;
        position[0][0] = new Position();
        position[0][1] = new Position();
        position[1][0] = new Position();
        position[1][1] = new Position();
    }

    public void storeBox(Box box) {
        if (boxCounter <= 3) {
            if (boxCounter <= 1) {
                position[0][boxCounter].storeBox(box);
            } else {
                position[1][boxCounter - 2].storeBox(box);
            }
            boxCounter++;
        } else {
            // next level
            boxCounter = 0;
            storeBox(box);
        }
    }

    public int getPalletID() {
        return palletID;
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
