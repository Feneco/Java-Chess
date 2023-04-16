package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Queen extends SlidingPieces {
    public Queen(Team team, Position position, boolean notMoved) {
        super(team, position, 'Q', notMoved);
    }

    @Override
    protected boolean canSlideTo(Position desiredPosition) {
        boolean verticalSlide   = desiredPosition.x() == position.x() && desiredPosition.y() != position.y();
        boolean horizontalSlide = desiredPosition.x() != position.x() && desiredPosition.y() == position.y();
        Position delta = desiredPosition.sub(position);
        boolean diagonalMove = Math.abs(delta.x()) == Math.abs(delta.y());
        return verticalSlide || horizontalSlide || diagonalMove;
    }
}
