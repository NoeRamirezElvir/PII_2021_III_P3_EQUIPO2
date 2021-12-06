package gui.Vuelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmVuelos extends JFrame{
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
    private JLabel lblFecha;
    private JLabel lblPuntoP;
    private JLabel lblHoraS;
    private JTextField txtId;
    private JTextField txtFecha;
    private JTextField txtLugarP;
    private JFormattedTextField ftfHoraP;
    private JLabel lblDestino;
    private JLabel lblAerolinea;
    private JLabel lblTiempoE;
    private JTextField txtDestino;
    private JFormattedTextField ftfTiempoE;
    private JLabel lblDescrpcion;
    private JTextField txtDescripcion;
    private JLabel lblVuelo;
    private JComboBox cboVuelo;
    private JComboBox cboAerolinea;
    DefaultTableModel modelo = new DefaultTableModel();
    public frmVuelos (){
        iniciar();
    }

    private void iniciar(){
        setTitle("Vuelos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoVuelos.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/vuelos.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/vuelos2.png"));
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
        modelo.addColumn("Fecha");
        modelo.addColumn("Lugar de Partida");
        modelo.addColumn("Hora Programada");
        modelo.addColumn("Destino");
        modelo.addColumn("Aerolínea");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Descripción");
    }
}
