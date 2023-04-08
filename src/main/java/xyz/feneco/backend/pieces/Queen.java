package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends SlidingPieces {
    public Queen(Team team, Position position, boolean notMoved) {
        super(team, position, 'Q', notMoved);
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
        for (int i = 0; i < 8; i++) {
            if(i == position.y()){
                continue;
            }
            ret.add(new Position(position.x(), i));
        }
        for (int j = 0; j < 8; j++) {
            if(j == position.x()){
                continue;
            }
            ret.add(new Position(j, position.y()));
        }
        return ret;
    }
}
