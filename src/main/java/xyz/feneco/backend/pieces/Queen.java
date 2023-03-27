package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Queen extends Piece {

    public Queen(Team team, Position position, Board board) {
        super(team, position, 'Q', board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        Boolean hMove = delta.getX() != 0 && delta.getY() == 0;
        Boolean vMove = delta.getX() == 0 && delta.getY() != 0;
        Boolean diagMove = Math.abs(delta.getX()) == Math.abs(delta.getY());
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
        if ( diagMove ) {
            int dist = Math.abs(delta.getY());
            int dx = (int) Math.signum(delta.getX());
            int dy = (int) Math.signum(delta.getY());
            int i = 1;
            Position testPos = position.getAdd(dx, dy);
            while (i != dist) {
                if (board.getPieceAt(testPos) != null) {
                    return false;
                }
                testPos = testPos.getAdd(dx, dy);
                i++;
            }
        }
        return hMove || vMove || diagMove;
    }
}
