package frontend;

import game.Player;
import utility.Utility;

import javax.swing.*;
import java.awt.*;


public class ColorPanel extends JPanel {
    /**
     * An array of JPanels representing various color options.
     */
    private JPanel[] colorPanels;
    /**
     * An array of JLabel components used to represent colors.
     * Each label is used to display a different color.
     */
    private JLabel[] colorLabels;
    /**
     * The colorCount variable stores an integer value representing the number of colors in a given context.
     * <p>
     * This variable is only accessible within the context of its class and is used to keep track of
     * the number of colors present in some fashion.
     */
    private int colorCount;

    /**
     * Constructs*/
    public ColorPanel(int colorCount) {
        this.colorCount = colorCount;
        primarySetup();
        refreshColors();
        generateColorPanel();

    }


    /**
     * Initializes the colorPanels and colorLabels arrays with the given colorCount. 
     * Sets the layout of the container to have one row and colorCount columns.
     */
    private void primarySetup() {
        colorPanels = new JPanel[colorCount];
        colorLabels = new JLabel[colorCount];
        setLayout(new GridLayout(1, colorCount));
    }

    /**
     * Update the color of the given CellPanel and all the cells related to the given Player.
     *
     * @param panel  CellPanel to be updated.
     * @param player Player object whose cells should be updated.
     */
    public void updateCellPanelColor(CellPanel panel, Player player) {
        var color = panel.getBackground();
        panel.setOpaque(true);
        player.setColor(color);
        for (var cell : player.getComponent().getCells()) {
            cell.setColor(color);
            cell.setBackground(color);
            cell.setOpaque(true);
            cell.revalidate();
            cell.repaint();
        }
    }


    /**
     * Removes all existing components from current panel and generates a new panel of colored panels with labels.
     * Each colored panel is assigned a number label.
     */
    public void generateColorPanel() {
        removeAll();
        for (int i = 0; i < colorCount; i++) {
            var panel = new JPanel();
            panel.setBackground(Utility.getSelectedColors()[i]);
            colorPanels[i] = panel;
            add(panel);
            var label = new JLabel(String.valueOf(i + 1));
            colorLabels[i] = label;
            add(label);
        }
    }

    /**
     * Refreshes the colors displayed in the color panel.
     */
    public void refreshColors() {
        primarySetup();
        removeAll();
        for (var i = 0; i < colorCount; i++) {
            var panel = new JPanel();
            panel.setBackground(Utility.getSelectedColors()[i]);
            colorPanels[i] = panel;
            add(panel);
        }
        revalidate();
        repaint();
        generateColorPanel();
    }

    /**
     * Returns an array of JPanel objects, which represents color panels.
     *
     * @return an array of JPanel objects
     */
    public JPanel[] getColorPanels() {
        return colorPanels;
    }

    /**
     * Returns an array of JLabel objects representing color labels.
     *
     * @return an array of JLabel objects representing color labels
     */
    public JLabel[] getColorLabels() {
        return colorLabels;
    }

    /**
     * Returns the number of colors.
     *
     * @return the number of colors as an integer.
     */
    public int getColorCount() {
        return colorCount;
    }

    /**
     * Sets the number of colors in the color palette of the object.
     *
     * @param colorCount The number of colors to be set in the color palette of the object.
     */
    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }
}
