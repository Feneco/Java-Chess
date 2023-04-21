package xyz.feneco.backend;

public class TestGame implements Game {
    private final Board board;
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
