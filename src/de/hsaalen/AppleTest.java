package de.hsaalen;

import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class AppleTest {

    @Test
    public void test_constructor(){
        Apple apple = new Apple(10, 5, 10);

        IntPair position = apple.get_position();
        assertNotNull(position);
        assertEquals(5, position.x);
        assertEquals(10, position.y);

        assertEquals(10, apple.get_tile_size_in_pixels());
    }
}