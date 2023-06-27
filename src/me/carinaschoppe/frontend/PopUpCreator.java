package me.carinaschoppe.frontend;

public class PopUpCreator {


    /**
     * Creates a swing window with the provided message and title and displays it as a pop-up.
     *
     * @param message the message to display in the pop-up window. Must be a String.
     * @param title   the title to display in the pop-up window. Must be a String.
     */
    public static void createPopUp(String message, String title) {
        //Create a swing window with a message and a title and display it
        var frame = new javax.swing.JFrame();
        javax.swing.JOptionPane.showMessageDialog(frame, message, title, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
