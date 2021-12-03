package gui.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmPersona extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JLabel lblTitulo;
    private JTextField txtID;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtDireccion;
    private JComboBox cboGenero;
    private JComboBox cboTipo;
    private JLabel lblID;
    private JLabel lblDNI;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblDireccion;
    private JLabel lblGenero;
    private JLabel lblTipo;
    private JLabel lblPersonas;
    private JComboBox cboPersonas;
    private JPanel jpaDatos;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JPanel jpaBotones;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    DefaultTableModel modelo;

    public frmPersona() {
        iniciar();

    }
    private void iniciar(){
        setTitle("Registro de Personas");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        /*ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());*/
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Dirección");
        modelo.addColumn("Género");
        modelo.addColumn("Tipo");

    }
}
