package main.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board implements ActionListener, KeyListener {

    private Vector2D upperLeft;
    private Vector2D lowerRight;
    private Apple apple;
    private Snake snake;
    private JFrame gameFrame = new JFrame("Snake!");
    private JPanel gamePanel;
    public Timer timer = new Timer(20,this);
    public int scale = 20;
    private int ticks = 0;//niepotrzebne w ogolnym

    Board(int width, int height){
        this.upperLeft = new Vector2D(0,0);
        this.lowerRight = new Vector2D(width-1,height-1);
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
                    (int)(Math.random()*this.lowerRight.x),
                    (int)(Math.random()*this.lowerRight.y)
            );
        }while(!(
                applePosition.follows(upperLeft) &&
                applePosition.precedes(lowerRight) &&
                !this.snake.getBody().contains(applePosition)
        ));

        this.apple = new Apple(applePosition);
    }

    private boolean validateSnakePosition(){
        return
                this.snake.getHead().follows(upperLeft) &&
                this.snake.getHead().precedes(lowerRight) &&
                !this.snake.getBody().contains(snake.getHead().add(snake.getOrientation().getUnitVector()));
    }

    private String gameOver(){
        timer.stop();

        return "Game over! Your score is: " + ( this.snake.getBody().size() - 3) + "!";
    }

    public boolean isOccupied(Vector2D position){
        return apple.getPosition().equals(position) || snake.getBody().contains(position);
    }

    private boolean snakeEats(){
        boolean eats = snake.getHead().add(snake.getOrientation().getUnitVector()).equals(apple.getPosition());

        if(eats) generateApple();

        return eats;
    }

    public void run(){
        //TODO: gameframe into seperate function
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize((lowerRight.x+2)*scale,(lowerRight.y+4)*scale);
        gameFrame.setLocation(
                (dim.width-gameFrame.getWidth())/2,
                (dim.height-gameFrame.getHeight())/2
        );
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

        gamePanel = new BoardVisualizer(this,gameFrame);
        gameFrame.add(gamePanel);
        gameFrame.addKeyListener(this);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.repaint();
        ticks++;

        if(ticks%5 == 0){
            if(validateSnakePosition()){
                snake.move(
                        snake.getOrientation(),
                        snakeEats()
                );
            }
            else{
                System.out.print(gameOver());
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                snake.changeOrientation(snake.getOrientation().getPrevious());
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                snake.changeOrientation(snake.getOrientation().getNext());
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
