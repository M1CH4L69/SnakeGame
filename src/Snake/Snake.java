package Snake;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a snake object
 */
public class Snake {

    /**
     * this variable stores the body of the snake
     */
    private ArrayList<HashMap<Integer, Integer>> body;
    /**
     * This variable specifies the X coordinate of the snake's head
     */
    private int snakeX;
    /**
     * This variable specifies the Y coordinate of the snake's head
     */
    private int snakeY;
    /**
     * This variable specifies the size of the snake
     */
    private int snakeSize;
    /**
     * This variable specifies the X score
     */
    private int score;
    /**
     * This variable determines the direction in which the snake moves
     */
    private int direction = 3;

//region constructor
public Snake(int snakeX, int snakeY, int snakeSize) {
    body = new ArrayList<>();
    HashMap<Integer, Integer> head = new HashMap<>();
    head.put(snakeX, snakeY);
    body.add(head);
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
