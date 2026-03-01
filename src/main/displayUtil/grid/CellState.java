package displayUtil.grid;

import java.awt.Color;

public enum CellState {
    EMPTY(new Color(0, 29, 74)),
    HIT(new Color(223, 41, 53)),
    SUNK(new Color(234, 99, 140)),
    MISS(new Color(170, 174, 142));

    public final Color color;

    CellState(Color color) {
        this.color = color;
    }

    public boolean isOccupied() {
        return this != EMPTY;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isSunk() {
        return this == SUNK;
    }

    public CellState next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
