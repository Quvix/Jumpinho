package gamestates;

import java.awt.Graphics2D;
import java.util.Stack;
import main.GameCanvas;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík
 */
public class GameStateManager {
    
    private final Stack<GameState> states = new Stack();
    
    public final GameState PLAY_STATE;
    
     private final GameCanvas gp;
    
    public GameStateManager(GameCanvas gp){
        this.PLAY_STATE = new PlayState(gp);
        
        this.pushState(this.PLAY_STATE);
        this.gp = gp;
    }
    
    public void tick(){
        states.peek().tick();
    }   
    
    public void draw(Graphics2D g, double interpolation){
        states.peek().draw(g, interpolation);
    }
    
    public void pause(){
        states.peek().pause();
    }
    
    public void resume(){
        states.peek().resume();
    }
    
    public void restart(){
        states.peek().restart();
    }
    
    public void init(){
        states.peek().init();
    }
    
    public void pushState(GameState s){
        if(!states.empty())
            states.peek().pause();
        states.push(s);
        states.peek().init();
    }
    
    public void pushState(GameState s, Object o){
        if(!states.empty())
            states.peek().pause();
        states.push(s);
        states.peek().init(o);
    }
    
    public void popCurrentState(){
        states.pop();
        if(!states.empty())
            states.peek().resume();
    }
    
    public GameState getCurrentState(){
        return states.peek();
    }
    
    public void prepareState(GameState s){
        s.init();
        s.pause();
    }
    
    public void resume(GameState s){
        states.push(s);
        resume();
    }
}
