package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Rook extends Piece {
    private final static Character rookSymbol = 'R';

    public Rook(Team team, Position position, Board board) {
        super(team, position, rookSymbol, board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        Boolean hMove = delta.getX() != 0 && delta.getY() == 0;
        Boolean vMove = delta.getX() == 0 && delta.getY() != 0;
        if ( hMove ) {
            Integer d = Integer.signum(delta.getX());
            for (int i = position.getX() + d; i != desiredPos.getX(); i += d) {
                Position pTest = new Position(i, position.getY());
                if (board.getPieceAt(pTest) != null) {
                    return false;
                }
            }
        }
        if ( vMove ) {
            Integer d = Integer.signum(delta.getY());
            for (int j = position.getY() + d; j != desiredPos.getY(); j += d) {
                Position pTest = new Position(position.getX(), j);
                if (board.getPieceAt(pTest) != null) {
                    return false;
                }
            }
        }
        if ( hMove || vMove ) {
            return true;
        }
        return false;
    }
}
