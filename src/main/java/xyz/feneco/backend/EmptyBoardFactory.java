package xyz.feneco.backend;
import java.util.ArrayList;

public class EmptyBoardFactory implements BoardFactoryInterface {
    @Override
    public Board getBoard() {
        return new Board(new ArrayList<>(), new ArrayList<>());
    }
}
