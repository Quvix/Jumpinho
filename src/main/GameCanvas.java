package main;

import gamestates.GameStateManager;
import input.KeyInput;
import input.MouseInput;
import input.MouseWheelMoved;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík & Martin Omacht
 */
public class GameCanvas extends Canvas implements Runnable {

    // Referenční rozlišení ve kterém se hra renderuje
    public static final int REF_WIDTH = 1280, REF_HEIGHT = 720;
    
    public Dimension size = new Dimension(REF_WIDTH, REF_HEIGHT);
    
    public GameStateManager gsm;
    private Thread thread;
    public static boolean paused = false;
    private double interpolation = 0;
    private Frame f;
    private Drawer g;
    
    public GameCanvas(){
        init();
        start();
    }
    
    private void init(){
        f = new Frame(size.width, size.height, "Jumpinho", this);
        //f.makeFullscreen(true);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFocusable(true);
        
        this.addMouseWheelListener(new MouseWheelMoved());
        this.addKeyListener(new KeyInput());
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());

        g = new Drawer();
    }
    
    private void start(){
        this.gsm = GameStateManager.getInstance();
        thread = new Thread(this);
        thread.start();
    }
    
    // GAME LOOP

    private static final int UPDATES_PER_SECOND = 30;
    private static final double UPDATE_INTERVAL = 1_000 / UPDATES_PER_SECOND * 1_000_000;
    private static final int MAX_FRAMESKIP = 5;
    
    private long nextUpdate = System.nanoTime();
    public int frames = 0;
    public int FPS = 0;

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        while (true) {
            if(!paused){
                int skippedFrames = 0;
                while (System.nanoTime() > this.nextUpdate && skippedFrames < MAX_FRAMESKIP) {
                    this.gameTick();
                    this.nextUpdate += UPDATE_INTERVAL;
                    skippedFrames++;
                }

                this.interpolation = (System.nanoTime() + UPDATE_INTERVAL - this.nextUpdate) / UPDATE_INTERVAL;
                this.gameRender();

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    FPS = frames;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        }
    }
    
    private void gameTick(){
        gsm.tick();
    }
    
    private void gameRender(){   
        if(!g.createGraphics(this))
            return;
        
        g.setFont(Drawer.FontE.SANS_SERIF);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.scaleToWindow(this);

        g.clear(this);

        g.setAntialiasing(false);
        g.setTextAntialiasing(true);
        g.setQuallityRender(true);
        
        gsm.draw(g, interpolation);

        g.drawBorders(this);
        
        this.frames++;

        g.show();
    }


}
