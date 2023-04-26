package xyz.feneco.backend;
import xyz.feneco.backend.pieces.King;
import xyz.feneco.backend.pieces.Pawn;

public class StandardGame implements Game {

    private final Board board;
    private Team playingTeam;

    public StandardGame(){
        board = new StandardBoardFactory().getBoard();
        playingTeam = Team.White;
    }

    @Override
    public boolean movePutKingInCheck() {
        Team enemyTeam = (playingTeam == Team.White ? Team.Black : Team.White);
        Piece king = board.getKing(playingTeam);
        if (king == null) {
            throw new IllegalArgumentException("King is null");
        }
        for (Piece enemyPiece : board.getPieces(enemyTeam)) {
            if ( enemyPiece.canMove(king.getPosition(), board) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public MovReport movePiece(Position start, Position end) {
        if ( !board.isPieceAt(start) ) {
            return MovReport.Invalid;
        }
        Piece pieceStart = board.getPieceAt(start);
        if (pieceStart.getTeam() != playingTeam) {
            return MovReport.Invalid;
        }
        if (!pieceStart.canMove(end, board)) {
            return MovReport.Invalid;
        }
        Piece pieceEnd = board.getPieceAt(end);
        if (pieceEnd instanceof King){
            throw new IllegalArgumentException("Capture of enemy king, game logic fault!");
        }
        // There is a Piece at the start position, the piece is a playing team piece, the move is valid,
        // the move doesn't put the player king in check, the move does not capture the enemy king.
        // Now we should check if it is en-passant, castling, promotion, a normal capture or just a move.
        // If said Move puts the playing team king in check, reset and return an Invalid report.
        if ( pieceStart instanceof Pawn pawnStart) {
            return pawnMoveReport(start, end, pieceEnd, pawnStart);
        }
        else if ( pieceStart instanceof King kingStart) {
            return kingMoveReport(start, end, pieceEnd, kingStart);
        }
        else { /* Normal piece movement */
            return normalPieceMoveReport(start, end, pieceStart, pieceEnd);
        }
    }

    private MovReport pawnMoveReport(Position start, Position end, Piece pieceEnd, Pawn pawnStart) {
        boolean pawnNotMovedMemory = pawnStart.notMoved();
        Position delta = end.sub(start);
        boolean captureMove = Math.abs(delta.x()) == 1 && delta.y() == pawnStart.getTeam().getDirection();
        boolean emptySquareAtEnd = pieceEnd == null;
        // En passant logic
        if (captureMove && emptySquareAtEnd) {
            Position EnPassantPos = end.sub(0, playingTeam.getDirection());
            Piece enPassantPawn = board.getPieceAt(EnPassantPos);
            pawnStart.move(end);
            board.capturePiece(enPassantPawn);
            if (movePutKingInCheck()) {
                pawnStart.setNotMoved(pawnNotMovedMemory);
                pawnStart.move(start);
                board.unCapturePiece(enPassantPawn);
                return MovReport.Invalid;
            }
        // Normal pawn logic
        } else {
            boolean captureRealized = false;
            pawnStart.move(end);
            if (pieceEnd != null) {
                captureRealized = true;
                board.capturePiece(pieceEnd);
            }
            if (movePutKingInCheck()) {
                pawnStart.move(start);
                pawnStart.setNotMoved(pawnNotMovedMemory);
                if (captureRealized) {
                    board.unCapturePiece(pieceEnd);
                }
                return MovReport.Invalid;
            }
        }
        boolean isOnPromotionPosition = (end.y() == 7 && playingTeam == Team.White)
                || (end.y() == 0 && playingTeam == Team.Black);
        return isOnPromotionPosition ? MovReport.Promotion : MovReport.Normal;
    }

    private MovReport kingMoveReport(Position start, Position end, Piece pieceEnd, King kingStart) {
        Position delta = end.sub(start);
        boolean castling = delta.x() == 2 || delta.x() == -3;
        if (castling) {
            int castlingXDirection = end.x() == 6 ? 7 : 0;
            Position rookPos = new Position(castlingXDirection, kingStart.getPosition().y());
            Piece rook = board.getPieceAt(rookPos);
            if(castlingXDirection == 7) {
                kingStart.move(new Position(6, kingStart.getPosition().y()));
                rook.move(new Position(5, rook.getPosition().y()));
            } else {
                kingStart.move(new Position(1, kingStart.getPosition().y()));
                rook.move(new Position(2, rook.getPosition().y()));
            }
            if (movePutKingInCheck()) {
                kingStart.move(start);
                kingStart.setNotMoved(true);
                rook.move(rookPos);
                rook.setNotMoved(true);
                return MovReport.Invalid;
            }
        } else {
            boolean kingNotMovedMemory = kingStart.notMoved();
            boolean capturedPiece = false;
            kingStart.move(end);
            if (pieceEnd != null) {
                capturedPiece = true;
                board.capturePiece(pieceEnd);
            }
            if (movePutKingInCheck()){
                kingStart.move(start);
                kingStart.setNotMoved(kingNotMovedMemory);
                if(capturedPiece){
                    board.unCapturePiece(pieceEnd);
                }
                return MovReport.Invalid;
            }
        }
        return MovReport.Normal;
    }

    private MovReport normalPieceMoveReport(Position start, Position end, Piece pieceStart, Piece pieceEnd) {
        boolean notMovedMemory = pieceStart.notMoved();
        boolean capture = false;
        pieceStart.move(end);
        if (pieceEnd != null) {
            capture = true;
            board.capturePiece(pieceEnd);
        }
        if (movePutKingInCheck()) {
            pieceStart.move(start);
            pieceStart.setNotMoved(notMovedMemory);
            if (capture) {
                board.unCapturePiece(pieceEnd);
            }
            return MovReport.Invalid;
        }
        return MovReport.Normal;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Team getPlayingTeam() {
        return playingTeam;
    }

    @Override
    public void changePlayingTeam() {
        playingTeam = playingTeam == Team.White ? Team.Black : Team.White;
    }

    @Override
    public void setPlayingTeam(Team team) {
        playingTeam = team;
    }
}
