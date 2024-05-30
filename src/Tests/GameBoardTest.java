package Tests;

import static org.junit.jupiter.api.Assertions.*;

import Snake.Apple;
import Snake.GameBoard;
import Snake.Snake;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoardTest {
    GameBoard gameBoard = new GameBoard();

    @Test
    public void collision() {
        //snake 1 expect true

        Snake snake = new Snake(4, 4, 5);
        ArrayList<HashMap<Integer, Integer>> body = new ArrayList<>();

        HashMap<Integer, Integer> head = new HashMap<>();
        head.put(4, 4);
        body.add(head);

        HashMap<Integer, Integer> segment1 = new HashMap<>();
        segment1.put(4, 5);
        body.add(segment1);

        HashMap<Integer, Integer> segment2 = new HashMap<>();
        segment2.put(5, 5);
        body.add(segment2);

        HashMap<Integer, Integer> segment3 = new HashMap<>();
        segment3.put(5, 4);
        body.add(segment3);

        HashMap<Integer, Integer> segment4 = new HashMap<>();
        segment4.put(4, 4);
        body.add(segment4);

        snake.setBody(body);
        gameBoard.setSnake(snake);

        assertTrue(snake.isCollisionWithSelf());

        //snake 2 expect false

        Snake snake2 = new Snake(4, 4, 5);
        ArrayList<HashMap<Integer, Integer>> body2 = new ArrayList<>();

        HashMap<Integer, Integer> head2 = new HashMap<>();
        head2.put(4, 4);
        body2.add(head2);

        HashMap<Integer, Integer> segment12 = new HashMap<>();
        segment12.put(4, 5);
        body2.add(segment12);

        HashMap<Integer, Integer> segment22 = new HashMap<>();
        segment22.put(5, 5);
        body2.add(segment22);

        HashMap<Integer, Integer> segment32 = new HashMap<>();
        segment32.put(5, 4);
        body2.add(segment32);

        HashMap<Integer, Integer> segment42 = new HashMap<>();
        segment42.put(5, 3);
        body2.add(segment42);

        snake2.setBody(body2);
        gameBoard.setSnake(snake2);

        assertFalse(snake2.isCollisionWithSelf());
    }

    @Test
    public void makingRocks() {
        assertNotNull(gameBoard.getRocks());
    }

    @Test
    public void getGameBoardSize() {
        assertEquals(gameBoard.getBoardSize(), 15);
        gameBoard.setBoardSize(10);
        assertEquals(gameBoard.getBoardSize(), 10);
    }

    @Test
    public void drawAnApple() {
        Apple apple = gameBoard.getApple();
        assertEquals(apple.getX(), 5);
        assertEquals(apple.getY(), 7);
    }

    @Test
    public void isAnAppleOnTheSnake() {

        Snake snake = new Snake(4, 4, 4);
        ArrayList<HashMap<Integer, Integer>> body = new ArrayList<>();
        HashMap<Integer, Integer> head = new HashMap<>();
        head.put(4, 4);
        body.add(head);

        HashMap<Integer, Integer> segment1 = new HashMap<>();
        segment1.put(4, 5);
        body.add(segment1);

        HashMap<Integer, Integer> segment2 = new HashMap<>();
        segment2.put(5, 5);
        body.add(segment2);

        HashMap<Integer, Integer> segment3 = new HashMap<>();
        segment3.put(5, 4);
        body.add(segment3);

        snake.setBody(body);
        gameBoard.setSnake(snake);

        Apple apple = new Apple(4, 5);
        gameBoard.placeApple();

        boolean isAppleOnSnake = false;
        for (HashMap<Integer, Integer> segment : body) {
            int x = segment.keySet().iterator().next();
            int y = segment.get(x);
            if (x == apple.getX() && y == apple.getY()) {
                isAppleOnSnake = true;
                break;
            }
        }
        assertTrue(isAppleOnSnake);

        Apple apple2 = new Apple(7, 7);
        gameBoard.placeApple();

        isAppleOnSnake = false;
        for (HashMap<Integer, Integer> segment : body) {
            int x = segment.keySet().iterator().next();
            int y = segment.get(x);
            if (x == apple2.getX() && y == apple2.getY()) {
                isAppleOnSnake = true;
                break;
            }
        }
        assertFalse(isAppleOnSnake);
    }
}