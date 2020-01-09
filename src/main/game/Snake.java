package main.game;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Vector2D> body;
    private MapOrientation orientation;

    Snake(Vector2D position,int length){
        this.orientation = MapOrientation.RIGHT;
        this.body = new LinkedList<>();

        for(int i=0;i<length;i++){
            body.add(new Vector2D(position.x-i,position.y));
        }
    }

    public LinkedList<Vector2D> getBody() {
        return body;
    }

    public MapOrientation getOrientation() {
        return orientation;
    }

    public Vector2D getHead() {
        return body.getFirst();
    }

    public Vector2D getTail() { return body.getLast(); }

    @Override
    public String toString() {
        return "s";
    }

    public void move(MapOrientation direction, boolean hasEaten){
        //TODO: delete direction from this function and replace it with internal call
        Vector2D newHead = getHead().add(direction.getUnitVector());

        body.addFirst(newHead);
        if(!hasEaten){
            body.removeLast();
        }

        orientation = direction;
    }

    public void changeOrientation(MapOrientation direction){
        this.orientation = direction;
    }

}
