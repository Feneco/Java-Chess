package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import xyz.feneco.backend.*;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void isMoveValid() {
        {
            Board board = new Board();
            Piece bQueen1 = new Queen(Team.Black, new Position(4, 4), board);
            Piece wPawn1 = new Pawn(Team.White, new Position(0, 0), board);
            Piece wPawn2 = new Pawn(Team.White, new Position(7, 7), board);
            Piece wPawn3 = new Pawn(Team.White, new Position(6, 2), board);
            Piece wPawn4 = new Pawn(Team.White, new Position(2, 6), board);
            Piece wPawn5 = new Pawn(Team.White, new Position(4, 2), board);
            Piece bPawn1 = new Pawn(Team.Black, new Position(1, 1), board);
            board.addPiece(bQueen1);
            board.addPiece(wPawn1);
            board.addPiece(wPawn2);
            board.addPiece(wPawn3);
            board.addPiece(wPawn4);
            board.addPiece(wPawn5);
            board.addPiece(bPawn1);

            assertFalse(bQueen1.canMove(wPawn1.getPosition()));
            assertFalse(bQueen1.canMove(bPawn1.getPosition()));
            assertTrue(bQueen1.canMove(wPawn2.getPosition()));
            assertTrue(bQueen1.canMove(wPawn3.getPosition()));
            assertTrue(bQueen1.canMove(wPawn4.getPosition()));
            assertTrue(bQueen1.canMove(wPawn5.getPosition()));
        }
        {
            Board board = new Board();
            board.initBoard();
            board.movePiece(new Position(4,1), new Position(4, 3));
            board.changeTeam();
            board.movePiece(new Position(4,6), new Position(4, 4));
            board.changeTeam();
            MovReport mr = board.movePiece(new Position(3,0), new Position(7, 4));
            assertEquals(mr, MovReport.Normal);
            assertNotNull(board.getPieceAt(new Position(7,4)));
            board.changeTeam();
            mr = board.movePiece(new Position(2,6), new Position(2, 4));
            assertEquals(mr, MovReport.Normal);
        }
    }
}