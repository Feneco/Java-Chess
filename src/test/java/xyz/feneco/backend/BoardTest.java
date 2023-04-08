package xyz.feneco.backend;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testToString() {
        Board b = new Board(new ArrayList<>(), new ArrayList<>());
        String test = b.toString();
    }
}