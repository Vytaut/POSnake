package main.game;

import javax.swing.*;
import java.awt.*;

public class BoardVisualizer extends JPanel {

    private JFrame frame;
    private Board board;

    BoardVisualizer(Board board, JFrame frame){
        this.frame = frame;
        this.board = board;
        this.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0,frame.getWidth(),frame.getHeight());

        g.setColor(Color.green);
        for(Point snakeElement : board.getSnake().getBody()){
            g.fillRect(
                    snakeElement.x * board.getScale(),
                    snakeElement.y * board.getScale(),
                    board.getScale(),
                    board.getScale()
            );
        }

        g.setColor(Color.red);
        g.fillRect(
                board.getApple().getPosition().x*board.getScale(),
                board.getApple().getPosition().y*board.getScale(),
                board.getScale(),
                board.getScale()
        );
    }
}
