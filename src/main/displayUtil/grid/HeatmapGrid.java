package displayUtil.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Set;

import config.Constants;
import math.MathUtils;
import math.Vec2d;
import model.Fleet;
import model.Ship;

public class HeatmapGrid extends AbstractGrid {
    private final double[][] values;
    private final PlacementGrid placementGrid;
    private final Fleet fleet;

    private static final Point[] DIRECTIONS = {
            new Point(1, 0), new Point(-1, 0),
            new Point(0, 1), new Point(0, -1)
    };

    public HeatmapGrid(Vec2d pos, int cols, int rows, Vec2d dim, PlacementGrid p, Fleet f) {
        super(pos, cols, rows, dim);
        placementGrid = p;
        fleet = f;
        values = new double[cols][rows];
    }

    @Override
    public void update() {
        clear();

        for (Ship s : fleet.getAliveShips()) {
            addShip(s);
        }

        addHitBias();
        addDistanceBias();
        resetOccupiedCells();
    }

    private void clear() {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                values[x][y] = 0;
            }
        }
    }

    private void addShip(Ship ship) {
        int length = ship.getLength();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x <= cols - length; x++) {
                place(x, y, 1, 0, length);
            }
        }

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y <= rows - length; y++) {
                place(x, y, 0, 1, length);
            }
        }
    }

    private void place(int xShip, int yShip, int dx, int dy, int length) {
        for (int i = 0; i < length; i++) {
            if (placementGrid.getOccupied().contains(new Point(xShip + i * dx, yShip + i * dy)))
                return;
        }

        for (int i = 0; i < length; i++) {
            values[xShip + i * dx][yShip + i * dy]++;
        }
    }

    private void addHitBias() {
        Set<Point> hits = placementGrid.getHits();
        for (Point hit : hits) {
            for (Point directionPoint : DIRECTIONS) {
                int xPoint = hit.x + directionPoint.x;
                int yPoint = hit.y + directionPoint.y;

                if (xPoint >= 0 && yPoint >= 0 && xPoint < cols && yPoint < rows)
                    values[xPoint][yPoint] += Constants.HIT_DETERMINANT_FACTOR;
            }
        }

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                if (isAdjacentToDoubleHit(hits, x, y)) {
                    values[x][y] += Constants.HIT_DETERMINANT_FACTOR;
                }
            }
        }
    }

    private boolean isAdjacentToDoubleHit(Set<Point> hits, int x, int y) {
        return (hits.contains(new Point(x - 1, y)) && hits.contains(new Point(x - 2, y))) ||
                (hits.contains(new Point(x + 1, y)) && hits.contains(new Point(x + 2, y))) ||
                (hits.contains(new Point(x, y - 1)) && hits.contains(new Point(x, y - 2))) ||
                (hits.contains(new Point(x, y + 1)) && hits.contains(new Point(x, y + 2)));
    }

    private void addDistanceBias() {
        Vec2d averageSunkPosition = placementGrid.getAverageSunkPosition();
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                values[x][y] += MathUtils.getDistance(new Vec2d(x, y), averageSunkPosition) / 28;
            }
        }
    }

    private void resetOccupiedCells() {
        for (Point p : placementGrid.getOccupied()) {
            values[p.x][p.y] = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        double min = MathUtils.getMin(values);
        double max = MathUtils.getMax(values);

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                double ratio = (values[x][y] - min) / (max - min + 0.0001);

                Color color = MathUtils.interpolate(ratio, Constants.WARM_COLOR, Constants.COLD_COLOR);
                g.setColor(color);

                g.fillRect(
                        (int) (pos.x + x * cellSize.x),
                        (int) (pos.y + y * cellSize.y),
                        (int) cellSize.x,
                        (int) cellSize.y);

                if (values[x][y] == max) {
                    g.setColor(Color.GREEN);

                    int cellX = (int) (pos.x + x * cellSize.x);
                    int cellY = (int) (pos.y + y * cellSize.y);
                    int innerSize = (int) (cellSize.x * (1 - Constants.BORDER_RATIO));
                    int innerX = cellX + (int) ((cellSize.x - innerSize) / 2);
                    int innerY = cellY + (int) ((cellSize.y - innerSize) / 2);

                    g.fillRect(
                            cellX,
                            cellY,
                            (int) cellSize.x,
                            (int) cellSize.y);
                    g.setColor(Constants.WARM_COLOR);
                    g.fillRoundRect(
                        innerX + 1, 
                        innerY + 1, 
                        innerSize - 2, 
                        innerSize - 2, 
                        10, 
                        10);
                }
            }

            drawGridLines(g);
        }
    }

    @Override
    public void handleMousePress(MouseEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
    }
}
