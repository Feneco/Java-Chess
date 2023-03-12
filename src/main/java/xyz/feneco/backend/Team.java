package xyz.feneco.backend;

public enum Team {
    White("white"),
    Black("black");

    public final String label;

    private Team(String label){
        this.label = label;
    }
}
