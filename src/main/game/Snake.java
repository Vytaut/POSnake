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

    public void move(boolean hasEaten){
        //TODO: delete direction from this function and replace it with internal call
        Vector2D newHead = getHead().add(orientation.getUnitVector());

        body.addFirst(newHead);
        if(!hasEaten){
            body.removeLast();
        }
    }

    public void changeOrientation(MapOrientation direction){
        this.orientation = direction;
    }

}
