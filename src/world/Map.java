package world;

import world.objects.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author test
 */
public class Map {

    private World world;

    private int width;
    private int height;

    private short[][] mapGrid;
    
    public Map(World world, String name){
        this.world = world;
        loadMap(name);
    }
    
    public void loadMap(String name) {
        try {
            InputStream is = Map.class.getClassLoader().getResourceAsStream("resources/maps/" + name + ".map");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            try {
                width = Integer.parseInt(br.readLine());
                height = Integer.parseInt(br.readLine());

                mapGrid = new short[width][height];

                for (int y = 0; y < height; y++) {
                    String line = br.readLine();
                    String[] tokens = line.split("\\s+");

                    for (int x = 0; x < width; x++) {
                        short id = Short.parseShort(tokens[x]);
                        if (id != 0){
                            // TODO some tile loading
                            world.getObjHandler().addTile(new Tile(world, id, true));
                        }
                        mapGrid[x][y] = id;
                    }
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Error loading map \"" + "/resources/maps/" + name + ".map" + "\": " + e.toString());
        }
    }
    
    public void tick() {

    }
    
    public void draw(Graphics2D g, double interpolation) {
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if (mapGrid[x][y] != 0)
                    world.getObjHandler().getTileByID(mapGrid[x][y]).drawAt(g, interpolation, x * Tile.DEFAULT_SIZE, y * Tile.DEFAULT_SIZE);
            }
        }
    }

    public ArrayList<Rectangle> getCollidingRects(Rectangle rect){
        ArrayList<Rectangle> list = new ArrayList<>();

        Point min = new Point(Math.max((int) Math.floor(rect.x / Tile.DEFAULT_SIZE), 0), Math.max((int) Math.floor(rect.y / Tile.DEFAULT_SIZE), 0));
        Point max = new Point(Math.min((int) Math.ceil((rect.x + rect.width) / Tile.DEFAULT_SIZE), width-1), Math.min((int) Math.ceil((rect.y + rect.height-1) / Tile.DEFAULT_SIZE), height - 1));

        for (int x = min.x; x <= max.x; x++){
            for (int y = min.y; y <= max.y; y++){
                if (mapGrid[x][y] != 0 && world.getObjHandler().getTileByID(mapGrid[x][y]).isSolid())
                    list.add(new Rectangle(x * Tile.DEFAULT_SIZE, y * Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE));
            }
        }

        return list;
    }
      
}
