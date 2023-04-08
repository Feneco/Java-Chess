package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.List;

public class Knight extends Piece {
    public Knight(Team team, Position position, boolean notMoved) {
        super(team, position, 'N', notMoved);
    }

    @Override
    public List<Position> canMove(Position desiredPosition, Board board) {
        // TODO
        return null;
    }
}
