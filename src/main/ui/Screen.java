package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import config.Constants;
import displayUtil.Corner;
import displayUtil.grid.HeatmapGrid;
import displayUtil.grid.PlacementGrid;
import displayUtil.textBox.TextBox;
import math.Vec2d;
import model.Fleet;
import model.Ship;
import toggles.Button;

public class Screen {
    private Fleet fleet;

    private Display placementDisplay;
    private Display heatmapDisplay;
    private Display buttonDisplay;

    private PlacementGrid placementGrid;
    private HeatmapGrid heatmap;

    private Button clearButton;
    private Button destroyerButton;
    private Button submarineButton;
    private Button cruiserButton;
    private Button battleshipButton;
    private Button carrierButton;

    private Button[] shipButtons;

    private TextBox clearText;
    private TextBox destroyerText;
    private TextBox submarineText;
    private TextBox cruiserText;
    private TextBox battleshipText;
    private TextBox carrierText;

    public Screen() {
        fleet = new Fleet();
        
        placementDisplay = new Display(
                new Vec2d(5, 5),
                new Vec2d(510, 510),
                10);

        placementGrid = new PlacementGrid(
                new Vec2d(placementDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d())),
                10,
                10,
                new Vec2d(500, 500));

        placementDisplay.add(placementGrid);

        heatmapDisplay = new Display(
                new Vec2d(520, 5),
                new Vec2d(510, 510),
                10);

        heatmap = new HeatmapGrid(
                new Vec2d(heatmapDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d())),
                10,
                10,
                new Vec2d(500, 500),
                placementGrid,
                fleet);

        heatmapDisplay.add(heatmap);

        heatmap.update();

        buttonDisplay = new Display(
                new Vec2d(5, 520),
                new Vec2d(1025, 70),
                20);

        clearButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(5, 5)),
                new Vec2d(100, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Color.WHITE,
                () -> {
                    placementGrid.clear();
                    fleet.reset();

                    for (Button button : shipButtons) {
                        button.setColor(Constants.ACTIVE_SHIP_COLOR);
                    }
                    
                    heatmap.update();
                });

        destroyerButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(110, 5)),
                new Vec2d(104, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Constants.ACTIVE_SHIP_COLOR,
                () -> {
                    fleet.toggle(Ship.DESTROYER);
                    heatmap.update();

                    destroyerButton.setColor(
                            fleet.getAliveShips().contains(Ship.DESTROYER)
                                    ? Constants.ACTIVE_SHIP_COLOR
                                    : Constants.DEACTIVE_SHIP_COLOR);
                });

        submarineButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(219, 5)),
                new Vec2d(155, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Constants.ACTIVE_SHIP_COLOR,
                () -> {
                    fleet.toggle(Ship.SUBMARINE);
                    heatmap.update();

                    submarineButton.setColor(fleet.getAliveShips().contains(Ship.SUBMARINE)
                            ? Constants.ACTIVE_SHIP_COLOR
                            : Constants.DEACTIVE_SHIP_COLOR);
                });

        cruiserButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(379, 5)),
                new Vec2d(155, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Constants.ACTIVE_SHIP_COLOR,
                () -> {
                    fleet.toggle(Ship.CRUISER);
                    heatmap.update();

                    cruiserButton.setColor(fleet.getAliveShips().contains(Ship.CRUISER)
                            ? Constants.ACTIVE_SHIP_COLOR
                            : Constants.DEACTIVE_SHIP_COLOR);
                });

        battleshipButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(539, 5)),
                new Vec2d(207, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Constants.ACTIVE_SHIP_COLOR,
                () -> {
                    fleet.toggle(Ship.BATTLESHIP);
                    heatmap.update();

                    battleshipButton.setColor(fleet.getAliveShips().contains(Ship.BATTLESHIP)
                            ? Constants.ACTIVE_SHIP_COLOR
                            : Constants.DEACTIVE_SHIP_COLOR);
                });

        carrierButton = new Button(
                buttonDisplay.getInteriorCorner(Corner.TOP_LEFT, new Vec2d(751, 5)),
                new Vec2d(259, 50),
                buttonDisplay.getRadius() - 2 * buttonDisplay.getBorderLength(),
                Constants.ACTIVE_SHIP_COLOR,
                () -> {
                    fleet.toggle(Ship.CARRIER);
                    heatmap.update();

                    carrierButton.setColor(fleet.getAliveShips().contains(Ship.CARRIER)
                            ? Constants.ACTIVE_SHIP_COLOR
                            : Constants.DEACTIVE_SHIP_COLOR);
                });

        shipButtons = new Button[] { destroyerButton, submarineButton, cruiserButton, battleshipButton, carrierButton };

        clearText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(31.5, -22.5))),
                "Clear");

        destroyerText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(118, -22.5))),
                "Destroyer");

        submarineText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(247, -22.5))),
                "Submarine");

        cruiserText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(423.5, -22.5))),
                "Cruiser");

        battleshipText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(596, -22.5))),
                "Battleship");

        carrierText = new TextBox(
                new Vec2d(buttonDisplay.getInteriorCorner(Corner.BOTTOM_LEFT, new Vec2d(849.5, -22.5))),
                "Carrier");

        buttonDisplay.add(
                // Buttons
                clearButton,
                destroyerButton,
                submarineButton,
                cruiserButton,
                battleshipButton,
                carrierButton,
                // Texts
                clearText,
                destroyerText,
                submarineText,
                cruiserText,
                battleshipText,
                carrierText);
    }

    public void update() {
        placementDisplay.update();
    }

    public void draw(Graphics g) {
        placementDisplay.draw(g);
        heatmapDisplay.draw(g);
        buttonDisplay.draw(g);
    }

    public void handleMousePress(MouseEvent e) {
        buttonDisplay.handleMousePress(e);
    }

    public void handleMouseRelease(MouseEvent e) {
        placementGrid.handleMouseRelease(e);
        heatmap.update();

        buttonDisplay.handleMouseRelease(e);
    }
}
