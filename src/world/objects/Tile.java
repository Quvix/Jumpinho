package world.objects;

import main.Drawer;
import world.World;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author test
 */
public class Tile extends GameObject {
    
    public static final int DEFAULT_SIZE = 64;
    public final short ID;
    private final boolean solid;
    
    public Tile(World world, short id, boolean solid){
        super(world);
        color = Color.BLACK;
        size = DEFAULT_SIZE;
        this.x = 0;
        this.y = 0;
        DEFAULT_SPEED = 0f;
        this.speed = DEFAULT_SPEED;
        ID = id;
        this.solid = solid;
    }
    
    public void drawAt(Drawer g, double interpolation, int x, int y){
        this.x = x;
        this.y = y;

        super.draw(g, interpolation);

        this.x = 0;
        this.y = 0;
    }

    public boolean isSolid() {
        return solid;
    }
}
