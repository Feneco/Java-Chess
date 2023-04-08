package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends SlidingPieces {
    public Bishop(Team team, Position position, boolean notMoved) {
        super(team, position, 'B', notMoved);
    }


    @Override
    protected List<Position> moveMask(Position position) {
        List<Position> ret = new ArrayList<>();
        // There is probably a better way of doing this
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i == position.x() || j == position.y()) {
                    continue;
                }
                Position delta = position.sub(i, j);
                if ( Math.abs(delta.x()) == Math.abs(delta.y()) ) {
                    ret.add(new Position(i, j));
                }
            }
        }
        return ret;
    }
}
