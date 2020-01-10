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
    public int scale;

    private JFrame gameFrame = new JFrame("Snake!");
    private JPanel gamePanel;
    public Timer timer = new Timer(20,this);
    private int ticks = 0;

    Board(int width, int height, int scale){
        this.upperLeft = new Vector2D(0,0);
        this.lowerRight = new Vector2D(width-1,height-1);
        this.scale = scale;
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

    private boolean validatePosition(Vector2D position){
        return
                position.follows(upperLeft) &&
                position.precedes(lowerRight) &&
                !this.snake.getBody().contains(position);
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
        gameFrame.setSize(
                (lowerRight.x+1)*scale+gameFrame.getInsets().left+gameFrame.getInsets().right,
                (lowerRight.y+1)*scale+gameFrame.getInsets().top+gameFrame.getInsets().bottom
        );
        gameFrame.setLocation(
                (dim.width-gameFrame.getWidth())/2,
                (dim.height-gameFrame.getHeight())/2
        );
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

        gamePanel = new BoardVisualizer(this,gameFrame);
        gameFrame.getContentPane().add(gamePanel);
        gameFrame.pack();
        gameFrame.addKeyListener(this);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.repaint();
        ticks++;

        if(ticks%5 == 0){
            if(validatePosition(snake.getHead().add(snake.getOrientation().getUnitVector()))){
                snake.move(snakeEats());
            }
            else{
                System.out.println(gameOver());
            }

            System.out.println(snake.getHead().x + ", " +snake.getHead().y);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                snake.changeOrientation(snake.getOrientation().getNext());
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                snake.changeOrientation(snake.getOrientation().getPrevious());
                break;
            case KeyEvent.VK_SPACE:
                if(timer.isRunning()){
                    timer.stop();
                }
                else{
                    timer.start();
                }
                break;
            case KeyEvent.VK_R:
                this.apple = new Apple(new Vector2D(3*lowerRight.x/4,lowerRight.y/2));
                this.snake = new Snake(new Vector2D(lowerRight.x/2,lowerRight.y/2),3);
                timer.restart();
                break;
            default:
                break;
        }
        ticks = 4;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
