package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Pawn extends Piece {
    private final static Character pawnSymbol = 'P';
    private Boolean enPassant;

    public Pawn(Team team, Position position, Board board) {
        super(team, position, pawnSymbol, board);
        enPassant = false;
    }

    public Boolean getEnPassant(){
        return enPassant;
    }

    @Override
    public Boolean isMoveValid(Position desiredPos) {
        Boolean oneForward  =  position.getY() +  getTeamDirection() == desiredPos.getY();
        Boolean lateralMove =  position.getX() == desiredPos.getX()  - 1 || position.getX() == desiredPos.getX() + 1;
        Boolean twoForward  = (position.getY() +  getTeamDirection() * 2) == desiredPos.getY();

        Piece piece = board.getPieceAt(desiredPos);
        if ( lateralMove && oneForward ) {
            if ( piece == null ) {
                Piece lateral = board.getPieceAt(desiredPos.getAdd(0, -getTeamDirection()));
                if ( lateral instanceof Pawn pawn && isEnemyPiece(pawn)) {
                    return pawn.getEnPassant(); // may be capture
                } else {
                    return false;
                }
            } else {
                return isEnemyPiece(piece); // may be capture
            }
        } else if ( oneForward ) {
            return piece == null;
        } else if ( twoForward ) {
            Piece piece2 = board.getPieceAt(desiredPos.getAdd(0, -getTeamDirection()));
            return getNotMoved() && piece == null && piece2 == null;
        } else {
            return false;
        }
    }

    @Override
    public void moveTo(Position pos) {
        Boolean twoForward = (position.getY() + getTeamDirection() * 2) == pos.getY();
        enPassant = getNotMoved() && twoForward;
        super.moveTo(pos);
    }
}
