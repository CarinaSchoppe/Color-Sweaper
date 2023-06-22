package game;

import frontend.DisplayPanel;

import java.awt.*;

public interface Strategy {
    Color chooseColor(Player player, DisplayPanel displayPanel);
}
