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

    public int getDirection() {if (this.ordinal() == 0) return 1; return -1;}
}
