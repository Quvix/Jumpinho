package world;

import java.awt.Graphics2D;

/**
 * Created by Martin on 23.2.2016.
 */
public class World {

    private Map map;
    private Camera cam;

    public World(String mapName){
        this.map = new Map(mapName);
        this.cam = new Camera();
    }

    public void tick(){
        cam.tick();
        map.tick();
    }

    public void draw(Graphics2D g, double interpolation){
        cam.draw(g, interpolation);
        map.draw(g, interpolation);
    }
}
