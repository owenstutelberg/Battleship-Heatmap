package math;

import java.awt.Color;

public class MathUtils {
    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double getMin(double[][] array) {
        double min = Integer.MAX_VALUE;

        for (double[] row : array) {
            for (double value : row) {
                min = Math.min(min, value);
            }
        }

        return min;
    }

    public static double getMax(double[][] array) {
        double max = Integer.MIN_VALUE;

        for (double[] row : array) {
            for (double value : row) {
                max = Math.max(max, value);
            }
        }

        return max;
    }

    public static Color interpolate(double ratio, Color warm, Color cold) {
        int r = (int) (ratio * (warm.getRed() - cold.getRed()) + cold.getRed());
        int g = (int) (ratio * (warm.getGreen() - cold.getGreen()) + cold.getGreen());
        int b = (int) (ratio * (warm.getBlue() - cold.getBlue()) + cold.getBlue());

        return new Color(r, g, b);
    }

    public static double getDistance(Vec2d pointOne, Vec2d pointTwo) {
        return Math.pow(pointOne.x - pointTwo.x, 2) + Math.pow(pointOne.y - pointTwo.y, 2);
    }
}
