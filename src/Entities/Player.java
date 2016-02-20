package Entities;

import Input.KeyInput;
import gamestates.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Set;
import main.GameObject;
import main.GamePanel;
import objects.Tile;

/**
 *
 * @author test
 */
public class Player extends GameObject {
    
    private boolean jumping = false, falling = false;
    private boolean bottomCollisionNextTick = false, topCollisionNextTick = false, leftCollisionNextTick = false, rightCollisionNextTick = false;
    private final float jumpSpeed = -10;
    private final float maxFallSpeed = 15;
    
    public Player(GamePanel gp, GameState gs){
        super(gp,gs);
        color = new Color(255, 0, 0);
        size = 32;
        x = gp.size.width / 2 - size / 2;
        y = gp.size.height / 2 - size / 2;
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
        if(key.contains(KeyEvent.VK_RIGHT)) velX = getSpeed();
        else velX = 0;
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
            velY += 0.3;
            if(velY >= 0) {
                jumping = false;
                falling = true;
                velY = 0.3f;
            }
        }
        
        if(falling) {
            if(velY < maxFallSpeed) {
                if(!bottomCollisionNextTick) {
                    velY += 0.3f;
                }
            }
                
        }
        
        if(!falling && !jumping) {
            velY += 0.3f;  
            falling = true;
        }
        
        // Konec mapy pouze pro test
        if(this.x < 0) {
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
        }
        if(this.y > gp.size.height - this.size) {
            y = gp.size.height - this.size;
            velY = 0;
            falling = false;
        }
        
        for(Tile e: gs.objects.tiles){
            if(this.predictPosition(1).intersects(e.getRect())) {
                
                if((this.getRect().x + 1) >= (e.getRect().x + e.getRect().width)) {
                    velX = ((e.getRect().x + e.getRect().width) - this.getRect().x);
                    leftCollisionNextTick = true;
                }
                
                if((this.getRect().x + this.getRect().width - 1) <= e.getRect().x) {
                    velX = (e.getRect().x - (this.x + this.size));
                    rightCollisionNextTick = true;
                }
                
                if((this.getRect().y + this.getRect().height - 1) <= e.getRect().y) {
                    if(getBottomBounds().intersects(e.getRect())) {
                        velY = (e.getRect().y - (this.y + this.size));
                        bottomCollisionNextTick = true;
                        falling = false;
                    }
                }
                
                if((this.getRect().y + 1) >= (e.getRect().y + e.getRect().height)) {
                    velY = ((e.getRect().y + e.getRect().height) - this.getRect().y);
                    topCollisionNextTick = true;
                    falling = true;
                    jumping = false;
                }
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
        return new Rectangle((int)(this.x), (int)(this.y + 10), this.getIntSize(), this.getIntSize());
    }

}
