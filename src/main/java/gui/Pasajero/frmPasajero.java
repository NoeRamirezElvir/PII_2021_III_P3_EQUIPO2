package gui.Pasajero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class frmPasajero extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JTextField txttelefono;
    private JComboBox cboCategoria;
    private JComboBox cboTipoDoc;
    private JTextField txtNacionalidad;
    private JComboBox cboPasajeros;
    private JLabel lblID;
    private JLabel lblCodigo;
    private JLabel lblCategoria;
    private JLabel lblTipoDoc;
    private JLabel lblTelefono;
    private JLabel lblIDDoc;
    private JLabel lblNacionalidad;
    private JLabel lblPasajeros;
    private JLabel lblNombre;
    private JComboBox cboNombrePersonas;
    private JTextField txtIdDoc;
    private JPanel jpaDatos;
    private JLabel lblImagen2;
    private JLabel lblImagen;
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


    public frmPasajero(){
        iniciar();
    }
    private void iniciar(){
        setTitle("Registro de Pasajeros");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoRegistrarPersonas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenCliente1.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenCliente2.png"));
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Tipo de Documento");
        modelo.addColumn("ID del Documento");
        modelo.addColumn("Nacionalidad");

    }
}
