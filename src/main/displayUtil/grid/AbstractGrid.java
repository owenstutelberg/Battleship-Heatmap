package displayUtil.grid;

import java.awt.Color;
import java.awt.Graphics;

import math.Vec2d;
import ui.DisplayComponent;

public abstract class AbstractGrid implements DisplayComponent {
    protected final Vec2d pos;
    protected final int cols;
    protected final int rows;
    protected final Vec2d dimensions;
    protected final Vec2d cellSize;

    protected AbstractGrid(Vec2d pos, int cols, int rows, Vec2d dimensions) {
        this.pos = pos;
        this.cols = cols;
        this.rows = rows;
        this.dimensions = dimensions;
        this.cellSize = new Vec2d(dimensions.x / cols, dimensions.y / rows);
    }

    protected void drawGridLines(Graphics g) {
        g.setColor(new Color(200, 200, 200));

        for (int x = 0; x <= cols; x++) {
            g.drawLine(
                    (int) (pos.x + x * cellSize.x),
                    (int) (pos.y),
                    (int) (pos.x + x * cellSize.x),
                    (int) (pos.y + dimensions.y));
        }

        for (int y = 0; y <= cols; y++) {
            g.drawLine(
                    (int) pos.x,
                    (int) (pos.y + y * cellSize.y),
                    (int) (pos.x + dimensions.x),
                    (int) (pos.y + y * cellSize.y));
        }
    }
}
