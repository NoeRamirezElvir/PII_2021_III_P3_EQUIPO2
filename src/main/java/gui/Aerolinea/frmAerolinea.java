package gui.Aerolinea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmAerolinea extends JFrame {

    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContain;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblCodigo;
    private JLabel lblTelefonoC;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtTelC;
    private JTextField txtId;
    private JLabel lblDireccion;
    private JLabel lblPagWeb;
    private JLabel lblUbicacion;
    private JLabel lblTelefonoA;
    private JTextField txtDirec;
    private JTextField txtPagWeb;
    private JTextField txtUbicacion;
    private JTextField txtTelA;
    private JLabel lblAerolinea;
    private JComboBox cboAerolinea;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmAerolinea (){
        iniciar();
    }

    private void iniciar(){
        setTitle("Aerolineas");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoAerolineas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/aerolinea.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/aerolinea2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar
        btnBuscar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Código");
        modelo.addColumn("Teléfono de Carga");
        modelo.addColumn("Dirección");
        modelo.addColumn("Página Web");
        modelo.addColumn("Ubicación Aeropuerto");
        modelo.addColumn("Teléfono  de Atención");
    }
}

