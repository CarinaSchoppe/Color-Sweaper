package game;


import frontend.CellPanel;
import frontend.PopUpCreator;
import logic.MoveValidator;
import utility.Component;
import utility.Utility;

import java.awt.*;

public class Player implements MoveValidator {
    private final Component component;
    private Color color;

    private final String name;

    private Game game;

    public Player(Component component, String name) {
        this.component = component;
        this.color = component.getCells().get(0).getBackground();
        this.name = name;
    }


    public Component getComponent() {
        return component;
    }


    public Color getColor() {
        return color;
    }


    public String getName() {
        return name;
    }


    public void makeMove(int x, int y) {
        //player will click on a field.
        var panel = Utility.getDisplayPanel().getCellPanels()[x][y];
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

    public void endTurn() {
        //check winning!
        if (game.checkWinning()) return;
        if (game.isGamePaused()) return;
        game.switchCurrentPlayers(this);
        game.getCurrentPlayer().startPlayersTurn();
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void startPlayersTurn() {
        PopUpCreator.createPopUp("The Player " + name + " is selected as the current player", "Beginning Player");
    }

    @Override
    public boolean validateMove(CellPanel panel) {
        //check if the color is my own or is the opponents color
        if (panel.getColor().equals(color) || panel.getColor().equals(game.getOpponent(this).getColor())) {
            return false;
        }
        //check if the component is adjecent to my component
        return component.isAdjacent(panel);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
