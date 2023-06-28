package Listeners;


import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.Normalizer;

public class OyenteCurp implements ActionListener {

    private JTextField txtNombre, txtAppellidoPa, txtAppellidoMa;
    private JComboBox cmbSexo, cmbEstado;
    private JDateChooser fecha;
    private JLabel lbl;
    private ArrayList<Character> vocales;
    private SimpleDateFormat fechaFormato;

    public OyenteCurp(JTextField txtNombre, JTextField txtAppellidoPa, JTextField txtAppellidoMa, JComboBox cmbSexo, JComboBox cmbEstado, JDateChooser fecha, JLabel lbl) {
        this.txtNombre = txtNombre;
        this.txtAppellidoPa = txtAppellidoPa;
        this.txtAppellidoMa = txtAppellidoMa;
        this.cmbSexo = cmbSexo;
        this.cmbEstado = cmbEstado;
        this.fecha = fecha;
        this.lbl = lbl;
        iniciarComponentes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            boolean vacio = !(txtNombre.getText().contains(" ") && txtAppellidoPa.getText().contains(" ") && txtAppellidoMa.getText().contains(" "));
            boolean combos = !cmbSexo.getSelectedItem().equals("Sexo") && !cmbEstado.getSelectedItem().equals("Estado");

            if (vacio && combos) {
                String curp = inicialApellidoPaternoVocal() + inicialesApellidoMaterno() + inicialNombre() + fechaNacimiento() + sexo() + estado() + PrimeraNoInicialConsoApelli();
                lbl.setText(curp);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException | StringIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private String inicialApellidoPaternoVocal() {
        String inicialApelliPa = "";
        for (int i = 1; i < txtAppellidoPa.getText().length(); i++) {
            if (vocales.contains(txtAppellidoPa.getText().charAt(i))) {
                inicialApelliPa = txtAppellidoPa.getText().charAt(0) + "" + txtAppellidoPa.getText().charAt(i) + "";
                i = txtAppellidoPa.getText().length();
            }
        }
        inicialApelliPa = quitarAcentos(inicialApelliPa);
        return inicialApelliPa.toUpperCase();
    }

    private String inicialesApellidoMaterno() {
        String inicialApelliMa = txtAppellidoMa.getText().charAt(0) + "";
        inicialApelliMa = quitarAcentos(inicialApelliMa);
        return inicialApelliMa.toUpperCase();
    }

    private String inicialNombre() {
        String inicialNom = txtNombre.getText().charAt(0) + "";
        inicialNom = quitarAcentos(inicialNom);
        return inicialNom.toUpperCase();
    }

    private String fechaNacimiento() {                         
        String fechaNa = fechaFormato.format(fecha.getDate()); 
        String dia = fechaNa.substring(0, 2);
        String mes = fechaNa.substring(3, 5);
        String anio = fechaNa.substring(8, 10);
        return anio + mes + dia;
    }

    private String sexo() {
        if (cmbSexo.getSelectedItem().equals("Hombre")) {
            return "H";
        } else {
            if (cmbSexo.getSelectedItem().equals("Mujer")) {
                return "M";
            }
        }
        return "";
    }

    private String estado() {
        String estado = cmbEstado.getSelectedItem().toString();
        switch (estado) {
            case "Aguascalientes":
                return "AS";
            case "Baja California":
                return "BC";
            case "Baja California Sur":
                return "BS";
            case "Campeche":
                return "CC";
            case "Chiapas":
                return "CS";
            case "Chihuahua":
                return "CH";
            case "Ciudad de México":
                return "DF";
            case "Coahuila":
                return "CL";
            case "Colima":
                return "CM";
            case "Durango":
                return "DG";
            case "Guanajuato":
                return "GT";
            case "Guerrero":
                return "GR";
            case "Hidalgo":
                return "HG";
            case "Jalisco":
                return "JC";
            case "México":
                return "MC";
            case "Michoacán":
                return "MN";
            case "Morelos":
                return "MS";
            case "Nayarit":
                return "NT";
            case "Nuevo León":
                return "NL";
            case "Oaxaca":
                return "OC";
            case "Puebla":
                return "PL";
            case "Querétaro":
                return "QO";
            case "Quintana Roo":
                return "QR";
            case "San Luis Potosí":
                return "SP";
            case "Sinaloa":
                return "SL";
            case "Sonora":
                return "SR";
            case "Tabasco":
                return "TC";
            case "Tamaulipas":
                return "TS";
            case "Tlaxcala":
                return "TL";
            case "Veracruz":
                return "VZ";
            case "Yucatán":
                return "YN";
            case "Zacatecas":
                return "ZS";
        }
        return "";
    }

    private String PrimeraNoInicialConsoApelli() {
        String paterno = "", materno = "", nombre = "";
        for (int i = 1; i < txtAppellidoPa.getText().length(); i++) {
            if (!vocales.contains(txtAppellidoPa.getText().charAt(i))) {
                paterno = txtAppellidoPa.getText().charAt(i) + "";
                i = txtAppellidoPa.getText().length();
            }
        }
        for (int i = 1; i < txtAppellidoMa.getText().length(); i++) {
            if (!vocales.contains(txtAppellidoMa.getText().charAt(i))) {
                materno = txtAppellidoMa.getText().charAt(i) + "";
                i = txtAppellidoMa.getText().length();
            }
        }
        for (int i = 1; i < txtNombre.getText().length(); i++) {
            if (!vocales.contains(txtNombre.getText().charAt(i))) {
                nombre = txtNombre.getText().charAt(i) + "";
                i = txtNombre.getText().length();
            }
        }

        String primeraInicialConso = paterno + materno + nombre;
        primeraInicialConso = quitarAcentos(primeraInicialConso); 
        return primeraInicialConso.toUpperCase();
    }

    private String quitarAcentos(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);  
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); 
        return texto;
    }

    private void iniciarComponentes() {
        vocales = new ArrayList<>();
        vocales.add('A');
        vocales.add('E');
        vocales.add('I');
        vocales.add('O');
        vocales.add('U');
        vocales.add('a');
        vocales.add('e');
        vocales.add('i');
        vocales.add('o');
        vocales.add('u');
        vocales.add('Á');
        vocales.add('É');
        vocales.add('Í');
        vocales.add('Ó');
        vocales.add('Ú');
        vocales.add('á');
        vocales.add('é');
        vocales.add('í');
        vocales.add('ó');
        vocales.add('ú');
        fechaFormato = new SimpleDateFormat("dd-MM-yyyy");
    }
    
}
