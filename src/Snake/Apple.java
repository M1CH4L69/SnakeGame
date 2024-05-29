package Snake;

/**
 * A class representing an apple
 */
public class Apple {

    /**
     * This variable specifies the X coordinate of the apple
     */
    private int appleX;
    /**
     * This variable specifies the Y coordinate of the apple
     */
    private int appleY;

    //region constructor

    public Apple(int appleX, int appleY) {
        this.appleX = appleX;
        this.appleY = appleY;
    }

    //endregion
    //region getters and setters

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    //endregion
}
