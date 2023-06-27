/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "Utility" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.utility;


import lombok.Getter;
import me.carinaschoppe.frontend.ColorPanel;
import me.carinaschoppe.frontend.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

@Getter
public class Utility {
    /**
     * An array of predefined colors.
     * This array contains the following colors:
     * Red, Green, Blue, Yellow, Black, Orange, Pink, Magenta, Cyan.
     */
    public static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.CYAN};
    /**
     * A static final instance of the {@link Random} class, used for generating
     * random numbers.
     */
    private static final Random random = new Random();
    /**
     * A private static variable that represents a color panel.
     * The color panel is used to display and manipulate colors in a graphical user interface.
     * This variable is accessible only within the class it is declared in and can be used to
     * update or retrieve the current color panel as needed.
     * -- GETTER --
     *  Returns the singleton instance of ColorPanel.
     *
     * @return the ColorPanel instance.

     */
    private static ColorPanel colorPanel;
    /**
     * The selectedColors variable represents an array of colors that have been selected.
     * This variable is declared as private static, meaning that it is accessible within the class only.
     * The type of each element in the array is Color.
     * -- GETTER --
     *  Returns an array of Colors that have been selected.
     *
     * @return an array of Color objects that have been selected.

     */
    @Getter
    private static Color[] selectedColors;
    /**
     * A static variable representing a timer object.
     * <p>
     * This variable can be used to schedule and cancel scheduled tasks. It is shared among all instances of the class
     * and can be accessed from any method of the class.
     * </p>
     * <p>
     * The Timer class allows to schedule tasks to be executed after a certain delay, or to execute them repeatedly with a fixed delay
     * between executions. The timer can be cancelled at any time, and the scheduled tasks will not be executed anymore.
     * </p>
     * -- GETTER --
     *  Returns the Timer instance. The Timer instance is a static variable that is shared across the application.
     *
     * @return the Timer instance

     */
    @Getter
    private static Timer timer;
    /**
     * The timerSeconds variable represents the number of seconds for a timer.
     * It is a private static integer variable which can only be accessed within the class it is declared in.
     * <p>
     * The value of timerSeconds can be set and retrieved using respective setter and getter methods.
     * -- GETTER --
     *  Returns the timerSeconds value which indicates the number of seconds elapsed in the timer.
     *
     * @return an integer value representing the current number of seconds elapsed in the timer.

     */
    @Getter
    private static int timerSeconds;
    /**
     * Represents a private static class-level variable, which holds an instance of DisplayPanel.
     *
     * <p> DisplayPanel is a UI component which renders content on the screen. This variable holds an
     * instance of DisplayPanel, which can be used by other classes to manage and update the displayed content.</p>
     *
     * <p>This variable is marked as private and static which means that it can be accessed only within the class where it is
     * declared, and it will hold the same value for all instances of the class.</p>
     *
     *
     * -- GETTER --
     *  Returns the display panel that holds the user interface components.
     *
     @see DisplayPanel
      * @return DisplayPanel object that contains the main UI components.
     */
    @Getter
    private static DisplayPanel displayPanel;

    /**
     * Returns a randomly generated java.awt.Color object.
     *
     * @return a randomly generated java.awt.Color object
     */

    /**
     * Sets the color panel used for displaying colors in the application.
     *
     * @param colorPanel the color panel to set
     */
    public static void setColorPanel(ColorPanel colorPanel) {
        Utility.colorPanel = colorPanel;
    }

    /**
     * Sets the selected colors for the application.
     *
     * @param selectedColors an array of Color objects representing the selected colors
     */
    public static void setSelectedColors(Color[] selectedColors) {
        Utility.selectedColors = selectedColors;
    }

    /**
     * This method sets the timer for Utility class.
     *
     * @param timer the Timer object to be set
     */
    public static void setTimer(Timer timer) {
        Utility.timer = timer;
    }

    /**
     * Sets the timer duration in seconds.
     *
     * @param timerSeconds the number of seconds for the timer duration
     */
    public static void setTimerSeconds(int timerSeconds) {
        Utility.timerSeconds = timerSeconds;
    }

    /**
     * Sets the DisplayPanel object to be used by the Utility class.
     *
     * @param displayPanel the DisplayPanel object to be set
     */
    public static void setDisplayPanel(DisplayPanel displayPanel) {
        Utility.displayPanel = displayPanel;
    }

    /**
     * This method returns a randomly selected color from the pre-defined set of colors.
     *
     * @return a randomly selected Color object
     **/
    public static java.awt.Color randomColor() {
        return Utility.getSelectedColors()[random.nextInt(Utility.getSelectedColors().length)];
    }

    /**
     * Generates an array of random colors.
     *
     * @param colorCount the number of random colors to generate
     * @return an array of random colors
     */
    public static Color[] getRandomColors(int colorCount) {
        var randomColors = new HashSet<Color>();

        while (randomColors.size() < colorCount) {
            randomColors.add(COLORS[random.nextInt(COLORS.length)]);
        }

        return randomColors.toArray(new Color[0]);

    }
}
