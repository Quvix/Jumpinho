package objects;

import Input.KeyInput;
import gamestates.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import static java.nio.file.Files.size;
import java.util.Set;
import main.GameObject;
import main.GamePanel;

/**
 *
 * @author test
 */
public class Tile extends GameObject {
    
    public static final float DEFAULT_SIZE = 64;
    
    public Tile(float x, float y, GamePanel gp, GameState gs){
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
