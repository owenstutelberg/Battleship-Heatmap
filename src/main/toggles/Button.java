package toggles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import displayUtil.Bounds;
import math.Vec2d;
import ui.DisplayComponent;

public class Button implements DisplayComponent {
    private Vec2d pos;
    private Vec2d dims;
    private double radius;
    private Bounds bounds;
    private Color color = Color.WHITE;

    private Runnable[] onClick;

    public Button(Vec2d pos, Vec2d dims, double radius, Color color, Runnable... onClick) {
        this.pos = pos;
        this.dims = dims;
        this.radius = radius;
        this.color = color;
        this.onClick = onClick;

        this.bounds = new Bounds(pos, new Vec2d(pos.x + dims.x, pos.y + dims.y));
    }

    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRoundRect((int) pos.x, (int) pos.y, (int) dims.x, (int) dims.y, (int) radius, (int) radius);
    }

    @Override
    public void handleMousePress(MouseEvent e) {
        if (!bounds.contains(e.getPoint()))
            return;

        dims.x *= 0.95;
        dims.y *= 0.95;

        pos.x += dims.x * 0.025;
        pos.y += dims.y * 0.025;

    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        if (!bounds.contains(e.getPoint()))
            return;

        pos.x -= dims.x * 0.025;
        pos.y -= dims.y * 0.025;

        dims.x /= 0.95;
        dims.y /= 0.95;

        for (Runnable task : onClick) {
            if (task != null)
                task.run();
        }
    }
}
