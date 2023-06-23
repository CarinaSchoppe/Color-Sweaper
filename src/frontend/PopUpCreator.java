package frontend;

public class PopUpCreator {


    public static void createPopUp(String message, String title) {
        //Create a swing window with a message and a title and display it
        var frame = new javax.swing.JFrame();
        javax.swing.JOptionPane.showMessageDialog(frame, message, title, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
