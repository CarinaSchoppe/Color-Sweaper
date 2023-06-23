package game;

import frontend.PopUpCreator;
import utility.Component;
import utility.Utility;

import java.awt.*;

public class AIPlayer extends Player {

    private Strategy strategy;

    public AIPlayer(Component component, Color color, String name) {
        super(component, color, name);
    }

    @Override
    public void makeMove(int x, int y) {
        System.out.println("Player " + getName() + " clicked on " + x + " " + y);


        //check valid move
        var panel = Utility.getDisplayPanel().getCellPanels()[x][y];
        //TODO: ENTFERNEN WENN NICHT MEHR BENÖTIGT
        if (!validateMove(panel)) {
            PopUpCreator.createPopUp("Invalid Move!", "Invalid Move");
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //color cell
        panel.setColor(getColor());
        getComponent().addCell(panel);
        endTurn();
    }

    public int[] findPosition() {
        return new int[0];
    }
}
