package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface DisplayComponent {
    public void update();
    public void draw(Graphics g);
    public void handleMousePress(MouseEvent e);
    public void handleMouseRelease(MouseEvent e);
}
