package main.game;

public class Board {

    private Vector2D lowerLeft;
    private Vector2D upperRight;
    private Apple apple;
    private Snake snake;

    Board(int width, int height){
        this.lowerLeft = new Vector2D(0,0);
        this.upperRight = new Vector2D(width-1,height-1);
        this.apple = new Apple(new Vector2D((3/4)*width,(1/2)*height));
        //this.snake
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
        
    }

}
