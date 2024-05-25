import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private long startTime;
    private int boardSize = 15;
    private int tileSize = 50;
    private int width = 750;
    private int height = 800;
    private String time = "00:00";
    private Timer timer;
    private boolean directionChanged = false;
    Random random = new Random();
    private ArrayList<Rock> rocks = new ArrayList<>();
//region getters and setters
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
        timer = new Timer(380, this);
        startGame();
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
//endregion
    Snake snake = new Snake(boardSize / 2 - 3, boardSize / 2, 1);
    Apple apple = new Apple(boardSize / 2 - 2, boardSize / 2);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Playground
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
        // Snake
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

        // Apple
        g.setColor(Color.red);
        g.fillOval(apple.getAppleX() * tileSize + 3, apple.getAppleY() * tileSize + 3, tileSize - 6, tileSize - 6);

        // Rocks
        g.setColor(Color.black);
        for (Rock rock : rocks) {
            g.fillRect(rock.getRockX() * tileSize, rock.getRockY() * tileSize, tileSize, tileSize);
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

    public void check() {
        Iterator<Rock> iterator = rocks.iterator();
        while (iterator.hasNext()) {
            Rock rock = iterator.next();
            if (snake.getSnakeX() == rock.getRockX() && snake.getSnakeY() == rock.getRockY()) {
                timer.stop();
                startGame();
                break;
            }
        }

        if (isCollisionWithSelf()) {
            timer.stop();
            startGame();
        }
    }

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

    public void startGame() {
        rocks.clear();
        for (int i = 0; i < 5; i++) {
            Rock rock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            while (isOccupied(rock.getRockX(), rock.getRockY())) {
                rock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            }
            rocks.add(rock);
        }
        int result = JOptionPane.showOptionDialog(this, "Snake\nScore: " + snake.getScore() + "\nTime: " + time, "Snake Game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Start Game", "Exit"}, "Start Game");

        if (result == 0) {
            resetGame();
        } else if (result == 1) {
            System.exit(0);
        }
    }

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

    public void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        int seconds = (int) (elapsedTime / 1000) % 60;
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);

        time = String.format("%02d:%02d", minutes, seconds);
        if (seconds % 20 == 0) {
            Rock newRock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            while (isOccupied(newRock.getRockX(), newRock.getRockY())) {
                newRock = new Rock(random.nextInt(boardSize), random.nextInt(boardSize));
            }
            rocks.add(newRock);
        }
    }

    public boolean grow() {
        if (snake.getSnakeX() == apple.getAppleX() && snake.getSnakeY() == apple.getAppleY()) {
            snake.setScore(snake.getScore() + 1);
            placeApple();
            return true;
        }
        return false;
    }

    public void placeApple() {
        do {
            apple.setAppleX(random.nextInt(boardSize));
            apple.setAppleY(random.nextInt(boardSize));
        } while (isOccupied(apple.getAppleX(), apple.getAppleY()));
    }

    public boolean isOccupied(int x, int y) {
        for (HashMap<Integer, Integer> segment : snake.getBody()) {
            if (segment.containsKey(x) && segment.get(x) == y) {
                return true;
            }
        }
        for (Rock rock : rocks) {
            if (rock.getRockX() == x && rock.getRockY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isCollisionWithSelf() {
        HashMap<Integer, Integer> head = snake.getBody().get(0);
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getBody().get(i).equals(head)) {
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
