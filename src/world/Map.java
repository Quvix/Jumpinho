package world;

import gamestates.GameState;
import main.GameCanvas;
import world.objects.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author test
 */
public class Map {

    private World world;

    private int width;
    private int height;
    
    public Map(World world, String path){
        this.world = world;
        loadMap(path);
    }
    
    public void loadMap(String path) {
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        try {
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());

            for (int y = 0; y < height; y++) {
                String line = br.readLine();
                String[] tokens = line.split("\\s+");
                
                for (int x = 0; x < width; x++) {
                    //blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, Integer.parseInt(tokens[x]));
                    if(Integer.parseInt(tokens[x]) != 0)
                        gs.objects.tiles.add(new Tile(x * Tile.DEFAULT_SIZE, y * Tile.DEFAULT_SIZE, gp, gs));
                }
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void tick() {

    }
    
    public void draw(Graphics2D g, double interpolation) {
        for(Tile e: gs.objects.tiles){
            e.draw((Graphics2D)g, interpolation);
        }
    }
      
}
