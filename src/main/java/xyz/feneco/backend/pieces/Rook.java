package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Rook extends Piece {
    private final static Character rookSymbol = 'K';

    protected Rook(Team team, Position position, Board board) {
        super(team, position, rookSymbol, board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        Boolean hMove = delta.getX() != 0 && delta.getY() == 0;
        Boolean vMove = delta.getX() == 0 && delta.getY() != 0;
        return hMove || vMove;
    }
}
