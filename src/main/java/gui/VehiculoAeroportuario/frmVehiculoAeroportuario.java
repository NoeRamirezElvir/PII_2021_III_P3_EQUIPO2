package gui.VehiculoAeroportuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmVehiculoAeroportuario extends JFrame{
    private JPanel jpaPrincipal;
    private JTable tblDatos;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JTextField txtNumPlaca;
    private JComboBox cboTipo;
    private JTextField txtDescripcion;
    private JTextField txtEstado;
    private JComboBox cboTipoCombustible;
    private JTextField txtCapacidad;
    private JComboBox cboVehiculos;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblCodigo;
    private JLabel lblNumPlaca;
    private JLabel lblTipo;
    private JLabel lblDescripcion;
    private JLabel lblEstado;
    private JLabel lblTipoCombustible;
    private JLabel lblCapacidad;
    private JLabel lblVehiculos;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    DefaultTableModel modelo;

    public frmVehiculoAeroportuario() {
        iniciar();
    }
    private void iniciar(){
        setTitle("Registro de Vehículos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoVehiculo.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenMontaCarga.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenGrua.png"));
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
        modelo.addColumn("Código");
        modelo.addColumn("Número de Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Tipo Combustible");
        modelo.addColumn("Capacidad");
    }
}
