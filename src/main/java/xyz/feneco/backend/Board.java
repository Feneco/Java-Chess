package xyz.feneco.backend;

import xyz.feneco.backend.pieces.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<Piece> capturedPieces = new ArrayList<>();
    private Team playingTeam;

    public Board(Board b){
        this.pieces = new ArrayList<>(b.pieces);
        this.capturedPieces = new ArrayList<>(b.capturedPieces);
        this.playingTeam = b.playingTeam;
    }

    public void initBoard() {
        // TODO when all pieces are implemented
        return;
    }

    public ArrayList<Piece> getPieces(Team team) {
        ArrayList<Piece> ret = new ArrayList<>();
        for( Piece piece : pieces ) {
            if ( piece.getTeam() == team ) {
                ret.add(piece);
            }
        }
        return ret;
    }

    public void addPiece(Piece p) {
        pieces.add(p);
    }

    public void removePiece(Piece p) {
        pieces.remove(p);
    }

    public void capturePiece(Piece p) {
        removePiece(p);
        capturedPieces.add(p);
    }

    public void movePiece(Position from, Position to) {
        // This function must check if move is:
        // En passant, Promotion, Castling
        Piece moving = getPieceAt(from);
        Piece capturing = getPieceAt(to);
        if (moving != null) {
            if (moving.canMove(to)) {
                if (capturing != null) {
                    capturePiece(capturing);
                    moving.moveTo(to);
                    if (moving instanceof Pawn pawn && (to.getY() == 0 || to.getY() == 7)) {
                        // TODO: Pawn promotion
                        // This function must communicate with upper layer to query user for desired piece.
                    } else if (moving instanceof King king && Math.abs(to.getSub(from).getX()) > 1) {
                        // TODO: Castling
                    }
                // If capturing square is null and pawn is moving diagonally, it is an en passant due to being a valid move
                } else if ( moving instanceof Pawn capturingPawn
                            && Math.abs(to.getSub(from).getX()) == 1
                            && Math.abs(to.getSub(from).getY()) == 1) {
                    // En passant
                    Piece lateral = getPieceAt( to.getAdd(0, -capturingPawn.getTeamDirection()) );
                    capturePiece(lateral);
                    capturingPawn.moveTo(to);
                }
            }
        }
    }

    public Piece getPieceAt(Position pos) {
        Piece ret = null;
        for( Piece piece : pieces ) {
            if ( piece.getPosition().equals(pos) ) {
                ret = piece;
                break;
            }
        }
        return ret;
    }

    public ArrayList<Piece> getEnemyPieces() {
        ArrayList<Piece> enemies = new ArrayList<Piece>();
        for (Piece p : pieces) {
            if (p.getTeam() != playingTeam) {
                enemies.add(p);
            }
        }
        return enemies;
    }

    public Piece getKing(Team team) {
        return null;
        /* TODO: Remove this comment block when King class is implemented
         * Piece ret = null;
         * for( Piece piece : pieces ) {
         *  if ( piece instanceof King && piece.getTeam() == team ) {
         *      ret = piece;
         *      break;
         *  }
         * }
         * return ret;
         */
    }

    public Boolean movePutKingInCheck(Position from, Position to) {
        Board backup = new Board(this);
        Piece p1 = backup.getPieceAt(from);
        Piece p2 = backup.getPieceAt(to);
        p1.getPosition().set(to);
        if ( p2 != null ) {
            backup.removePiece(p2);
        }
        Piece king = backup.getKing(playingTeam);
        ArrayList<Piece> enemies = backup.getEnemyPieces();
        for (Piece e : enemies) {
            if ( e.canMove(king.position) ) {
                return true;
            }
        }
        return false;
    }
}
