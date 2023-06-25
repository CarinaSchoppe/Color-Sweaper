package frontend;

import game.Player;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPanel extends JPanel {
    private JPanel[] colorPanels;
    private JLabel[] colorLabels;
    private int colorCount;

    public ColorPanel(int colorCount) {
        this.colorCount = colorCount;
        primarySetup();
        refreshColors();
        generateColorPanel();

    }


    private void primarySetup() {
        colorPanels = new JPanel[colorCount];
        colorLabels = new JLabel[colorCount];
        setLayout(new GridLayout(1, colorCount));
    }

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


    public void generateColorPanel() {
        for (int i = 0; i < colorCount; i++) {
            var panel = new JPanel();
            panel.setBackground(Utility.getSelectedColors()[i]);
            colorPanels[i] = panel;
            add(panel);

            var label = new JLabel(String.valueOf(i + 1));
            colorLabels[i] = label;
            add(label);

            colorPanels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panel.setBackground(Utility.generateRandomColor());
                }
            });
        }
    }

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

    public JPanel[] getColorPanels() {
        return colorPanels;
    }

    public JLabel[] getColorLabels() {
        return colorLabels;
    }

    public int getColorCount() {
        return colorCount;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }
}
