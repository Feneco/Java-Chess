package xyz.feneco.backend;

import xyz.feneco.backend.pieces.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<Piece> capturedPieces = new ArrayList<>();
    private Team playingTeam;

    public Board() {
        playingTeam = Team.White;
    }

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

    public MovReport movePiece(Position from, Position to) {
        // This function must check if move is:
        // En passant, Promotion, Castling
        Piece moving = getPieceAt(from);
        Piece capturing = getPieceAt(to);
        if (moving != null) {
            if (moving.getTeam() != playingTeam) {
                return MovReport.Invalid;
            }
            if (moving.canMove(to)) {
                moving.moveTo(to);
                if (capturing != null) {
                    capturePiece(capturing);
                    if (moving instanceof Pawn pawn && (to.getY() == 0 || to.getY() == 7)) {
                        return MovReport.Promotion;
                    }
                // If capturing square is null and pawn is moving diagonally, it is an en passant due to being a valid move
                } else if (moving instanceof Pawn capturingPawn
                           && Math.abs(to.getSub(from).getX()) == 1
                           && Math.abs(to.getSub(from).getY()) == 1) {
                    // En passant
                    Piece lateral = getPieceAt(to.getAdd(0, -capturingPawn.getTeamDirection()));
                    capturePiece(lateral);
                    capturingPawn.moveTo(to);
                } else if (moving instanceof King king
                           && Math.abs(to.getSub(from).getX()) > 1) {
                    // Castling
                    Boolean kingSide = king.getPosition().getX() == 6;
                    Integer xCoord = kingSide ? 7 : 0;
                    Piece mayBeCastle = getPieceAt(new Position(xCoord, king.getPosition().getY()));
                    if ( mayBeCastle instanceof Rook rook && king.getNotMoved() && rook.getNotMoved() ) {
                        rook.moveTo(new Position(kingSide? 6 : 2, king.getPosition().getY()));
                    }
                }
                return MovReport.Normal;
            } else {
                return MovReport.Invalid;
            }
        }
        return MovReport.Invalid;
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
        for (Piece p : pieces) {
            if ( p.getTeam() == playingTeam && p instanceof King king ) {
                return king;
            }
        }
        return null;
    }

    public Boolean movePutKingInCheck(Position from, Position to) {
        Boolean retVal = false;
        Boolean removedPiece = false;

        Piece p1 = getPieceAt(from);
        Piece p2 = getPieceAt(to);

        p1.setPosition(to);
        if ( p2 != null ) {
            removePiece(p2);
            removedPiece = true;
        }

        Piece king = getKing(playingTeam);
        if ( king != null ) {
            ArrayList<Piece> enemies = getEnemyPieces();
            for (Piece e : enemies) {
                if (e.canMove(king.position)) {
                    retVal = true;
                }
            }
        }

        p1.setPosition(from);
        if ( removedPiece ) {
            addPiece(p2);
        }
        return retVal;
    }

    public void changeTeam(){
        playingTeam = (playingTeam == Team.White) ? Team.Black : Team.White;
    }

    public Team getPlayingTeam() {
        return playingTeam;
    }
}
