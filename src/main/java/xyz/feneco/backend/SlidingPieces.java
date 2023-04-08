package xyz.feneco.backend;

import java.util.Comparator;
import java.util.List;

public abstract class SlidingPieces extends Piece {

    public SlidingPieces(Team team, Position position, Character symbol, boolean notMoved) {
        super(team, position, symbol, notMoved);
    }

    protected abstract List<Position> moveMask(Position position);

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        List<Position> mask = moveMask(desiredPosition);
        mask.sort((p1, p2) -> {
            int sp1 = 8 * p1.y() + p1.x();
            int sp2 = 8 * p2.y() + p2.x();
            return Integer.compare(sp1, sp2);
        });
        for (Position slot: mask) {
            if ( slot.equals(desiredPosition) ) {
                // Check if piece at the desired position is enemy, if friend return false
                if ( board.isPieceAt(desiredPosition)) {
                    Piece testPiece = board.getPieceAt(desiredPosition);
                    if (!isEnemy(testPiece)) {
                        return false;
                    }
                }
                // Defining start values
                Position delta = desiredPosition.sub(position);
                int dx = Integer.signum(delta.x());
                int xStartPos = desiredPosition.x() - dx;
                int dy = Integer.signum(delta.y());
                int yStartPos = desiredPosition.y() - dy;
                // check for a piece at every square between the desired position towards this piece position.
                // In case there is, return false. This won't check this piece's position and the desiredPosition,
                // only spaces between them, marching "backwards".
                for (int i = xStartPos; i != position.x(); i -= dx) {
                    for (int j = yStartPos; j != position.y(); j -= dy) {
                        Position testPos = new Position(i, j);
                        if ( board.isPieceAt(testPos) ){
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
