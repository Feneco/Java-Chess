package xyz.feneco.backend.pieces;

import xyz.feneco.backend.Board;
import xyz.feneco.backend.Piece;
import xyz.feneco.backend.Position;
import xyz.feneco.backend.Team;

public class Bishop extends Piece {

    public Bishop(Team team, Position position, Board board) {
        super(team, position, 'B', board);
    }

    @Override
    protected Boolean isMoveValid(Position desiredPos) {
        Position delta = desiredPos.getSub(position);
        boolean diagMove = Math.abs(delta.getX()) == Math.abs(delta.getY());
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
        return false;
    }
}
