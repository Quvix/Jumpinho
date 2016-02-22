package input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelMoved implements MouseWheelListener{
    
    public static int count = 0;

    public void mouseWheelMoved(MouseWheelEvent e) {
        count = e.getWheelRotation();
        //System.out.println(count);
    }
    
}
