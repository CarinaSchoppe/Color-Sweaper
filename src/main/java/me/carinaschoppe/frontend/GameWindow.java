/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:56 PM All contents of "GameWindow" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.frontend;


import lombok.Getter;
import me.carinaschoppe.game.Game;
import me.carinaschoppe.listeners.KeyboardControlListener;
import me.carinaschoppe.listeners.PlayPauseAction;
import me.carinaschoppe.listeners.StartStopAction;
import me.carinaschoppe.logic.Strategies;
import me.carinaschoppe.utility.Utility;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    /**
     * This variable represents the previous strategy used, as a string.
     * It is immutable and is initialized as "strategy1".
     */
    private static final String stratPrev = Strategies.STAGNATION.getName();
    /**
     * A static JButton instance representing a button used to start or stop an operation.
     * -- GETTER --
     *  This method returns the start/stop button that was created in the 
     *  method.
     *  This button can be used to start or stop the game.
     *
     * @return the start/stop button

     */
    @Getter
    private static JButton startStopButton;
    /**
     * Represents the play/pause button of the application.
     * This button toggles between play and pause functionality.
     * The button is static because there should be only one instance of the button.
     * -- GETTER --
     *  This method returns the play/pause button that is present on the menu panel of the game window. This button can be
     *  used to start or pause the game.
     *
     * @return the play/pause button present on the menu panel of the game window.

     */
    @Getter
    private static JButton playPauseButton;
    /**
     * A private static JComboBox that represents the player selection drop-down box.
     * The JComboBox contains a list of String values, where each item corresponds to a player.
     * The user can select a player from this drop-down list to perform certain operations.
     * -- GETTER --
     *  This method returns the player select drop down menu used in the game window. It allows the user to choose
     *  which player they want to be represented as in the game.
     *
     * @return the player select JComboBox<String>

     */
    @Getter
    private static JComboBox<String> playerSelect;
    /**
     * A private static JSpinner representing a color selection component.
     * -- GETTER --
     *  This method returns the color spinner used in the menu panel. It allows the user to select the number of colors
     *  to be used in the game, and updates the color panel accordingly when the user changes the value. The minimum and
     *  maximum values for the spinner are set to 4 and 9 respectively, with a step size of 1. A change listener is also
     *  added to the spinner to update the color panel and prevent changes during game play.
     *  <p>
     *
     * @return the color spinner used in the game window's menu panel.

     */
    @Getter
    private static JSpinner colorSpinner;
    /**
     * This variable represents a Java Swing component that allows the user to select a value from a range of numeric values using up/down arrows or by typing directly into the component.
     * <p>
     * The JSpinner is set as static and is only accessible within the class it is declared in.
     * <p>
     * This specific JSpinner is used to allow the user to select a value that represents a row in a table or grid.
     * <p>
     * Note: A SpinnerModel needs to be set to this JSpinner to specify the range of values and the behavior when the user interacts with the component.
     * -- GETTER --
     *  This method returns the JSpinner instance used for selecting the number of rows in the game board.
     *
     * @return the JSpinner instance used for selecting the number of rows in the game board

     */
    @Getter
    private static JSpinner rowSpinner;
    /**
     * Private static JSpinner variable columnSpinner holds an instance of JSpinner,
     * which displays the value of a specified column in a table.
     * The spinner allows the user to select a valid column number to display.
     * <p>
     * This variable is used in conjunction with a table to allow the user to select
     * the column they would like to display a value for.
     * <p>
     * Note: This variable is only used internally and is not intended to be accessed
     * or modified externally.
     * -- GETTER --
     *  Returns the JSpinner object for selecting the number of columns in the game grid.
     *
     * @return the columnSpinner JSpinner object

     */
    @Getter
    private static JSpinner columnSpinner;
    /**
     * Integer variable representing the previous column in a table or grid.
     * This variable is used to keep track of the column that was accessed or
     * modified previously so that the program can make decisions based on it.
     */
    private static int colPrev = 0;
    /**
     * Represents the previous column index for a specific operation.
     * This variable is static and private, meaning it can only be accessed within this class.
     * The default value is 0, which is used as the initial value for the previous column
     * when no other value has been assigned yet.
     */
    private static int coluPrev = 0;
    /**
     * Represents the row index of the previous row in a matrix.
     * This variable is declared as private static which means it can be accessed by any method within the class.
     * Its initial value is 0 which implies that there is no previous row at the beginning of the matrix.
     */
    private static int rowPrev = 0;
    /**
     * A private static JComboBox that allows the user to choose a strategy.
     * -- GETTER --
     *  This method retrieves the strategy select dropdown menu used in the game window. The dropdown menu allows the user to select
     *  which strategy to use in the game. It returns a JComboBox of String type that contains the list of available strategies.
     *
     * @return a JComboBox of String type containing the list of available strategies

     */
    @Getter
    private static JComboBox<String> strategySelect;
    /**
     * JLabel used to display the timer value.
     * -- GETTER --
     *  This method returns the JLabel object for the timer in the game's menu panel. This label is used to display the
     *  current time elapsed during the game.
     *
     * @return JLabel object for the game's timer.

     */
    @Getter
    private static JLabel timerLabel;

    /**
     * This variable represents the current points of player 1 in the game.
     * It is a private static JLabel object and can be accessed using its getter method.
     * It is used to display the points of player 1 on the GUI of the game.
     */
    @Getter
    private static JLabel player1Points;
    /**
     * player2Points represents a graphical component of type JLabel that displays
     * the number of points scored by player 2 in a game. This variable is static,
     * meaning it can be accessed from any instance of the class in which it is declared.
     * This JLabel can be updated to change the displayed value using the appropriate
     * class methods.
     * -- GETTER --
     *  This method returns the JLabel object containing the score of player 2 in the game.
     *
     * @return the JLabel object containing the score of player 2

     */
    @Getter
    private static JLabel player2Points;

    /**
     * Represents the game status label on the user interface.
     * This variable holds a reference to a JLabel object which is used to display the current status of the game to the user.
     * This label is used to display messages like "Player won", "Game Tied", "It's player 1's turn".
     * <p>
     * As it is a static variable, it is shared among all instances of the class in which it is declared.
     * <p>
     * The variable is private to provide encapsulation of the game status and it is only accessible within the same class.
     * -- GETTER --
     *  This method returns the JLabel used to display the current game status.
     *
     * @return the game status JLabel

     */
    @Getter
    private static JLabel gameStatus;

    /**
     * This method creates and shows the GUI for the game window. It sets the size and minimum size for the window,
     * creates a menu panel, adds various buttons and drop down menus to it, creates a display panel and color panel,
     * and adds them to different parts of the window. It also creates a timer and sets up key mappings to listen for
     * input from the user.
     * <p>
     * This method does not return anything.
     */
    public static void createAndShowGUI() {
        var frame = new JFrame("Start");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set size of the Frame
        frame.setSize(600, 600);
        for (int i = 1; i <= 9; i++) {
            String key = String.valueOf(i);
            String numpadKey = "NUMPAD" + i;
            frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
            frame.getRootPane().getActionMap().put(key, new KeyboardControlListener());

            frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(numpadKey), numpadKey);
            frame.getRootPane().getActionMap().put(numpadKey, new KeyboardControlListener());
        }

        //Set Minimum Size
        frame.setMinimumSize(new Dimension(600, 600));

        //Position Frame in the center of the screen
        frame.setLocationRelativeTo(null);

        //Create Menu Panel
        var menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, 600));
        menuPanel.setBackground(Color.CYAN);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        //Add Help Button to menuPanel
        var helpButton = new JButton("Help");
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "THIS IS HOW THE GAME IS PLAYED", "Instructions", JOptionPane.INFORMATION_MESSAGE));
        menuPanel.add(helpButton);

        //Add Start/Stop Button to MenuPanel
        startStopButton = new JButton("Start");
        startStopButton.addActionListener(new StartStopAction());
        menuPanel.add(startStopButton);


        //Add Play/Pause button to menuPanel
        playPauseButton = new JButton("Play");
        playPauseButton.addActionListener(new PlayPauseAction());
        menuPanel.add(playPauseButton);

        //Add game.Player select dropdown to menuPanel
        var players = new String[]{"Spieler1", "Spieler2"};
        playerSelect = new JComboBox<>(players);
        menuPanel.add(playerSelect);

        //Add color Spinner to MenuPanel
        var colorModel = new SpinnerNumberModel(5, 4, 9, 1);
        colorSpinner = new JSpinner(colorModel);
        menuPanel.add(colorSpinner);
        colorSpinner.addChangeListener(e -> {
            if (Game.getGame().isGameRunning()) {
                colorSpinner.setValue(colPrev);
                return;
            }
            var count = (Integer) colorSpinner.getValue();
            updateSelectedColors(count);
            Utility.getColorPanel().setColorCount((Integer) colorSpinner.getValue());
            Utility.getColorPanel().refreshColors();
            colPrev = count;

        });
        updateSelectedColors((Integer) colorModel.getValue());
        //Add row Spinner to MenuPanel
        var rowModel = new SpinnerNumberModel(6, 3, 10, 1);
        rowSpinner = new JSpinner(rowModel);
        menuPanel.add(rowSpinner);

        rowSpinner.addChangeListener(e -> {
            if (Game.getGame().isGameRunning()) {
                rowSpinner.setValue(rowPrev);
                return;
            }
            Utility.getDisplayPanel().setRows((Integer) rowSpinner.getValue());
            rowPrev = (Integer) rowSpinner.getValue();
        });

        //Add column Spinner to MenuPanel
        var columnModel = new SpinnerNumberModel(6, 3, 10, 1);
        columnSpinner = new JSpinner(columnModel);
        menuPanel.add(columnSpinner);

        columnSpinner.addChangeListener(e -> {
            if (Game.getGame().isGameRunning()) {
                columnSpinner.setValue(coluPrev);
                return;
            }
            Utility.getDisplayPanel().setColumns((Integer) columnSpinner.getValue());
            coluPrev = (Integer) columnSpinner.getValue();
        });

        //Add strategy select Dropdown to menuPanel
        var strategies = new String[]{Strategies.STAGNATION.getName(), Strategies.GREEDY.getName(), Strategies.MULTIPLAYER.getName()};
        strategySelect = new JComboBox<>(strategies);
        /**
         * Represents a string variable named "strategy" that specifies the strategy being used in the software.
         * This variable is static, meaning it is a class-level variable and can be accessed without creating an instance of the class.
         * Its visibility is private, so it can only be accessed within the class in which it is defined.
         * Its data type is String, meaning it can hold alphanumeric characters and is immutable.
         * Its initial value is null, so it must be assigned a value before it can be used.
         * <p>
         * Example:
         * strategy = "Aggressive";
         */
        String strategy = (String) strategySelect.getSelectedItem();
        menuPanel.add(strategySelect);
        //Add Timer Label to menuPanel
        timerLabel = new JLabel("00:00");
        player2Points = new JLabel("Player2: 0");
        player1Points = new JLabel("Player1: 0");
        menuPanel.add(timerLabel);
        menuPanel.add(player1Points);
        menuPanel.add(player2Points);

        //Create display Panel
        Utility.setDisplayPanel(new DisplayPanel((Integer) rowSpinner.getValue(), (Integer) columnSpinner.getValue()));
        Utility.getDisplayPanel().setBackground(Color.LIGHT_GRAY);
        Utility.setColorPanel(new ColorPanel((Integer) colorSpinner.getValue()));


        gameStatus = new JLabel(" ");
        //Add Panels to Frame
        frame.getContentPane().add(BorderLayout.EAST, menuPanel);
        frame.getContentPane().add(BorderLayout.CENTER, Utility.getDisplayPanel());
        frame.getContentPane().add(Utility.getColorPanel(), BorderLayout.SOUTH);
        frame.getContentPane().add(gameStatus, BorderLayout.NORTH);

        //Display the Window
        frame.setVisible(true);

        //Create Timer
        Utility.setTimer(new Timer(1000, e -> {
            Utility.setTimerSeconds(Utility.getTimerSeconds() + 1);

            timerLabel.setText(String.format("%02d:%02d", Utility.getTimerSeconds() / 60, Utility.getTimerSeconds() % 60));
        }));
    }

    /**
     * This method updates the selected number of colors for the game. It takes in an integer parameter, colorCount,
     * which represents the number of colors to be selected for the game. It retrieves a list of random colors from the
     * Utility class, based on the colorCount parameter, and sets them as the selected colors for the game. This method
     * does not return anything.
     *
     * @param colorCount an integer representing the number of colors to be selected for the game
     */
    static void updateSelectedColors(int colorCount) {

        //get random colors from Utility:colors based on colorCount
        var randomColors = Utility.getRandomColors(colorCount);
        Utility.setSelectedColors(randomColors);
    }


    /**
     * This method sets the value of player1Points JLabel to the given JLabel parameter. This is used to update the points
     * displayed for player 1 during the game.
     *
     * @param player1Points the JLabel representing the points of player 1 to be set.
     */
    public static void setPlayer1Points(JLabel player1Points) {
        GameWindow.player1Points = player1Points;
    }

    /**
     * This method sets the JLabel representing the points earned by player 2 in the game window. It takes in a JLabel
     * parameter and assigns it to the static player2Points variable in the GameWindow class. This Jlabel will be updated
     * throughout the game to reflect the current score of player 2.
     *
     * @param player2Points the JLabel representing the points of player 2
     */
    public static void setPlayer2Points(JLabel player2Points) {
        GameWindow.player2Points = player2Points;
    }
}
