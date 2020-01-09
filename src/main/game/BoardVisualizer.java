package main.game;

import javax.swing.*;
import java.awt.*;

public class BoardVisualizer extends JPanel {

    private JFrame frame;
    private Board board;

    BoardVisualizer(Board board, JFrame frame){
        this.frame = frame;
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());

        for(Point snakeElement : board.getSnake().getBody()){
            g.setColor(Color.green);
            g.fillRect(
                    snakeElement.x * board.scale,
                    snakeElement.y * board.scale,
                    board.scale,
                    board.scale
            );
        }
    }
}
