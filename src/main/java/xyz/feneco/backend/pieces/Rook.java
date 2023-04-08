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
        List<Position> ret = new ArrayList<>();
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
