package math;

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
}
