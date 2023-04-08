package xyz.feneco.backend;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected final Team team;
    protected Position position;
    protected final Character symbol;
    private boolean notMoved;

    protected Piece(Team team, Position position, Character symbol, boolean notMoved) {
        this.team = team;
        this.position = position;
        this.symbol = symbol;
        this.notMoved = notMoved;
    }

    public List<Position> getValidPositions(Position desiredPosition, Board board){
        List<Position> ret = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position testPos = new Position(i, j);
                if (canMove(testPos, board)){
                    ret.add(testPos);
                }
            }
        }
        return ret;
    }

    public abstract boolean canMove(Position desiredPosition, Board board);

    public final Team getTeam() {
        return team;
    }
    public final Position getPosition() {
        return position;
    }
    public final Character getSymbol() {
        return symbol;
    }
    public final boolean notMoved() {
        return notMoved;
    }

    public void move(Position position) {
        notMoved = false;
        this.position = position;
    }

    public boolean isEnemy(Piece piece) {
        return this.getTeam() != piece.getTeam();
    }

    @Override
    public String toString() {
        return symbol.toString() + " at " + position.toString() + ". " + (notMoved?"un moved":"moved");
    }
}