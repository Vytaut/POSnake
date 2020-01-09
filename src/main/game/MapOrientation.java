package main.game;

public enum MapOrientation {
    UP (new Vector2D(0,1),MapOrientation.values()[1],MapOrientation.values()[3]),
    RIGHT (new Vector2D(1,0),MapOrientation.values()[2],MapOrientation.values()[0]),
    DOWN (new Vector2D(0,-1),MapOrientation.values()[3],MapOrientation.values()[1]),
    LEFT (new Vector2D(-1,0),MapOrientation.values()[0],MapOrientation.values()[2]);

    private final Vector2D unitVector;
    private final MapOrientation next;
    private final MapOrientation previous;

    MapOrientation(Vector2D vector, MapOrientation next, MapOrientation previous){
        this.unitVector = vector;
        this.next = next;
        this.previous = previous;
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
