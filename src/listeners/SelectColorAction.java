package listeners;

import game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectColorAction extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        //clicked on appropiate panel
        var panel = (javax.swing.JPanel) e.getSource();
        //get the color of the panel
        var color = panel.getBackground();
        
                
    }
}
