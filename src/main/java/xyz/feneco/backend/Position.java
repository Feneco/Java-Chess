package xyz.feneco.backend;

public record Position ( int x, int y ) {
    public Position(Position pos) {
        this(pos.x(), pos.y());
    }

    public int x() { return x; }
    public int y() { return y; }

    public Position add(int x, int y) { return new Position(this.x + x, this.y + y); }
    public Position add(Position pos) { return this.add(pos.x(), pos.y()); }

    public Position sub(int x, int y) { return new Position(this.x - x, this.y - y); }
    public Position sub(Position pos) { return this.sub(pos.x(), pos.y()); }

    public boolean equals(Position pos) {
        if (pos != null) {
            return x == pos.x() && y == pos.y();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
