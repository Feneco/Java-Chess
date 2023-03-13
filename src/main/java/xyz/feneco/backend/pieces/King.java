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
            boolean kingside = delta.getX() > 0;
            if (kingside) {
                Piece p1 = board.getPieceAt(new Position(5, position.getY()));
                Piece p2 = board.getPieceAt(new Position(6, position.getY()));
                if (p1 != null || p2 != null) {
                    return false;
                }
            } else {
                Piece p1 = board.getPieceAt(new Position(3, position.getY()));
                Piece p2 = board.getPieceAt(new Position(2, position.getY()));
                Piece p3 = board.getPieceAt(new Position(1, position.getY()));
                if (p1 != null || p2 != null || p3 != null) {
                    return false;
                }
            }
            Piece probableRook = board.getPieceAt(new Position(delta.getX() == -3 ? 0 : 7, position.getY()));
            if (probableRook instanceof Rook rook) {
                return rook.getNotMoved();
            }
            return false;
        }
        return normalDelta;
    }
}
