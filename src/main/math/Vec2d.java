package math;

import java.awt.Dimension;
import java.awt.Point;

public class Vec2d {
    public double x;
    public double y;

    public Vec2d() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d(Vec2d v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2d(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public Vec2d(Dimension d) {
        this.x = d.getWidth();
        this.y = d.getHeight();
    }

    public Vec2d add(Vec2d v) {
        return new Vec2d(this.x + v.x, this.y + v.y);
    }

    public Vec2d scale(double scalar) {
        return new Vec2d(this.x * scalar, this.y * scalar);
    }
}
