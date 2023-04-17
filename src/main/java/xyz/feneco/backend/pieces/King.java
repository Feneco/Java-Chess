package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.List;

public class King extends Piece  {
    public King(Team team, Position position, boolean notMoved) {
        super(team, position, 'K', notMoved);
    }

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        Position delta = desiredPosition.sub(position);
        boolean move1Distance = Math.max( Math.abs(delta.x()), Math.abs(delta.y()) ) == 1;
        boolean startPosition = (position.y() == 0 || position.y() == 7) && (position.x() == 4);
        // Might be castling
        // Check if it is in the starting chess position (as we might start the match halfway through)
        if (!move1Distance && notMoved() && startPosition && desiredPosition.y() == position.y()) {
            // kingSide will be 1 if it is kingSide, -1 if queenside, 0 if none.
            int kingSide = desiredPosition.x() == 6 ? 1 : (desiredPosition.x() == 1 ? -1 : 0);
            Position rookTestPosition;
            if (kingSide == 1) {
                rookTestPosition = new Position(7, position.y());
            } else if (kingSide == -1) {
                rookTestPosition = new Position(0, position.y());
            } else {
                return false;
            }
            if (board.isPieceAt(rookTestPosition)) {
                Piece mayBeRook = board.getPieceAt(rookTestPosition);
                if (mayBeRook instanceof Rook rook) {
                    if (kingSide == 1) {
                        Position testPos1 = new Position(5, position.y());
                        Position testPos2 = new Position(6, position.y());
                        return !board.isPieceAt(testPos1) && !board.isPieceAt(testPos2) && rook.notMoved();
                    } else if (kingSide == -1){
                        Position testPos1 = new Position(1, position.y());
                        Position testPos2 = new Position(2, position.y());
                        Position testPos3 = new Position(3, position.y());
                        return !board.isPieceAt(testPos1) && !board.isPieceAt(testPos2) && !board.isPieceAt(testPos3) && rook.notMoved();
                    }
                }
            }
        }
        return move1Distance;
    }
}
