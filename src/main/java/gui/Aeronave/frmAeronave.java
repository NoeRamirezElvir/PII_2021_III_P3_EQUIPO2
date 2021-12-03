package gui.Aeronave;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmAeronave extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblModelo;
    private JLabel lblDescripcion;
    private JLabel lblAerolinea;
    private JLabel lblTamaño;
    private JLabel lblCapacidad;
    private JLabel lblTipo;
    private JLabel lblAeronaves;
    private JTextField txtID;
    private JTextField txtModelo;
    private JTextField txtDescripcion;
    private JComboBox comboBox1;
    private JTextField txtTamaño;
    private JTextField txtCapacidad;
    private JComboBox cboTipo;
    private JComboBox cboAeronaves;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTable tblDatos;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    private JPanel jpaBotones;
    DefaultTableModel modelo;

    public frmAeronave() {
        iniciar();
    }
    private void iniciar(){
        setTitle("Registro de Aeronaves");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoAvion.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/avion.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/Helicoptero.png"));
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
        modelo.addColumn("Modelo");
        modelo.addColumn("Descripción");
        modelo.addColumn("Aerolínea");
        modelo.addColumn("Tamaño");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Tipo");
    }
}
