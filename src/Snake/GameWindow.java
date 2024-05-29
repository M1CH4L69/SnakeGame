package Snake;

import javax.swing.*;

/**
 * A class representing a JPanel
 */
public class GameWindow extends JFrame{

    //region constructor
    public GameWindow() {
        this.add(new GameBoard());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //endregion
}