package me.carinaschoppe.game;


import me.carinaschoppe.frontend.CellPanel;
import me.carinaschoppe.frontend.PopUpCreator;
import me.carinaschoppe.logic.MoveValidator;
import me.carinaschoppe.utility.Component;
import me.carinaschoppe.utility.Utility;

import java.awt.*;

public class Player implements MoveValidator {
    /**
     * Represents a final Component instance that holds a visual element of the user interface.
     * This variable is immutable and can only be set once upon initialization.
     */
    private final Component component;
    /**
     * A string representation of a name that is immutable.
     */
    private final String name;
    /**
     * The color variable represents the color of an object.
     * The color is defined using the RGB color model and is represented in the software as an instance of the java.awt.Color class.
     */
    private Color color;
    /**
     * This variable represents an instance of the Game class, which handles the main game logic and provides access to game state and game settings.
     */
    private Game game;

    /**
     * Constructor to create a new player object.
     *
     * @param component The game component that the player is associated with.
     * @param name      The name of the player.
     * @throws NullPointerException if the given component or name is null.
     */
    public Player(Component component, String name) {
        this.component = component;
        this.color = component.getCells().get(0).getBackground();
        this.name = name;
    }


    /**
     * Returns the component object of the game board.
     * This method returns the component object which is a graphical representation of the game board.
     * The component object is used to display and update game information displayed on the user interface.
     *
     * @return A Component object representing the game board.
     */
    public Component getComponent() {
        return component;
    }


    /**
     * Method to retrieve the Color value of an object.
     *
     * @return The Color value associated with the object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of an object.
     *
     * @param color The color to be set for the object.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the name of the object.
     *
     * @return The name of the object as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to make a move on a game board.
     *
     * @param row    The row of the cell to make a move on.
     * @param column The column of the cell to make a move on.
     * @throws IllegalArgumentException if the given row or column value is out of range or invalid.
     */
    public void makeMove(int row, int column) {
        //player will click on a field.
        var panel = Utility.getDisplayPanel().getCellPanels()[column][row];
        //check valid move
        if (!validateMove(panel)) {
            PopUpCreator.createPopUp("Invalid Move!", "Invalid Move");
            return;
        }
        Utility.getColorPanel().updateCellPanelColor(panel, this);
        component.addCell(panel);
        component.tracePath();
        endTurn();
    }

    /**
     * Method to end the current player's turn and switch to the next player.
     * <p>
     * If a player has won the game or the game is currently paused, this method does nothing.
     * Otherwise, it switches to the next player and calls the startPlayersTurn method for the new player.
     */
    public void endTurn() {
        //check winning!
        if (game.checkWinning()) return;
        if (game.isGamePaused()) return;
        game.switchCurrentPlayers(this);
        game.getCurrentPlayer().startPlayersTurn();
    }

    /**
     * Method to set the current game being played.
     *
     * @param game The game object to set.
     * @throws NullPointerException if the given game object is null.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Starts the turn of the selected player. If the player is an AI player, it will perform its move automatically.
     * If the player is a human, a pop-up message will be displayed to indicate the start of their turn.
     *
     * @throws NullPointerException if the player name is null.
     */
    public void startPlayersTurn() {
        if (this instanceof AIPlayer ai) {
            ai.perform();
        } else {
            PopUpCreator.createPopUp("The Player " + name + " is selected as the current player", "Beginning Player");
        }
    }

    /**
     * Validates whether the candidate move is valid or not on the game board.
     *
     * @param panel The CellPanel element to be validated.
     * @return true, if the move is valid; false, otherwise.
     * @throws IllegalArgumentException if the given panel is null or invalid.
     */
    @Override
    public boolean validateMove(CellPanel panel) {
        //check if the color is my own or is the opponents color
        if (panel.getColor().equals(color) || panel.getColor().equals(game.getOpponent(this).getColor())) {
            return false;
        }
        //check if the component is adjecent to my component
        return component.isAdjacent(panel);
    }
}
