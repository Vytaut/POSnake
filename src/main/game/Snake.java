package main.game;

import java.util.HashSet;

public class Snake {

    private Vector2D head;
    private HashSet<Vector2D> body;
    private MapOrientation orientation;

    Snake(Vector2D position,int length){
        this.head = position;
        this.orientation = MapOrientation.RIGHT;

        for(int i=1;i<length;i++){
            body.add(new Vector2D(position.getX()-i,position.getY()));
        }
    }

    public HashSet<Vector2D> getBody() {
        return body;
    }

    public MapOrientation getOrientation() {
        return orientation;
    }

    public Vector2D getHead() {
        return head;
    }

    

}
