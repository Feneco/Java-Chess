package xyz.feneco.backend;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Team team;
    protected Position position;
    protected final Character symbol;
    private boolean notMoved;

    protected Piece(Team team, Position position, Character symbol, boolean notMoved) {
        this.team = team;
        this.position = position;
        this.symbol = symbol;
        this.notMoved = notMoved;
    }

    public List<Position> getValidPositions(Position desiredPosition, Board board){
        List<Position> ret = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position testPos = new Position(i, j);
                if (canMove(testPos, board)){
                    ret.add(testPos);
                }
            }
        }
        return ret;
    }

    /** The classes implementing this should test if there is a piece at the desired position and if it is an
     * enemy or not. Implementations should obviously check for special conditions, such as en-passant, castling and
     * promotion, but shouldn't report the condition.
     * @param desiredPosition The desired position we want to move the piece to.
     * @param board the current board being played
     * @return Whether the piece can move to the desiredPosition.
     */
    public abstract boolean canMove(Position desiredPosition, Board board);

    public final Team getTeam() {
        return team;
    }
    public final Position getPosition() {
        return position;
    }
    public final Character getSymbol() {
        return symbol;
    }
    public final void setNotMoved(boolean notMoved) {
        this.notMoved = notMoved;
    }
    public final boolean notMoved() {
        return notMoved;
    }

    public void move(Position toPosition) {
        notMoved = false;
        this.position = toPosition;
    }

    public boolean isEnemy(Piece piece) {
        return this.getTeam() != piece.getTeam();
    }

    @Override
    public String toString() {
        return symbol.toString() + " at " + position.toString() + ". " + (notMoved?"un moved":"moved");
    }
}