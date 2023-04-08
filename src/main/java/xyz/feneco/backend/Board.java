package xyz.feneco.backend;

import xyz.feneco.backend.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Piece> boardPieces;
    private final List<Piece> capturedPieces;

    public Board(List<Piece> boardPieces, List<Piece> capturedPieces) {
        this.boardPieces = boardPieces;
        this.capturedPieces = capturedPieces;
    }

    public List<Piece> getPieces() {
        return boardPieces;
    }

    public List<Piece> getPieces(Team team) {
        ArrayList<Piece> ret = new ArrayList<>();
        for( Piece piece : boardPieces) {
            if ( piece.getTeam() == team ) {
                ret.add(piece);
            }
        }
        return ret;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public List<Piece> getCapturedPieces(Team team) {
        ArrayList<Piece> ret = new ArrayList<>();
        for( Piece piece : capturedPieces ) {
            if ( piece.getTeam() == team ) {
                ret.add(piece);
            }
        }
        return ret;
    }

    public void addPiece(Piece piece) {
        if ( boardPieces.contains(piece) ){
            throw new IllegalArgumentException("Piece is already on pieces list.");
        }
        boardPieces.add(piece);
    }

    public void removePiece(Piece piece) {
        boardPieces.remove(piece);
    }

    public void addCapturedPiece(Piece piece){
        if ( boardPieces.contains(piece) ){
            throw new IllegalArgumentException("Piece is already on captured pieces list.");
        }
        capturedPieces.add(piece);
    }

    public void removeCapturedPiece(Piece piece){
        capturedPieces.remove(piece);
    }

    public void capturePiece(Piece piece) throws IllegalArgumentException {
        if ( boardPieces.remove(piece) ){
            capturedPieces.add(piece);
            return;
        }
        throw new IllegalArgumentException("Piece to remove is not on board.");
    }

    public Piece getPieceAt(Position pos) {
        for( Piece piece : boardPieces) {
            if (piece.position.equals(pos)) {
                return piece;
            }
        }
        return null;
    }

    public final boolean isPieceAt(Position pos) {
        for( Piece piece : boardPieces) {
            if (piece.position.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public final King getKing(Team team) {
        for (Piece p : boardPieces) {
            if ( p.getTeam() == team && p instanceof King king ) {
                return king;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("┌───┬───┬───┬───┬───┬───┬───┬───┐\n");
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                sb.append("│");
                Piece p = getPieceAt(new Position(j, i));
                if ( p != null) {
                    sb.append(' ');
                    sb.append(p.getTeam() == Team.White ? p.getSymbol() : Character.toLowerCase(p.getSymbol()));
                    sb.append(' ');
                }else {
                    sb.append("   ");
                }
            }
            sb.append("│\n");
            if (i != 0 ) {
                sb.append("├───┼───┼───┼───┼───┼───┼───┼───┤\n");
            }
        }
        sb.append("└───┴───┴───┴───┴───┴───┴───┴───┘\n");
        return sb.toString();
    }
}
