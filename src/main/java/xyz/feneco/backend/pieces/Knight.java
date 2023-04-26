package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Knight extends Piece {
    public Knight(Team team, Position position, boolean notMoved) {
        super(team, position, 'N', notMoved);
    }

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        Position delta = desiredPosition.sub(position);
        int dx = Math.abs(delta.x());
        int dy = Math.abs(delta.y());
        if (board.isPieceAt(desiredPosition)){
            if (!board.getPieceAt(desiredPosition).isEnemy(this)){
                return false;
            }
        }
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }
}
