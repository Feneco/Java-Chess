package xyz.feneco.backend;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testToString() {
        Board b = new Board(new ArrayList<>(), new ArrayList<>());
        String test = b.toString();
    }
}