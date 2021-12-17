package gui.NuevoUsuario;

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
import java.util.Locale;

public class frmNuevoUsuario extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JTextField txtEmpleado;
    private JPanel jpaBotones;
    private JLabel lblId;
    private JLabel lblContraseña;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JLabel lblImagen;
    private JLabel lblNombre;
    private JTextField txtNombreU;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JTextField txtId;
    private JButton btnBuscarId;
    private JButton btnBuscarNombre;
    private JLabel lblUsuario;
    private JComboBox cboUsuarios;
    private JLabel lblEmpleado;
    private JComboBox cboEmpleado;
    private JLabel lbNombrelUsuario;
    private JTextField txtContra;
    DefaultTableModel modelo = new DefaultTableModel();
    static final String URL = "http://192.168.1.3:8080/api/v1/usuarios";
    static final String URL2 = "http://192.168.1.3:8080/api/v1/empleados";

    public frmNuevoUsuario() {
        iniciar();

        cboEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = cboEmpleado.getSelectedItem();
                String item = ((ItemUsuario)obj).getNombre();
                txtEmpleado.setText(item);
                txtNombreU.setText(formarUsuario(item));
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addUsuario");
                    Invocation.Builder solicitud = target.request();
                    Usuarios usuarios = new Usuarios();
                    usuarios.setEmpleado(txtEmpleado.getText());
                    usuarios.setUsuario(txtNombreU.getText());
                    usuarios.setContraseña(txtContra.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(usuarios);
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
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    Usuarios usuarios = new Usuarios();
                    usuarios.setId(Long.parseLong(txtId.getText()));
                    usuarios.setEmpleado(txtEmpleado.getText());
                    usuarios.setUsuario(txtNombreU.getText());
                    usuarios.setContraseña(txtContra.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(usuarios);
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
                    WebTarget target = client.target(URL+"/delete/" + txtId.getText());
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
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtEmpleado.setText("");
                txtNombreU.setText("");
                txtContra.setText("");
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtEmpleado.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtNombreU.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtContra.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog(null,"Ingrese el ID del Usuario a buscar","Búsqueda",JOptionPane.QUESTION_MESSAGE);
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Usuarios data = new Gson().fromJson(responseJson,Usuarios.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getEmpleado(),
                                data.getUsuario(),
                                data.getContraseña(),
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
                    WebTarget target = client.target(URL+"/nombre/" + txtNombreU.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Usuarios data = new Gson().fromJson(responseJson,Usuarios.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getEmpleado(),
                                data.getUsuario(),
                                data.getContraseña(),
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Usuario");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de usuario");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLogin.png");
        setIconImage(imagen.getImage());
        //Imagen
        lblImagen.setIcon(new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/usuario.png"));
        //-----------------------------------------------------------------------------------
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //boton Buscar Id
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton Buscar Nombre
        btnBuscarNombre.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Empleado");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        leerDatos();
        llenarCBOEmpleado();
    }

    private void llenarCBOEmpleado() {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(URL2 + "");
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
                    //Item item = new Item(empleado.getId(), empleado.getCodigo());
                    ItemUsuario item = new ItemUsuario(empleado.getId(), empleado.getNombre(), empleado.getCodigo());
                    modeloCombo.addElement(item);
                }
                cboEmpleado.setModel(modeloCombo);
            } else {
                throw new Exception("> No se cargaron los datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            client.close();
        }
    }

    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Usuarios> data = new Gson().fromJson(responseJson,new TypeToken<List<Usuarios>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Usuarios usuarios: data) {
                    Object [] registro ={
                            usuarios.getId(),
                            usuarios.getEmpleado(),
                            usuarios.getUsuario(),
                            usuarios.getContraseña(),
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(usuarios.getId(),usuarios.getUsuario());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboUsuarios.setModel(modeloCombo);
            }else{
                throw new Exception("> No se cargaron los datos.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally {
            client.close();
        }
    }
    private String formarUsuario(String nombre){
        String usuario = nombre;
        String[] nuevaCadena = nombre.split("\\s+");
        if (nuevaCadena.length > 2) {
            usuario = String.format("%s.%s", nuevaCadena[0], nuevaCadena[2]);
        }else if (nuevaCadena.length == 2){
            usuario = String.format("%s.%s", nuevaCadena[0], nuevaCadena[1]);
        }else {
            usuario = String.format("%s.2021",nombre);
        }
        return usuario.toLowerCase();
    }
}
