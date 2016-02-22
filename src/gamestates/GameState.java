package gamestates;

import java.awt.Graphics2D;
import main.GameCanvas;
import main.ObjectHandler;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík
 */
public abstract class GameState {
    
    protected GameCanvas gp;
    public ObjectHandler objects = new ObjectHandler();
    protected boolean paused;
    
    public double xOffset, yOffset;
    
    
    public GameState(GameCanvas gp){
        this.gp = gp;
        paused = false;
        
        xOffset = 0;
        yOffset = 0;
    }
    
    public ObjectHandler getObjects(){
        return objects;
    }
    
    public abstract void tick();
    public abstract void draw(Graphics2D g, double interpolation);
    public abstract void restart();
    public abstract void init();
    public abstract void init(Object o);
    
    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }
}
