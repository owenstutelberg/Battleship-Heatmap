package displayUtil;

import java.awt.Point;

import math.Vec2d;

public class Bounds {
    private Vec2d min;
    private Vec2d max;

    public Bounds(Vec2d min, Vec2d max) {
        this.min = min;
        this.max = max;
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
}
