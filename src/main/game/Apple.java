package main.game;

import java.awt.*;

public class Apple {

    private Vector2D position;

    Apple(Vector2D position){
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "a";
    }
}
