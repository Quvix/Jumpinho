package gamestates;

import main.Drawer;

import java.awt.Graphics2D;

/**
 *
 * @author Quvix & Matěj Stuchlík & Martin Omacht
 */
public abstract class GameState {

    protected boolean paused;
    
    public GameState(){
        init();
    }
    
    public abstract void tick();
    public abstract void draw(Drawer g, double interpolation);

    public void restart(){
        init();
    }

    protected void init(){
        paused = false;
    }
    
    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }
}
