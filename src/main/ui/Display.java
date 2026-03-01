package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import displayUtil.Bounds;
import displayUtil.Corner;
import math.Vec2d;

public class Display {
    private Vec2d pos;
    private Vec2d dimensions;

    private double radius;
    private double borderLength = 5;

    private Bounds exteriorBounds;
    private Bounds interiorBounds;

    private List<DisplayComponent> components;

    private Color exteriorColor = Color.WHITE;
    private Color interiorColor = new Color(50, 50, 50);

    public Display(Vec2d pos, Vec2d dimensions, double radius) {
        this.pos = pos;
        this.dimensions = dimensions;
        this.radius = radius;

        this.exteriorBounds = new Bounds(pos, new Vec2d(pos.x + dimensions.x, pos.y + dimensions.y));
        this.interiorBounds = new Bounds(
                new Vec2d(exteriorBounds.getMin().x + borderLength, exteriorBounds.getMin().y + borderLength),
                new Vec2d(exteriorBounds.getMax().x - borderLength, exteriorBounds.getMax().y - borderLength));

        this.components = new ArrayList<>();
    }

    public Vec2d getCorner(Bounds bounds, Corner corner, Vec2d offset) {
        double x = (corner == Corner.TOP_RIGHT || corner == Corner.BOTTOM_RIGHT)
                ? bounds.getMax().x
                : bounds.getMin().x;

        double y = (corner == Corner.BOTTOM_LEFT || corner == Corner.BOTTOM_RIGHT)
                ? bounds.getMax().y
                : bounds.getMin().y;

        return new Vec2d(x + offset.x, y + offset.y);
    }

    public Vec2d getInteriorCorner(Corner c, Vec2d offset) {
        return getCorner(interiorBounds, c, offset);
    }

    public Vec2d getExteriorCorner(Corner c, Vec2d offset) {
        return getCorner(exteriorBounds, c, offset);
    }

    public Vec2d getDimensions() {
        return dimensions;
    }

    public double getRadius() {
        return radius;
    }

    public double getBorderLength() {
        return borderLength;
    }

    public Bounds getInteriorBounds() {
        return interiorBounds;
    }

    public Bounds getExteriorBounds() {
        return exteriorBounds;
    }

    public void add(DisplayComponent... items) {
        for (DisplayComponent c : items) {
            components.add(c);
        }
    }

    public void setInteriorColor(Color c) {
        interiorColor = c;
    }

    public void setExteriorColor(Color c) {
        exteriorColor = c;
    }

    public void update() {
        for (DisplayComponent c : components) {
            c.update();
        }
    }

    public void draw(Graphics g) {
        drawExterior(g, exteriorColor);
        drawInterior(g, interiorColor);
        drawComponents(g);
    }

    public void drawExterior(Graphics g, Color c) {
        g.setColor(c);
        g.fillRoundRect(
                (int) exteriorBounds.getMin().x,
                (int) exteriorBounds.getMin().y,
                (int) dimensions.x,
                (int) dimensions.y,
                (int) radius,
                (int) radius);
    }

    public void drawInterior(Graphics g, Color c) {
        g.setColor(c);
        g.fillRoundRect(
                (int) interiorBounds.getMin().x,
                (int) interiorBounds.getMin().y,
                (int) (dimensions.x - 2 * borderLength),
                (int) (dimensions.y - 2 * borderLength),
                (int) (radius - borderLength),
                (int) (radius - borderLength));
    }

    public void drawComponents(Graphics g) {
        if (!components.isEmpty()) {
            for (DisplayComponent c : components) {
                c.draw(g);
            }
        }
    }

    public void handleMousePress(MouseEvent e) {
        for (DisplayComponent c : components) {
            c.handleMousePress(e);
        }
    }

    public void handleMouseRelease(MouseEvent e) {
        for (DisplayComponent c : components) {
            c.handleMouseRelease(e);
        }
    }
}
