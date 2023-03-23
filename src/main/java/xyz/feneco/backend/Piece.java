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
            boolean e = true;
            Piece pieceAtDesiredPos = board.getPieceAt(desiredPos);
            if ( pieceAtDesiredPos != null )
                e = board.isP1EnemyOfP2(this, pieceAtDesiredPos);
            return b && e;
        }
        return false;
    }

    protected abstract Boolean isMoveValid(Position desiredPos);

    /**
     * This function will set the piece position.
     * It will change the notMoved variable, so it should be used while in game.
     *
     * @param pos The position to move this piece to.
     */
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

    /**
     * This function will hard set the piece position.
     * It will not change the notMoved variable, so it should be used
     * in testing and debugging.
     *
     * @param pos The position to move this piece to.
     */
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
        return team.getLabel() + " " + symbol.toString() + " at " + position.toString();
    }
}
