package de.hsaalen;

import java.util.List;
import javax.swing.JFrame;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Image;

public class Obstacles{
    List<IntPair> positions;
    int tile_size_in_pixels;

    public Obstacles(int tile_size_in_pixels){
        this.tile_size_in_pixels = tile_size_in_pixels;
        positions = new LinkedList<IntPair>();
    }

    public int length() 
    {
        return positions.size();
    }

    public IntPair position( int index )
    {
        return positions.get( index );
    }

    public boolean isObstacleAt(int x, int y) {
        for (IntPair position : positions) {
            if (position.x == x && position.y == y) {
                return true;
            }
        }
        return false;
    }

    public void addObstacle(int x, int y){
        positions.add(new IntPair(x, y));
    }

    public void placeRandomObstacles(int numObstacles, int boardWidth, int boardHeight) {
        for (int i = 0; i < numObstacles; i++) {
            int x = (int) (Math.random() * (boardWidth / tile_size_in_pixels)) * tile_size_in_pixels;
            int y = (int) (Math.random() * (boardHeight / tile_size_in_pixels)) * tile_size_in_pixels;
            if (!isObstacleAt(x, y) && !(x==50 && y ==50)) {
                addObstacle(x, y);
            }
        }
    }

    public void renderObstacles(Graphics g, Image obstacleImage) {
        for (IntPair position : positions) {
            g.drawImage(obstacleImage, position.x, position.y, null);
        }
    }

    public List<IntPair> getPositions() {
        return new LinkedList<>(positions); // Gibt eine Kopie zurück
    }

    public boolean isCollidingWith(int x, int y) {
        return isObstacleAt(x, y);
    }
}