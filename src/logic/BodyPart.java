package logic;

public class BodyPart {
    private int currY;
    private int currX;
    private int nextY;
    private int nextX;
    private int value;

    public BodyPart(int currY, int currX, int nextY, int nextX, int value) {
        this.currY = currY;
        this.currX = currX;
        this.nextY = nextY;
        this.nextX = nextX;
        this.value = value;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getCurrY() {
        return currY;
    }

    public int getCurrX() {
        return currX;
    }

    public int getNextY() {
        return nextY;
    }

    public int getNextX() {
        return nextX;
    }

    public int getValue() {
        return value;
    }
}
