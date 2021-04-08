package generate;

public class Pallet {

    int id;
    int boxCounter;
    Position[][] position = new Position[2][2];

    Position position1 = new Position();
    Position position2 = new Position();
    Position position3 = new Position();
    Position position4 = new Position();


    public Pallet(int id) {
        this.id = id;
        position[0][0] = position1;
        position[0][1] = position2;
        position[1][0] = position3;
        position[1][1] = position4;
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
        position1.lvlCounter = 0;
        position2.lvlCounter = 0;
        position3.lvlCounter = 0;
        position4.lvlCounter = 0;
    }
}
