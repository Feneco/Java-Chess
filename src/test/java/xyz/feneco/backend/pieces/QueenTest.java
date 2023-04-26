package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.pieces.Queen;
import xyz.feneco.backend.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void moveMask() {
        Piece wQueen1 = new Queen(Team.White, new Position(4, 4), true);
        Piece wQueen2 = new Queen(Team.White, new Position(1, 4), true);
        Piece wQueen3 = new Queen(Team.White, new Position(5, 2), true);

        Piece bQueen1 = new Queen(Team.Black, new Position(3, 3), true);
        Piece bQueen2 = new Queen(Team.Black, new Position(6, 4), true);
        Piece bQueen3 = new Queen(Team.Black, new Position(2, 6), true);
        Piece bQueen4 = new Queen(Team.Black, new Position(5, 7), true);
        Board board = new EmptyBoardFactory().getBoard();
        board.addPiece(wQueen1);
        board.addPiece(wQueen2);
        board.addPiece(wQueen3);
        board.addPiece(bQueen1);
        board.addPiece(bQueen2);
        board.addPiece(bQueen3);
        board.addPiece(bQueen4);


        List<Position> positions = wQueen1.getValidPositions(wQueen1.getPosition(), board);
        assertFalse(positions.contains(wQueen1.getPosition()));
        assertFalse(positions.contains(wQueen2.getPosition()));
        assertFalse(positions.contains(wQueen3.getPosition()));
        assertTrue(positions.contains(bQueen1.getPosition()));
        assertTrue(positions.contains(bQueen2.getPosition()));
        assertTrue(positions.contains(bQueen3.getPosition()));
        assertFalse(positions.contains(bQueen4.getPosition()));

        assertTrue(positions.contains(new Position(2, 4)));
        assertTrue(positions.contains(new Position(7, 7)));
        assertTrue(positions.contains(new Position(7, 1)));
        assertFalse(positions.contains(new Position(7, 4)));
        assertFalse(positions.contains(new Position(0, 0)));
    }
}