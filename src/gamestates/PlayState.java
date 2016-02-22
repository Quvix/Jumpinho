/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import entities.Player;

import java.awt.Graphics2D;
import main.GameCanvas;
import main.Map;
import main.ObjectHandler;

/**
 *
 * @author test
 */
public class PlayState extends GameState {
    
    private long ticks;
    private Map map;
    
    public PlayState(GameCanvas gp) {
        super(gp);
    }
    
    @Override
    public void tick() {
        if(!paused){
            objects.player.tick();
            map.tick();
        }
        this.ticks++;
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation) {
        if(paused)
            interpolation = 0;
        
        this.xOffset = Math.floor(gp.size.width/2 - objects.player.getX() + objects.player.getSize()/2 - objects.player.getVelX() * interpolation);
        this.objects.player.draw((Graphics2D)g, interpolation);
        map.draw(g, interpolation);
        
        
    }
    
    @Override
    public void restart() {
        this.init();
    }
    
     @Override
    public void init() {
        this.objects = new ObjectHandler();

        objects.player = new Player(gp, this);
        map = new Map("/resources/maps/map1.map", gp, this);
        
        /*this.objects.tiles.add(new Tile(150f, 450f + 128f, gp, this));
        this.objects.tiles.add(new Tile(214f, 450f + 128f, gp, this));
        //this.objects.tiles.add(new Tile(278f, 450f, gp, this));
        this.objects.tiles.add(new Tile(342f, 450f + 128f, gp, this));
        this.objects.tiles.add(new Tile(342f, 386f + 128f, gp, this));
        
        this.objects.tiles.add(new Tile(470f, 386f + 128f, gp, this));
        this.objects.tiles.add(new Tile(534f, 450f + 128f, gp, this));
        this.objects.tiles.add(new Tile(534f, 386f + 128f, gp, this));
        
         for (int i = 0; i < 20; i++) {
             this.objects.tiles.add(new Tile(i * 64f, 704f, gp, this));
         }*/
        

        ticks = 0;
        this.resume();
    }
    
    @Override
    public void init(Object o) {
        
    }
    
     public long getTicks() {
        return ticks;
    }
}
