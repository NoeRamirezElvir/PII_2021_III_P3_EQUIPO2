package gui.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmEmpleado extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JComboBox cboNombre;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JTextField txtEmail;
    private JTextField txtSueldo;
    private JComboBox cboCargo;
    private JComboBox cboHorario;
    private JLabel lblNombre;
    private JLabel lblId;
    private JLabel lblCodigo;
    private JLabel lblEmail;
    private JLabel lblDepartamento;
    private JComboBox cboDepartamento;
    private JLabel lblSueldo;
    private JTextField txtFechaIngreso;
    private JLabel lblFechaIngreso;
    private JLabel lblCargo;
    private JLabel lblHorario;
    private JComboBox cboEmpleados;
    private JLabel lblEmpleados;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    DefaultTableModel modelo;


    public frmEmpleado() {
        iniciar();
    }
    private void iniciar(){
        setTitle("Registro de Empleados");
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Correo Electrónico");
        modelo.addColumn("Departamento");
        modelo.addColumn("Sueldo");
        modelo.addColumn("Fecha de Ingreso");
        modelo.addColumn("Cargo");
        modelo.addColumn("Horario");

    }
}
