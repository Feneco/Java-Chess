package xyz.feneco.backend;

public class TestGame implements Game {
    private Board board;
    private Team team;

    public TestGame(Team playingTeam){
        team = playingTeam;
        board = BoardFactory.getEmptyBoard();
    }

    @Override
    public boolean movePutKingInCheck(Position from, Position to) {
        return false;
    }

    @Override
    public boolean isEnemy(Piece piece1, Piece piece2) {
        return piece1.getTeam() == piece2.getTeam();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Team getPlayingTeam() {
        return team;
    }

    @Override
    public void changePlayingTeam() {
        team = (team == Team.White) ? Team.Black : Team.White;
    }

    @Override
    public void setPlayingTeam(Team team) {
        this.team = team;
    }

    @Override
    public MovReport movePiece(Position from, Position to) {
        return MovReport.Normal;
    }
}
