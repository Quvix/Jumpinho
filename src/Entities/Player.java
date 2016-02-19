package Entities;

import gamestates.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GameObject;
import main.GamePanel;

/**
 *
 * @author test
 */
public class Player extends GameObject {
    
    public Player(GamePanel gp, GameState gs){
        super(gp,gs);
        color = new Color(255, 0, 0);
        size = 24;
        x = gp.size.width / 2 - size / 2;
        y = gp.size.height / 2 - size / 2;
        DEFAULT_SPEED = 8f;
        this.speed = DEFAULT_SPEED;
    }
    
    @Override
    public void tick(){
        
        this.x += velX;
        this.y += velY;
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation){
        super.draw(g, interpolation);
    }
}
