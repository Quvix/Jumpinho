package world;

import world.objects.GameObject;
import world.objects.Player;
import world.objects.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 */
public class ObjectHandler {
    private List<Tile> tiles = new ArrayList();
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

    public void draw(Graphics2D g, double interpolation){
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
        tiles.add(tile);
    }
}
