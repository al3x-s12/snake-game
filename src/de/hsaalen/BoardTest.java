package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class BoardTest {

    @Test
    public void test_maximum_tile_index_x(){
        Board board = new Board();
        int maximum_tile_index_x = board.maximum_tile_index_x();
		assertEquals( ( maximum_tile_index_x + 1 ) * board.tile_size_in_pixels, board.width_in_pixels );
    }

    @Test
	public void  test_maximum_tile_index_y(){
		Board board = new Board();
		int maximum_tile_index_y = board.maximum_tile_index_y();
		assertEquals( ( maximum_tile_index_y + 1 ) * board.tile_size_in_pixels, board.width_in_pixels );
	}

    @Test
    public void testConcatenate() {
        Board board = new Board();
        assertNotNull( board );
    }

    @Test
    public void test_place_snake_at_initial_location(){
        Board board = new Board();
        board.place_snake_at_initial_location();
        assertEquals(board.snake.position(0).x, 50);
        assertEquals(board.snake.position(1).x, 40);
        assertEquals(board.snake.position(2).x, 30);

        assertEquals(board.snake.position(0).y, 50);
        assertEquals(board.snake.position(1).y, 50);
        assertEquals(board.snake.position(2).y, 50);
    }

    @Test
    public void test_place_apple_at_random_location(){

        Board board = new Board();
        board.place_snake_at_initial_location();
        assertTrue(board.get_apple_x() >= 0);
        assertTrue(board.get_apple_x() < board.maximum_tile_index_x() * board.tile_size_in_pixels);

        assertTrue(board.get_apple_y() >= 0);
        assertTrue(board.get_apple_y() < board.maximum_tile_index_y() * board.tile_size_in_pixels);
    }

    @Test
    public void test_start_game_loop_timer(){

        Board board = new Board();
        board.start_game_loop_timer();

        assertNotNull(board.get_timer());

        assertEquals(board.game_loop_duration_in_ms, board.get_timer().getDelay());

        assertTrue(board.get_timer().isRunning());
    }
}