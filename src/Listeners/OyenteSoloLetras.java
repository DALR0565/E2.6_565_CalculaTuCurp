package Listeners;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OyenteSoloLetras extends KeyAdapter {

    @Override
    public void keyTyped(KeyEvent e) {
        if (!Character.isLetter(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_SPACE) {
            e.consume();
        }
    }
}
