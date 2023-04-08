package xyz.feneco.backend;

import java.util.List;

public abstract class SlidingPieces extends Piece {

    public SlidingPieces(Team team, Position position, Character symbol, boolean notMoved) {
        super(team, position, symbol, notMoved);
    }

    protected abstract List<Position> moveMask(Position position);

    @Override
    public List<Position> canMove(Position desiredPosition, Board board) {
        // TODO
        return null;
    }
}
