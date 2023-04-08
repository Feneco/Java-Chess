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
        for (int i = position.x(); i < 8; i++) {
            for (int j = position.y(); j < 8; j++) {
                if(i == position.y() || j == position.x()) {
                    continue;
                }
                ret.add(new Position(i, j));
            }
        }
        for (int i = position.x(); i >= 0; i--) {
            for (int j = position.y(); j < 8; j++) {
                if(i == position.y() || j == position.x()) {
                    continue;
                }
                ret.add(new Position(i, j));
            }
        }
        for (int i = position.x(); i >= 0; i--) {
            for (int j = position.y(); j >= 0; j--) {
                if(i == position.y() || j == position.x()) {
                    continue;
                }
                ret.add(new Position(i, j));
            }
        }
        for (int i = position.x(); i < 8; i++) {
            for (int j = position.y(); j >= 0; j--) {
                if(i == position.y() || j == position.x()) {
                    continue;
                }
                ret.add(new Position(i, j));
            }
        }
        return ret;
    }
}
