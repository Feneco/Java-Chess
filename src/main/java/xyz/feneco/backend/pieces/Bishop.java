package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.List;

public class Bishop extends SlidingPieces {
    public Bishop(Team team, Position position, boolean notMoved) {
        super(team, position, 'B', notMoved);
    }


    @Override
    protected List<Position> moveMask(Position position) {
        // TODO
        return null;
    }
}
