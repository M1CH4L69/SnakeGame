import java.util.ArrayList;
import java.util.HashMap;

public class Snake {

    private ArrayList<HashMap<Integer, Integer>>  body;
    private int snakeX;
    private int snakeY;
    private int snakeSize;
    private int score;
    private int direction = 3;
//region constructor

    public Snake(int snakeX, int snakeY, int snakeSize) {
        body = new ArrayList<>();
        this.snakeX = snakeX;
        this.snakeY = snakeY;
        this.snakeSize = snakeSize;
        score = 0;
    }

    //endregion
    //region getters and setters

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int snakeX) {
        this.snakeX = snakeX;
    }

    public int getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int snakeY) {
        this.snakeY = snakeY;
    }

    public int getSnakeSize() {
        return snakeSize;
    }

    public void setSnakeSize(int snakeSize) {
        this.snakeSize = snakeSize;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<HashMap<Integer, Integer>> getBody() {
        return body;
    }

    public void setBody(ArrayList<HashMap<Integer, Integer>> body) {
        this.body = body;
    }
//endregion
}
