package world;

import main.Drawer;

import java.awt.Graphics2D;

/**
 * Created by Martin on 23.2.2016.
 */
public class World {

    private Map map;
    private Camera cam;
    private ObjectHandler objHandler;

    public World(String mapName){
        this.objHandler = new ObjectHandler();
        this.map = new Map(this, mapName);
        this.cam = new Camera();
    }

    public void tick(){
        getCam().tick();
        getMap().tick();
        getObjHandler().tick();
    }

    public void draw(Drawer g, double interpolation){
        getCam().draw(g, interpolation);
        getMap().draw(g, interpolation);
        getObjHandler().draw(g, interpolation);
    }

    public Map getMap() {
        return map;
    }

    public Camera getCam() {
        return cam;
    }

    public ObjectHandler getObjHandler() {
        return objHandler;
    }
}
