package de.hsaalen;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class ObstaclesTest {

    private Obstacles obstacles;
    private final int tileSize = 10;

    private void setUp() {
        obstacles = new Obstacles(tileSize);
    }

    @Test
    public void testAddObstacle() {
        setUp();
        obstacles.addObstacle(20, 30);
        assertEquals(1, obstacles.length());
        assertTrue(obstacles.isObstacleAt(20, 30));
    }

    @Test
    public void testIsObstacleAt() {
        setUp();
        obstacles.addObstacle(20, 30);
        assertTrue(obstacles.isObstacleAt(20, 30));
        assertFalse(obstacles.isObstacleAt(10, 10));
    }

    @Test
    public void testPlaceRandomObstacles() {
        setUp();
        int initialCount = obstacles.length();
        obstacles.placeRandomObstacles(5, 300, 300);

        assertEquals(initialCount + 5, obstacles.length());

        assertFalse(obstacles.isObstacleAt(50, 50));
    }

    @Test
    public void testGetPositions() {
        setUp();
        obstacles.addObstacle(20, 30);
        List<IntPair> positions = obstacles.getPositions();
        assertEquals(1, positions.size());
        assertEquals(20, positions.get(0).x);
        assertEquals(30, positions.get(0).y);
        
        positions.add(new IntPair(10, 10));
        assertEquals(1, obstacles.length());
    }
}
