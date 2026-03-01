package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import config.Constants;
import ui.Screen;

public class Renderer extends JPanel {
    private final Screen screen;

    public Renderer(Screen screen) {
        this.screen = screen;

        setPreferredSize(new Dimension(Constants.Screen.WIDTH, Constants.Screen.HEIGHT));
        setBackground(Constants.Screen.BACKGROUND_COLOR);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                screen.handleMouseRelease(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                screen.handleMousePress(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        screen.draw(g);
    }

}
