package toggles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import displayUtil.Bounds;
import math.Vec2d;
import ui.DisplayComponent;

public class Button implements DisplayComponent {
    // private Vec2d dims;
    // private Vec2d pos;
    // private Vec2d pressedDims;
    // private Vec2d pressedPos;
    // private double radius;
    // private Bounds bounds;
    // private Color color = Color.WHITE;
    // private boolean isPressed = false;

    // private int presses = 0;
    // private int releases = 0;

    private Bounds bounds;

    private Vec2d pos;

    private Vec2d pressedPos;

    private Dimension dims;

    private Dimension pressedDims;

    private double radius;

    private Color color;

    private Runnable[] onClick;

    private boolean isPressed = false;

    public Button(Bounds bounds, double radius, Color color, Runnable... onClick) {
        this.bounds = bounds;

        this.pos = bounds.getMin();

        this.pressedPos = bounds.scaleSameCenter(0.95).getMin();

        this.dims = bounds.getDimensions();

        this.pressedDims = bounds.scaleSameCenter(0.95).getDimensions();

        this.radius = radius;

        this.color = color;

        this.onClick = onClick;
    }

    public Button(Vec2d pos, Dimension dims, double radius, Color color, Runnable... onClick) {
        this.bounds = new Bounds(pos, pos.add(new Vec2d(dims)));

        this.pos = pos;

        this.pressedPos = bounds.scaleSameCenter(0.95).getMin();

        this.dims = dims;

        this.pressedDims = bounds.scaleSameCenter(0.95).getDimensions();

        this.radius = radius;

        this.color = color;

        this.onClick = onClick;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        if (isPressed) {
            g.fillRoundRect(
                (int) pressedPos.x,
                (int) pressedPos.y,
                (int) pressedDims.getWidth(),
                (int) pressedDims.getHeight(),
                (int) radius,
                (int) radius
            );
        } else {
            g.fillRoundRect(
                (int) pos.x, 
                (int) pos.y, 
                (int) dims.getWidth(), 
                (int) dims.getHeight(), 
                (int) radius, 
                (int) radius
            );
        }
    }

    @Override
    public void handleMousePress(MouseEvent e) {
        if (!bounds.contains(e.getPoint())) return;

        isPressed = true;
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        isPressed = false;

        if (!bounds.contains(e.getPoint())) return;

        for (Runnable task : onClick) {
            if (task != null)
                task.run();
        }
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        // isPressed = false;

        // if (!bounds.contains(e.getPoint())) return;

        // for (Runnable task : onClick) {
        //     if (task != null) task.run();
        // }
    }
}
