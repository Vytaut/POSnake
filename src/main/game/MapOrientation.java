package main.game;

public enum MapOrientation {
    UP,RIGHT,DOWN,LEFT;

    public Vector2D toUnitVector(){
        switch (this){
            case UP:
                return new Vector2D(0,1);
            case RIGHT:
                return new Vector2D(1,0);
            case DOWN:
                return new Vector2D(0,-1);
            case LEFT:
                return new Vector2D(-1,0);
            default:
                throw new IllegalArgumentException("Enum cannot be converted to unit vector");
        }
    }
}
