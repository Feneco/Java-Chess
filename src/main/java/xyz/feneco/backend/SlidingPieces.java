package xyz.feneco.backend;

import java.util.List;

public abstract class SlidingPieces extends Piece {

    public SlidingPieces(Team team, Position position, Character symbol, boolean notMoved) {
        super(team, position, symbol, notMoved);
    }

    protected abstract boolean canSlideTo(Position desiredPosition);

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        if ( !canSlideTo(desiredPosition) ) {
            return false;
        }

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
        // check every space between this piece and desiredPosition for pieces. If there is a piece, the loop below will
        // return false, as sliding pieces can't jump.
        for(int count = 1; count < maxCount; count++){
            if(board.isPieceAt(testpos)){
                return false;
            }
            testpos = testpos.add(dx, dy);
        }

        return true;
    }
}
