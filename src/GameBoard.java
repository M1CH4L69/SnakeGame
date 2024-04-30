import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.*;

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
    Snake snake = new Snake(boardSize / 2 - 3,boardSize / 2, 1);

    //endregion
    //region constructor
    public GameBoard() {

        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setBackground(Color.black);
        timer = new Timer(500, this);
        timer.start();


        addKeyListener(this);
        setFocusable(true);
    }
//endregion
    
    public void startGame(){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //playground
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int x = j * tileSize;
                int y = i * tileSize;

                //playground
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(10,250,10));
                } else {
                    g.setColor(new Color(131, 179, 89));
                }
                g.fillRect(x, y, tileSize, tileSize);
            }
        }

        //Snake
        g.setColor(Color.black);
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

        switch (direction) {
            case 0:
                snake.setSnakeY(snake.getSnakeY()-1);
                break;
            case 1:
                snake.setSnakeY(snake.getSnakeY()+1);
                break;
            case 2:
                snake.setSnakeX(snake.getSnakeX()-1);
                break;
            case 3:
                snake.setSnakeX(snake.getSnakeX()+1);
                break;
        }
    }
    private void updateTime() {
        //aktualizace casu
        //inkrementace sekund
        //aktualiyace zobrazeni casu
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

        //zmena smeru podle stisknute klavesi
        switch (key) {
            case KeyEvent.VK_W:
                if (direction != 1){
                    direction = 0; //up
                    //directionChanged = true;
                }
                break;
            case KeyEvent.VK_S:
                if (direction != 0){
                    direction = 1; //down
                    //directionChanged = true;
                }
                break;
            case KeyEvent.VK_A:
                if (direction != 3){
                    direction = 2; //left
                    //directionChanged = true;
                }
                break;
            case KeyEvent.VK_D:
                if (direction != 2){
                    direction = 3; //right
                    //directionChanged = true;
                }
                break;
        }
        moveSnake();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    //endregion
}