package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class King extends Piece  {
    private final static Character kingSymbol = 'K';

    public King(Team team, Position position, Board board) {
        super(team, position, kingSymbol, board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        Boolean normalDelta = Math.abs(delta.getX()) == 1 || Math.abs(delta.getY()) == 1;
        Boolean castling = ( delta.getY() == 0 ) && ( delta.getX() == -3 || delta.getX() == 2 );
        if ( castling && getNotMoved() ) {
            Piece probableRook = board.getPieceAt(new Position(delta.getX() == -3 ? 0 : 7, position.getY()));
            if (probableRook instanceof Rook rook) {
                return rook.getNotMoved();
            }
            return false;
        }
        return normalDelta;
    }
}
