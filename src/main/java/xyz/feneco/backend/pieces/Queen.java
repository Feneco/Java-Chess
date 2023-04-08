package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.List;

public class Queen extends SlidingPieces {
    public Queen(Team team, Position position, boolean notMoved) {
        super(team, position, 'Q', notMoved);
    }

    @Override
    protected List<Position> moveMask(Position position) {
        // TODO
        return null;
    }
}
