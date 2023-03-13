package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void isMoveValid() {
        Board board = new Board();
        Piece wPawn1 = new Pawn(Team.White, new Position(0, 1), board);
        Piece wPawn2 = new Pawn(Team.White, new Position(1, 1), board);
        Piece bPawn1 = new Pawn(Team.Black, new Position(1, 2), board);
        Piece bPawn2 = new Pawn(Team.Black, new Position(2, 6), board);
        board.addPiece(wPawn1);
        board.addPiece(wPawn2);
        board.addPiece(bPawn1);
        board.addPiece(bPawn2);

        assertTrue(wPawn1.canMove(bPawn1.getPosition()));
        assertFalse(wPawn2.canMove(wPawn2.getPosition().getAdd(0, 2)));
        assertTrue(bPawn2.canMove(bPawn2.getPosition().getAdd(0, -2)));
        assertTrue(bPawn1.canMove(wPawn1.getPosition()));
    }

    @Test
    void testForEnPassant() {
        {
            Board board = new Board();
            Piece wPawn1 = new Pawn(Team.White, new Position(1, 4), board);
            Piece bPawn1 = new Pawn(Team.Black, new Position(2, 6), board);
            board.addPiece(wPawn1);
            board.addPiece(bPawn1);

            board.changeTeam();
            MovReport report = board.movePiece(new Position(2, 6), new Position(2, 4));
            board.changeTeam();
            assertTrue(wPawn1.canMove(new Position(2, 5)));
            assertEquals(report, MovReport.Normal);
            MovReport report2 = board.movePiece(new Position(1, 4), new Position(2, 5));
            assertEquals(report2, MovReport.Normal);
        }
        {
            Board board = new Board();
            Piece wPawn1 = new Pawn(Team.White, new Position(0, 4), board);
            Piece bPawn1 = new Pawn(Team.Black, new Position(1, 5), board);
            board.addPiece(wPawn1);
            board.addPiece(bPawn1);

            board.changeTeam();
            MovReport report = board.movePiece(new Position(1, 5), new Position(1, 4));
            board.changeTeam();
            assertFalse(wPawn1.canMove(new Position(1, 5)));
            assertEquals(report, MovReport.Normal);
        }
    }
}