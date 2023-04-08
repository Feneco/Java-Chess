package xyz.feneco.backend;

import xyz.feneco.backend.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Piece> pieces;
    private final List<Piece> capturedPieces;

    public Board(List<Piece> pieces, List<Piece> capturedPieces) {
        this.pieces         = pieces;
        this.capturedPieces = capturedPieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Piece> getPieces(Team team) {
        ArrayList<Piece> ret = new ArrayList<>();
        for( Piece piece : pieces ) {
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

    public Board addPiece(Piece piece) {
        pieces.add(piece);
        return this;
    }

    public Board removePiece(Piece piece) {
        pieces.remove(piece);
        return this;
    }

    public Board addCapturedPiece(Piece piece){
        capturedPieces.add(piece);
        return this;
    }

    public Board removeCapturedPiece(Piece piece){
        capturedPieces.remove(pieces);
        return this;
    }

    public Board capturePiece(Piece p) {
        Board b = removePiece(p);
        List<Piece> listCapturedPieces = new ArrayList<>(capturedPieces);
        listCapturedPieces.add(p);
        return new Board(b.getPieces(), listCapturedPieces);

    }

    public Piece getPieceAt(Position pos) {
        for( Piece piece : pieces ) {
            if (piece.position.equals(pos)) {
                return piece;
            }
        }
        return null;
    }

    public final boolean isPieceAt(Position pos) {
        for( Piece piece : pieces ) {
            if (piece.position.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public final King getKing(Team team) {
        for (Piece p : pieces) {
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
