package xyz.feneco.backend;

import java.util.List;

public abstract class SlidingPieces extends Piece {

    public SlidingPieces(Team team, Position position, Character symbol, boolean notMoved) {
        super(team, position, symbol, notMoved);
    }

    protected abstract List<Position> moveMask(Position position);

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        List<Position> mask = moveMask(position);
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
                int xStartPos = position.x();
                int dy = Integer.signum(delta.y());
                int yStartPos = position.y();

                int maxCount = Math.max(Math.abs(delta.x()), Math.abs(delta.y()));
                Position testpos = new Position(xStartPos + dx, yStartPos + dy);
                for(int count = 1; count < maxCount; count++){
                    if(board.isPieceAt(testpos)){
                        return false;
                    }
                    testpos = testpos.add(dx, dy);
                }
                // check for a piece at every square between the desired position towards this piece position.
                // In case there is, return false. This won't check this piece's position and the desiredPosition,
                // only spaces between them, marching "backwards".

                return true;
            }
        }
        return false;
    }
}
