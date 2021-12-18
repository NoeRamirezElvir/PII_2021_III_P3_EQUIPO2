package gui.Empleado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

public class frmEmpleado extends JFrame {
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
    private JButton btnBuscarID;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JButton btnBuscarNombre;
    DefaultTableModel modelo;
    static final String URL = "http://192.168.1.3:8080/api/v1/empleados";
    static final String URL2 = "http://192.168.1.3:8080/api/v1/personas";


    public frmEmpleado() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addEmpleado");
                    Invocation.Builder solicitud = target.request();
                    Empleado empleado = new Empleado();
                    empleado.setCodigo(txtCodigo.getText());
                    empleado.setNombre(String.valueOf(cboNombre.getSelectedItem()));
                    empleado.setCorreoElectronico(txtEmail.getText());
                    empleado.setDepartamento(String.valueOf(cboDepartamento.getSelectedItem()));
                    empleado.setSueldo(Double.parseDouble(txtSueldo.getText()));
                    empleado.setFechaIngreso(txtFechaIngreso.getText());
                    empleado.setCargo(String.valueOf(cboCargo.getSelectedItem()));
                    empleado.setHorario(String.valueOf(cboHorario.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(empleado);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    if(post.getStatus() == 201){
                        JOptionPane.showMessageDialog(null,"Agregado correctamente","GUARDADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else if(post.getStatus() == 500){
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    Empleado empleado = new Empleado();
                    empleado.setId(Long.parseLong(txtID.getText()));
                    empleado.setCodigo(txtCodigo.getText());
                    empleado.setNombre(String.valueOf(cboNombre.getSelectedItem()));
                    empleado.setCorreoElectronico(txtEmail.getText());
                    empleado.setDepartamento(String.valueOf(cboDepartamento.getSelectedItem()));
                    empleado.setSueldo(Double.parseDouble(txtSueldo.getText()));
                    empleado.setFechaIngreso(txtFechaIngreso.getText());
                    empleado.setCargo(String.valueOf(cboCargo.getSelectedItem()));
                    empleado.setHorario(String.valueOf(cboHorario.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(empleado);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    if(put.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Actualizado correctamente","ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/delete/" + txtID.getText());
                    Invocation.Builder solicitud = target.request();
                    Response post = solicitud.delete();
                    if(post.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Eliminado correctamente","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        throw new Exception("> No se encontró el objeto");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtCodigo.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    cboNombre.setSelectedItem(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtEmail.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    cboDepartamento.setSelectedItem(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtSueldo.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    txtFechaIngreso.setText(modelo.getValueAt(filaSeleccionada,6).toString());
                    cboCargo.setSelectedItem(modelo.getValueAt(filaSeleccionada,7).toString());
                    cboHorario.setSelectedItem(modelo.getValueAt(filaSeleccionada,8).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog("¿Cuál es el ID a buscar?");
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Empleado data = new Gson().fromJson(responseJson,Empleado.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigo(),
                                data.getNombre(),
                                data.getCorreoElectronico(),
                                data.getDepartamento(),
                                data.getSueldo(),
                                data.getFechaIngreso(),
                                data.getCargo(),
                                data.getHorario()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el ID");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/nombre/" + cboNombre.getSelectedItem().toString());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Empleado data = new Gson().fromJson(responseJson,Empleado.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigo(),
                                data.getNombre(),
                                data.getCorreoElectronico(),
                                data.getDepartamento(),
                                data.getSueldo(),
                                data.getFechaIngreso(),
                                data.getCargo(),
                                data.getHorario()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el ID");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnLeerCBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object obj = cboEmpleados.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del Empleado: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }

    private void iniciar() {
        setTitle("Registro de Empleados");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //place holders
        TextPrompt titulo = new TextPrompt("yyyy-MM-dd",txtFechaIngreso);
        titulo.changeAlpha(0.75f);
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
        //boton buscar ID
        btnBuscarID.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
        //boton buscar Nombre
        btnBuscarNombre.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
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
        llenarCboNombre();
        leerDatos();
        limpiar();
    }
    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Empleado> data = new Gson().fromJson(responseJson, new TypeToken<List<Empleado>>() {
            }.getType());
            if (get.getStatus() == 200) {
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Empleado empleado : data) {
                    Object[] registro = {
                            empleado.getId(),
                            empleado.getCodigo(),
                            empleado.getNombre(),
                            empleado.getCorreoElectronico(),
                            empleado.getDepartamento(),
                            empleado.getSueldo(),
                            empleado.getFechaIngreso(),
                            empleado.getCargo(),
                            empleado.getHorario()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(empleado.getId(), empleado.getNombre());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboEmpleados.setModel(modeloCombo);
            } else {
                throw new Exception("> No se cargaron los datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            client.close();
        }
    }
    private void llenarCboNombre(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Persona> data = new Gson().fromJson(responseJson,new TypeToken<List<Persona>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Persona persona: data) {
                   if(persona.getTipo().equals("Empleado")){
                        modeloCombo.addElement(persona.getNombre());
                   }
                }
                cboNombre.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de personas.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }
    private void limpiar(){
        txtID.setText("0");
        txtCodigo.setText("");
        txtEmail.setText("");
        txtFechaIngreso.setText("");
        txtSueldo.setText("0");
    }

}
