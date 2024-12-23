package de.hsaalen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    public final int width_in_pixels  = 300;
    public final int height_in_pixels = 300;
    public final int tile_size_in_pixels = 10;
    public final int maximum_snake_length = 900;
    public final int game_loop_duration_in_ms = 140;
    public final int initial_snake_size = 3;

    public Snake snake;

    private int current_snake_size;
    private Apple apple;
    private Obstacles obstacles;

    private Direction direction = Direction.right;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image head;

    public static int counter_apple = 0;

    public Board() {
        
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(width_in_pixels, height_in_pixels));
        loadImages();
        initGame();
    }

    public int get_current_snake_size(){
        return current_snake_size;
    }

    public Apple get_apple(){
        return apple;
    }

    public Timer get_timer(){
        return timer;
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        Apple.image = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void switch_apple_image(){
        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        ImageIcon iisa = new ImageIcon("src/resources/apple_red.png");

        if(counter_apple % 5 == 0) Apple.image = iisa.getImage();
        else Apple.image = iia.getImage();
    }

    private void initGame() {

        place_snake_at_initial_location();    
        initObstacles();    
        place_apple_at_random_location();
		start_game_loop_timer();
    }

    private void initObstacles() {
        obstacles = new Obstacles(tile_size_in_pixels);
        obstacles.placeRandomObstacles(10, width_in_pixels, height_in_pixels);
    }

    public void start_game_loop_timer()
	{
        timer = new Timer(game_loop_duration_in_ms, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(Apple.image, apple.get_position().x, apple.get_position().y, this);

            for (int i = 0; i < snake.length(); i++) {
                if (i == 0) {
                    g.drawImage(head, snake.position(i).x, snake.position(i).y, this);
                } else {
                    g.drawImage(ball, snake.position(i).x, snake.position(i).y, this);
                }
            }

            obstacles.renderObstacles(g, ball);

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width_in_pixels - metr.stringWidth(msg)) / 2, height_in_pixels / 2);
    }

    private void checkApple() 
	{
        if ((snake.head_position().x == apple.get_position().x) && (snake.head_position().y == apple.get_position().y)) 
		{
			snake.grow( direction );
            place_apple_at_random_location();
        }
    }

    private void move() 
	{
		snake.move( direction );
    }

    private void checkCollision()
	{
		if ( snake.is_snake_colliding( width_in_pixels, height_in_pixels) )
			inGame = false;

        if(obstacles.isCollidingWith(snake.head_position().x, snake.head_position().y)){
            inGame = false;
        }
		
        if (!inGame) {
            timer.stop();
        }
    }

    public int maximum_tile_index_x()
	{
		return ( width_in_pixels / tile_size_in_pixels ) - 1;
	}
	
    public int maximum_tile_index_y()
	{
		return ( height_in_pixels / tile_size_in_pixels ) - 1;
	}

    public void place_snake_at_initial_location() 
	{
        snake = new Snake( 3, tile_size_in_pixels );	
	}

    private void place_apple_at_random_location() {

        int apple_x, apple_y;
        boolean isValidPosition;

        do{

            int r = (int) (Math.random() * maximum_tile_index_x());
            apple_x = ((r * tile_size_in_pixels));

            r = (int) (Math.random() * maximum_tile_index_y());
            apple_y = ((r * tile_size_in_pixels));

            isValidPosition = true;
            for(int i =0; i < obstacles.length(); i++){
                if(obstacles.position(i).x == apple_x && obstacles.position(i).y == apple_y){
                    isValidPosition = false;
                    break;
                }
            }
        }while(!isValidPosition);

        counter_apple++;
        apple = new Apple(tile_size_in_pixels, apple_x, apple_y);
        switch_apple_image();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ( key == KeyEvent.VK_LEFT )
				direction = Direction.left;
			
            if ( key == KeyEvent.VK_RIGHT )
				direction = Direction.right;
			
            if ( key == KeyEvent.VK_UP )
				direction = Direction.up;
			
            if ( key == KeyEvent.VK_DOWN )
				direction = Direction.down;
        }
    }
}
