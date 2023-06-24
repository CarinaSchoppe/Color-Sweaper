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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                var color = panel.getBackground();
                panel.setOpaque(true);
                player.setColor(color);
                System.out.println("neu: " + color);
                for (var cell : player.getComponent().getCells()) {
                    cell.setColor(color);
                    cell.setBackground(color);
                    cell.setOpaque(true);
                    System.out.println("Cell color: " + cell.getBackground() + " position: " + cell.getRow() + " " + cell.getColumn());
                    cell.revalidate();
                    cell.repaint();
                }

                System.out.println(player.getComponent().getCells().get(0).getColor() + " " + player.getComponent().getCells().get(0).getBackground() + " " + player.getComponent().getCells().get(0).getRow() + " " + player.getComponent().getCells().get(0).getColumn());

            }
        });
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
