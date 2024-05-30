package Snake;

import java.io.Serializable;

/**
 * A class representing an obstacle
 */
public class Rock implements Suplements, Serializable {

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

    public void setRockX(int rockX) {
        this.rockX = rockX;
    }

    public void setRockY(int rockY) {
        this.rockY = rockY;
    }

    @Override
    public int getX() {
        return rockX;
    }

    @Override
    public int getY() {
        return rockY;
    }

    //endregion
}