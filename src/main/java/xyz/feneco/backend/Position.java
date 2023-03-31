package xyz.feneco.backend;

public class Position {
    private final int x;
    private final int y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public Position add(int a, int b) { return new Position(this.x + a, this.y + b); }
    public Position add(Position pos) { return this.add(pos.getX(), pos.getY()); }

    public Position sub(int a, int b) { return new Position(this.x + a, this.y + b); }
    public Position sub(Position pos) { return this.sub(pos.getX(), pos.getY()); }

    public boolean equals(Position pos) {
        if (pos != null) {
            return x == pos.getX() && y == pos.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
