package main;

import Input.KeyInput;
import Input.MouseInput;
import Input.MouseWheelMoved;
import gamestates.GameStateManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Jakub Vitásek & Matěj Stuchlík
 */
public class GamePanel extends Canvas implements Runnable {
    
    public Dimension size = new Dimension(800, 600);
    
    public GameStateManager gsm;
    private Thread thread;
    public static boolean paused = false;
    private double interpolation = 0;
    private Frame f;
    
    public GamePanel (){    
        init();
        start();
    }
    
    private void init(){
        f = new Frame(size.width, size.height, "Cubic Madness", this);
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFocusable(true);
        
        this.addMouseWheelListener(new MouseWheelMoved());
        this.addKeyListener(new KeyInput());
        this.addMouseListener(new MouseInput());
        this.addMouseMotionListener(new MouseInput());
    }
    
    private void start(){
        this.gsm = new GameStateManager(this);
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
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.size.width, this.size.height);
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        //g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        
        gsm.draw(g, interpolation);
        
        this.frames++;
        
        g.dispose();
        bs.show();
    }
    
}
