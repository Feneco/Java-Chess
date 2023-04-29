package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Rook extends SlidingPieces {
    public Rook(Team team, Position position, boolean notMoved) {
        super(team, position, 'R', notMoved);
    }

    @Override
    protected boolean canSlideTo(Position desiredPosition) {
        boolean verticalSlide   = desiredPosition.x() == position.x() && desiredPosition.y() != position.y();
        boolean horizontalSlide = desiredPosition.x() != position.x() && desiredPosition.y() == position.y();
        return verticalSlide || horizontalSlide;
    }
}
