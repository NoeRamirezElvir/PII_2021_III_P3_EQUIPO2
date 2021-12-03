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

        /*ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());*/
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
