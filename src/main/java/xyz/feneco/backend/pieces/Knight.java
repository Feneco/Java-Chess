package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Knight extends Piece {

    public Knight(Team team, Position position, Board board) {
        super(team, position, 'N', board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(this.position);
        if ( Math.abs(delta.getY()) == 2 && Math.abs(delta.getX()) == 1 ) {
            return true;
        }
        if ( Math.abs(delta.getY()) == 1 && Math.abs(delta.getX()) == 2 ) {
            return true;
        }
        return false;
    }
}
