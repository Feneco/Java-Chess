package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;
import xyz.feneco.backend.Piece;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void isMoveValid() {
        Board board = new Board();
        Piece wPawn1 = new Pawn(Team.White, new Position(0, 1), board);
        Piece wPawn2 = new Pawn(Team.White, new Position(1, 1), board);
        Piece bPawn1 = new Pawn(Team.Black, new Position(1, 2), board);
        Piece bPawn2 = new Pawn(Team.Black, new Position(2, 6), board);

        assertTrue(wPawn1.canMove(bPawn1.getPosition()));
        assertFalse(wPawn2.canMove(wPawn2.getPosition().getAdd(0, 2)));
        assertTrue(bPawn2.canMove(bPawn2.getPosition().getAdd(0, -2)));
        assertTrue(bPawn1.canMove(wPawn1.getPosition()));
    }
}