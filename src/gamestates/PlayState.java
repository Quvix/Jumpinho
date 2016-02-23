package gamestates;

import java.awt.Graphics2D;

/**
 *
 * @author test
 */
public class PlayState extends GameState {
    
    public PlayState() {
        super();
    }
    
    @Override
    public void tick() {
        if(!paused){

        }
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation) {
        if(paused)
            interpolation = 0;
    }
    
    @Override
    public void restart() {
        this.init();
    }
    
     @Override
    public void init() {

    }
}
