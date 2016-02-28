package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * Created by Martin on 28.2.2016.
 *
 * Class for rendering (mainly wrapper for Graphics2D)
 */
public class Drawer {

    enum FontE {
        SANS_SERIF,
        MONOSPACED
    }

    BufferStrategy bs;
    Graphics2D g;

    HashMap<FontE, java.awt.Font> defaultFonts = new HashMap<>();

    public Drawer(){
        bs = null;
        g = null;

        defaultFonts.put(FontE.SANS_SERIF, new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        defaultFonts.put(FontE.MONOSPACED, new Font(Font.MONOSPACED, Font.PLAIN, 16));
    }

    public Graphics2D getGraphics(){
        return g;
    }

    public boolean createGraphics(GameCanvas canvas){
        bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            return false;
        }

        g = (Graphics2D) bs.getDrawGraphics();
        return true;
    }

    public void setFont(Drawer.FontE font){
        g.setFont(defaultFonts.get(font));
    }

    public void setFontSize(float size){
        g.setFont(g.getFont().deriveFont(size));
    }

    public void setFontStyle(int style){
        g.setFont(g.getFont().deriveFont(style));
    }

    public void setFontSizeAndStyle(int style, float size){
        g.setFont(g.getFont().deriveFont(style, size));
    }

    public void setNewFont(Font font){
        g.setFont(font);
    }

    public Font getFont(){
        return g.getFont();
    }

    public void setColor(Color color){
        g.setColor(color);
    }

    public void fillRect(int x, int y, int w, int h){
        g.fillRect(x,y,w,h);
    }

    public void fillRect(float x, float y, float w, float h){
        g.fillRect(Math.round(x), Math.round(y),Math.round(w),Math.round(h));
    }

    public void fill(Shape shape){
        g.fill(shape);
    }

    public void setTransform(AffineTransform transform){
        g.setTransform(transform);
    }
    
    public void scaleToWindow(GameCanvas canvas){
        AffineTransform at = new AffineTransform();
        double scale = Math.min(canvas.getWidth() / canvas.size.getWidth(), canvas.getHeight() / canvas.size.getHeight());
        double xOff = (canvas.getWidth() - canvas.size.width * scale) / 2f;
        if(canvas.getWidth() / canvas.size.getWidth() < canvas.getHeight() / canvas.size.getHeight()){
            xOff = 0;
        }
        at.translate(xOff, 0);
        at.scale(scale, scale);
        g.setTransform(at);
    }

    public void setRenderingHint(RenderingHints.Key key, Object o){
        g.setRenderingHint(key, o);
    }

    public void setAntialiasing(boolean b){
        if(b)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    public void setTextAntialiasing(boolean b){
        if (b)
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        else
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

    public void setQuallityRender(boolean b){
        if(b)
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        else
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    }

    public void setAlpha(float alpha){
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public void clear(GameCanvas canvas){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvas.size.width, canvas.size.height);
        g.setColor(Color.BLACK);
    }

    public void drawBorders(GameCanvas canvas){
        this.setAlpha(1);
        g.setColor(Color.BLACK);
        g.fillRect(-500, 0, 500, canvas.size.height);
        g.fillRect(canvas.size.width, 0, 500, canvas.size.height);
        g.fillRect(-500, -1000, canvas.size.width + 500, 0);
        g.fillRect(-500, canvas.size.height, canvas.size.width + 500, 1500);
    }

    public void show(){
        g.dispose();
        bs.show();
    }
}
