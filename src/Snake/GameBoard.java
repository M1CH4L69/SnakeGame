package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * GameBoard is a class to store all dimensions and functions for a game
 */
public class GameBoard extends JPanel implements ActionListener, KeyListener {
    /**
     * This variable is used to hold the initial time when the game starts or is restarted
     */
    private long startTime;
    /**
     * This variable means the size of the playing field in tiles
     */
    private int boardSize = 15;
    /**
     * This variable represents the size of the tile in pixels
     */
    private int tileSize = 50;
    /**
     * This variable represents the width of the game board in pixels
     */
    private int width = 750;
    /**
     * This variable represents the height of the game board in pixels
     */
    private int height = 800;
    /**
     * This variable represents a text string of time
     */
    private String time = "00:00";
    /**
     * This variable represents a timer
     */
    private Timer timer;
    /**
     * This variable represents the truth value of whether the snake's direction of movement has changed or not
     */
    private boolean directionChanged = false;

    /**
     * This variable represents a delay
     */
    private double delay = 380;
    /**
     * This variable represents a sheet of rocks (obstacles) on the playing field
     */
    private ArrayList<Rock> rocks = new ArrayList<>();
    Random random = new Random();
    /**
     * This variable represents a snake object
     */
    private Snake snake = new Snake(boardSize / 2 - 3, boardSize / 2, 1);
    /**
     * This variable represents an apple object
     */
    private Apple apple = new Apple(boardSize / 2 - 2, boardSize / 2);
    //region getters and setters
    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public ArrayList<Rock> getRocks() {
        return rocks;
    }

    public void setRocks(ArrayList<Rock> rocks) {
        this.rocks = rocks;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setTime(String time) {
        this.time = time;
    }
//endregion
    //region constructor
    public GameBoard() {
        startTime = System.currentTimeMillis();
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        timer = new Timer((int) delay, this);
        startGame();
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
//endregion

    /**
     * This method is intended for the graphic part of the project
     * In the first part to create the playing field (inner cycle for rows, outer cycle is for columns) and is supplemented with a condition that guarantees that the field will be two-colored like a checkerboard
     * In the second part for the rendered snake. It goes through its entire body thanks to the iterator, and the head is painted in blue, the rest of the snake in pink. The snake's body is a few pixels smaller than the size of the tile
     * In the third part for rendering the apple
     * In the fourth part for rendering the obstacles
     * In the fifth part, the score and time panel + score and time display is drawn
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //playground
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int x = j * tileSize;
                int y = i * tileSize;

                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(45, 79, 45));
                } else {
                    g.setColor(new Color(36, 55, 16));
                }
                g.fillRect(x, y, tileSize, tileSize);
            }
        }
        //snake
        ArrayList<HashMap<Integer, Integer>> body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            HashMap<Integer, Integer> segment = body.get(i);
            for (Iterator<Integer> it = segment.keySet().iterator(); it.hasNext(); ) {
                Integer x = it.next();
                Integer y = segment.get(x);
                if (i == 0) {
                    g.setColor(new Color(101, 0, 255));
                } else {
                    g.setColor(new Color(227, 0, 255));
                }
                g.fillRect(x * tileSize + 3, y * tileSize + 3, tileSize - 6, tileSize - 6);
            }
        }

        // Snake.Snake.Apple
        g.setColor(Color.red);
        g.fillOval(apple.getX() * tileSize + 3, apple.getY() * tileSize + 3, tileSize - 6, tileSize - 6);

        // Rocks
        g.setColor(Color.black);
        for (Rock rock : rocks) {
            g.fillRect(rock.getX() * tileSize, rock.getY() * tileSize, tileSize, tileSize);
        }

        // Score and time panel
        g.setColor(Color.gray);
        g.fillRect(0, 750, getWidth(), tileSize);

        // Score and time display
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString("Score: " + snake.getScore(), 20, 780);
        g.drawString("Time: " + time, 610, 780);
    }

    /**
     * This method checks whether the snake has hit an obstacle by going through the entire list of stones and comparing the coordinates of the snake with the coordinates of the obstacle
     * Furthermore, the isCollisionWithSelf method is used to check whether it has collided with another part of its body
     */

    public void check() {
        Iterator<Rock> iterator = rocks.iterator();
        while (iterator.hasNext()) {
            Rock rock = iterator.next();
            if (snake.getSnakeX() == rock.getX() && snake.getSnakeY() == rock.getY()) {
                timer.stop();
                startGame();
                break;
            }
        }

        if (snake.isCollisionWithSelf()) {
            timer.stop();
            startGame();
        }
    }

    /**
     * A method for moving the snake and checking if the snake has hit the edge of the board
     * After crashing or otherwise ending the game, a panel will appear in which a new game can be started
     */
    public void moveSnake() {
        if (!directionChanged) {
            HashMap<Integer, Integer> newHead = new HashMap<>();
            switch (snake.getDirection()) {
                case 0:
                    if (snake.getSnakeY() <= 0) {
                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeY(snake.getSnakeY() - 1);
                    }
                    break;
                case 1:
                    if (snake.getSnakeY() >= getBoardSize() - 1) {
                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeY(snake.getSnakeY() + 1);
                    }
                    break;
                case 2:
                    if (snake.getSnakeX() <= 0) {
                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeX(snake.getSnakeX() - 1);
                    }
                    break;
                case 3:
                    if (snake.getSnakeX() >= getBoardSize() - 1) {
                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeX(snake.getSnakeX() + 1);
                    }
                    break;
            }
            newHead.put(snake.getSnakeX(), snake.getSnakeY());
            snake.getBody().add(0, newHead);

            if (!grow()) {
                snake.getBody().remove(snake.getBody().size() - 1);
            }
            check();
        }
        directionChanged = false;
    }

    /**
     * Method for starting the game
     * A window will appear where the user can decide whether he wants to play or turn off the game
     * If the user selects a new game, stones and a snake will appear on the playing field and the user can play immediately. If he clicks Exit, the game will shut down
     */

    public void startGame() {
        rocks.clear();
        for (int i = 0; i < 2; i++) {
            Rock rock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            while (isOccupied(rock.getX(), rock.getY())) {
                rock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            }
            rocks.add(rock);
        }
        int result = JOptionPane.showOptionDialog(this, "Snake\nScore: " + snake.getScore() + "\nTime: " + time, "Snake.Snake Game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Start Game", "Exit"}, "Start Game");

        if (result == 0) {
            resetGame();
        } else if (result == 1) {
            System.exit(0);
        }
    }

    /**
     * A method to reset the game
     * the time is set to 00:00, the score is set to 0, the snake's body is set to its original size, the snake's direction is set to the right, the first apple is set right next to the snake's head in the direction of its movement, and the obstacle sheet is cleared
     */
    public void resetGame() {
        setTime("00:00");
        snake.setScore(0);
        snake.getBody().clear();
        HashMap<Integer, Integer> head = new HashMap<>();
        snake.setSnakeX(boardSize / 2 - 3);
        snake.setSnakeY(boardSize / 2);
        head.put(snake.getSnakeX(), snake.getSnakeY());
        snake.getBody().add(head);
        snake.setDirection(3);
        apple.setAppleX(boardSize / 2 - 2);
        apple.setAppleY(boardSize / 2);
        directionChanged = false;
        timer.start();
        startTime = System.currentTimeMillis();
        rocks.clear();
    }

    /**
     * A method for counting time
     * The number of obstacles on the board is also updated here
     */
    public void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        int seconds = (int) (elapsedTime / 1000) % 60;
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);

        time = String.format("%02d:%02d", minutes, seconds);
        if (seconds % 20 == 0) {
            Rock newRock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            while (isOccupied(newRock.getX(), newRock.getY())) {
                newRock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            }
            rocks.add(newRock);
        }
    }

    /**
     * This method checks if the snake has eaten the apple and grows the snake if it has
     * If the snake's head is at the same position as the apple, the snake's score is increased, a new apple is placed on the board, and the method returns {@code true} and delay is updated.
     * Otherwise, the method returns {@code false}.
     * @return {@code true} if the snake has eaten the apple and grown, {@code false} otherwise
     */
    public boolean grow() {
        if (snake.getSnakeX() == apple.getX() && snake.getSnakeY() == apple.getY()) {
            snake.setScore(snake.getScore() + 1);
            placeApple();
            delay *= 0.98;
            timer.setDelay((int) delay);
            //System.out.println(delay);
            return true;
        }
        return false;
    }

    /**
     * A method for placing an apple on the board
     */

    public void placeApple() {
        do {
            apple.setAppleX(random.nextInt(boardSize));
            apple.setAppleY(random.nextInt(boardSize));
        } while (isOccupied(apple.getX(), apple.getY()));
    }

    /**
     * This method checks if the specified coordinates (x, y) are occupied by the snake or a rock
     * The method iterates through the snake's body segments and the list of rocks to determine if any segment or rock is at the specified coordinates
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return {@code true} if the coordinates are occupied by the snake or a rock, {@code false} otherwise
     */
    public boolean isOccupied(int x, int y) {
        for (HashMap<Integer, Integer> segment : snake.getBody()) {
            if (segment.containsKey(x) && segment.get(x) == y) {
                return true;
            }
        }
        for (Rock rock : rocks) {
            if (rock.getX() == x && rock.getY() == y) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
        updateTime();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * A method that takes input from the keyboard
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                if (snake.getDirection() != 1) {
                    snake.setDirection(0); // up
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_S:
                if (snake.getDirection() != 0) {
                    snake.setDirection(1); // down
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_A:
                if (snake.getDirection() != 3) {
                    snake.setDirection(2); // left
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_D:
                if (snake.getDirection() != 2) {
                    snake.setDirection(3); // right
                    directionChanged = true;
                    moveSnake();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}