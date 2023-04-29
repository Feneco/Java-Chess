package xyz.feneco.backend;

public class MockGame implements Game {
    private final Board board;
    private Team team;

    public MockGame(Team playingTeam){
        team = playingTeam;
        board = new EmptyBoardFactory().getBoard();
    }

    @Override
    public boolean movePutKingInCheck() {
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
