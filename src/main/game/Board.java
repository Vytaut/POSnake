package main.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Board implements ActionListener, KeyListener {

    private Vector2D upperLeft;
    private Vector2D lowerRight;
    private Apple apple;
    private Snake snake;
    private LinkedList<Vector2D> wall;
    private int scale;

    private JFrame gameFrame;
    private JPanel gamePanel;
    private Timer timer;
    private int speed;
    private int ticks;

    Board(int width, int height, int scale, int wallElementsCount, int speed){
        this.upperLeft = new Vector2D(0,0);
        this.lowerRight = new Vector2D(width-1,height-1);

        this.scale = scale;

        this.apple = new Apple(new Vector2D(3*height/4,height/2));
        this.snake = new Snake(new Vector2D(width/2,height/2),3);
        this.wall = new LinkedList<>();

        for(int i = 0; i<wallElementsCount; i++){
            generateObject('w');
        }

        this.timer = new Timer(20,this);
        this.ticks = 0;
        this.speed = speed;
    }

    public Apple getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getScale() {
        return scale;
    }

    public LinkedList<Vector2D> getWall() {
        return wall;
    }

    private void generateObject(char objectType){
        Vector2D position;

        do{
            position = new Vector2D(
                    (int)(Math.random()*this.lowerRight.x),
                    (int)(Math.random()*this.lowerRight.y)
            );
        }while(!(
                position.follows(upperLeft) &&
                position.precedes(lowerRight) &&
                !isOccupied(position)
        ));

        switch (objectType) {
            case 'a':
                apple = new Apple(position);
                break;
            case 'w':
                wall.add(position);
                break;
            default:
                break;
        }
    }

    private boolean validatePosition(Vector2D position){
        return
                position.follows(upperLeft) &&
                position.precedes(lowerRight) &&
                !this.snake.getBody().contains(position) &&
                !this.wall.contains(position);
    }

    private boolean isOccupied(Vector2D position){
         return
                 snake.getBody().contains(position) ||
                 apple.getPosition().equals(position) ||
                 wall.contains(position);
    }

    private String gameOver(){
        timer.stop();

        return "Game over! Your score is: " + ( this.snake.getBody().size() - 3) + "!";
    }

    private boolean snakeEats(){
        boolean eats = snake.getHead().add(snake.getOrientation().getUnitVector()).equals(apple.getPosition());

        if(eats) generateObject('a');

        return eats;
    }

    public void run(){
        gameFrame = setupFrame();
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

        if(ticks%speed == 0){
            if(validatePosition(snake.getHead().add(snake.getOrientation().getUnitVector()))){
                snake.move(snakeEats());
            }
            else{
                System.out.println(gameOver());
            }
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
                apple = new Apple(new Vector2D(3*lowerRight.x/4,lowerRight.y/2));
                snake = new Snake(new Vector2D(lowerRight.x/2,lowerRight.y/2),3);

                int wallCount = wall.size();
                wall = new LinkedList<>();
                for(int i=0;i<wallCount;i++){
                    generateObject('w');
                }

                timer.restart();
                break;
            default:
                break;
        }
        ticks = 4;
    }

    private JFrame setupFrame(){
        JFrame frame = new JFrame("Snake");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(
                (lowerRight.x+1)*scale+frame.getInsets().left+frame.getInsets().right,
                (lowerRight.y+1)*scale+frame.getInsets().top+frame.getInsets().bottom
        );
        frame.setLocation(
                (dim.width-frame.getWidth())/2,
                (dim.height-frame.getHeight())/2
        );
        frame.setResizable(false);
        frame.setVisible(true);

        return frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
