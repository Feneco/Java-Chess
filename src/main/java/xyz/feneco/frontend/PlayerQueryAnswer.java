package xyz.feneco.frontend;

import xyz.feneco.backend.Position;

public class PlayerQueryAnswer {
    private Position from;
    private Position to;
    private int ans;

    PlayerQueryAnswer(int ans, Position from, Position to){
        this.ans = ans;
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public int getAns() {
        return ans;
    }
}
