package de.hsaalen;

import java.util.List;

import javax.swing.JFrame;

import java.util.LinkedList;

public class Snake {
    List<IntPair> positions;
    int tile_size_in_pixels;
        
    public Snake( int initial_snake_size, int tile_size_in_pixels ) 
    {
        this.tile_size_in_pixels = tile_size_in_pixels;
        allocate_memory();
        place_at_initial_location( initial_snake_size );
    }

    private void allocate_memory()
    {
        positions = new LinkedList<IntPair>();
    }
    
    public void place_at_initial_location( int initial_snake_size ) 
    {
        for (int i = 0; i < initial_snake_size; i++) 
        {
            int x = 50 - i * 10;
            int y = 50;
            IntPair new_position = new IntPair(x,y);
            positions.add( new_position );
        }		
    }
    
    public void move(Direction direction) {
        IntPair newHeadPosition = head_position().clone();
        newHeadPosition.move(direction, tile_size_in_pixels);
    
        positions.add(0, newHeadPosition);
        positions.remove(positions.size() - 1);
    }
    
    
    public void grow( Direction direction )
    {
        if(Board.counter_apple % 5 == 0){
            for(int i = 0; i<3; i++){
                IntPair new_head_position = head_position().clone();
                new_head_position.move( direction, tile_size_in_pixels );
                positions.add( 0, new_head_position );
            }
        }
        else{
            IntPair new_head_position = head_position().clone();
            new_head_position.move( direction, tile_size_in_pixels );
            positions.add( 0, new_head_position );
        }
    }
    
    public boolean is_snake_colliding(int board_width_in_pixels, int board_height_in_pixels) {
        return hasSelfCollision() || isOutOfBounds(board_width_in_pixels, board_height_in_pixels);
    }
    
    public boolean hasSelfCollision() {
        return is_colliding_with_itself();
    }
    
    public boolean isOutOfBounds(int board_width_in_pixels, int board_height_in_pixels) {
        return is_outside_board(board_width_in_pixels, board_height_in_pixels);
    }

    public boolean is_colliding_with_itself()
    {
        for ( int i = length()-1; i > 1; i-- )
        {
            if ( position( i ).x == head_position().x &&
                position( i ).y == head_position().y )
                return true;
        }
        return false;
    }
    public boolean is_outside_board( int board_width_in_pixels, int board_height_in_pixels )
    {
        if ( head_position().x < 0 )
            return true;
        if ( head_position().x >= board_width_in_pixels )
            return true;
        if ( head_position().y < 0 )
            return true;
        if ( head_position().y >= board_height_in_pixels )
            return true;
        return false;
    }

    public int length() 
    {
        return positions.size();
    }
    
    public IntPair position( int index )
    {
        return positions.get( index );
    }
    
    public IntPair head_position()
    {
        return position( 0 );
    }
    
    public String toString() 
    {
        String result = "Snake" + positions.toString();
        return result;
    }

}
