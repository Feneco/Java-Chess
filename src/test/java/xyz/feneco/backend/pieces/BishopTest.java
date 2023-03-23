package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.Board;
import xyz.feneco.backend.Piece;
import xyz.feneco.backend.Position;
import xyz.feneco.backend.Team;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void isMoveValid() {
        Board board = new Board();
        Piece wBishop1 = new Bishop(Team.White, new Position(4, 4), board);
        Piece wBishop2 = new Bishop(Team.White, new Position(3, 5), board);
        Piece bPawn1 = new Pawn(Team.Black, new Position(5, 5), board);
        Piece bPawn2 = new Pawn(Team.Black, new Position(6, 6), board);
        Piece bPawn3 = new Pawn(Team.Black, new Position(4, 2), board);
        Piece bPawn4 = new Pawn(Team.Black, new Position(1, 5), board);
        board.addPiece(wBishop1);
        board.addPiece(wBishop2);
        board.addPiece(bPawn1);
        board.addPiece(bPawn2);
        board.addPiece(bPawn3);
        board.addPiece(bPawn4);

        assertTrue(wBishop1.canMove(bPawn1.getPosition()));
        assertFalse(wBishop1.canMove(bPawn2.getPosition()));
        assertFalse(wBishop1.canMove(bPawn3.getPosition()));
        assertFalse(wBishop1.canMove(bPawn4.getPosition()));

        assertFalse(wBishop1.canMove(wBishop2.getPosition()));
        assertTrue(wBishop1.canMove(new Position(1, 1)));
        assertTrue(wBishop1.canMove(new Position(6, 2)));
        assertFalse(wBishop1.canMove(new Position(4, 7)));
    }
}
