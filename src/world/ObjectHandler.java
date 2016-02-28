package world;

import main.Drawer;
import world.objects.GameObject;
import world.objects.Player;
import world.objects.Tile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 */
public class ObjectHandler {
    private HashMap<Short, Tile> tiles = new HashMap<>();
    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> toAdd = new ArrayList<>();

    private boolean isIterating = false;

    public void tick(){
        isIterating = true;

        ListIterator<GameObject> it = objects.listIterator();
        while (it.hasNext()){
            GameObject obj = it.next();
            obj.tick();
            if(obj.isDead())
                it.remove();
        }

        isIterating = false;

        objects.addAll(toAdd);
        toAdd.clear();
    }

    public void draw(Drawer g, double interpolation){
        isIterating = true;

        ListIterator<GameObject> it = objects.listIterator();
        while (it.hasNext()){
            GameObject obj = it.next();
            obj.draw(g, interpolation);
        }

        isIterating = false;
    }

    public void addObject(GameObject object){
        if(isIterating)
            toAdd.add(object);
        else
            objects.add(object);
    }

    public void addTile(Tile tile){
        if (tiles.get(Short.valueOf(tile.ID)) == null)
            tiles.put(tile.ID, tile);
    }

    public Tile getTileByID(short id){
        return tiles.get(Short.valueOf(id));
    }
}
