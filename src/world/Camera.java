package world;

import main.GameCanvas;
import world.objects.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Martin on 23.2.2016.
 */
public class Camera {

    public int x, y;
    private int width, heigth;

    private GameObject target;

    public Camera () {
        width = GameCanvas.REF_WIDTH;
        heigth = GameCanvas.REF_HEIGHT;
        x = 0;
        y = 0;
        target = null;
    }

    public void setTarget(GameObject object){
        target = object;
    }

    public GameObject getTarget(){
        return target;
    }

    public Point2D.Float screenToWorld(float x, float y){
        return new Point2D.Float(x + this.x, y + this.y);
    }

    public Point2D.Float worldToScreen(float x, float y){
        return new Point2D.Float(x - this.x, y - this.y);
    }

    public void tick(){}

    public void draw(Graphics2D g, double interpolation){
        if (target != null) {
            Rectangle rect = target.getRect(interpolation);
            this.x = rect.x;
            this.y = rect.y;
        }
    }
}
