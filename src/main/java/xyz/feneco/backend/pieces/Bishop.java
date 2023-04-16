package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Bishop extends SlidingPieces {
    public Bishop(Team team, Position position, boolean notMoved) {
        super(team, position, 'B', notMoved);
    }


    @Override
    protected boolean canSlideTo(Position desiredPosition) {
        Position delta = desiredPosition.sub(position);
        boolean diagonalMove = Math.abs(delta.x()) == Math.abs(delta.y());
        return diagonalMove;
    }
}
