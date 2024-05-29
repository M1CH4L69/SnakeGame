package Snake;

/**
 * A class representing an obstacle
 */
public class Rock {

    /**
     * This variable specifies the X coordinate of the obstacle
     */
    private int rockX;
    /**
     * This variable specifies the Y coordinate of the obstacle
     */
    private int rockY;

    //region constructor

    public Rock(int rockX, int rockY) {
        this.rockX = rockX;
        this.rockY = rockY;
    }

    //endregion
    //region getters and setters

    public int getRockX() {
        return rockX;
    }

    public void setRockX(int rockX) {
        this.rockX = rockX;
    }

    public int getRockY() {
        return rockY;
    }

    public void setRockY(int rockY) {
        this.rockY = rockY;
    }

    //endregion
}

