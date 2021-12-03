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

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoLeerCBOPersonas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenEmpleado2.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenEmpleado1.png"));
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
