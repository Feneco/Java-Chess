package xyz.feneco.backend;

public abstract class Piece {
    protected final Team team;
    protected final Position position;
    protected final Character symbol;
    private Boolean notMoved;
    protected final Board board;

    protected final Integer getTeamDirection() {
        return team == Team.White ? 1 : -1;
    }

    protected Piece(Team team, Position position, Character symbol, Board board) {
        this.team = team;
        this.position = position;
        this.symbol = symbol;
        this.board = board;
        notMoved = true;
    }

    public Boolean canMove(Position desiredPos){
        if ( isMoveValid(desiredPos) ) { // Short-circuiting
            return board.movePutKingInCheck(position, desiredPos);
        }
        return false;
    }

    protected abstract Boolean isMoveValid(Position desiredPos);

    public void moveTo(Position pos) {
        notMoved = false;
        position.set(pos);
    }

    public final Team getTeam() {
        return team;
    }

    public final Boolean isEnemyPiece(Piece p) {
        return p.getTeam() != team;
    }

    public final Position getPosition() {
        return position;
    }

    public final Character getSymbol() {
        return symbol;
    }

    public final Boolean getNotMoved() {
        return notMoved;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
