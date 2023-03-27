package xyz.feneco.frontend;

import xyz.feneco.backend.*;
import xyz.feneco.backend.pieces.*;

public class ChessGame {
    private Board board;
    public void init() {
        board = new Board();
        board.initBoard();
        while(true) {
            BoardRenderer renderer = new BoardRenderer(board);
            renderer.render();
            if (board.isPlayerInCheckMate()){
                System.out.println("Checkmate, " + board.getOtherTeam() + " wins.");
                return;
            }
            PlayerQueryAnswer ans = PlayerQuery.query();
            if (ans.getAns() == 0) {
                MovReport rep = board.movePiece(ans.getFrom(), ans.getTo());
                if (rep == MovReport.Normal) {
                    board.changeTeam();
                    continue;
                } else if ( rep == MovReport.Promotion) {
                    Piece p = board.getPieceAt(ans.getTo());
                    while (true) {
                        int promo = PlayerQuery.QueryPromotion();
                        if (promo == 0) {
                            board.removePiece(p);
                            board.addPiece(new Queen(board.getPlayingTeam(), p.getPosition(), board));
                            board.changeTeam();
                            break;
                        }
                        if (promo == 1) {
                            board.removePiece(p);
                            board.addPiece(new Bishop(board.getPlayingTeam(), p.getPosition(), board));
                            board.changeTeam();
                            break;
                        }
                        if (promo == 2) {
                            board.removePiece(p);
                            board.addPiece(new Knight(board.getPlayingTeam(), p.getPosition(), board));
                            board.changeTeam();
                            break;
                        }
                        if (promo == 3) {
                            board.removePiece(p);
                            board.addPiece(new Rook(board.getPlayingTeam(), p.getPosition(), board));
                            board.changeTeam();
                            break;
                        }
                    }
                    board.changeTeam();
                    continue;
                }
                else {
                    continue;
                }
            } else if (ans.getAns() == 2) {
                System.out.println(board.getPlayingTeam() + " resigns, " + board.getOtherTeam() + " wins.");
                return;
            }
        }
    }
}
