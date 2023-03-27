package xyz.feneco.frontend;

import xyz.feneco.backend.Board;
import xyz.feneco.backend.MovReport;

public class ChessGame {
    private Board board;
    public void init() {
        board = new Board();
        board.initBoard();
        while(true) {
            BoardRenderer renderer = new BoardRenderer(board);
            renderer.render();
            PlayerQueryAnswer ans = PlayerQuery.query();
            if (ans.getAns() == 0) {
                MovReport rep = board.movePiece(ans.getFrom(), ans.getTo());
                if (rep == MovReport.Normal || rep == MovReport.Promotion) {
                    board.changeTeam();
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }
}
