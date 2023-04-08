package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.List;

public class King extends Piece  {
    public King(Team team, Position position, boolean notMoved) {
        super(team, position, 'K', notMoved);
    }


    @Override
    public List<Position> canMove(Position desiredPosition, Board board) {
        // TODO
        return null;
    }
}
