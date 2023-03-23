package xyz.feneco.backend.pieces;

import xyz.feneco.backend.Board;
import xyz.feneco.backend.Piece;
import xyz.feneco.backend.Position;
import xyz.feneco.backend.Team;

public class Bishop extends Piece {

    public Bishop(Team team, Position position, Board board) {
        super(team, position, 'b', board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        boolean diagMove = Math.abs(delta.getX()) == Math.abs(delta.getY());
        if ( diagMove ) {
            Integer dx = Integer.signum(delta.getX());
            Integer dy = Integer.signum(delta.getY());
            Integer fx = desiredPos.getX();
            Integer fy = desiredPos.getY();
            for (int i = position.getX() + dx; i != fx; i += dx) {
                for (int j = position.getY() + dy; j != fy; j += dy) {
                    Position pTest = new Position(i, j);
                    if (board.getPieceAt(pTest) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
