package xyz.feneco.backend;

public abstract class Piece {
    protected final Team team;
    protected Position position;
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
        board.addPiece(this);
        notMoved = true;
    }

    public Boolean canMove(Position desiredPos){
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

    public void setPosition(Position pos) {
        position = pos;
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
        return team.label + " " + symbol.toString() + " at " + position.toString();
    }

}
