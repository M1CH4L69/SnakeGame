import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {
    private int height = 750;
    private int width = 750;
    private int score = 0;

    //region getters and setters
    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    //endregion

    //region constructor
    public GameBoard() {
        this.setPreferredSize(new Dimension(getHeight(), getWidth()));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }
    //endregion

    public void startGame(){
    }


//region method for writing score
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), 40);

        g.setColor(Color.black);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString("Score: " + getScore(), 10, 25);
    }
//endregion

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
