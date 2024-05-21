public class Rock {
    private int rockX;
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

