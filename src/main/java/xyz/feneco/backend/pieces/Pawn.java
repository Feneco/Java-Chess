package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private Boolean enPassant;

    public Pawn(Team team, Position position, boolean notMoved) {
        super(team, position, 'P', notMoved);
        enPassant = false;
    }

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        // TODO
        return false;
    }
}
