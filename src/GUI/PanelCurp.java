package GUI;

import Listeners.OyenteSoloLetras;
import Listeners.OyenteCurp;
import Render.RenderComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class PanelCurp extends JPanel {

    private JTextField txtNombre, txtApellidoPa, txtApellidoMa;
    private JComboBox cmbSexo, cmbEstado;
    private Font verdana, times_new_roman, roboto;
    private JDateChooser fecha;
    private JButton btnGenerarCurp;
    private static final int ROJO_INTENSO = 0xc70039;
    private static final int VERDE_BRILLANTE = 0xc0e218;
    private static final int BEIGE_CLARO = 0xf4eee8;

    public PanelCurp() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(VERDE_BRILLANTE));
        iniciarFuenteLetras();
        setEstilo();
        encabezado();
        iniciarCamposTextos();
        iniciarComboBox();
        iniciarCalendario();
        iniciarBotonResultado();
    }

    private void encabezado() {
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBackground(new Color(VERDE_BRILLANTE));
        JLabel lblEncabezado = new JLabel("Ingrese sus datos personales");
        lblEncabezado.setFont(times_new_roman);
        panelEncabezado.add(lblEncabezado, JLabel.CENTER);

        this.add(panelEncabezado);
    }

    private void iniciarFuenteLetras() {
        times_new_roman = new Font("Times new roman", Font.BOLD, 18);
        roboto = new Font("Roboto", Font.BOLD, 12);
        verdana = new Font("Verdana", Font.BOLD, 12);
    }

    private void iniciarComboBox() {
        JPanel panelCombosBox = new JPanel();
        panelCombosBox.setBackground(new Color(VERDE_BRILLANTE));
        cmbSexo = new JComboBox(new String[]{"Sexo", "Hombre", "Mujer"});
        cmbEstado = new JComboBox(new String[]{"Estado", "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Ciudad de México", "Coahuila", "Colima",
            "Durango", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "México", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"});
        cmbSexo.setRenderer(new RenderComboBox());
        cmbEstado.setRenderer(new RenderComboBox());

        cmbSexo.setFont(verdana);
        cmbEstado.setFont(verdana);
        panelCombosBox.add(cmbEstado, BoxLayout.X_AXIS);
        panelCombosBox.add(new JLabel("Estado: "), BoxLayout.X_AXIS);
        panelCombosBox.add(cmbSexo, BoxLayout.X_AXIS);
        panelCombosBox.add(new JLabel("Sexo: "), BoxLayout.X_AXIS);

        this.add(panelCombosBox);
    }

    private void iniciarCamposTextos() {
        JPanel panelCamposTextos = new JPanel();
        panelCamposTextos.setBackground(new Color(VERDE_BRILLANTE));
        panelCamposTextos.setLayout(new GridLayout(3, 2, 0, 5));
        txtNombre = new JTextField();
        txtNombre.addKeyListener(new OyenteSoloLetras());
        txtApellidoPa = new JTextField();
        txtApellidoPa.addKeyListener(new OyenteSoloLetras());
        txtApellidoMa = new JTextField();
        txtApellidoMa.addKeyListener(new OyenteSoloLetras());
        panelCamposTextos.add(new JLabel("Nombre: "));
        panelCamposTextos.add(txtNombre);
        panelCamposTextos.add(new JLabel("Apellido paterno: "));
        panelCamposTextos.add(txtApellidoPa);
        panelCamposTextos.add(new JLabel("Apellido materno: "));
        panelCamposTextos.add(txtApellidoMa);
        this.add(panelCamposTextos);
    }

    private void iniciarCalendario() {
        JPanel panelFecha = new JPanel();
        panelFecha.setBackground(new Color(VERDE_BRILLANTE));
        panelFecha.setLayout(new GridLayout(1, 2));
        fecha = new JDateChooser();
        //Fecha maxima. Se coloca el dia actual.
        fecha.getJCalendar().setMaxSelectableDate(Calendar.getInstance().getTime());
        panelFecha.add(new JLabel("Fecha de nacimiento: "));
        panelFecha.add(fecha);
        this.add(panelFecha);
    }

    private void iniciarBotonResultado() {
        JPanel panelGenerar = new JPanel();
        panelGenerar.setBackground(new Color(VERDE_BRILLANTE));
        btnGenerarCurp = new JButton("Generar CURP");
        btnGenerarCurp.setOpaque(false);
        btnGenerarCurp.setForeground(new Color(ROJO_INTENSO));
        btnGenerarCurp.setFont(roboto);

        JLabel lblResultado = new JLabel();
        btnGenerarCurp.addActionListener(new OyenteCurp(txtNombre, txtApellidoPa, txtApellidoMa, cmbSexo, cmbEstado, fecha, lblResultado));

        lblResultado.setFont(times_new_roman);
        lblResultado.setText("                                          ");
        lblResultado.setOpaque(true);
        Border borde = BorderFactory.createLineBorder(Color.BLACK);
        lblResultado.setBorder(borde);
        lblResultado.setBackground(Color.YELLOW);
        panelGenerar.add(lblResultado, BoxLayout.X_AXIS);
        panelGenerar.add(btnGenerarCurp, BoxLayout.X_AXIS);
        this.add(panelGenerar);
    }

    private void setEstilo() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
