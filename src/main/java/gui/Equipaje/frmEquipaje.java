package gui.Equipaje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmEquipaje extends JFrame {
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContain;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JLabel lblTitulo;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblId;
    private JLabel lblBoleto;
    private JLabel lblNombreP;
    private JLabel lblPeso;
    private JTextField txtId;
    private JComboBox cboBoleto;
    private JTextField txtNombreP;
    private JTextField txtPeso;
    private JLabel lblFechaRegistro;
    private JLabel lblDestino;
    private JLabel lblColor;
    private JLabel lbltamaño;
    private JTextField txtFecha;
    private JTextField txtDestino;
    private JTextField txtColor;
    private JComboBox cboTama;
    private JLabel lblEquipajes;
    private JComboBox cboEquipaje;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmEquipaje (){
        iniciar();
    }

    private void iniciar(){
        setTitle("Equipajes");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoEquipaje.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/equipaje.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/equipaje2.png"));
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
        modelo.addColumn("Boleto");
        modelo.addColumn("Pasajero");
        modelo.addColumn("Peso");
        modelo.addColumn("Fecha de Registro");
        modelo.addColumn("Destino");
        modelo.addColumn("Color");
        modelo.addColumn("Tamaño");
    }
}
