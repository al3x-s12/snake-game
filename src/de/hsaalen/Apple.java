package de.hsaalen;

import java.awt.Image;

public class Apple {
    public static Image image;

    private IntPair position;
    private int tile_size_in_pixels;

    public Apple(int tile_size_in_pixels, int position_x, int position_y){
        
        this.tile_size_in_pixels = tile_size_in_pixels;
        position = new IntPair(position_x, position_y);
    }

    public IntPair get_position()
    {
        return position;
    }

    public int get_tile_size_in_pixels(){
        return tile_size_in_pixels;
    }
}