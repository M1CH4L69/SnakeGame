import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private long startTime;
    private int boardSize = 15;
    private int tileSize = 50;
    private int width = 750;
    private int height = 800;
    private String time = "00:00";
    private int direction = 3;
    private Timer timer;
    private boolean directionChanged = false;
    Random random = new Random();

    //region getters and setters

    public boolean isDirectionChanged() {
        return directionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        this.directionChanged = directionChanged;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    Snake snake = new Snake(boardSize / 2 - 3, boardSize / 2, 1);
    Apple apple = new Apple(boardSize / 2 - 2, boardSize / 2);

    //endregion
    //region constructor
    public GameBoard() {
        startTime = System.currentTimeMillis();
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        timer = new Timer(350, this);
        startGame();
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
//endregion

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //playground
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int x = j * tileSize;
                int y = i * tileSize;

                //playground
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(45, 79, 45));
                } else {
                    g.setColor(new Color(36, 55, 16));
                }
                g.fillRect(x, y, tileSize, tileSize);
            }
        }

        //Snake
        g.setColor(new Color(227, 0, 255));
        g.fillRect(snake.getSnakeX() * tileSize, snake.getSnakeY() * tileSize, tileSize, tileSize);

        //apple
        g.setColor(Color.red);
        g.fillRect(apple.getAppleX() * tileSize, apple.getAppleY() * tileSize, tileSize, tileSize);

        //score and time panel
        g.setColor(Color.gray);
        g.fillRect(0, 750, getWidth(), tileSize);

        //score and timetable
        g.setColor(Color.white );
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString("Score: " + snake.getScore(), 20, 780);
        g.drawString("Time: " + time, 610, 780);
    }

    public void moveSnake() {
        if (!directionChanged) {
            switch (direction) {
                case 0:
                    if (snake.getSnakeY() <= 0) { // up
                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeY(snake.getSnakeY() - 1);
                        grow();
                    }
                    break;
                case 1:
                    if (snake.getSnakeY() >= getBoardSize() - 1) { // down

                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeY(snake.getSnakeY() + 1);
                        grow();
                    }
                    break;
                case 2:
                    if (snake.getSnakeX() <= 0) { // left

                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeX(snake.getSnakeX() - 1);
                        grow();
                    }
                    break;
                case 3:
                    if (snake.getSnakeX() >= getBoardSize() - 1) { // right

                        timer.stop();
                        startGame();
                    } else {
                        snake.setSnakeX(snake.getSnakeX() + 1);
                        grow();
                    }
                    break;
            }
        }
        directionChanged = false;
    }
    public void startGame() {
        int result = JOptionPane.showOptionDialog(this,
                "Snake\nScore: " + snake.getScore() + "\nTime: " + time,
                "Snake Game", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new String[]{"Start Game", "Exit"}, "Start Game");

        if (result == 0) {
            resetGame();
        }else if(result == 1){
            System.exit(0);
        }
    }

    public void resetGame() {
        setTime("00:00");
        snake.setScore(0);
        snake.setSnakeX(boardSize / 2 - 3);
        snake.setSnakeY(boardSize / 2);
        direction = 3;
        directionChanged = false;
        timer.start();
        startTime = System.currentTimeMillis();
    }

    public void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        int seconds = (int) (elapsedTime / 1000) % 60;
        int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);

        time = String.format("%02d:%02d", minutes, seconds);
    }

    public void grow(){
        if (snake.getSnakeX() == apple.getAppleX() && snake.getSnakeY() == apple.getAppleY()){
            snake.setScore(snake.getScore()+1);
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(apple.getAppleX(), apple.getAppleY());
            apple.setAppleX(random.nextInt(boardSize));
            apple.setAppleY(random.nextInt(boardSize));
            snake.getBody().add(map);
            System.out.println(snake.getBody());
        }
    }

    //region ActionListener method
    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();
        updateTime();
        repaint();
    }
    //endregion
    //region KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                if (direction != 1 || direction != getDirection()) {
                    setDirection(0); // up
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_S:
                if (direction != 0) {
                    setDirection(1); // down
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_A:
                if (direction != 3) {
                    setDirection(2); // left
                    directionChanged = true;
                    moveSnake();
                }
                break;
            case KeyEvent.VK_D:
                if (direction != 2) {
                    setDirection(3); // right
                    directionChanged = true;
                    moveSnake();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    //endregion
}