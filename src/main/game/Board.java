package main.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener {

    private Vector2D lowerLeft;
    private Vector2D upperRight;
    private Apple apple;
    private Snake snake;
    private JFrame gameFrame = new JFrame("Snake!");
    private JPanel gamePanel = new JPanel();
    public Timer timer = new Timer(20,this);
    public int scale = 10;

    Board(int width, int height){
        this.lowerLeft = new Vector2D(0,0);
        this.upperRight = new Vector2D(width-1,height-1);
        this.apple = new Apple(new Vector2D(3*height/4,height/2));
        this.snake = new Snake(new Vector2D(width/2,height/2),3);
    }

    public Apple getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    private void generateApple(){
        Vector2D applePosition;

        do{
            applePosition = new Vector2D(
                    (int)(Math.random()*this.upperRight.getX()),
                    (int)(Math.random()*this.upperRight.getY())
            );
        }while(!(
                applePosition.follows(lowerLeft) &&
                applePosition.precedes(upperRight) &&
                !this.snake.getBody().contains(applePosition)) &&
                !this.snake.getHead().equals(applePosition)
        );

        this.apple = new Apple(applePosition);
    }

    private boolean validateSnakePosition(){
        return this.snake.getHead().follows(lowerLeft) && this.snake.getHead().precedes(upperRight);
    }

    private String gameOver(){
        return "Game over! Your score is:" + ( this.snake.getBody().size() - 3) + "!";
    }

    public boolean isOccupied(Vector2D position){
        return apple.getPosition().equals(position) || snake.getBody().contains(position);
    }

    public void run(){
        //TODO: gameframe into seperate function
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800,800);
        gameFrame.setLocation(
                (dim.width-gameFrame.getWidth())/2,
                (dim.height-gameFrame.getHeight())/2
        );
        gameFrame.setVisible(true);

        gamePanel = new BoardVisualizer(this,gameFrame);
        gameFrame.add(gamePanel);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.repaint();
    }
}
