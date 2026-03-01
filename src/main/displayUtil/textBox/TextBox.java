package displayUtil.textBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import math.Vec2d;
import ui.DisplayComponent;

public class TextBox implements DisplayComponent {
    private Vec2d pos;
    private String text;

    public TextBox(Vec2d pos, String text) {
        this.pos = pos;
        this.text = text;
    }


    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
        g.setColor(new Color(50, 50, 50));
        g.drawString(text, (int) pos.x, (int) pos.y);
    }

    @Override
    public void handleMousePress(MouseEvent e) {
        
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        
    }

}
