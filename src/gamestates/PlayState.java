package gamestates;

import world.World;
import world.objects.Player;

import java.awt.Graphics2D;

/**
 *
 * @author test
 */
public class PlayState extends GameState {

    protected World world;
    
    public PlayState() {
        super();
        world = new World("map1");
        Player player = new Player(world);
        world.getObjHandler().addObject(player);
        world.getCam().setTarget(player);
    }
    
    @Override
    public void tick() {
        if(!paused){
            world.tick();
        }
    }
    
    @Override
    public void draw(Graphics2D g, double interpolation) {
        if(paused)
            interpolation = 0;
        world.draw(g, interpolation);
    }
    
    @Override
    public void restart() {
        this.init();
    }
    
     @Override
    public void init() {

    }
}
