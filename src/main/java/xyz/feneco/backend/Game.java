package xyz.feneco.backend;

public interface Game {
    boolean movePutKingInCheck();
    MovReport movePiece(Position from, Position to);
    Board getBoard();
    Team getPlayingTeam();
    void changePlayingTeam();
    void setPlayingTeam(Team team);
}
