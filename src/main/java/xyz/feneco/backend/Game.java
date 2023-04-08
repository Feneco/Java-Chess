package xyz.feneco.backend;

public interface Game {
    boolean movePutKingInCheck(Position from, Position to);
    boolean isEnemy(Piece piece1, Piece piece2);
    MovReport movePiece(Position from, Position to);
    Board getBoard();
    Team getPlayingTeam();
    void changePlayingTeam();
    void setPlayingTeam(Team team);
}
