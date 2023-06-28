package GUI;


import javax.swing.JFrame;

public class FrameCurp extends JFrame {
    
    private static final String TITULO = "E2.6-565-Calcula Tu Curp";
    public static final int ANCHO = 450;
    public static final int ALTO = 240;
    
    public FrameCurp() {
        setTitle(TITULO);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PanelCurp panel = new PanelCurp();
        getContentPane().add(panel);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
