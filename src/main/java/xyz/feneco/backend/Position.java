package xyz.feneco.backend;

public class Position {
    private Integer x;
    private Integer y;

    public Position(Integer x, Integer y) {
        set(x, y);
    }

    public Position(Position pos) {
        set(pos);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void set(Integer x, Integer y) {
        this.x = x; this.y = y;
    }

    public void set(Position pos) {
        set(pos.getX(), pos.getY());
    }

    public void add(Integer a, Integer b) {
        set(this.x + a, this.y + b);
    }

    public void add(Position pos) {
        set(this.x + pos.getX(), this.y + pos.getY());
    }

    public static Position add(Position pos1, Position pos2) {
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }

    public Position getAdd(Integer a, Integer b) {
        return new Position(this.x + a, this.y + b);
    }

    public Position getAdd(Position pos) {
        return new Position(this.x + pos.getX(), this.y + pos.getY());
    }

    public Boolean equals(Position pos) {
        return (x.equals(pos.getX()) && y.equals(pos.getY()));
    }

    public static Boolean equals(Position pos1, Position pos2) {
        return (pos1.equals(pos2));
    }

    public static Integer l1Distance(Position pos1, Position pos2) {
        Integer a = Math.abs(pos1.getX() - pos2.getX());
        Integer b = Math.abs(pos1.getY() - pos2.getY());
        return a + b;
    }

    public Position getSub(Position pos) {
        return new Position(x - pos.getX(), y - pos.getY());
    }
}
