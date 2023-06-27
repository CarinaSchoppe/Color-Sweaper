package frontend;

import game.Game;
import listeners.KeyboardControlListener;
import listeners.PlayPauseAction;
import listeners.StartStopAction;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private static JButton startStopButton;
    private static JButton playPauseButton;
    private static JComboBox<String> playerSelect;
    private static JSpinner colorSpinner;
    private static JSpinner rowSpinner;
    private static JSpinner columnSpinner;

    private static int colPrev = 0;
    private static int coluPrev = 0;
    private static int rowPrev = 0;

    private static final String stratPrev = "strategy1";
    private static JComboBox<String> strategySelect;
    private static JLabel timerLabel;

    private static JLabel player1Points;
    private static JLabel player2Points; 

    private static String strategy;
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
        var players = new String[]{"S1", "S2"};
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
        var strategies = new String[]{"Strategy01", "Strategy02", "Strategy03"};
        strategySelect = new JComboBox<>(strategies);
        strategy = (String) strategySelect.getSelectedItem();
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

    static void updateSelectedColors(int colorCount) {

        //get random colors from Utility:colors based on colorCount
        var randomColors = Utility.getRandomColors(colorCount);
        Utility.setSelectedColors(randomColors);
    }


    public static JButton getStartStopButton() {
        return startStopButton;
    }

    public static JButton getPlayPauseButton() {
        return playPauseButton;
    }


    public static JComboBox<String> getPlayerSelect() {
        return playerSelect;
    }

    public static JSpinner getColorSpinner() {
        return colorSpinner;
    }

    public static JSpinner getRowSpinner() {
        return rowSpinner;
    }

    public static JSpinner getColumnSpinner() {
        return columnSpinner;
    }

    public static JComboBox<String> getStrategySelect() {
        return strategySelect;
    }

    public static JLabel getTimerLabel() {
        return timerLabel;
    }


    public static JLabel getGameStatus() {
        return gameStatus;
    }

    public static JLabel getPlayer1Points() {
        return player1Points;
    }

    public static void setPlayer1Points(JLabel player1Points) {
        GameWindow.player1Points = player1Points;
    }

    public static JLabel getPlayer2Points() {
        return player2Points;
    }

    public static void setPlayer2Points(JLabel player2Points) {
        GameWindow.player2Points = player2Points;
    }
}
