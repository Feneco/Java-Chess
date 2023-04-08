package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends SlidingPieces {
    public Rook(Team team, Position position, boolean notMoved) {
        super(team, position, 'R', notMoved);
    }

    @Override
    protected List<Position> moveMask(Position position) {
        // TODO
        return null;
    }
}
