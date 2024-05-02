import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private int boardSize = 15;
    private int tileSize = 50;
    private int width = 750;
    private int height = 800;
    private int score = 0;
    private String time = "00:00";
    private int direction = 3;
    private Timer timer;
    private boolean directionChanged = false;


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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    Snake snake = new Snake(boardSize / 2 - 3, boardSize / 2, 1);

    //endregion
    //region constructor
    public GameBoard() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setBackground(Color.black);
        timer = new Timer(350, this);
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

        //score and time panel
        g.setColor(Color.gray);
        g.fillRect(0, 750, getWidth(), tileSize);

        //score and timetable
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString("Score: " + score, 20, 780);
        g.drawString("Time: " + time, 610, 780);
    }

    private void moveSnake() {
        if (!directionChanged) {
            switch (direction) {
                case 0:
                    snake.setSnakeY(snake.getSnakeY() - 1); // up
                    break;
                case 1:
                    snake.setSnakeY(snake.getSnakeY() + 1); // down
                    break;
                case 2:
                    snake.setSnakeX(snake.getSnakeX() - 1); // left
                    break;
                case 3:
                    snake.setSnakeX(snake.getSnakeX() + 1); // right
                    break;
            }
        }
        directionChanged = false;
    }

    private void updateTime() {
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