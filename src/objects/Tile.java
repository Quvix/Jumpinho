package objects;

import gamestates.GameState;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GameObject;
import main.GameCanvas;

/**
 *
 * @author test
 */
public class Tile extends GameObject {
    
    public static final float DEFAULT_SIZE = 64;
    
    public Tile(float x, float y, GameCanvas gp, GameState gs){
        super(gp,gs);
        color = Color.BLACK;
        size = DEFAULT_SIZE;
        this.x = x;
        this.y = y;
        DEFAULT_SPEED = 0f;
        this.speed = DEFAULT_SPEED;
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation){
        super.draw(g, interpolation);
    }
    
}
