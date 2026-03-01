package displayUtil.grid;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import math.Vec2d;

public class PlacementGrid extends AbstractGrid {
    private final CellState[][] cells;
    private final Set<Point> occupied = new HashSet<>();
    private final Set<Point> hits = new HashSet<>();
    private final Set<Point> sunks = new HashSet<>();

    public PlacementGrid(Vec2d pos, int cols, int rows, Vec2d dims) {
        super(pos, cols, rows, dims);

        cells = new CellState[cols][rows];
        clear();
    }

    public void clear() {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = CellState.EMPTY;
            }
        }

        occupied.clear();
        hits.clear();
        sunks.clear();
    }

    public Set<Point> getOccupied() {
        return occupied;
    }

    public Set<Point> getHits() {
        return hits;
    }

    public Vec2d getAverageSunkPosition() {
        Vec2d sum = new Vec2d();
        for (Point p : sunks) {
            sum.x += p.x;
            sum.y += p.y;
        }

        double x = sunks.size() == 0 ? 4.5 : sum.x / sunks.size();
        double y = sunks.size() == 0 ? 4.5 : sum.y / sunks.size();

        return new Vec2d(x, y);
    }

    @Override
    public void draw(Graphics g) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                g.setColor(cells[x][y].color);
                g.fillRect((int) (pos.x + x * cellSize.x),
                        (int) (pos.y + y * cellSize.y),
                        (int) cellSize.x, (int) cellSize.y);
            }
        }
        drawGridLines(g);
    }

    @Override
    public void update() {}

    @Override
    public void handleMousePress(MouseEvent e) {}

    @Override
    public void handleMouseRelease(MouseEvent e) {
        Point cellPos = new Point(
                (int) Math.floor((e.getX() - pos.x) / cellSize.x),
                (int) Math.floor((e.getY() - pos.y) / cellSize.y));

        if (cellPos.x < 0 || cellPos.y < 0 || cellPos.x >= cols || cellPos.y >= rows) return;

        CellState next = cells[cellPos.x][cellPos.y].next();
        cells[cellPos.x][cellPos.y] = next;

        if (next.isOccupied()) occupied.add(cellPos); else occupied.remove(cellPos);
        if (next.isHit()) hits.add(cellPos); else hits.remove(cellPos);
        if (next.isSunk()) sunks.add(cellPos); else sunks.remove(cellPos);
    }
}
