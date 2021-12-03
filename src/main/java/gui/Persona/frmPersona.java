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

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoActualizarPersonas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenPersona2.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenPersona.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoRegistrarPersonas.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoActualizarPersonas.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoEliminarPersonas.png"));
        //boton buscar
        btnBuscar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoLeerCBOPersonas.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoLimpiarPersonas.png"));
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
