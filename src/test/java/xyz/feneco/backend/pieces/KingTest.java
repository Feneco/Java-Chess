package xyz.feneco.backend.pieces;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import xyz.feneco.backend.*;


class KingTest {

    @Test
    void isMoveValid() {
        Board board = new Board();
        Piece wKing1 = new King(Team.White, new Position(3, 3), board);
        board.addPiece(wKing1);
        assertTrue (wKing1.canMove(new Position(4, 3)));
        assertTrue (wKing1.canMove(new Position(4, 4)));
        assertFalse(wKing1.canMove(new Position(3, 5)));
        assertFalse(wKing1.canMove(new Position(3, 6)));
    }

    @Test
    void castling() {
        { // King side castle
            Board board = new Board();
            Piece wKing1 = new King(Team.White, new Position(4, 0), board);
            Piece wRook1 = new Rook(Team.White, new Position(7, 0), board);
            board.addPiece(wKing1);
            board.addPiece(wRook1);

            assertTrue(wKing1.canMove(new Position(6, 0)));
            MovReport report = board.movePiece(new Position(4, 0), new Position(6, 0));
            assertEquals(report, MovReport.Normal);
            assertSame(board.getPieceAt(new Position(5, 0)), wRook1);
        }
        { // Queen side castle
            Board board = new Board();
            Piece wKing1 = new King(Team.White, new Position(4, 0), board);
            Piece wRook1 = new Rook(Team.White, new Position(0, 0), board);
            board.addPiece(wKing1);
            board.addPiece(wRook1);

            assertTrue(wKing1.canMove(new Position(1, 0)));
            MovReport report = board.movePiece(new Position(4, 0), new Position(1, 0));
            assertEquals(report, MovReport.Normal);
            assertSame(board.getPieceAt(new Position(2, 0)), wRook1);
        }
        { // Piece on the way
            Board board = new Board();
            Piece bKing1 = new King(Team.Black, new Position(4, 7), board);
            Piece bRook1 = new Rook(Team.Black, new Position(0, 7), board);
            Piece bRook2 =  new Rook(Team.Black, new Position(3, 7), board);
            Piece bRook3 =  new Rook(Team.Black, new Position(5, 7), board);
            Piece bRook4 =  new Rook(Team.Black, new Position(7, 7), board);
            board.addPiece(bKing1);
            board.addPiece(bRook1);
            board.addPiece(bRook2);
            board.addPiece(bRook3);
            board.addPiece(bRook4);

            assertFalse(bKing1.canMove(new Position(1, 7)));
            assertFalse(bKing1.canMove(new Position(6, 7)));
        }
        { // Piece moved
            Board board = new Board();
            Piece wKing1 = new King(Team.White, new Position(4, 0), board);
            Piece wRook1 = new Rook(Team.White, new Position(7, 0), board);
            board.addPiece(wKing1);
            board.addPiece(wRook1);

            board.movePiece(new Position(7, 0), new Position(6, 0));
            board.movePiece(new Position(6, 0), new Position(7, 0));
            assertFalse(wKing1.canMove(new Position(6, 0)));
        }
    }
}