package xyz.feneco.backend;

public enum Team {
    White("white"),
    Black("black");

    private final String label;

    private Team(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
