package main.game;

import java.awt.*;

public class Apple {

    private Point position;

    Apple(Point position){
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "a";
    }
}
