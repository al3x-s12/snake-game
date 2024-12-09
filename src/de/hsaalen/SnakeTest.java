package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class SnakeTest 
{
	@Test
	public void test_initialization()
	{
		Snake snake = new Snake( 3, 10 );
		assertEquals( snake.length(), 3 );
		
		IntPair coordinate;
		coordinate= snake.position(0);
		assertEquals( coordinate.x, 50 );
		assertEquals( coordinate.y, 50 );
		
		coordinate = snake.position(2);
		assertEquals( coordinate.x, 30 );
		assertEquals( coordinate.y, 50 );
	}

	@Test
	public void test_move_snake()
	{
		Snake snake = new Snake( 3, 10 );
		snake.move( Direction.up );
		snake.move( Direction.up );
		
		IntPair head_position = snake.head_position();
		assertEquals( 50, head_position.x);
		assertEquals( 30, head_position.y);

		assertEquals( 3, snake.length() );

		assertEquals(50, snake.position(1).x);
		assertEquals(40, snake.position(1).y);

		assertEquals(50, snake.position(2).x);
		assertEquals(50, snake.position(2).y);		
	}

	@Test
	public void test_isOutOfBounds()
	{
		Snake snake = new Snake( 3, 10 );
		assertFalse( snake.isOutOfBounds(300, 300) );
	}

	@Test
	public void test_hasSelfCollition()
	{
		Snake snake = new Snake( 3, 10 );
		snake.grow( Direction.up );
		snake.grow( Direction.left );
		snake.move( Direction.down );
		assertTrue( snake.hasSelfCollision());		
	}

	@Test
	public void test_is_outside_board()
	{
		Snake snake = new Snake( 3, 10 );
		assertFalse( snake.is_outside_board( 300, 300 ) );		
		for ( int i = 0; i < 10; i++ )
			snake.move( Direction.left );
		assertTrue( snake.is_outside_board( 300, 300 ) );		
	}	
}