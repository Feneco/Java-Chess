package xyz.feneco.frontend;

import xyz.feneco.backend.*;

public class BoardRenderer {
    private Board board;

    public BoardRenderer(Board board){
        this.board = board;
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ┌───┬───┬───┬───┬───┬───┬───┬───┐\n");
        for (int i = 7; i >= 0; i--) {
            sb.append(i+1).append(" ");
            for (int j = 0; j < 8; j++) {
                sb.append("│");
                Piece p = board.getPieceAt(new Position(j, i));
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
                sb.append("  ├───┼───┼───┼───┼───┼───┼───┼───┤\n");
            }
        }
        sb.append("  └───┴───┴───┴───┴───┴───┴───┴───┘\n");
        sb.append("    A   B   C   D   E   F   G   H\n");
        sb.append("Team to play: ").append(board.getPlayingTeam()).append("\n");
        System.out.print(sb);
    }
}
