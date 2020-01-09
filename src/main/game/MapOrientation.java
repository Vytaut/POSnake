package main.game;

public enum MapOrientation {
    UP (new Vector2D(0,1)),
    RIGHT (new Vector2D(1,0)),
    DOWN (new Vector2D(0,-1)),
    LEFT (new Vector2D(-1,0));

    private final Vector2D unitVector;
    private MapOrientation next;
    private MapOrientation previous;

    static {
        UP.next = RIGHT;
        RIGHT.next = DOWN;
        DOWN.next = LEFT;
        LEFT.next = UP;
    }

    static {
        UP.previous = LEFT;
        RIGHT.previous = UP;
        DOWN.previous = RIGHT;
        LEFT.previous = DOWN;
    }

    MapOrientation(Vector2D vector){
        this.unitVector = vector;
    }

    public MapOrientation getNext() {
        return next;
    }

    public MapOrientation getPrevious() {
        return previous;
    }

    public Vector2D getUnitVector() {
        return unitVector;
    }

}
