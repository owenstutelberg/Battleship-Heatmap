package config;

import java.awt.Color;

public class Constants {
    public static final Color WARM_COLOR = Color.RED;
    public static final Color COLD_COLOR = Color.BLUE;

    public static final double HIT_DETERMINANT_FACTOR = 25;
    public static final double HORIZONTAL_DETERMINANT_FACTOR = 1;

    public static final Color ACTIVE_SHIP_COLOR = new Color(0, 191, 160);
    public static final Color DEACTIVE_SHIP_COLOR = new Color(253, 89, 111);

    public static final double BORDER_RATIO = 0.2;

    public static class Screen {
        public static final int WIDTH = 1035;
        public static final int HEIGHT = 595;
        public static final Color BACKGROUND_COLOR = new Color(80, 80, 80);
    }
}
