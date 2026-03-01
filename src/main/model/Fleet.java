package model;

import java.util.EnumSet;
import java.util.Set;

public class Fleet {
    private final Set<Ship> alive = EnumSet.allOf(Ship.class);

    public Set<Ship> getAliveShips() {
        return alive;
    }

    public void sink(Ship ship) {
        alive.remove(ship);
    }

    public void toggle(Ship ship) {
        if (alive.contains(ship)) {
            alive.remove(ship);
        } else {
            alive.add(ship);
        }
    }

    public void reset() {
        for (Ship ship : Ship.values()) {
            if (!alive.contains(ship)) {
                alive.add(ship);
            }
        }
    }

    
}
