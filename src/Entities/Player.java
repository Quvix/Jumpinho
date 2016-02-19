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

/**
 *
 * @author test
 */
public class Player extends GameObject {
    
    private boolean jumping = false, falling = false;
    private final float jumpSpeed = -6;
    private final float maxFallSpeed = 7;
    
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
        Set<Integer> key = KeyInput.pressed;
        if(key.contains(KeyEvent.VK_UP)) {
            if(!jumping && !falling) {
                velY = (float)jumpSpeed;
                jumping = true;
            }
        }
        if(key.contains(KeyEvent.VK_RIGHT)) velX = getSpeed();
        else velX = 0;
        if(key.contains(KeyEvent.VK_LEFT)) velX = -getSpeed();
        
        if(key.contains(KeyEvent.VK_RIGHT) && key.contains(KeyEvent.VK_LEFT)) velX = 0;
       
        this.x += velX;
        
        if(jumping) {
            this.y += velY;
            velY += 0.15;
            if(velY >= 0) {
                jumping = false;
                falling = true;
                velY = 0.15f;
            }
        }
        
        if(falling) {
            this.y += velY;
            if(velY < maxFallSpeed)
                velY += 0.15f;
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
        System.out.println(velY);
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

}
