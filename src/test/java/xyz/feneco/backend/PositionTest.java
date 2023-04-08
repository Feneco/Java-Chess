package xyz.feneco.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void constructorGetterSetter() {
        Position pos1 = new Position(5, 8);
        assertEquals(pos1.x(), 5);
        assertNotEquals(pos1.x(), 0);
        assertEquals(pos1.y(), 8);
        Position pos2 = new Position(pos1);
        assertEquals(pos2.x(), pos1.x());
        assertNotEquals(pos2.x(), 0);
        assertEquals(pos2.y(), pos1.y());
    }

    @Test
    void add() {
        Position pos1 = new Position(2, 5);
        Position pos2 = new Position(-1, 3);
        Position sum1 = pos1.add(pos2);
        assertEquals(sum1.x(), 1);
        assertEquals(sum1.y(), 8);
        assertNotSame(sum1, pos1);
        assertNotSame(sum1, pos2);

        Position sum2 = pos2.add(8, -3);
        assertEquals(sum2.x(), 7);
        assertEquals(sum2.y(), 0);
    }

    @Test
    void sub() {
        Position pos1 = new Position(4, 6);
        Position pos2 = new Position(-5, 4);
        Position sum1 = pos1.sub(pos2);
        assertEquals(9, sum1.x());
        assertEquals(2, sum1.y());
        assertNotSame(sum1, pos1);
        assertNotSame(sum1, pos2);

        Position sum2 = pos2.sub(-8, 2);
        assertEquals(3, sum2.x());
        assertEquals(2, sum2.y());
    }

    @Test
    void testEquals() {
        Position pos1 = new Position(4, 6);
        Position pos2 = new Position(4, 6);
        Position pos3 = new Position(4, 6);
        assertEquals(pos1, pos2);
        assertEquals(pos2, pos1);// reflex
        assertEquals(pos1, pos3);
        assertEquals(pos2, pos3);// sym
        Position pos4 = new Position(3,2);
        assertNotEquals(pos1, pos4);
        assertNotEquals(pos2, pos4);
        assertNotEquals(pos3, pos4);
    }
}