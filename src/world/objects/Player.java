package world.objects;

import input.KeyInput;
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
public class Player extends GameObject {
    
    private boolean jumping = false, falling = false;
    private boolean bottomCollisionNextTick = false, topCollisionNextTick = false, leftCollisionNextTick = false, rightCollisionNextTick = false;
    private final float jumpSpeed = -31;
    private final float maxFallSpeed = 30;
    private final float gravity = 2f;
    
    public Player(World world){
        super(world);
        color = new Color(255, 0, 0);
        size = 48;
        x = 500;//gp.size.width / 2 - size / 2;
        y = 200;//gp.size.height / 2 - size / 2;
        DEFAULT_SPEED = 8f;
        this.speed = DEFAULT_SPEED;
    }
    
    @Override
    public void tick(){
        
        this.x += velX;
        this.y += velY;
        
        Set<Integer> key = KeyInput.pressed;
        if(key.contains(KeyEvent.VK_UP)) {
            if(!jumping && !falling) {
                velY = (float)jumpSpeed;
                jumping = true;
            }
        }
        if(key.contains(KeyEvent.VK_RIGHT)) if(!rightCollisionNextTick) velX = getSpeed();
        if(key.contains(KeyEvent.VK_LEFT)) if(!leftCollisionNextTick) velX = -getSpeed();
        
        if(key.contains(KeyEvent.VK_RIGHT) && key.contains(KeyEvent.VK_LEFT)) velX = 0;
        
        if(bottomCollisionNextTick) {
            if(!jumping) velY = 0;
            bottomCollisionNextTick = false;
        }
        
        if(topCollisionNextTick) {
            topCollisionNextTick = false;
        }
        
        if(leftCollisionNextTick) {
            velX = 0;
            leftCollisionNextTick = false;
        }
        
        if(rightCollisionNextTick) {
            velX = 0;
            rightCollisionNextTick = false;
        }
        
        
        if(jumping) {
            velY += gravity;
            if(velY >= 0) {
                jumping = false;
                falling = true;
                velY = gravity;
            }
        }
        
        if(falling) {
            if(velY < maxFallSpeed) {
                if(!bottomCollisionNextTick) {
                    velY += gravity;
                }
            }
                
        }
        
        if(!falling && !jumping) {
            velY += gravity;  
            falling = true;
        }
        
        // Konec mapy pouze pro test
        /*if(this.x < 0) {
            x = 0;
            velX = 0;
        }
        if(this.x > gp.size.width - this.size){
            x = gp.size.width - this.size;
            velX = 0;
        }
        if(this.y < 0) {
            y = 0;
            velY = 0;
        }*/
        /*if(this.y > gp.size.height - this.size) {
            /*y = gp.size.height - this.size;
            velY = 0;
            falling = false;
            x = gp.size.width / 2 - size / 2;
            y = gp.size.height / 2 - size / 2;
        }*/

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
                
                if((this.y + this.getRect().height - 10) <= r.y) {
                    if(getBottomBounds().intersects(r)) {
                        velY = (r.y - (this.y + this.size));
                        bottomCollisionNextTick = true;
                        falling = false;
                    }
                }
                
                if((this.y + 10) >= (r.y + r.height)) {
                    if(getTopBounds().intersects(r)) {
                       velY = ((r.y + r.height) - this.y);
                        topCollisionNextTick = true;
                        falling = true;
                        jumping = false; 
                    }
                }
                
                // Pouze Quvixuv test
                /*if(this.predictPosition(1).intersects(e)) {
                    if(getLeftBounds().intersects(e)) {
                        if((this.x) <= (e.x + e.width)) {
                            velX = ((this.x + this.size) - e.x) + 1;
                        }
                    }
                    
                    System.out.println("Chyba kolize!");
                }*/
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation){
        
        super.draw(g, interpolation);
        
        Set<Integer> key = KeyInput.pressed;
        if(!(key.contains(KeyEvent.VK_LEFT)) && velX < 0){
            this.x += velX * interpolation;
            velX = 0;
        }
        if(!(key.contains(KeyEvent.VK_RIGHT)) && velX > 0){
            this.x += velX * interpolation;
            velX = 0;
        }
    }
    
    private Rectangle predictPosition(int ticks){
        return new Rectangle((int)(this.x + (velX * ticks)), (int)(this.y + (velY * ticks)), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getBottomBounds() {
        return new Rectangle((int)(this.x), (int)(this.y + maxFallSpeed), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getTopBounds() {
        return new Rectangle((int)(this.x), (int)(this.y - maxFallSpeed), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getLeftBounds() {
        return new Rectangle((int)(this.x - speed), (int)(this.y), this.getIntSize(), this.getIntSize());
    }
    
    public Rectangle getRightBounds() {
        return new Rectangle((int)(this.x + speed), (int)(this.y), this.getIntSize(), this.getIntSize());
    }

}
