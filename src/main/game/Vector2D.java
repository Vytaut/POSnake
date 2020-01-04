package main.game;

public class Vector2D {

    private final int x;
    private final int y;

    Vector2D(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Vector2D add(Vector2D position){
        return new Vector2D(this.x + position.x, this.y + position.y);
    }

    public boolean precedes(Vector2D position){
        return this.x <= position.x && this.y <= position.y;
    }

    public boolean follows(Vector2D position){
        return this.x <= position.x && this.y <= position.y;
    }

}
