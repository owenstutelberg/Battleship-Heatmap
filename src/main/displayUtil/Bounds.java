package displayUtil;

import java.awt.Point;
import java.awt.Dimension;

import math.Vec2d;

public class Bounds {
    private Vec2d min;
    private Vec2d max;

    private Dimension dimensions;

    public Bounds(Vec2d min, Vec2d max) {
        this.min = min;
        this.max = max;

        this.dimensions = new Dimension(
            (int) (max.x - min.x), 
            (int) (max.y - min.y)
        );
    }

    public boolean contains(Vec2d p) {
        return (p.x > min.x && p.x < max.x && p.y > min.y && p.y < max.y);
    }

    public boolean contains(Point p) {
        return (p.x > min.x && p.x < max.x && p.y > min.y && p.y < max.y);
    }

    public Vec2d getMin() {
        return min;
    }

    public Vec2d getMax() {
        return max;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public double getWidth() {
        return dimensions.getWidth();
    }

    public double getHeight() {
        return dimensions.getHeight();
    }

    public Bounds scaleSameCenter(double scalar) {
        return new Bounds(
            new Vec2d(
                min.x + getWidth() * (1 - scalar), 
                min.y + getHeight() * (1 - scalar)
            ), 
            new Vec2d(
                max.x - getWidth() * (1 - scalar), 
                max.y - getHeight() * (1 - scalar)
            )
        );
    }
}
