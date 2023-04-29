package xyz.feneco.backend.pieces;

import xyz.feneco.backend.*;

public class Pawn extends Piece {
    private Boolean enPassant;

    public Pawn(Team team, Position position, boolean notMoved) {
        super(team, position, 'P', notMoved);
        enPassant = false;
    }

    @Override
    public boolean canMove(Position desiredPosition, Board board) {
        Position delta = desiredPosition.sub(position);
        boolean forwardDiagonalMovement = Math.abs(delta.x()) == 1 && delta.y() == getTeam().getDirection() ;
        boolean move1AheadMovement = delta.y() == 1 * getTeam().getDirection() && delta.x() == 0;
        boolean move2AheadMovement = delta.y() == 2 * getTeam().getDirection() && delta.x() == 0 && notMoved();
        if (forwardDiagonalMovement) {
            Position enPassantTestPosition = desiredPosition.sub(0, getTeam().getDirection());
            if(board.isPieceAt(desiredPosition)) {
                Piece testPiece = board.getPieceAt(desiredPosition);
                return testPiece.isEnemy(this);
            } else if ( board.isPieceAt(enPassantTestPosition) && !board.isPieceAt(desiredPosition) ) { // En-Passant check
                Piece testPiece = board.getPieceAt(enPassantTestPosition);
                if (testPiece instanceof Pawn pawn) {
                    return this.isEnemy(pawn) && pawn.getEnPassant();
                }
            }
        } else if (move2AheadMovement) {
            boolean testPos1Clear = !board.isPieceAt(position.add(0, 1 * getTeam().getDirection()));
            boolean testPos2Clear = !board.isPieceAt(position.add(0, 2 * getTeam().getDirection()));
            return testPos1Clear
                    && testPos2Clear;
        } else if (move1AheadMovement) {
            boolean testPos1Clear = !board.isPieceAt(position.add(0, 1 * getTeam().getDirection()));
            return testPos1Clear;
        }
        return false;
    }

    public boolean getEnPassant(){
        return enPassant;
    }

    public void setEnPassant(boolean var){
        enPassant = var;
    }

    @Override
    public void move(Position toPosition) {
        enPassant = false;
        if(notMoved()) {
            Position delta = toPosition.sub(position);
            if(delta.x()==0 && delta.y() == 2 * getTeam().getDirection()) {// Check if movement is two forward
                setEnPassant(true);
            }
        }
        super.move(toPosition);
    }
}
