package world.objects;

import input.KeyInput;
import main.Drawer;
import world.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

/**
 *
 * @author test
 */
public class Enemy extends GameObject {
    
    private boolean jumping = false, falling = false;
    private boolean bottomCollisionNextTick = false, topCollisionNextTick = false, leftCollisionNextTick = false, rightCollisionNextTick = false;
    private final float jumpSpeed = -31;
    private final float maxFallSpeed = 30;
    private final float gravity = 2f;
    
    public Enemy(World world){
        super(world);
        color = new Color(0, 255, 0);
        size = 68;
        x = 500;//gp.size.width / 2 - size / 2;
        y = 300;//gp.size.height / 2 - size / 2;
        DEFAULT_SPEED = 8f;
        this.speed = DEFAULT_SPEED;
    }
    
    @Override
    public void tick(){
        this.x += velX + 3;
        
         if(leftCollisionNextTick) {
            velX = 0;
            leftCollisionNextTick = false;
        }
        
        if(rightCollisionNextTick) {
            velX = -6;
            rightCollisionNextTick = false;
        }
        
        Rectangle sweptRect = this.getRect();
        sweptRect.add(this.predictPosition(1));
        List<Rectangle> collidingBoxes = world.getMap().getCollidingRects(sweptRect);
        
        for(Rectangle r: collidingBoxes){
            if(this.predictPosition(1).intersects(r)) {
                if((this.x + 10) >= (r.x + r.width)) {
                    if(getLeftBounds().intersects(r)) {
                        velX = ((r.x + r.width) - this.x);
                        leftCollisionNextTick = true;
                    }
                }
                
                if((this.x + this.getRect().width - 10) <= r.x) {
                    if(getRightBounds().intersects(r)) {
                        velX = (r.x - (this.x + this.size));
                        rightCollisionNextTick = true;
                    }
                    
                }
            }
        }
    }
    
    @Override
    public void draw(Drawer g, double interpolation){
         super.draw(g, interpolation);
    }
    
    private Rectangle predictPosition(int ticks){
        return new Rectangle((int)(this.x + (velX * ticks)), (int)(this.y + (velY * ticks)), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getLeftBounds() {
        return new Rectangle((int)(this.x - speed), (int)(this.y), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getRightBounds() {
        return new Rectangle((int)(this.x + speed), (int)(this.y), this.getIntSize(), this.getIntSize());
    }

    
}
