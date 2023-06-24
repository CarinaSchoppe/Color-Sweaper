package utility;

import frontend.ColorPanel;
import frontend.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Utility {
    public static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.CYAN};
    private static ColorPanel colorPanel;
    private static Color[] selectedColors;
    private static Timer timer;
    private static int timerSeconds;
    private static DisplayPanel displayPanel;

    public static java.awt.Color generateRandomColor() {
        return selectedColors[random.nextInt(selectedColors.length)];
    }

    public static ColorPanel getColorPanel() {
        return colorPanel;
    }

    public static void setColorPanel(ColorPanel colorPanel) {
        Utility.colorPanel = colorPanel;
    }

    public static Color[] getSelectedColors() {
        return selectedColors;
    }

    public static void setSelectedColors(Color[] selectedColors) {
        Utility.selectedColors = selectedColors;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static void setTimer(Timer timer) {
        Utility.timer = timer;
    }

    public static int getTimerSeconds() {
        return timerSeconds;
    }

    public static void setTimerSeconds(int timerSeconds) {
        Utility.timerSeconds = timerSeconds;
    }

    public static DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public static java.awt.Color randomColor() {
        return Utility.getSelectedColors()[random.nextInt(Utility.getSelectedColors().length)];
    }
    public static void setDisplayPanel(DisplayPanel displayPanel) {
        Utility.displayPanel = displayPanel;
    }

    private static final Random random = new Random();
    public static Color[] getRandomColors(int colorCount) {
        var randomColors = new HashSet<Color>();

        while (randomColors.size() < colorCount) {
            randomColors.add(COLORS[random.nextInt(COLORS.length)]);
        }

        return randomColors.toArray(new Color[0]);

    }
}
