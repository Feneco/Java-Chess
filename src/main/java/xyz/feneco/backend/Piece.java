package xyz.feneco.backend;

public abstract class Piece {
    protected final Team team;
    protected Position position;
    protected final Character symbol;
    protected final Board board;
    private Boolean notMoved;


    protected Piece(Team team, Position position, Character symbol, Board board) {
        this.team = team;
        this.position = position;
        this.symbol = symbol;
        this.board = board;
        notMoved = true;
    }

    public Boolean canMove(Position desiredPos) {
        // I wrote it like this to facilitate debug
        boolean a = isMoveValid(desiredPos);
        if ( a ) { // Short-circuiting
            boolean b = !board.movePutKingInCheck(position, desiredPos);
            return b;
        }
        return false;
    }

    protected abstract Boolean isMoveValid(Position desiredPos);

    public void moveTo(Position pos) {
        notMoved = false;
        setPosition(pos);
    }

    public final Team getTeam() {
        return team;
    }

    public final Position getPosition() {
        return position;
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public final Character getSymbol() {
        return symbol;
    }

    public final Boolean getNotMoved() {
        return notMoved;
    }

    @Override
    public String toString() {
        return team.label + " " + symbol.toString() + " at " + position.toString();
    }
}
