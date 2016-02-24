/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.objects;

import world.World;

import java.awt.*;

/**
 *
 * @author test
 */
public abstract class GameObject {
    
    protected float x, y;
    protected float velX, velY;
    protected Color color;
    protected float size;
    protected float speed;
    private boolean dead = false;
    protected World world;
    
    protected float DEFAULT_SPEED = 0;
    
    public GameObject(World world){
        this.world = world;
    }
    
    public void draw(Graphics2D g, double interpolation){
        g.setColor(color);
        g.fill(world.getCam().rectToScreenCoords(getRect(interpolation)));
    }
    
    public Rectangle getCollisionBox(){
        return this.getRect();
    }
    
    public Rectangle getRect(){
        return getRect(0);
    }
    
    public Rectangle getRect(double i){
        return new Rectangle((int)(x + (velX * i)), (int) (y + (velY * i)), this.getIntSize(), this.getIntSize());
    }
    
    public void tick(){        
        this.x += velX;
        this.y += velY;
    }
    
    public int getIntSize(){
        return Math.round(this.size);
    }
    
    public float getVelocity(){
        return (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
    }
    
    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the velX
     */
    public float getVelX() {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     * @return the velY
     */
    public float getVelY() {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    /**
     * @return the size
     */
    public float getSize() {
        return size;
    }
    
    protected AlphaComposite makeTransparent(float alpha){
        return (AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public Color getColor() {
        return this.color;
    }
    
    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    /**
     * @return the DEFAULT_SPEED
     */
    public float getDEFAULT_SPEED() {
        return DEFAULT_SPEED;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
