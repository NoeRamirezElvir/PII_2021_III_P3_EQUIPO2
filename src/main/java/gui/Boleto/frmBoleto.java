package gui.Boleto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmBoleto extends JFrame{
    private JPanel jpaPrincipal;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JComboBox cboPasajero;
    private JComboBox cboVuelo;
    private JTextField txtAsiento;
    private JTextField txtPuertaEmbarque;
    private JComboBox cboClase;
    private JTextField txtTotal;
    private JComboBox cboBoletos;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTable tblDatos;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JLabel lblTitulo;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    private JLabel lblID;
    private JLabel lblCodigoFactura;
    private JLabel lblPasajero;
    private JLabel lblVuelo;
    private JLabel lblAsiento;
    private JLabel lblPuertaEmbarque;
    private JLabel lblClase;
    private JLabel lblTotal;
    private JLabel lblBoletos;
    DefaultTableModel modelo;

    public frmBoleto() {
        iniciar();
    }
    private void iniciar(){
        setTitle("Registro de Boletos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        /*ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());*/
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código de Factura");
        modelo.addColumn("Nombre de Pasajero");
        modelo.addColumn("Vuelo");
        modelo.addColumn("Asiento");
        modelo.addColumn("Puerta de Embarque");
        modelo.addColumn("Clase");
        modelo.addColumn("Total a Pagar");
    }

}
